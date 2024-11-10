#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include <inttypes.h>


#define DEFINE_LIST(type, format_spec)           \
    struct list_##type {            \
        type value;                 \
        struct list_##type* next;   \
    };                              \
    void list_##type##_push(struct list_##type** head, type value) { \
        struct list_##type* newNode = malloc(sizeof(struct list_##type)); \
        newNode->value = value;     \
        newNode->next = NULL;       \
        if (*head == NULL) {        \
            *head = newNode;        \
        } else {                    \
            struct list_##type* current = *head; \
            while (current->next != NULL) { \
                current = current->next; \
            }                        \
            current->next = newNode; \
        }                            \
    }                                \
    void list_##type##_print(struct list_##type* head) {                  \
    struct list_##type* temp = head;                                      \
    while (temp != NULL) {                                                \
      printf(format_spec " ", temp->value);                                 \
      temp = temp->next;                                                  \
    }                                                                     \
    printf("\n");                                                         \
  }   

#define push(head, value) _Generic((head),              \
    struct list_int64_t**: list_int64_t_push,           \
    struct list_double**: list_double_push              \
)(head, value)

#define print_list(head) _Generic((head),               \
    struct list_int64_t*: list_int64_t_print,           \
    struct list_double*: list_double_print              \
)(head)

DEFINE_LIST(int64_t, "%" PRId64)
DEFINE_LIST(double, "%lf")

int main() {
    struct list_int64_t* int_list = NULL;
    push(&int_list, 1);
    push(&int_list, 2);
    push(&int_list, 3);
    printf("int64_t list: ");
    print_list(int_list);

    struct list_double* double_list = NULL;
    push(&double_list, 1.1);
    push(&double_list, 2.2);
    push(&double_list, 3.3);
    printf("double list: ");
    print_list(double_list);

    return 0;
}