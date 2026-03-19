import matplotlib.pyplot as plt
import pandas as pd

# Данные из таблицы
data = {
    "Rk": [float('inf'), 5400, 2400, 1400, 900, 600, 400, 257, 150, 67, 0],
    "Uk": [36, 32.4, 28.8, 25.2, 21.6, 18, 14.4, 10.795, 7.2, 3.6162, 0],
    "Ik": [0, 6, 12, 18, 24, 30, 36, 42, 48, 54, 60],
    "Pk": [0, 0.1944, 0.3456, 0.4536, 0.5184, 0.54, 0.5184, 0.4536, 0.3456, 0.1944, 0],
    "eta": [1, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0]
}

df = pd.DataFrame(data)

# Построение графиков
fig, axs = plt.subplots(2, 1, figsize=(8, 10))

# --- 1. Внешняя характеристика источника (Uk(Ik)) ---
axs[0].plot(df["Ik"], df["Uk"], marker="o", color="blue", linestyle="-")
for x, y in zip(df["Ik"], df["Uk"]):
    axs[0].text(x+16, y, f"{x:.0f};{y:.0f}", fontsize=12, ha='right', va='bottom')

axs[0].set_title("Внешняя характеристика источника")
axs[0].set_xlabel("I, мА")
axs[0].set_ylabel("U, В")
axs[0].grid(True)

# --- 2. Рабочие характеристики источника (P(I), η(I)) ---
axs[1].plot(df["Ik"], df["Pk"], marker="o", color="blue", label="P(I)")
axs[1].plot(df["Ik"], [e * 0.45 for e in df["eta"]], marker="s", color="orange", label="η*0.45")

axs[1].set_title("Рабочие характеристики источника")
axs[1].set_xlabel("I, мА")
axs[1].set_ylabel("P [Вт], η*0.45")
axs[1].grid(True)
axs[1].legend(loc="center left", bbox_to_anchor=(1, 0.5))

plt.tight_layout()
plt.show()
