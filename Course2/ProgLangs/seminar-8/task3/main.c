#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <unistd.h>

#define SIZE 10

void *create_shared_memory(size_t size) {
  return mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS,
              -1, 0);
}

int main() {
  int *shmem = (int *)create_shared_memory(SIZE * sizeof(int));
  sem_t *sem_parent =
      (sem_t *)create_shared_memory(sizeof(sem_t));
  sem_t *sem_child = (sem_t *)create_shared_memory(
      sizeof(sem_t));

  sem_init(sem_parent, 1, 0);
  sem_init(sem_child, 1, 1);

  for (int i = 0; i < SIZE; i++) {
    shmem[i] = i + 1;
  }

  printf("Shared memory at: %p\n", shmem);

  int pid = fork();

  if (pid == 0) {
    int index, value;

    while (1) {
      sem_wait(sem_child);

      printf("Enter index (0-9, or negative to exit): ");
      scanf("%d", &index);

      if (index < 0) {
        shmem[0] = -1;
        sem_post(sem_parent);
        break;
      }

      printf("Enter new value: ");
      scanf("%d", &value);

      if (index >= 0 && index < SIZE) {
        shmem[index] = value;
      } else {
        printf("Invalid index!\n");
      }

      sem_post(sem_parent);
    }

    exit(0);
  } else {
    printf("Child's pid is: %d\n", pid);

    while (1) {
      sem_wait(sem_parent);

      if (shmem[0] == -1) {
        printf("Child process has exited.\n");
        break;
      }

      printf("Updated array: ");
      for (int i = 0; i < SIZE; i++) {
        printf("%d ", shmem[i]);
      }
      printf("\n");

      sem_post(sem_child);
    }

    wait(NULL);
  }

  sem_destroy(sem_parent);
  sem_destroy(sem_child);
  munmap(sem_parent, sizeof(sem_t));
  munmap(sem_child, sizeof(sem_t));
  munmap(shmem, SIZE * sizeof(int));

  return 0;
}