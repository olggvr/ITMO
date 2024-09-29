# First seminar of programming languages
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
Change program, so it can write two messages: one to stdout thread ans one to stderr thread. Run program redirecting stdout -> stdout.txt and stderr -> stderr.txt 
