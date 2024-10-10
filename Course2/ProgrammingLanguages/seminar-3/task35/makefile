
ASM= nasm
ASMFLAGS= -felf64
LD= ld

.PHONY: clean
clean:
	rm *.o



%.o: %.asm
	$(ASM) $(ASMFLAGS) -o $@ $<

program: main.o lib.o
	$(LD) -o program $^