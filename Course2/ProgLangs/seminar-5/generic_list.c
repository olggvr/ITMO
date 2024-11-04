/* generic_list.c */

#include <stdint.h>

#define DEFINE_LIST(type)                                               \
  struct list_##type {                                                  \
    type value;                                                         \
    struct list_##type* next;                                           \
  };

DEFINE_LIST(int64_t)
DEFINE_LIST(double)
