import numpy as np
import matplotlib.pyplot as plt

width, height = 800, 800
x_min, x_max = -0.1, 0.1
y_min, y_max = -0.1, 0.1
max_iter = 500
c = complex(-0.5251993, 0.5251993)

x = np.linspace(x_min, x_max, width)
y = np.linspace(y_min, y_max, height)
X, Y = np.meshgrid(x, y)
Z = X + 1j * Y

iterations = np.zeros(Z.shape, dtype=int)

mask = np.full(Z.shape, True, dtype=bool)
for i in range(max_iter):
    Z[mask] = Z[mask] ** 2 + c
    mask[np.abs(Z) > 2] = False
    iterations[mask] = i

plt.figure(figsize=(8, 8))
plt.imshow(iterations, extent=(x_min, x_max, y_min, y_max), cmap="magma")
plt.colorbar(label="Iterations to escape")
plt.title("Julia Set")
plt.xlabel("Re(z)")
plt.ylabel("Im(z)")
plt.show()
