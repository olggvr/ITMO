# Seminar-7
## Task 1
Как можно переписать функцию (какую?) чтобы программа корректно считала длину длинного списка?
```
/* tail-rec.c */

#include <inttypes.h>
#include <malloc.h>
#include <stddef.h>
#include <stdio.h>

void print_size(size_t i) { printf("%zu" , i); }

struct list {
  int64_t elem;
  struct list *next;
};

struct list *c(int64_t head, struct list *tail) {
  struct list *h = (struct list *)malloc(sizeof(struct list));
  h->elem = head;
  h->next = tail;
  return h;
}

size_t list_length(struct list const *l) {
  if (!l)
    return 0;
  return 1 + list_length(l->next);
}

int main(int argc, char **argv) {
  const size_t len = 1024 * 1024;

  struct list *lst = NULL;

  for( size_t i = 0; i < len; i++) {
    lst = c(i, lst);
  }

  print_size(list_length(lst));
  return 0;
}
```
## Task 2
Попробуйте переписать адрес возврата так, чтобы вместо возвращения из `vulnerable` в main запустить функцию `print_users`. Программа может аварийно завершиться, главное – чтобы функция отработала и вывела на экран список пользователей и их паролей.
```
/* stack-smash.c */

#include <stdio.h>
#include <stdlib.h>

struct user {
  const char *name, *password;
} const users[] = {{"Cat", "Meowmeow"}, {"Skeletor", "Nyarr"}};

void print_users() {
  printf("Users:\n");
  for (size_t i = 0; i < sizeof(users) / sizeof(struct user); i++) {
    printf("%s: %s\n", users[i].name, users[i].password);
  }
}

void fill(FILE *f, char *where) {
  size_t read_total = 0;
  for (;;) {
    size_t read = fread(where + read_total, 1, 1, f);
    if (!read)
      break;
    else
      read_total += read;
  }
}

void vulnerable(FILE *f) {
  char buffer[8];
  fill(f, buffer);
}

int main(int argc, char **argv) {
  vulnerable(stdin);

  puts("nothing happened");
}
```
## Task 3
Исправьте уязвимость кода из задания 2
## Task 4
Исправьте данный код так, чтобы исключить потенциальную уязвимость.
```
/* check-pwd.c */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int check_password(FILE *f, const char *password) {
  char buffer[10];
  int okay = 0;
  fscanf(f, "%s", buffer);
  if (strcmp(buffer, password) == 0)
    okay = 1;

  return okay;
}

int main(int argc, char **argv) {
  if (check_password(stdin, "password"))
    puts("Access granted.");
  else
    puts("Wrong password.");
}
```
