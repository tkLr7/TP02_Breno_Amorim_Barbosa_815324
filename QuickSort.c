#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 1000

struct Player {
    int id;
    char nome[MAX];
    int altura;
    int peso;
    int anoNascimento;
    char universidade[MAX];
    char cidadeNascimento[MAX];
    char estadoNascimento[MAX];
};

char estados[MAX][MAX];
int posicao = 0;

void swap(char *i, char *j) {
    char temp[MAX];
    strcpy(temp, i);
    strcpy(i, j);
    strcpy(j, temp);
}

void quicksortRec(int esq, int dir) {
    int i = esq, j = dir;
    char pivo[MAX];
    strcpy(pivo, estados[(dir + esq) / 2]);

    while (i <= j) {
        while (strcmp(estados[i], pivo) < 0)
            i++;
        while (strcmp(estados[j], pivo) > 0)
            j--;
        if (i <= j) {
            swap(estados[i], estados[j]);
            i++;
            j--;
        }
    }

    if (esq < j)
        quicksortRec(esq, j);
    if (i < dir)
        quicksortRec(i, dir);
}

void ler(int idDesejado) {
    FILE *arquivo = fopen("players.csv", "r");
    if (arquivo == NULL) {
        printf("Não foi possível abrir o arquivo.\n");
        return;
    }

    char linha[MAX];
    struct Player jogadorEncontrado;
    int jogadorEncontradoFlag = 0;

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        size_t len = strlen(linha);
        if (len > 0 && linha[len - 1] == '\n') {
            linha[len - 1] = '\0'; // Remova o caractere de nova linha
        }
        struct Player jogador;
        int camposLidos = sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]",
                                &jogador.id, jogador.nome, &jogador.altura, &jogador.peso,
                                jogador.universidade, &jogador.anoNascimento,
                                jogador.cidadeNascimento, jogador.estadoNascimento);

        if (camposLidos < 8) {
            if (camposLidos < 1) {
                jogador.id = -1;
            }
            if (camposLidos < 2) {
                strcpy(jogador.nome, "nao informado");
            }
            if (camposLidos < 3) {
                jogador.altura = -1;
            }
            if (camposLidos < 4) {
                jogador.peso = -1;
            }
            if (camposLidos < 5) {
                strcpy(jogador.universidade, "nao informado");
            }
            if (camposLidos < 6) {
                jogador.anoNascimento = -1;
            }
            if (camposLidos < 7) {
                strcpy(jogador.cidadeNascimento, "nao informado");
            }
            if (camposLidos < 8) {
                strcpy(jogador.estadoNascimento, "nao informado");
            }
        }

        if (jogador.id == idDesejado) {
            jogadorEncontrado = jogador;
            jogadorEncontradoFlag = 1;
            strcpy(estados[posicao], jogador.estadoNascimento);
            strcat(estados[posicao], ",");
            strcat(estados[posicao], jogador.nome);
            posicao++;
            break;
        }
    }
    fclose(arquivo);
}

void procurarnome(char taste[]) {
    char *separar;
    separar = strtok(taste, ",");
    char info[2][MAX];
    int i = 0;
    while (i < 2) {
        strcpy(info[i], separar);
        i++;
        separar = strtok(NULL, ",");
    }

    FILE *arquivo = fopen("players.csv", "r");
    if (arquivo == NULL) {
        printf("Não foi possível abrir o arquivo.\n");
        return;
    }

    char linha[MAX];
    struct Player jogadorEncontrado;
    int jogadorEncontradoFlag = 0;

    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        size_t len = strlen(linha);
        if (len > 0 && linha[len - 1] == '\n') {
            linha[len - 1] = '\0';
        }
        struct Player jogador;
        int camposLidos = sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]",
                                &jogador.id, jogador.nome, &jogador.altura, &jogador.peso,
                                jogador.universidade, &jogador.anoNascimento,
                                jogador.cidadeNascimento, jogador.estadoNascimento);

        if (camposLidos < 8) {
            if (camposLidos < 1) {
                jogador.id = -1;
            }
            if (camposLidos < 2) {
                strcpy(jogador.nome, "nao informado");
            }
            if (camposLidos < 3) {
                jogador.altura = -1;
            }
            if (camposLidos < 4) {
                jogador.peso = -1;
            }
            if (camposLidos < 5) {
                strcpy(jogador.universidade, "nao informado");
            }
            if (camposLidos < 6) {
                jogador.anoNascimento = -1;
            }
            if (camposLidos < 7) {
                strcpy(jogador.cidadeNascimento, "nao informado");
            }
            if (camposLidos < 8) {
                strcpy(jogador.estadoNascimento, "nao informado");
            }
        }

        if (strcmp(jogador.nome, info[1]) == 0) {
            jogadorEncontrado = jogador;
            jogadorEncontradoFlag = 1;
            break;
        }
    }
    fclose(arquivo);

    if (jogadorEncontradoFlag) {
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
               jogadorEncontrado.id, jogadorEncontrado.nome, jogadorEncontrado.altura, jogadorEncontrado.peso,
               jogadorEncontrado.anoNascimento, jogadorEncontrado.universidade,
               jogadorEncontrado.cidadeNascimento, jogadorEncontrado.estadoNascimento);
    } else {
        printf("Nao encontrado.\n");
    }
}

int main() {
    char idDesejado[MAX] = "";
    int contagem = 0;

    while (true) {
        scanf(" %s", idDesejado);
        if (strcmp(idDesejado, "FIM") == 0) {
            break;
        }
        int numID = atoi(idDesejado);
        ler(numID);
        contagem++;
        while (getchar() != '\n'); // Limpa o buffer de entrada
    }

    quicksortRec(0, contagem - 1); // Subtrai 1 de contagem

    for (int i = 0; i < contagem; i++) {
        procurarnome(estados[i]);
    }

    return 0;
}
