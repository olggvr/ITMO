import bisect
import matplotlib.pyplot as plt
from math import sqrt, pi, exp

N = 100


class TemplateHandler:
    def __init__(self, variant, name) -> None:
        self.template = self.load_tex_template(variant, name)

    def get_template(self):
        return self.template

    def load_tex_template(self, variant, name):
        with open('template.tex', 'r', encoding='utf-8') as f:
            text = f.read()
            text = text.replace("??", str(variant), 1)
            text = text.replace("??", name, 1)
            return text

    def save_template(self, name):
        with open(name + '.tex', 'w', encoding='utf-8') as f:
            f.write(self.template)
        print("༼ つ ◕_◕ ༽つ Saved result in `" + name + ".tex`")

    def end_text(self):
        self.template = self.template.replace("??", "")

    def add_text(self, text, newline=False):
        if newline:
            postfix = "\n\n??"
        else:
            postfix = "\n??"
        self.template = self.template.replace("??", text + postfix, 1)

    def add_picture(self, name):
        text = "\\centerline{\\includegraphics[scale=0.7]{" + name + ".png}}"
        self.add_text(text, True)

    def generate_table(self, top_row, bottom_row, values):
        table_text = "\\begin{center}\n\\begin{tabular}{ |"
        table_text += "c|" * len(top_row) + " }"
        table_text += "\n\\hline\n"
        table_text += " & ".join(top_row) + " \\\\\n"

        for val in values:
            table_text += " & ".join(val)
            table_text += " \\\\\n"

        if bottom_row:
            table_text += " & ".join(bottom_row) + " \\\\\n"
        table_text += "\\hline\n"
        table_text += "\\end{tabular}\n\\end{center}"

        self.add_text(table_text, True)

    def generate_first_table(self, intervals, middles, freqs, relative_freqs, F, mf, m2f):
        top_row = [
            "Интервал",
            "Середина интервала $x_i$",
            "Частота $f_i$",
            "$w_i=\\frac{f_i}{n}$",
            "$w_i^{HAK}$",
            "$x_if_i$",
            "$x_i^2f_i$"]
        tuple_intervals = zip(intervals[:-1], intervals[1:])
        rows = zip(tuple_intervals, middles, freqs, relative_freqs, F, mf, m2f)
        values = []
        for row in rows:
            values.append([])
            for el in row:
                if type(el) == tuple:
                    values[-1].append(f"{el[0]} - {el[1]}")
                else:
                    values[-1].append(str(el))
        bottom_row = ["\\quad", "$\\sum$", "$n=100$", "1", "\\quad", str(sum(mf)), str(sum(m2f))]
        self.generate_table(top_row, bottom_row, values)

    def generate_second_table(self, middles, t, f, ft):
        top_row = ["$x_i$", "$t_i$", "$f(t_i)$", "$f_i^T$"]
        rows = list(map(list, zip(middles, t, f, ft)))

        values = [list(map(lambda x: str(round(x, 2)), row)) for row in rows]
        self.generate_table(top_row, None, values)

    def generate_third_table(self, f, fT, crit):
        top_row = ["$f_i$", "$f_i^T$", "$\\chi^2$"]
        rows = list(map(list, zip(f, fT, crit)))

        values = [list(map(lambda x: str(round(x, 2)), row)) for row in rows]
        self.generate_table(top_row, None, values)


def print_table(values):
    for i in range(0, len(values), 10):
        for j in range(10):
            try:
                print(values[i + j], end=' ')
            except BaseException:
                break
        print()
    print()


def make_table(string):
    correct_string = string.replace(',', '.')
    values = list(map(float, correct_string.split()))
    assert len(values) == N, f"must be {N} vallues, check the table."

    print("Given table:")
    print_table(values)

    return sorted(values)


def find_ni(values, l, r):
    lower_bound = bisect.bisect_right(values, l) if l > values[0] else 0
    upper_bound = bisect.bisect_right(values, r, lower_bound)
    return upper_bound - lower_bound


def draw_graph(x, y, name, save=False, saveName=''):
    plt.title(name)
    plt.xticks(x)
    plt.yticks(y)
    plt.plot(x, y, marker='o')
    if save:
        plt.savefig(saveName + '.png')
        plt.clf()
    else:
        plt.show()


def draw_histogram(data, intervals, r_freqs, name, save=False, saveName=''):
    plt.title(name)
    plt.xticks(intervals)
    plt.yticks(r_freqs)
    plt.hist(data, bins=intervals, weights=r_freqs)
    if save:
        plt.savefig(saveName + '.png')
        plt.clf()
    else:
        plt.show()


def process(values, th, save=False):
    w = max(values) - min(values)
    h = round(w / (10 - 1), 2)
    intervals = [round(min(values) + i * h, 2) for i in range(10)]
    middles = [(x1 + x2) / 2 for x1, x2 in zip(intervals[:-1], intervals[1:])]
    freqs = [find_ni(values, x1, x2) for x1, x2 in zip(intervals[:-1], intervals[1:])]
    relative_freqs = [f / N for f in freqs]
    m = [sum(freqs[:i + 1]) for i in range(len(freqs))]
    F = [mx / N for mx in m]
    mf = [m * f for m, f in zip(middles, freqs)]
    m2f = [m * m * f for m, f in zip(middles, freqs)]
    x_overline = round(sum(mf) / N, 2)
    D_chosen = round(sum(m2f) / N - x_overline ** 2, 2)
    sigma_chosen = round(sqrt(D_chosen), 2)
    t = [(m - x_overline) / sigma_chosen for m in middles]
    f = [1 / sqrt(2 * pi) * exp(-(ti ** 2 / 2)) for ti in t]
    fT = [round(h * N / sigma_chosen * fi) for fi in f]
    crit = [(fr - fTi) ** 2 / fTi for fr, fTi in zip(freqs, fT)]
    ty = round(2.31 * sigma_chosen / 10, 2)
    dk1 = sqrt(2 * N) / (sqrt(2 * N - 3) + 2.31) * sigma_chosen
    dk2 = sqrt(2 * N) / (sqrt(2 * N - 3) - 2.31) * sigma_chosen

    draw_graph(middles, freqs, name='Полигон частот', save=save, saveName="1")
    draw_histogram(middles, intervals, [fi / N for fi in freqs], name='Гистограмма относительных частот', save=save, saveName="2")
    draw_graph(intervals, [0] + F, name='Эмпирическая функция распределения', save=save, saveName="3")

    th.add_text("а-б)")
    th.generate_first_table(intervals, middles, freqs, relative_freqs, F, mf, m2f)
    th.add_text("в)", True)
    th.add_picture("1")
    th.add_picture("2")
    th.add_picture("3")
    th.add_text("г)", True)
    th.add_text("Вычислим среднюю арифметическую:")
    th.add_text(f"\\[\\overline{{x}}=\\frac{{\\sum x_i f_i}}{{n}}={x_overline}\\]")
    th.add_text(f"\\[D=\\frac{{\\sum x_i^2 f_i}}{{n}} - \\overline{{x}}={D_chosen}\\]", True)
    th.add_text("д)", True)
    th.add_text("Найдем значение теоретических частот $f_i^T$ используя формулу:")
    th.add_text("$f_i^T=\\frac{hn}{\\sigma}f(t)$", True)
    th.add_text("где $f(t)=\\frac{1}{\\sqrt{2\\pi}}e^{-\\frac{t^2}{2}}, t=\\frac{x_i-\\overline{x}}{\\sigma}$")
    th.add_text(f"$\\sigma=\\sqrt{{D}}={sigma_chosen}$", True)
    th.add_text("Расчетная таблица")
    th.generate_second_table(middles, t, f, fT)
    th.add_text("Расчетное значение критерия вычислим по формуле:")
    th.add_text("$\\chi^2=\\sum_{i=1}^l\\frac{(f_i-f^T_i)^2}{f_i^T}$.", True)
    th.add_text("где $l$ - количество интервалов\\\\", True)
    th.add_text("Расчетная таблица")
    th.generate_third_table(f, fT, crit)
    th.add_text(f"Таким образом $\\chi^2 = {round(sum(crit), 2)}$.", True)
    th.add_text("Теоретическое значение критерия возьмем из таблицы. Оно равно $14.4$.")
    th.add_text(f"Таким образом $\\chi ^ 2 = {round(sum(crit), 2)} < 14.4$, то есть на уровне значимости")
    th.add_text("$\\alpha=0.025$ принимаем нулевую гипотезу о том, что генеральная совокупность,")
    th.add_text("из которой извлечена выборка, имеет нормальное распределение.", True)
    th.add_text("е)", True)
    th.add_text("Доверительный интервал истинного значения генеральной средней вычислим по формуле")
    th.add_text("\\[\\overline{x}-\\frac{t_{\\gamma}\\sigma}{\\sqrt{n}} < \\tilde{x} <")
    th.add_text("\\overline{x} + \\frac{t_{\\gamma}\\sigma}{\\sqrt{n}}\\]")
    th.add_text("Выберем уровень доверительной вероятности $\\gamma = 0.05$.")
    th.add_text("По таблице распределения Стъюдента находим $t_{\\gamma}=2.31$.\\\\", True)
    th.add_text("Вычислим точность оценки:")
    th.add_text("\\[\\frac{t_{\\gamma}\\sigma}{\\sqrt{n}} \\approx " + str(ty) + "\\]", True)
    th.add_text("Таким образом")
    th.add_text(f"\\[{round(x_overline - ty, 2)} < \\tilde{{x}} < {round(x_overline + ty, 2)}\\]", True)
    th.add_text("с вероятностью $95\\%$ данный интервал накроет истинное значение $\\tilde{x}$")
    th.add_text("генеральной средней.\\\\", True)
    th.add_text("Доверительный интервал для генерального среднего квадратического отклонения")
    th.add_text("определяется по формуле")
    th.add_text("\\[\\frac{\\sqrt{2n}}{\\sqrt{2n}-3+t_{\\gamma}}\\sigma<\\tilde{\\sigma}<")
    th.add_text("\\frac{\\sqrt{2n}}{\\sqrt{2n}-3-t_{\\gamma}}\\sigma\\]", True)
    th.add_text("Таким образом")
    th.add_text(f"\\[{round(dk1, 2)} < \\tilde{{\\sigma}} < {round(dk2, 2)}\\]", True)
    th.add_text("с вероятностью $95\\%$ данный интервал накроет истинное значение $\\tilde{\\sigma}$")
    th.add_text("генерального среднего квадратического отклонения.")
    th.end_text()
    th.save_template("result")


if __name__ == "__main__":
    # starting values
    values = """
    189 207 213 208 186 210 198 219 231 227
    202 211 220 236 227 220 210 183 213 190
    197 227 187 226 213 191 209 196 202 235
    211 214 220 195 182 228 202 207 192 226
    193 203 232 202 215 195 220 233 214 185
    234 215 196 220 203 236 225 221 193 215
    204 184 217 193 216 205 197 203 229 204
    225 216 233 223 208 204 207 182 216 191
    210 190 207 205 232 222 198 217 211 201
    185 217 225 201 208 211 189 205 207 199
    """
    
    variant = "3"  # your variant
    name = "Гаврилин Олег"  # your name

    values = make_table(values)
    print_table(values)
    template_handler = TemplateHandler(variant, name)
    process(values, template_handler, True)
