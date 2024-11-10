#include <stdlib.h>

#define DEFINE_LIST(type, format_spec)                                                 \
  struct list_##type {                                                    \
      type value;                                                         \
      struct list_##type* next;                                           \
  };                                                                      \
                                                                          \
  void list_##type##_push(struct list_##type** head, type value) {        \
      struct list_##type* new_node = (struct list_##type*)malloc(sizeof(struct list_##type)); \
      new_node->value = value;                                            \
      new_node->next = NULL;                                              \
      if (*head == NULL) {                                                \
        *head = new_node;                                                 \
      } else {                                                            \
        struct list_##type* temp = *head;                                 \
        while (temp->next != NULL) {                                      \
          temp = temp->next;                                              \
        }                                                                 \
        temp->next = new_node;                                            \
      }                                                                   \
  }                                                                       \
                                                                          \
  void list_##type##_print(struct list_##type* head) {                  \
    struct list_##type* temp = head;                                      \
    while (temp != NULL) {                                                \
      printf(format_spec " ", temp->value);                                 \
      temp = temp->next;                                                  \
    }                                                                     \
    printf("\n");                                                         \
  }                                                     

DEFINE_LIST(int64_t, "%ld")
DEFINE_LIST(double, "%f")



int main() {
    struct list_int64_t* int_list = NULL;
    list_int64_t_push(&int_list, 10);
    list_int64_t_push(&int_list, 20);
    list_int64_t_push(&int_list, 30);

    struct list_double* double_list = NULL;
    list_double_push(&double_list, 1.1);
    list_double_push(&double_list, 2.2);
    list_double_push(&double_list, 3.3);

    printf("int64_t list: ");
    list_int64_t_print(int_list);

    printf("double list: ");
    list_double_print(double_list);

    return 0;
}