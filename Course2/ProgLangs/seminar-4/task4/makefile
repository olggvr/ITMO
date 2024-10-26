CC=gcc
CFLAGS=-Wall -Werror -Wextra -pedantic -std=c99 -no-pie
AS=nasm
ASFLAGS=-felf64
TARGET=main

.PHONY: all
all: $(TARGET)

print_file.o: print_file.asm
	$(AS) $(ASFLAGS) -o $@ $?

main.o: main.c
	$(CC) -c $(CFLAGS) -o $@ $?

$(TARGET): print_file.o main.o
	$(CC) $(CFLAGS) -o $@ $?

.PHONY: clean
clean:
	rm $(TARGET) *.o