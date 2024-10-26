; hello_mmap.asm
%define O_RDONLY 0 
%define PROT_READ 0x1
%define MAP_PRIVATE 0x2
%define SYS_WRITE 1
%define SYS_OPEN 2
%define SYS_MMAP 9
%define SYS_EXIT 60
%define FD_STDOUT 1

section .text
global print_string
global print_file


exit:
    mov  rax, SYS_EXIT
    xor  rdi, rdi
    syscall

print_string:
    push rdi
    call string_length
    pop  rsi
    mov  rdx, rax 
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

string_length:
    xor  rax, rax
.loop:
    cmp  byte [rdi+rax], 0
    je   .end 
    inc  rax
    jmp .loop 
.end:
    ret

print_substring:
    mov  rdx, rsi 
    mov  rsi, rdi
    mov  rax, SYS_WRITE
    mov  rdi, FD_STDOUT
    syscall
    ret

print_file:
    mov  rax, SYS_OPEN

    mov  rsi, O_RDONLY   
    mov  rdx, 0 	     
                          
    syscall

    mov r8, rax
    push r8
    mov r9, 0
    mov r10, MAP_PRIVATE
    mov rax, SYS_MMAP
    mov rdi, 0
    mov rsi, 4096
    mov rdx, PROT_READ
    syscall

    push rax
    sub rsp, 144
    mov r15, rax

    mov rax, 5
    mov rsi, rsp
    mov rdi, r8
    syscall

    mov rdi, r15
    mov rsi, [rsp + 48]
    push rdi
    call print_substring

    mov rax, 11
    pop rdi
    mov rsi, 4096

    syscall

    mov rax, 3
    pop rdi
    syscall

    call exit
