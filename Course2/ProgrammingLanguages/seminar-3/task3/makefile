lib.o: lib.asm
	nasm -f elf64 -o lib.o lib.asm

main.o: main.asm lib.o
	nasm -f elf64 -o main.o main.asm  

program: main.o lib.o
	ld -o program main.o lib.o
    