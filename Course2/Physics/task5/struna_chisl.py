import numpy as np
import matplotlib.animation as animation
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt
from scipy.integrate import quad

LENGTH = 1.0
STIFFNESS = 0.07
DENSITY = 0.06
ALPHA = 0.49
BETA = 0.51
C = 2.3
A = np.sqrt(STIFFNESS / DENSITY)

def f(x):
    return np.exp(-x) * np.sin(np.pi * x)

def g(x):
    return np.where((x >= ALPHA) & (x <= BETA), C, 0)

#Функции для расчёта параметров гармоник
def w_n(n):
    return (np.pi * n / LENGTH) * A#собственная частота

def a_n(n):
    integrand = lambda x: f(x) * np.sin(np.pi * n * x / LENGTH)
    result, _ = quad(integrand, 0, LENGTH)
    return (2 / LENGTH) * result#коэффицент разложение начального отклонения f(x)

def b_n(n):
    integrand = lambda x: g(x) * np.sin(np.pi * n * x / LENGTH)
    result, _ = quad(integrand, 0, LENGTH, points=[ALPHA, BETA])
    return (2 / (np.pi * n * A)) * result#Это коэффициент разложения начальной скорости g(x)

def initial_displacement(position):
    """
    Функция начального смещения f(x):
    Показывает, как струна была изогнута в начальный момент времени.
    """
    return np.sqrt(position * (LENGTH - position))

def initial_velocity(position):
    """
    Функция начальной скорости g(x):
    Показывает, где струне дали начальный толчок (скорость).
    """
    return np.where(
        (position >= ALPHA) & (position <= BETA),
        C,  # на отрезке толкаем струну со скоростью INITIAL_SPEED
        0.0  # в других местах скорость = 0
    )


#f(x) и g(x)
x = np.linspace(0, LENGTH, 500)
f_vals = f(x)
g_vals = g(x)

fig_fg, (ax1, ax2) = plt.subplots(2, 1, figsize=(10, 8))
ax1.plot(x, f_vals, color = 'black', label='$f(x)$')
ax1.set_title('Начальное смещение $f(x)$')
ax1.set_xlabel('x')
ax1.set_ylabel('f(x)')
ax1.grid(True)

ax2.plot(x, g_vals, color='black', label='$g(x)$')
ax2.set_title('Начальная скорость $g(x)$')
ax2.set_xlabel('x')
ax2.set_ylabel('g(x)')
ax2.grid(True)

plt.tight_layout()
plt.show()

#Предрасчёт значений для n = 1..5
n_vals = np.arange(1, 6)
omega_vals = np.array([w_n(n) for n in n_vals])
a_vals = np.array([a_n(n) for n in n_vals])
b_vals = np.array([b_n(n) for n in n_vals])
A_vals = np.sqrt(a_vals ** 2 + b_vals ** 2)

#Печать таблицы значений
print(f"{'n':^5} | {'ω_n':^15} | {'a_n':^15} | {'b_n':^15} | {'A_n':^15}")
print("-" * 80)
for i, n in enumerate(n_vals): print(f"{n:^5} | {omega_vals[i]:^15.4f} | {a_vals[i]:^15.4f} | {b_vals[i]:^15.4f} | {A_vals[i]:^15.4f}")

#Анимация движения струны
t = np.linspace(0, 2 * np.pi / omega_vals[0], 100)  # период по первой гармонике

fig, axs = plt.subplots(2, 3, figsize=(18, 8))
fig.suptitle('Движение струны для разных гармоник', fontsize=16)

# Подготовка графиков 1–5: по одной гармонике
lines = []
for idx in range(5):
    row, col = divmod(idx, 3)
    axs[row, col].set_title(f'Гармоника {idx + 1}')
    axs[row, col].set_xlim(0, LENGTH)

    #axs[row, col].set_ylim(-1, 1)
    amp = A_vals[idx]
    delta = 0.1 * amp if amp != 0 else 0.1  # немного запас по амплитуде
    axs[row, col].set_ylim(-amp - delta, amp + delta)

    axs[row, col].set_xlabel('x')
    axs[row, col].set_ylabel('u(x, t)')
    line, = axs[row, col].plot([], [], 'b')
    lines.append(line)

# 6-й график: все гармоники вместе
axs[1, 2].set_title('Все гармоники')
axs[1, 2].set_xlim(0, LENGTH)
axs[1, 2].set_ylim(-1, 1)
axs[1, 2].set_xlabel('x')
axs[1, 2].set_ylabel('u(x, t)')
colors = ['r', 'g', 'b', 'm', 'orange']
multi_lines = [axs[1, 2].plot([], [], color)[0] for color in colors]

#Инициализация
def init():
    for line in lines + multi_lines:
        line.set_data([], [])
    return lines + multi_lines

#Анимация
'''def animate(i):
    for idx, n in enumerate(n_vals):
        u_xt = A_vals[idx] * np.sin(omega_vals[idx] * t[i]) * np.sin(np.pi * n * x / LENGTH)
        lines[idx].set_data(x, u_xt)
        multi_lines[idx].set_data(x, u_xt)
    return lines + multi_line'''

# Анимация
def animate(i):
    total_u = np.zeros_like(x)  # Инициализация суммы гармоник

    for idx, n in enumerate(n_vals):
        u_xt = A_vals[idx] * np.sin(omega_vals[idx] * t[i]) * np.sin(np.pi * n * x / LENGTH)
        lines[idx].set_data(x, u_xt)
        multi_lines[idx].set_data(x, u_xt)  # Обновление графиков отдельных гармоник
        total_u += u_xt  # Суммируем гармоники

    return lines + multi_lines


ani = animation.FuncAnimation(fig, animate, frames=len(t), init_func=init, interval=50, blit=False)

plt.tight_layout()
plt.show()

# --- Численное решение методом конечных разностей ---

# Параметры численного расчёта
Nx = 90  # Количество точек по X
Nt = 1800  # Количество шагов по времени
Tmax = 10.0  # Максимальное время анимации

dx = LENGTH / (Nx - 1)
dt = Tmax / Nt
r = (A * dt / dx) ** 2

print(f"\nЧисленное решение:\ndx = {dx:.5f}, dt = {dt:.5f}, r = {r:.5f}")

if r > 1:
    raise ValueError("Неустойчивая схема: уменьшите dt или увеличьте dx.")

# Сетка и массивы
x_num = np.linspace(0, LENGTH, Nx)
u_num = np.zeros((Nx, Nt))

# Начальные условия
u_num[:, 0] = initial_displacement(x_num)
u_num[:, 1] = u_num[:, 0] + dt * initial_velocity(x_num)  # с учётом начальной скорости

# Расчёт по схеме конечных разностей
for j in range(1, Nt - 1):
    for i in range(1, Nx - 1):
        u_num[i, j + 1] = (
                2 * (1 - r) * u_num[i, j]
                + r * (u_num[i + 1, j] + u_num[i - 1, j])
                - u_num[i, j - 1]
        )

print("Численное решение рассчитано.\n")

fig_num, ax_num = plt.subplots(figsize=(10, 6))
line_num, = ax_num.plot(x_num, u_num[:, 0], 'b-', label='Численное решение')
ax_num.set_xlim(0, LENGTH)
ax_num.set_ylim(-1.0, 1.0)
ax_num.set_xlabel('x')
ax_num.set_ylabel('u(x, t)')
ax_num.set_title('Численное решение волнового уравнения')
ax_num.grid(True)
ax_num.legend()


def update_num(frame):
    line_num.set_ydata(u_num[:, frame])
    return line_num,


ani_num = animation.FuncAnimation(
    fig_num,
    update_num,
    frames=Nt,
    interval=30,
    blit=True
)

plt.show()
