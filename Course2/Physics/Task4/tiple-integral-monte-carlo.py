import numpy as np
import matplotlib.pyplot as plt

N = 1000000
x = np.random.uniform(0, 1, N)
y = np.random.uniform(0, 1, N)
z = np.random.uniform(0, 1, N)
inside = (x <= 1 - y - z) & (y <= 1 - z)
volume = np.sum(inside) / N
print(volume)

x_inside, y_inside, z_inside = x[inside], y[inside], z[inside]

fig = plt.figure(figsize=(8, 8))
ax = fig.add_subplot(111, projection='3d')

ax.scatter(x_inside, y_inside, z_inside, c='blue', alpha=0.5, s=1)

ax.set_xlabel("X")
ax.set_ylabel("Y")
ax.set_zlabel("Z")
ax.set_title("точки")
plt.show()
