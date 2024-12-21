/* fork-example-1.c */

#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <unistd.h>

void *create_shared_memory(size_t size) {
  return mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS,
              -1, 0);
}
// 1 - 1 игрок
// 2 - player 2
// 3 - checker

int main() {
  int *shmem = (int *)create_shared_memory(10 * sizeof(int));

  for (int i = 0; i < 10; i++) {
    shmem[i] = i + 1;
  }

  printf("Shared memory at: %p\n", shmem);

  int pid = fork();

  if (pid == 0) {
    int index, value;

    printf("Enter index (0-9): ");
    scanf("%d", &index);

    printf("Enter new value: ");
    scanf("%d", &value);

    if (index >= 0 && index < 10) {
      shmem[index] = value;
    } else {
      printf("Invalid index!\n");
    }

    exit(0);
  } else {
    printf("Child's pid is: %d\n", pid);

    wait(NULL);

    printf("Updated array: ");
    for (int i = 0; i < 10; i++) {
      printf("%d ", shmem[i]);
    }
    printf("\n");
  }

  munmap(shmem, 10 * sizeof(int));

  return 0;
}