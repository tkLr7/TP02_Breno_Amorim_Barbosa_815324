#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int numComp = 0;
int ids[1000];
char nomesGravados[1000][100]; // Array bidimensional para armazenar nomes

int pesquisaBinaria(char *nomes[], int tamanho, char *nome) {
    int inicio = 0;
    int fim = tamanho - 1;

    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        numComp++;
        int comparacao = strcmp(nomes[meio], nome);

        if (comparacao == 0) {
            return meio; // Encontrou o nome
        } else if (comparacao < 0) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }

    return -1; // O nome não foi encontrado
}

void lerNomes(int idTeste) {
    FILE *arquivo = fopen("/tmp/players.csv", "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return;
    }

    int index = 0;
    char linha[1000];
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        char *token;
        char *tokens[7];

        token = strtok(linha, ",");
        int i = 0;
        while (i < 7) {
            tokens[i] = token;
            i++;
            token = strtok(NULL, ",");
        }
        if (idTeste == atoi(tokens[0])) {
            strcpy(nomesGravados[index], tokens[1]); // Copia o nome para a matriz
            index++;
        }
    }

    fclose(arquivo);
}

int main() {
    clock_t inicio = clock();

    FILE *arquivo_saida = fopen("matricula_binaria.txt", "w");
    if (arquivo_saida == NULL) {
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
    }

    int numIds = 0;
    int ids[1000];

    // Ler IDs dos jogadores
    while (1) {
        char input[100];
        scanf("%s", input);
        if (strcmp(input, "FIM") == 0) {
            break;
        }
        ids[numIds] = atoi(input);
        numIds++;
    }

    // Ler nomes dos jogadores correspondentes aos IDs
    for (int i = 0; i < numIds; i++) {
        lerNomes(ids[i]);
    }

    // Ler nomes para pesquisa e verificar se estão na lista de nomes gravados
    int inicioNomes = numIds;
    while (1) {
        char nomePesquisa[100];
        scanf("%s", nomePesquisa);
        if (strcmp(nomePesquisa, "FIM") == 0) {
            break;
        }

        int encontrado = 0;
        for (int i = 0; i < numIds; i++) {
            if (strcmp(nomesGravados[i], nomePesquisa) == 0) {
                encontrado = 1;
                break;
            }
        }

        if (encontrado) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    clock_t fim = clock();
    double tempo = (double)(fim - inicio) / CLOCKS_PER_SEC;

    fprintf(arquivo_saida, "815324\t%d\t%.3f\n", numComp, tempo);
    fclose(arquivo_saida);

    return 0;
}
