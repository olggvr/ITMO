# Seminar-2
## Task 1
Find **print_hex** function from following code and try to write three any numbers using this function
```
print_hex.asm
section .data
codes:
    db      '0123456789ABCDEF'

section .text
global _start
_start:
    ; number 1122... in hexadecimal format
    mov  rax, 0x1122334455667788

    mov  rdi, 1
    mov  rdx, 1
    mov  rcx, 64
	; Each 4 bits should be output as one hexadecimal digit
	; Use shift and bitwise AND to isolate them
	; the result is the offset in 'codes' array
.loop:
    push rax
    sub  rcx, 4
	; cl is a register, smallest part of rcx
	; rax -- eax -- ax -- ah + al
	; rcx -- ecx -- cx -- ch + cl
    sar  rax, cl
    and  rax, 0xf

    lea  rsi, [codes + rax]
    mov  rax, 1

    ; syscall leaves rcx and r11 changed
    push rcx
    syscall
    pop  rcx

    pop rax
	; test can be used for the fastest 'is it a zero?' check
	; see docs for 'test' command
    test rcx, rcx
    jnz .loop

    mov  rax, 60            ; invoke 'exit' system call
    xor  rdi, rdi
    syscall
```
## Task 2 тестировщик
Rewrite following program to **print_string** get zero-terminated string in the single string argument, test functions using tester
```
print_string.asm 
section .data
message:       db  'hello, world!', 10
error_message: db  'hello, errors!', 10

section .text
global _start

exit:
    mov  rax, 60
    xor  rdi, rdi          
    syscall

string_length:
    mov  rax, rdi
  .counter:
    cmp  byte [rdi], 0
    je   .end
    inc  rdi
    jmp  .counter
  .end:
    sub  rdi, rax
    mov  rax, rdi
    ret

print_string:
    mov  rdx, rsi
    mov  rsi, rdi
    mov  rax, 1
    mov  rdi, 1
    syscall
    ret

_start:
    mov  rdi, message
    mov  rsi, 14  
    call print_string
    call exit
```
## Task 2.5 
Does your solution to task 2 align the stack before calling **string_length**? Use the tester to test whether **print_string** conforms to the stack alignment convention
## Task 3
Write a function that allocates space for four local variables, writes aa, bb, cc and ff and displays them on the screen. Then the function must free the allocated memory and terminate correctly (with the ret instruction). Test it. To access local variables, use relative addressing and remember to align the stack before calling functions. Use the print_hex function already written at the beginning of the workshop to output to the screen.
## Task 4
Draw an automaton for the parse_uint function on the basis of a blank from one of the previous paragraphs; equip its transitions also with actions on registers, as a result of which the number parsed from the string will appear in rax, and the number of characters in it will appear in rdx. Then code this automaton in assembler and check it with a test.
