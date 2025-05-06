import numpy as np
from matplotlib.animation import FuncAnimation
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt

# Гравитационная постоянная и параметры
G = 6.67430e-11  # грав. пост.
M_sun = 1.9885e30  # масса Солнца
mu = G * M_sun
AU = 1.495978707e11  # астрономическая единица

# Уран
a = 19.19126393 * AU
e_real = 0.04716771
rp = a * (1 - e_real)

# Скорость в перигелии
vp = np.sqrt(mu * (1 + e_real) / (a * (1 - e_real)))

# Начальные условия
pos = np.array([rp, 0.0])
vel = np.array([0.0, vp])

# Шаги симуляции
hour_step = 6
dt = hour_step * 3600
sim_speed = 5000
first_pass = True
prev_r = np.linalg.norm(pos)
period_est = 0.0

# Для отрисовки
fig, ax = plt.subplots(figsize=(6, 6))
(traj,) = ax.plot([], [], lw=1)
(pt,) = ax.plot([], [], "o", markersize=4)
(sun,) = ax.plot(0, 0, "o", ms=10)

ax.set_aspect("equal")
ax.set_xlim(-22, 22)
ax.set_ylim(-22, 22)
ax.set_xlabel("x (au)")
ax.set_ylabel("y (au)")
text_time = ax.text(0.02, 0.95, "", transform=ax.transAxes)
text_period = ax.text(0.02, 0.90, "", transform=ax.transAxes)

xs, ys = [], []

def update(frame):
    global pos, vel, prev_r, period_est, first_pass

    for _ in range(sim_speed):
        r_vec = pos
        v_vec = vel
        r = np.linalg.norm(r_vec)
        v = np.linalg.norm(v_vec)
        acc = -mu / r**3 * r_vec

        # Интеграция методом Эйлера
        pos_n = pos + vel * dt
        r_n = np.linalg.norm(pos_n)
        acc_n = -mu / r_n**3 * pos_n
        vel += acc_n * dt
        pos = pos_n

        # Расчёт энергии и момента
        E = 0.5 * v**2 - mu / r
        L = np.linalg.norm(np.cross(r_vec, v_vec))
        try:
            epsilon = np.sqrt(1 + (2 * E * L**2) / mu**2)
        except RuntimeWarning:
            epsilon = float('nan')

        # Оценка периода
        if prev_r > r and r_n > r:
            if first_pass:
                first_pass = False
                period_est = 0.0
            else:
                print(f"epsilon = {epsilon:.8f}, e_real = {e_real}")
                print(f"Период из симуляции: {period_est / (365.25 * 24 * 3600):.3f} лет")
            period_est = 0.0
        prev_r = r
        period_est += dt

    xs.append(pos[0] / AU)
    ys.append(pos[1] / AU)
    traj.set_data(xs, ys)
    pt.set_data([pos[0] / AU], [pos[1] / AU])

    sim_days = len(xs) * dt * sim_speed / 86400
    text_time.set_text(f"t = {sim_days / 365.25:6.2f} yr")
    if not first_pass:
        text_period.set_text(f"period = {period_est / 365.25 / 24 / 3600:6.2f} yr")
    return traj, pt, text_time, text_period

# Запуск анимации
anim = FuncAnimation(fig, update, frames=20000000, interval=20, blit=False)
plt.show()
