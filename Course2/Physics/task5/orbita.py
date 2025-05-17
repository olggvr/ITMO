import numpy as np
import matplotlib.pyplot as plt
import matplotlib
from matplotlib.animation import FuncAnimation

matplotlib.use('TkAgg')

# Гравитационная постоянная
G = 6.67430e-11

# Массы
M_sun = 1.989e30
M_earth = 5.972e24

# Начальные условия
r0_earth = np.array([1.496e11, 0])
v0_earth = np.array([0, 29.78e3])

# Временные параметры
dt = 60 * 60 * 6  # 6 часов
T = 3 * 365.25 * 24 * 3600  # 3 года (для ускорения анимации)
N = int(T / dt)

positions_earth = np.zeros((N, 2))
velocities_earth = np.zeros((N, 2))

positions_earth[0] = r0_earth
velocities_earth[0] = v0_earth

# Метод Эйлера
for i in range(1, N):
    r = positions_earth[i - 1]
    v = velocities_earth[i - 1]
    distance = np.linalg.norm(r)
    force = -G * M_sun * r / distance ** 3
    a = force
    velocities_earth[i] = v + a * dt
    positions_earth[i] = r + velocities_earth[i] * dt

# --- Анимация ---
fig, ax = plt.subplots(figsize=(8, 8))
ax.set_xlim(-1.6e11, 1.6e11)
ax.set_ylim(-1.6e11, 1.6e11)
ax.set_aspect('equal')
ax.set_xlabel('x (м)')
ax.set_ylabel('y (м)')
ax.set_title('Анимация движения Земли вокруг Солнца')
ax.grid(True)

sun = plt.plot(0, 0, 'yo', label='Солнце')[0]
earth, = plt.plot([], [], 'bo', label='Земля')
trace, = plt.plot([], [], 'b--', linewidth=0.5)

def init():
    earth.set_data([], [])
    trace.set_data([], [])
    return earth, trace

def update(frame):
    earth.set_data([positions_earth[frame, 0]], [positions_earth[frame, 1]])
    trace.set_data(positions_earth[:frame, 0], positions_earth[:frame, 1])
    return earth, trace


anim = FuncAnimation(fig, update, frames=N, init_func=init, blit=True, interval=1)
plt.legend()
plt.show()