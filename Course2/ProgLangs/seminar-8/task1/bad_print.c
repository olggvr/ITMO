/* thread-example.c */

#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

pthread_mutex_t print_mutex;

void bad_print(char *s)
{
    for (; *s != '\0'; s++)
    {
        fprintf(stdout, "%c", *s);
        fflush(stdout);
        usleep(100);
    }
}

void* my_thread(void* arg)
{
    for (int i = 0; i < 10; i++)
    {
        pthread_mutex_lock(&print_mutex);
        bad_print((char *)arg);
        pthread_mutex_unlock(&print_mutex);

        sleep(1);
    }
    return NULL;
}

int main()
{
    pthread_t t1, t2;

    pthread_mutex_init(&print_mutex, NULL);

    pthread_create(&t1, NULL, my_thread, "Hello\n");
    pthread_create(&t2, NULL, my_thread, "world\n");

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    pthread_mutex_destroy(&print_mutex);

    return 0;
}