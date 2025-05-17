import numpy as np
import matplotlib.pyplot as plt

width, height = 800, 800
x_min, x_max = -0.8, -0.6
y_min, y_max = -0.04, 0.04
max_iter = 500

def mandelbrot(c, max_iter):

    z = 0
    for n in range(max_iter):
        z = z * z + c
        if abs(z) > 2:
            return n
    return max_iter

x, y = np.linspace(x_min, x_max, width), np.linspace(y_min, y_max, height)
X, Y = np.meshgrid(x, y)
C = X + 1j * Y

Z = np.zeros(C.shape, dtype=int)
for i in range(height):
    for j in range(width):
        Z[i, j] = mandelbrot(C[i, j], max_iter)


plt.figure(figsize=(10, 10))
plt.imshow(Z, extent=[x_min, x_max, y_min, y_max], cmap='hot', origin='lower')
plt.colorbar(label='Количество итераций до расходимости')
plt.title("Множество Мандельброта")
plt.xlabel("Re(c)")
plt.ylabel("Im(c)")
plt.show()
