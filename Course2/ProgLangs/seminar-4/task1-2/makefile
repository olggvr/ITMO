
ASM= nasm
ASMFLAGS= -felf64
LD= ld

.PHONY: clean
clean:
	rm *.o

%.o: %.asm
	$(ASM) $(ASMFLAGS) -o $@ $<

hello_mmap_substr: hello_mmap_substr.o
	$(LD) -o hello_mmap_substr $^	

hello_mmap: hello_mmap.o
	$(LD) -o hello_mmap $^