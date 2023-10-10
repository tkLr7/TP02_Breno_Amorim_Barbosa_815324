import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Playerone {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Playerone() {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.anoNascimento = -1;
        this.nome = null;
        this.universidade = null;
        this.cidadeNascimento = null;
        this.estadoNascimento = null;
    }

    Playerone(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setAno(int ano) {
        this.anoNascimento = ano;
    }

    public int getAno() {
        return anoNascimento;
    }

    public void setCidade(String cidade) {
        this.cidadeNascimento = cidade;
    }

    public String getCidade() {
        return cidadeNascimento;
    }

    public void setEstado(String estado) {
        this.estadoNascimento = estado;
    }

    public String getEstado() {
        return estadoNascimento;
    }

    public void ler(String linha) {
        String[] campos = linha.split(",");
        id = -1;
        nome = "nao informado";
        altura = -1;
        peso = -1;
        universidade = "nao informado";
        anoNascimento = -1;
        cidadeNascimento = "nao informado";
        estadoNascimento = "nao informado";
    
        if (campos.length >= 1) {
            id = Integer.parseInt(campos[0]);
        }
        if (campos.length >= 2) {
            nome = campos[1];
        }
        if (campos.length >= 3) {
            altura = Integer.parseInt(campos[2]);
        }
        if (campos.length >= 4) {
            peso = Integer.parseInt(campos[3]);
        }
        if (campos.length >= 5) {
            universidade = campos[4];
        }
        if (campos.length >= 6) {
            anoNascimento = Integer.parseInt(campos[5]);
        }
        if (campos.length >= 7) {
            cidadeNascimento = campos[6];
        }
        if (campos.length >= 8) {
            estadoNascimento = campos[7];
        }
    }
    

    public void imprimir() {
        System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
    }

    public static void main(String[] args) {
        List<Playerone> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        

        try {
            String info = "";
            Arq.openRead("/tmp/players.csv");

            Arq.readLine();
            
            while ((info = Arq.readLine())!= null) 
            {
                if (info.length() > 3) 
                {
                    Playerone player = new Playerone();
                    player.ler(info);
                    players.add(player);
                }
            }
            Arq.openReadClose("/tmp/players.csv");
        } catch (Exception e) {
            System.out.println(e);
        }

        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            int playerId = Integer.parseInt(id);
            boolean found = false;
            for (Playerone player : players) {
                if (player.getId() == playerId) {
                    player.imprimir();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Jogador com ID " + id + " não encontrado.");
            }
            id = scanner.nextLine();
        }
        scanner.close();
    }
}
