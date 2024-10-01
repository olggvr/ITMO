# First seminar of programming languages
## 0.5 task
Make with regexp:
- Find strings consisting of only one any symbol
- Find strings, which have at least one digit
- Find strings, which are numbers in hexadecimal format, 0x1F as example
- Find strings that contain a three-letter word
- Find empty strings
- Invert results of **grep** search using keys, make a search of strings, that are not satisfying to pattern
## 1st task
Compile and execute file from asm file hello.asm
```
hello.asm 
  section .data
  message: db  'hello, world!', 10

  section .text
  global _start

  _start:
      mov     rax, 1           ; 'write' syscall number
      mov     rdi, 1           ; stdout descriptor
      mov     rsi, message     ; string address
      mov     rdx, 14          ; string length in bytes
      syscall

      mov     rax, 60          ; 'exit' syscall number
      xor     rdi, rdi
      syscall
```
Write following commands in console
```
$ nasm -g hello.asm -felf64 -o hello.o
$ ld -o hello hello.o
```
Execute:
```
./hello
```
## 2nd task
Change program, so it can write two messages: one to **stdout** thread ans one to **stderr** thread. Run program redirecting stdout -> stdout.txt and stderr -> stderr.txt 
## 3rd task
Run preveous program using gdb, track changes on rip register. Pay attention to rax after syscall write
## 3.5 task 
Try to go back with debugger
## 4th task 
Debug folowing program step by step and watch for changing of rax register value. Explain this changes
```
section .text
global _start

_start:
    mov     rax, 0FFFFFFFFFFFFFFFFh
    mov     al, 0
    mov     ax, 0
    mov     eax, 0

    mov     rax, 60
    xor     rdi, rdi
    syscall
```
