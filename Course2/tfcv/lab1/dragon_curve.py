import matplotlib.pyplot as plt

def dragon_curve(iterations):

    points = [0 + 0j, 1 + 0j]
    for _ in range(iterations):
        new_points = []
        for i in range(len(points) - 1):
            start, end = points[i], points[i + 1]

            mid = (start + end) / 2

            diff = end - start
            rotated = mid + diff * 1j / 2

            new_points.extend([start, rotated])
        new_points.append(points[-1])
        points = new_points

    return points

iterations = 15
points = dragon_curve(iterations)

x = [p.real for p in points]
y = [p.imag for p in points]

plt.figure(figsize=(10, 10))
plt.plot(x, y, color='blue', linewidth=0.8)
plt.title(f'Дракон Хартера-Хейтуэя (итерации = {iterations})')
plt.axis('equal')
plt.grid(True)
plt.show()
