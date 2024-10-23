
%include "lib.inc"

section .text
global _start

_start:

    mov rdi, 0x12345678
    call print_hex
    call exit