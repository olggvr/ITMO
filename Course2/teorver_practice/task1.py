import matplotlib.pyplot as plt
import numpy as np

# Исходные данные: выборка из 20 чисел
data = [-0.03, 0.73, -0.59, -1.59, 0.38, 1.49, 0.14, -0.62, -1.59, 1.45, -0.38, -1.49, -0.15, 0.63,
        0.06, -1.59, 0.61, 0.62, -0.05, 1.56]

# 1. Вариационный ряд
data_sorted = sorted(data)

# 2. Экстремальные значения
min_val = data_sorted[0]
max_val = data_sorted[-1]

# 3. Размах
range_val = max_val - min_val

# 4. Оценка математического ожидания (среднего)
mean = sum(data_sorted) / len(data_sorted)

# 5. Среднеквадратическое отклонение
variance = sum((x - mean) ** 2 for x in data_sorted) / len(data_sorted)
std_dev = variance ** 0.5

# 6. Построение статистического ряда (распределение частот)
unique_values = sorted(set(data_sorted))
frequencies = [data_sorted.count(x) for x in unique_values]

# Статистический ряд
statistical_series = list(zip(unique_values, frequencies))

# 7. Эмпирическая функция распределения
def empirical_distribution(x, data_sorted):
    return sum(1 for value in data_sorted if value <= x) / len(data_sorted)

# Эмпирическая функция для всех значений выборки
empirical_values = [empirical_distribution(x, data_sorted) for x in data_sorted]

# 8. Построение графиков

# Гистограмма группированной выборки
intervals = np.linspace(min_val, max_val, 6)  # 5 равных интервалов
histogram_data = [0] * (len(intervals) - 1)

for x in data_sorted:
    for i in range(1, len(intervals)):
        if intervals[i - 1] <= x < intervals[i]:
            histogram_data[i - 1] += 1
            break
# Нормируем частоты
relative_frequencies = [freq / len(data_sorted) for freq in histogram_data]

# Полигон приведенных частот
polygon_x = [(intervals[i] + intervals[i + 1]) / 2 for i in range(len(intervals) - 1)]
polygon_y = relative_frequencies

# Построение графиков
plt.figure(figsize=(12, 6))

# Эмпирическая функция распределения
plt.subplot(1, 2, 1)
plt.step(data_sorted, empirical_values, where='post', label='Эмпирическая функция распределения')
plt.xlabel('Значение выборки')
plt.ylabel('F(x)')
plt.title('Эмпирическая функция распределения')
plt.grid()
plt.legend()

# Гистограмма и полигон
plt.subplot(1, 2, 2)
plt.bar(intervals[:-1], relative_frequencies, width=np.diff(intervals), align='edge', alpha=0.7, label='Гистограмма')
plt.plot(polygon_x, polygon_y, marker='o', color='red', label='Полигон частот')
plt.xlabel('Интервалы')
plt.ylabel('Относительная частота')
plt.title('Гистограмма и полигон частот')
plt.grid()
plt.legend()

plt.tight_layout()
plt.show()

# Вывод характеристик
print("Вариационный ряд:", data_sorted)
print("Статистический ряд (значение, частота):", statistical_series)
print("Экстремальные значения: min =", min_val, ", max =", max_val)
print("Размах:", range_val)
print("Математическое ожидание:", mean)
print("Среднеквадратическое отклонение:", std_dev)
