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
r0_earth = np.array([1.496e11, 0])  # Перигелий
v0_earth = np.array([0, 29.78e3])    # Меньше, чем для круговой (29.78e3 м/с)

# Временные параметры
dt = 60 * 60 * 6  # 6 часов
T = 3 * 365.25 * 24 * 3600  # 3 года
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
    a = -G * M_sun * r / distance ** 3
    velocities_earth[i] = v + a * dt
    positions_earth[i] = r + velocities_earth[i] * dt

# --- Теоретический фокальный параметр ---
r0 = positions_earth[0]
v0 = velocities_earth[0]
M = M_earth * np.cross(r0, v0)
p_formula = M**2 / (G * M_sun * M_earth**2)

# --- Экспериментальный фокальный параметр по определению ---
p_exp_direct = None
for i in range(N):
    r_vec = positions_earth[i]
    v_vec = velocities_earth[i]
    dot_product = np.dot(r_vec, v_vec)
    if abs(dot_product) < 1e6:  # почти перпендикулярны (меньше ~1°)
        p_exp_direct = np.linalg.norm(r_vec)
        break

# --- Сравнение ---
print(f"\nТеоретический параметр p (из импульса): {p_formula:.2e} м")
print(f"Экспериментальный параметр p (из r ⟂ v): {p_exp_direct:.2e} м")
print(f"Абсолютная ошибка: {abs(p_exp_direct - p_formula):.2e} м")
print(f"Относительная ошибка: {abs(p_exp_direct - p_formula)/p_formula * 100:.5f} %")

# --- Анимация ---
fig, ax = plt.subplots(figsize=(8, 8))
ax.set_xlim(-2e11, 2e11)
ax.set_ylim(-2e11, 2e11)
ax.set_aspect('equal')
ax.set_xlabel('x (м)')
ax.set_ylabel('y (м)')
ax.set_title('Эллиптическое движение Земли вокруг Солнца')
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
