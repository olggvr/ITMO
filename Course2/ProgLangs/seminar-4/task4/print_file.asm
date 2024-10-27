; hello_mmap.asm
%define O_RDONLY    0
%define PROT_READ   0x1
%define MAP_PRIVATE 0x2
%define SYS_WRITE   1
%define SYS_OPEN    2
%define SYS_CLOSE   3
%define SYS_MMAP    9
%define SYS_MUNMAP  11
%define FD_STDOUT   1
%define FD_STDERR   2

section .text

global print_file
global print_string

print_string:
  push    rdi
  call    string_length
  pop     rsi
  mov     rdx, rax
  mov     rax, SYS_WRITE
  mov     rdi, FD_STDOUT
  syscall 
  ret     

string_length:
  xor rax, rax
.loop:
  cmp byte [rdi+rax], 0
  je  .end
  inc rax
  jmp .loop
.end:
  ret 

print_substring:
  mov     rdx, rsi
  mov     rsi, rdi
  mov     rax, SYS_WRITE
  mov     rdi, FD_STDOUT
  syscall 
  ret     

open:
  mov rax, SYS_OPEN
  syscall
  ret

close:
  mov rax, SYS_CLOSE
  syscall
  ret

mmap:
  mov rax, SYS_MMAP
  syscall
  ret

munmap:
  mov rax, SYS_MUNMAP
  syscall
  ret

print_file:
  mov rsi, O_RDONLY
  mov rdx, 0
  call open
  push rax

  mov rdi, 0         
  mov rsi, 4096        
  mov rdx, PROT_READ
  mov r10, MAP_PRIVATE
  mov r8,  rax 
  mov r9,  0         
  call mmap

  pop rdi
  push rax

  call close

  mov rdi, [rsp]
  call print_string

  pop rdi
  mov rsi, 4096
  call munmap
  ret