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
  int pipefd[2];
  if (pipe(pipefd) == -1) {
    perror("pipe");
    exit(EXIT_FAILURE);
  }

  int *shmem = (int *)create_shared_memory(SIZE * sizeof(int));

  for (int i = 0; i < SIZE; i++) {
    shmem[i] = i + 1;
  }

  printf("Shared memory at: %p\n", shmem);

  int pid = fork();

  if (pid == 0) {
    close(pipefd[0]);
    int index, value;

    while (1) {
      printf("Enter index (0-9, or negative to exit): ");
      scanf("%d", &index);

      if (index < 0) {
        write(pipefd[1], &index, sizeof(int));
        break;
      }

      printf("Enter new value: ");
      scanf("%d", &value);

      if (index >= 0 && index < SIZE) {
        shmem[index] = value;

        write(pipefd[1], &index, sizeof(int));
      } else {
        printf("Invalid index!\n");
      }
    }

    close(pipefd[1]);
    exit(0);
  } else {
    close(pipefd[1]);

    int received_index;
    printf("Child's pid is: %d\n", pid);

    while (1) {
      if (read(pipefd[0], &received_index, sizeof(int)) > 0) {
        if (received_index < 0) {
          printf("Child process has exited.\n");
          break;
        }

        printf("Updated array: ");
        for (int i = 0; i < SIZE; i++) {
          printf("%d ", shmem[i]);
        }
        printf("\n");
      }
    }

    close(pipefd[0]);
    wait(NULL);
  }

  munmap(shmem, SIZE * sizeof(int));

  return 0;
}