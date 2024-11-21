CFLAGS = -Wall -std=c17 -pedantic
TARGET = main

.PHONY: all
all: run

.PHONY: run
run: heap-1.c
	$(CC) $(CFLAGS) $? -o $(TARGET)
	./$(TARGET)

.PHONY: clean
clean:
	@rm $(TARGET)