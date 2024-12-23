#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <stdbool.h>

#define SIZE 3
char board[SIZE][SIZE];

pthread_mutex_t board_mutex;
bool gameOver = false;
int currentPlayer = 1;

void printBoard() {
    pthread_mutex_lock(&board_mutex);
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%c ", board[i][j]);
        }
        printf("\n");
    }
    pthread_mutex_unlock(&board_mutex);
}

bool checkWinner() {
    for (int i = 0; i < SIZE; i++) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
            return true;
        }
    }
    for (int i = 0; i < SIZE; i++) {
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
            return true;
        }
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
        return true;
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
        return true;
    }
    return false;
}

bool isDraw() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j] == '-') {
                return false;
            }
        }
    }
    return true;
}

void makeMove(int player, int row, int col) {
    pthread_mutex_lock(&board_mutex);

    if (board[row][col] == '-' && !gameOver && player == currentPlayer) {
        board[row][col] = (player == 1) ? 'X' : 'O';
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    pthread_mutex_unlock(&board_mutex);
}

void* playerThread(void* arg) {
    int player = *((int*)arg);
    while (!gameOver) {
        int row, col;

        pthread_mutex_lock(&board_mutex);
        if (player != currentPlayer || gameOver) {
            pthread_mutex_unlock(&board_mutex);
            continue;
        }
        pthread_mutex_unlock(&board_mutex);

        printf("Player %d: ", player);
        scanf("%d %d", &row, &col);

        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            printf("wrong coordinates\n");
            continue;
        }

        makeMove(player, row, col);
        printBoard();

    }

    return NULL;
}

void* checkerThread(void* arg) {
    while (!gameOver) {
        pthread_mutex_lock(&board_mutex);
        if (checkWinner()) {
            gameOver = true;
            printf("\nplayer %c won\n", (currentPlayer == 2) ? 'X' : 'O');
        } else if (isDraw()) {
            gameOver = true;
            printf("\ndraw!\n");
        }
        pthread_mutex_unlock(&board_mutex);
    }
    return NULL;
}

int main() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            board[i][j] = '-';
        }
    }

    pthread_t player1, player2, checker;
    pthread_mutex_init(&board_mutex, NULL);

    int playerId1 = 1;
    int playerId2 = 2;

    pthread_create(&player1, NULL, playerThread, &playerId1);
    pthread_create(&player2, NULL, playerThread, &playerId2);
    pthread_create(&checker, NULL, checkerThread, NULL);

    pthread_join(player1, NULL);
    pthread_join(player2, NULL);
    pthread_join(checker, NULL);

    pthread_mutex_destroy(&board_mutex);

    return 0;
}