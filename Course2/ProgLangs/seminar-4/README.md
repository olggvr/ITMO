# Seminar-4
## Task 1
Создайте файл hello.txt с текстом Hello, mmap!. Используя заготовку, отобразите его в память и выведите текст из него в стандартный поток вывода. Не забудьте вызвать munmap (его номер системного вызова 11) и закрыть файл (close, номер системного вызова 3) по завершению работы с файлом.

## Task 2
Прочитайте документацию по системному вызову fstat (номер 5). Вас будет интересовать поле st_size типа off_t структуры struct stat, которую функция fstat заполняет. Используйте его, чтобы корректно вычислить размер файла при выводе данных, и выведите их, используя функцию print_substring (принимает на вход два аргумента: адрес начала строки и количество байт для вывода). Полученный размер файла используйте в вызовах mmap, munmap и print_substring.

## Task 3
В этих файлах не хватает нескольких строчек чтобы можно было взаимодействовать с кодом друг друга. Допишите файлы так, чтобы функции print_string и hello вызывалась и проверьте результат. Подсказка: вспомните, что нужно, чтобы из одного файла с C-кодом вызвать код из другого файла.

## Task 4
Объедините ассемблерный код для вывода содержимого файла с кодом на языке C. Пусть ваша программа будет просить пользователя ввести название файла, а затем выведет его содержимое в стандартный поток вывода используя код, написанный в начале сегодняшнего семинара (сделайте из него функцию print_file, которая будет принимать имя файла первым аргументом). Не забудьте, что для корректной работы необходимо следовать соглашениям о вызовах и сохранить callee-saved регистры, которые вы используете, в начале своей функции print_file. Для вывода сообщений (например “Please enter file name: “) используйте собственную реализацию print_string из сегодняшнего семинара.
