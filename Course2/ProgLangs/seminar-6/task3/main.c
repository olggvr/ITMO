/* main.c */
#include "vector.h"
#include <stdio.h>

int main() {
    struct vector *v = vector_create(5);
    if (!v) {
        fprintf(stderr, "Failed to create vector\n");
        return 1;
    }

    for (size_t i = 0; i <= 100; i++) {
        vector_push_back(v, i * i);
    }
    vector_print(v, stdout);

    printf("%zu\n", vector_size(v));
    printf("%zu\n", vector_capacity(v));
    printf("%ld\n", vector_get(v,9));

    int64_t array[] = {1, 2, 3, 4, 5};
    size_t size = sizeof(array) / sizeof(array[0]);
    vector_append_array(v, array, size);
    vector_print(v, stdout);

    vector_destroy(v);

    return 0;
}
