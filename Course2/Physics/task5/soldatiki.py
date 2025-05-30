from matplotlib.patches import Circle
import numpy as np
import matplotlib.animation as animation
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt

# Параметры системы
L = 10.0  # Размер внешнего квадрата
inner_L = 5.0  # Размер внутреннего вырезанного квадрата
N = 20  # Количество шаров по горизонтали
M = 20  # Количество шаров по вертикали

fig, ax = plt.subplots(figsize=(5, 5))
ax.set_xlim(-L, 2 * L)
ax.set_ylim(-L, 2 * L)
ax.set_aspect('equal')
ax.grid(True)

inner_x_start = (L - inner_L) / 2
inner_x_end = inner_x_start + inner_L
inner_y_start = (L - inner_L) / 2
inner_y_end = inner_y_start + inner_L

balls = []

# Направление движения (45 градусов вправо-вверх)
theta = np.pi / 4
direction = np.array([np.cos(theta), np.sin(theta)])

for i in range(N):
    for j in range(M):
        x = L * i / (N - 1)
        y = L * j / (M - 1)

        # Проверяем, не попадает ли шар во внутренний квадрат
        if not (inner_x_start < x < inner_x_end and inner_y_start < y < inner_y_end):
            # Каждый шар — своя скорость! (например, как раньше)
            speed = 0.01 * (i + j + 1)  # можно выбрать свою формулу, например, зависит от индекса
            velocity = speed * direction
            balls.append({
                'position': np.array([x, y], dtype=np.float64),
                'velocity': velocity
            })

inner_square = plt.Rectangle((inner_x_start, inner_y_start), inner_L, inner_L,
                             facecolor='white', edgecolor='black')
ax.add_patch(inner_square)

circles = []
for ball in balls:
    circle = Circle(ball['position'], radius=0.08, color='blue')
    ax.add_patch(circle)
    circles.append(circle)

def update(frame):
    for ball, circle in zip(balls, circles):
        ball['position'] += ball['velocity']
        circle.center = ball['position']
    return circles

ani = animation.FuncAnimation(fig, update, frames=200, interval=50, blit=True)

plt.title("Линейное движение шаров")
plt.tight_layout()
plt.show()
