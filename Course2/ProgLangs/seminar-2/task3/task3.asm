section .data
codes:
    db '0123456789ABCDEF'
n: db 10



section .text
global  _start
_start:

mov     rdi, 0xAA
mov     rsi, 0xBB
mov     rdx, 0xCC
mov     rcx, 0xFF

call    put_stack
jmp     exit

exit:
    mov rax, 60
    xor rdi, rdi
    syscall

put_stack:
    xor  r10, r10
    push rcx
    push rdx
    push rsi
    push rdi
.loop:
    pop  rdi
    sub  rsp, 8
    call print_hex
    add  rsp, 8
    inc  r10
    cmp  r10, 4
    jne  .loop
    ret


print_hex: 
    mov rax, rdi
    mov rdi, 1
    mov rdx, 1
    mov rcx, 64

.loop:
    push rax
    sub  rcx, 4

    sar rax, cl
    and rax, 0xf

    lea rsi, [codes + rax]
    mov rax, 1

    push rcx
    syscall
    pop  rcx

    pop rax
	
    test rcx, rcx
    jnz  .loop
    ret