import numpy as np
import matplotlib.pyplot as plt

def f(x):
    return (x**4 * (1 - x)**4) / (1 + x**2)

N = 10000
x_random = np.random.uniform(0, 1, N)
y_random = np.random.uniform(0, f(0.5), N)
y_values = f(x_random)
under_curve = y_random <= y_values
integral = np.sum(under_curve) / N * (1 * f(0.5))
x = np.linspace(0, 1, 1000)
y = f(x)

print(round(integral, 4))
plt.figure(figsize=(8, 6))
plt.plot(x, y, 'r-', label='f(x)')
plt.fill_between(x, y, alpha=0.2, color='red')
plt.scatter(x_random[under_curve], y_random[under_curve], color='blue', s=1, label='Под кривой')
plt.scatter(x_random[~under_curve], y_random[~under_curve], color='gray', s=1, label='Над кривой', alpha=0.3)
plt.legend()
plt.xlabel('x')
plt.ylabel('f(x)')
plt.title(f'Метод Монте-Карло: оценка интеграла ≈ {integral:.5f}')
plt.show()
