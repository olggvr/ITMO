section .text
 
; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
    xor rax, rax
    mov	rax, rdi
	.counter:
		cmp byte[rdi], 0
		jz .end
		inc rdi
		jmp	.counter
	.end:
		sub	rdi, rax
		mov rax, rdi
		xor rdi, rdi
		xor rsi, rsi
		ret

; Принимает указатель на нуль-терминированную строку, выводит её в stdout
print_string:
	mov rsi, rdi
	push rsi
	call string_length
	pop rsi
	mov rdx, rax
	mov rax, 1
	mov rdi, 1
	syscall
	ret

; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
parse_uint:
	mov rsi, 0x0A
	
	xor r8, r8
	xor rax, rax
	xor rcx, rcx
	
	.loop:
		mov al, byte[rdi, rcx]
		cmp al, '0'
		jb .fin
		cmp al, '9'
		ja .fin
		sub al, '0'
		push rax
		mov rax, r8
		mul rsi
		mov r8, rax
		pop rax
		
		add	r8, rax
		inc rcx
		jmp .loop
	.fin:
		mov rdx, rcx
		mov rax,r8
		ret
