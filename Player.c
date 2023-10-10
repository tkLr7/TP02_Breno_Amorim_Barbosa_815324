#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 1000 // Tamanho máximo assumido para as strings de char

struct Player {
    int id;
    char nome[MAX];
    int altura;
    int peso;
    char universidade[MAX];
    int anoNascimento;
    char cidadeNascimento[MAX];
    char estadoNascimento[MAX];
};
//SETS e GETS
void setId(struct Player *player, int newid) {
    player->id = newid;
}

int getId(struct Player *player) {
    return player->id;
}

void setNome(struct Player *player, const char *newnome) {
    strcpy(player->nome, newnome);
}

const char *getNome(struct Player *player) {
    return player->nome;
}

void setAltura(struct Player *player, int newaltura) {
    player->altura = newaltura;
}

int getAltura(struct Player *player) {
    return player->altura;
}

void setPeso(struct Player *player, int newpeso) {
    player->peso = newpeso;
}

int getPeso(struct Player *player) {
    return player->peso;
}

void setUniversidade(struct Player *player, const char *newuniversidade) {
    if (strcmp(newuniversidade, "") != 0) {
        strcpy(player->universidade, newuniversidade);
    } else {
        strcpy(player->universidade, "nao informado");
    }
}

const char *getUniversidade(struct Player *player) {
    return player->universidade;
}

void setAno(struct Player *player, int newano) {
    player->anoNascimento = newano;
}

int getAno(struct Player *player) {
    return player->anoNascimento;
}

void setCidade(struct Player *player, const char *newcidade) {
    if (strcmp(newcidade, "") != 0) {
        strcpy(player->cidadeNascimento, newcidade);
    } else {
        strcpy(player->cidadeNascimento, "nao informado");
    }
}

const char *getCidade(struct Player *player) {
    return player->cidadeNascimento;
}

void setEstado(struct Player *player, const char *newestado) {
    if (strcmp(newestado, "") != 0) {
        strcpy(player->estadoNascimento, newestado);
    } else {
        strcpy(player->estadoNascimento, "nao informado");
    }
}

const char *getEstado(struct Player *player) {
    return player->estadoNascimento;
}

void clone(struct Player *player, struct Player *newplayer) {
    player->id = newplayer->id;
    strcpy(player->nome, newplayer->nome);
    player->altura = newplayer->altura;
    player->peso = newplayer->peso;
    strcpy(player->universidade, newplayer->universidade);
    player->anoNascimento = newplayer->anoNascimento;
    strcpy(player->cidadeNascimento, newplayer->cidadeNascimento);
    strcpy(player->estadoNascimento, newplayer->estadoNascimento);
}

void print(struct Player *player) {
    printf("[%d ## %s ## %d ## %d ## %s ## %d ## %s ## %s]\n",
           player->id, player->nome, player->altura, player->peso, player->universidade,
           player->anoNascimento, player->cidadeNascimento, player->estadoNascimento);
}

int ler(int idDesejado) {
    FILE *arquivo = fopen("tmp/players.csv", "r");
    if (arquivo == NULL) {
        printf("Não foi possível abrir o arquivo.\n");
        return 1; // Retorna código de erro
    }

    char linha[MAX];
    struct Player jogadorEncontrado;
    int jogadorEncontradoFlag = 0;

    // Loop para ler o arquivo linha por linha
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        struct Player jogador;
        int camposLidos = sscanf(linha, "%d, %99[^,], %d, %d, %99[^,] , %d, %99[^,], %99[^,]",
               &jogador.id, jogador.nome, &jogador.altura, &jogador.peso,
               jogador.universidade, &jogador.anoNascimento,
               jogador.cidadeNascimento, jogador.estadoNascimento);
         if (camposLidos < 8) {
        // Menos de 6 campos foram lidos na linha, preencha os campos ausentes com "nao informado"
        if (camposLidos < 1) {
            jogador.id = -1; // Valor inválido para ID
        }
        if (camposLidos < 2) {
            strcpy(jogador.nome, "nao informado");
        }
        if (camposLidos < 3) {
            jogador.altura = -1; // Valor inválido para altura
        }
        if (camposLidos < 4) {
            jogador.peso = -1; // Valor inválido para peso
        }
        if (camposLidos < 5) {
            strcpy(jogador.universidade, "nao informado");
        }
        if (camposLidos < 6) {
            jogador.anoNascimento = -1; // Valor inválido para ano de nascimento
        }
        if (camposLidos < 7)
        {
            strcpy(jogador.cidadeNascimento, "nao informado");
        }
        if (camposLidos < 8)
        {
            strcpy(jogador.estadoNascimento, "nao informado");
        }
        
    }

        if (jogador.id == idDesejado) {
            jogadorEncontrado = jogador;
            jogadorEncontradoFlag = 1;
            break; // Encerra a busca após encontrar o jogador
        }
    }

    fclose(arquivo);

    if (jogadorEncontradoFlag) {
        print(&jogadorEncontrado);
        return 0; // Retorna sucesso
    } else {
        printf("Jogador com ID %d não encontrado.\n", idDesejado);
        return 1; // Retorna código de erro
    }
}

int main() {
    char idDesejado[] = ""; //ID que você deseja encontrar
    while (idDesejado!="FIM")
    {
    scanf("%s", &idDesejado);
    int numID = atoi(idDesejado);
    int resultado = ler(numID);
    }

    return 0;
}
