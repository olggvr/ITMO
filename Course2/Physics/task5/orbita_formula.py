import numpy as np
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt

# --- Константы ---
G = 6.67430e-11                  # гравитационная постоянная, м^3 / (кг * с^2)
M_sun = 1.989e30                 # масса Солнца, кг
M_earth = 5.972e24               # масса Земли, кг

# --- Начальные условия ---
r0_earth = np.array([1.496e11, 0])     # начальная позиция Земли: 1 а.е. по оси X
v0_earth = np.array([0, 29.78e3])      # начальная скорость: направлена вдоль оси Y, м/с

# --- Временные параметры ---
dt = 60 * 60 * 6                        # шаг интегрирования: 6 часов в секундах
T = 3 * 365.25 * 24 * 3600             # общее время моделирования: 3 года
N = int(T / dt)                        # общее число шагов

# --- Инициализация массивов положений и скоростей ---
positions_earth = np.zeros((N, 2))
velocities_earth = np.zeros((N, 2))
positions_earth[0] = r0_earth
velocities_earth[0] = v0_earth

# --- Метод Эйлера: моделирование движения по орбите ---
for i in range(1, N):
    r = positions_earth[i - 1]                        # текущая позиция
    v = velocities_earth[i - 1]                       # текущая скорость
    distance = np.linalg.norm(r)                     # расстояние до Солнца
    a = -G * M_sun * r / distance**3                 # ускорение по закону всемирного тяготения
    velocities_earth[i] = v + a * dt                 # новая скорость
    positions_earth[i] = r + velocities_earth[i] * dt  # новая позиция

# --- Анализ на кадре №1000 ---
frame_energy = 1000
r = positions_earth[frame_energy]                    # радиус-вектор на выбранном кадре
v = velocities_earth[frame_energy]                   # скорость на этом кадре
r_norm = np.linalg.norm(r)
v_norm = np.linalg.norm(v)

# --- Угловой момент M (только компонент z, плоская система) ---
M = M_earth * np.cross(np.append(r, 0), np.append(v, 0))[2]  # r x v и берём z-компоненту

# формула M^2/2m возможна только в безразмерной системе или с нормированными переменными.
# В реальных физических единицах она не даёт длину


# --- Параметр орбиты p = M^2 / (G * M_sun * m^2) ---
# Эта формула даёт реальный геометрический параметр эллипса и имеет размерность длины
p_orbit = M**2 / (G * M_sun * M_earth**2)

# --- Вывод пошагового доказательства формулы ---
print("\n--- Доказательство формулы параметра орбиты ---")
print("Формула: p = M² / (G * M_sun * m²)")
print(f"M² = {M**2:.5e} кг²·м⁴/с²  ← квадрат углового момента")
print(f"m² = {M_earth**2:.5e} кг²")
print(f"G × M_sun × m² = {G * M_sun * M_earth**2:.5e} (в знаменателе формулы)")
print(f"p = {p_orbit:.5e} м ← параметр орбиты по формуле")
print(f"\nФактическое расстояние r = {r_norm:.5e} м")
diff = abs(r_norm - p_orbit)
rel_error = diff / r_norm * 100
print(f"Разность |r - p| = {diff:.5e} м")
print(f"Относительная ошибка: {rel_error:.3f} %")
if rel_error < 1:
    print("✅ Формула подтверждается: орбита почти круговая.")
else:
    print("⚠️ Существенное отклонение: орбита эллиптическая.")

# --- Построение графика ---
fig, ax = plt.subplots(figsize=(8, 8))
ax.set_xlim(-1.6e11, 1.6e11)
ax.set_ylim(-1.6e11, 1.6e11)
ax.set_aspect('equal')
ax.set_xlabel("x (м)")
ax.set_ylabel("y (м)")
ax.set_title("Анимация орбиты Земли и параметра орбиты p")
ax.grid(True)

# --- Объекты на графике ---
sun, = ax.plot(0, 0, 'yo', label='Солнце', markersize=10)
earth_dot, = ax.plot([], [], 'bo', label='Земля')
trail, = ax.plot([], [], 'b--', linewidth=0.5)

# --- Построение отрезка параметра p от фокуса вдоль направления скорости ---
unit_v = v / np.linalg.norm(v)                        # единичный вектор скорости
focus_to_p = unit_v * p_orbit                         # вектор длиной p вдоль скорости
ax.plot([0, focus_to_p[0]], [0, focus_to_p[1]], 'r-', label='Параметр p')
ax.text(focus_to_p[0] * 1.02, focus_to_p[1] * 1.02, f"p = {p_orbit:.2e} м", color='red')

plt.legend()

# --- Анимация орбиты Земли ---
step = 10
for i in range(0, N, step):
    earth_dot.set_data([positions_earth[i, 0]], [positions_earth[i, 1]])  # позиция Земли
    trail.set_data(positions_earth[:i + 1, 0], positions_earth[:i + 1, 1])  # след
    plt.pause(0.01)  # пауза между кадрами

plt.show()
