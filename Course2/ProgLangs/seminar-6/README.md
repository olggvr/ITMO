# Seminar-6
## Task 1
Изучите файл `heap-0.c`  и реализуйте в нём недостающие функции для резервирования блока и для возвращения блока в пул доступных. Почему `block_id` содержит ссылку на `struct heap`?
## Task 2
Модифицируйте аллокатор так, чтобы он умел выделять несколько блоков подряд. См. файл heap-1.c. У блоков теперь будет статус с большей гранулярностью, нежели “занят — не занят”. Придумайте тесты для вашего кода для проверки всех особых случаев.
`enum block_status { BLK_FREE = 0, BLK_ONE, BLK_FIRST, BLK_CONT, BLK_LAST };` \
Блоки будут связываться в одну из следующих конфигураций:
```
BLK_FREE //  свободный блок
BLK_ONE //  единичный занятый блок.
// после него или свободный или занятый другими данными.

BLK_FIRST, BLK_CONT, BLK_CONT, BLK_LAST
// последовательность блоков с  началом, серединкой и концом.

BLK_FIRST, BLK_LAST  // то же, но без серединки.
```
Выделение памяти должно привести к выделению адекватного количества блоков; освобождение памяти должно привести к освобождению блоков начиная с указанного, если этот блок – начало.
## Task 3
У вас есть код внутри одной функции `main`, который реализует расширяемый массив (вектор). Изучите его.
```
/* bad.c */

#include <inttypes.h>
#include <malloc.h>
#include <stdio.h>

int main() {
  int64_t *array = malloc(sizeof(int64_t) * 5);
  // вместимость -- сколько памяти выделено
  size_t capacity = 5;
  // количество -- сколько памяти по факту используется из выделенной.
  size_t count = 0;
  // заполните массив квадратами чисел от 0 до 100
  // если не хватает места, расширяем в два раза
  for (size_t i = 0; i <= 100; i++) {
    if (count == capacity) {
      array = realloc(array, sizeof(int64_t) * capacity * 2);
      capacity = capacity * 2;
    }
    array[count++] = i * i;
  }

  for (size_t i = 0; i < 100; i++) {
    printf("%" PRId64 " ", array[i]);
  }
  return 0;
}
```
**Задача** \
Ваша задача — выделить из этого кода как минимум модуль с реализацией вектора, снабжённый заголовочным файлом.

- Вектор должен быть реализован как непрозрачная структура данных.
- Доступ к его элементам должен быть контролируем и осуществляться через getter и setter.
- Постарайтесь максимально переиспользовать код и ничего не дублировать.
- Вывод вектора реализуйте как отдельную функцию, которая принимает FILE*, в который нужно вывести его содержимое. Эту функцию можно также разбить на функцию foreach и принтер одного элемента.
- Стремитесь сделать настолько маленькие функции, насколько возможно.

Ваша реализацяия как минимум должна позволять:

- Получить доступ к любому элементу.
- Получить информацию о текущей длинне массива и количестве выделенной памяти.
- Добавить элемент в конец массива.
- Добавить другой массив в конец массива (скопировать данные).
- Изменить размер массива на заданный (при необхоимости выделить память).
- Изменить размер массива на меньший с освобождением неиспользуемой памяти.
- Освободить память.
- Вывести массив в указанный поток вывода.

В результате должна получиться программа из нескольких файлов, которая делает то же самое, но в которой `main` содержит только заполнение вектора числами и вызов функции, печатающей его в `stdout`.
