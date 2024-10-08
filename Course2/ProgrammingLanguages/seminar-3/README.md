# Seminar-3
## Task 1
Напишите пару многострочных макросов *pushn* и *popn*, которые будут принимать два или более аргументов и сохранять (или, в случае *pop*, восстанавливать) перечисленные в аргументах регистры в стек. Напишите пример использования и протестируйте работу вашего макроса.
Язык препроцессора в nasm позволяет удобным образом генерировать большие фрагменты кода и существенно упрощает разработку приложений. 
## Task 2
На предыдущем семинаре вы работали с функцией *print_hex*. Создайте программу из трех файлов: первые два - это библиотека с функциями *print_hex* и *exit*, в третьем содержится метка *_start* и происходит вызов *print_hex*. Скомпилируйте их и запустите программу. (Создайте библиотеку *lib.asm* с двумя функциями: *print_hex* и *exit* для выхода из приложения. Напишите к ней заголовочный файл *lib.inc*. Протестируйте её, запустив функцию из другого файла с меткой *_start*.) В результате ваша программа должна состоять из трех файлов:
 - *lib.asm* - файл с реализацией функций *print_hex* и *exit*.
 - *lib.inc* - файл с заголовками функций *print_hex* и *exit*.
 - *main.asm* - файл с функцией *_start*, вызывающей функции *print_hex* и *exit*.

## Task 3 
Напишите *makefile* для программы из текущего семинара, состоящей из двух файлов; в одном хранились функции *exit* и *print_hex*, а  в другом содержались их вызовы.

## Task 3.5
Перепишите *makefile* для *print_hex* написаного вами ранее так, чтобы использовать phony targets, переменные, автоматические переменные и шаблоны. Напишите pattern rule, которое подойдёт для создания объектного файла из любого ассемблерного файла и используйте его в свем *makefile* для *print_hex*.

## Task 4
Напишите *makefile* для любой из ваших лабораторных по программированию с первого курса, для любой лабораторной по web-программированию, для первой или второй лабораторной по этому курсу,  или вообще для любой программы из более, чем двух файлов, которую вы когда-либо писали. Не зубудьте про использование phony target для сборки программы и удаления временных файлов.
