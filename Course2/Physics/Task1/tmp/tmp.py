import numpy as np
import matplotlib.pyplot as plt


def radioactive_decay(initial_mass, half_life, final_percent, max_days=200):
    mass_values = [initial_mass]
    decay_time = None

    decay_rate_min = 0.0001
    decay_rate_max = 0.1
    tolerance = 1e-6

    while decay_rate_max - decay_rate_min > tolerance:
        decay_rate = (decay_rate_min + decay_rate_max) / 2

        simulated_mass = initial_mass
        simulated_mass_values = [simulated_mass]
        for day in range(1, max_days + 1):
            simulated_mass -= decay_rate * simulated_mass
            simulated_mass_values.append(simulated_mass)

        if simulated_mass <= initial_mass * final_percent / 100:
            decay_time = day
            decay_rate_max = decay_rate
        else:
            decay_rate_min = decay_rate

    mass_values = [initial_mass]
    for day in range(1, max_days + 1):
        mass_values.append(mass_values[-1] - decay_rate * mass_values[-1])

    return np.arange(0, max_days + 1), mass_values, decay_time, decay_rate


if __name__ == "__main__":
    half_life = 30
    initial_mass = 100
    final_percent = 1
    max_days = 200

    time, mass, decay_time, decay_rate = radioactive_decay(initial_mass, half_life, final_percent, max_days)

    print(f"Подобранный коэффициент распада λ: {decay_rate:.6f}")
    print(f"Время, за которое останется {final_percent}% вещества: {decay_time} дней")
    print("Дни | Масса вещества")
    print("--------------------")
    for t, m in zip(time, mass):
        print(f"{t:3} | {m:.4f}")

    # График
    plt.figure(figsize=(10, 5))
    plt.plot(time, mass, label='Масса вещества', color='blue')
    plt.axhline(y=initial_mass * final_percent / 100, color='red', linestyle='--',
                label=f'{final_percent}% от начальной массы')
    plt.axvline(x=decay_time, color='green', linestyle='--', label=f'Время ≈ {decay_time} дней')
    plt.xlabel('Время (дни)')
    plt.ylabel('Масса вещества')
    plt.title('Радиоактивный распад (численный подбор)')
    plt.legend()
    plt.grid()
    plt.show()
