CFLAGS=-fno-stack-protector -z execstack -no-pie -g

all: main

.PHONY: main
main: main.c
	$(CC) $(CFLAGS) -o $@ $<
	./exp.sh

.PHONY: clean
clean:
	rm main