TARGET = main

.PHONY: all
all: run

.PHONY: run
run: main.c vector.c
	$(CC) $(CFLAGS) $? -o $(TARGET)
	./$(TARGET)

.PHONY: clean
clean:
	@rm $(TARGET)