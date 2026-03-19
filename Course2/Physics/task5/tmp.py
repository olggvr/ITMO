import numpy as np
import matplotlib.pyplot as plt

def manchester_signal(bits, bit_time=1.0, convention='ieee'):
    """
    bits: строка '1100101...'
    bit_time: длительность одного бита по оси времени
    convention: 'ieee'  -> 1: high->low, 0: low->high
                'g_e'   -> 1: low->high, 0: high->low
    Возвращает (t, v) для step plot.
    """
    # половинки бита
    half = bit_time / 2.0
    t = []
    v = []
    t_now = 0.0

    for b in bits:
        if convention == 'ieee':
            first_level  = 1.0 if b == '1' else 0.0
            second_level = 0.0 if b == '1' else 1.0
        else:  # 'g_e'
            first_level  = 0.0 if b == '1' else 1.0
            second_level = 1.0 if b == '1' else 0.0

        # оставляем уровень на первый полубит
        t.extend([t_now, t_now + half * 0.999999])
        v.extend([first_level, first_level])
        t_now += half

        # и на второй полубит (переход в середине)
        t.extend([t_now, t_now + half * 0.999999])
        v.extend([second_level, second_level])
        t_now += half

    # добавим немного завершающего времени для лучшего отображения
    t.append(t_now)
    v.append(v[-1])
    return np.array(t), np.array(v)

# Пример: первые 4 байта из вашего сообщения (C3 C0 CE D1)
bits = ''.join([
    format(int(x, 16), '08b') for x in ["C3","C0","CE","D1"]
])  # -> строка из 32 символов '0'/'1'

t, v = manchester_signal(bits, bit_time=1.0, convention='ieee')

plt.figure(figsize=(12,3))
plt.step(t, v, where='post')
plt.ylim(-0.2, 1.2)
plt.yticks([0,1], ['LOW','HIGH'])
plt.xlabel('Time (bit periods)')
plt.title('Manchester encoding — bytes: C3 C0 CE D1\nbits: ' + ' '.join([bits[i:i+8] for i in range(0,len(bits),8)]))

# Разметка — вертикальные линии через каждые бит-времена и подпись битов
n_bits = len(bits)
for i in range(n_bits+1):
    plt.axvline(i, color='black', linewidth=0.4, linestyle='--', alpha=0.3)

# подставим надписи битов в центре бит-периода
for i in range(n_bits):
    plt.text(i + 0.5, 1.08, bits[i], ha='center', va='bottom', fontsize=9)

plt.tight_layout()
plt.savefig('manchester_C3C0CED1.png', dpi=300)
plt.show()
