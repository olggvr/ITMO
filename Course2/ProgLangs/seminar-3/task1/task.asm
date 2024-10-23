section .data
message: db "hello",10
message1: db "HELLO", 0xA, 10

section .text

%macro pushn 2-*
      %rep %0
        push %1
        %rotate 1
      %endrep
    %endmacro

    %macro popn 2-*
      %rep %0
        pop %1
        %rotate 1
      %endrep
    %endmacro

global _start

exit:
    mov rax, 60
    xor rdi, rdi
    syscall

out:
    mov rax, 1
    mov rdi, 1
    mov rsi, r8
    mov rdx, 6
    syscall 
    ret
    
_start:

    mov r8, message
    mov r9, 0x42

    pushn r8, r9
    mov r8, message1
    mov r9, 0x11
    call out
    popn r9, r8
    call out

    jmp exit
