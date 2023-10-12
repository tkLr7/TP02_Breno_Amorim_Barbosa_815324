import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PesquiS {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public static int numComp=0;
    

    PesquiS()
    {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.anoNascimento = -1;
        this.nome = null;
        this.universidade = null;
        this.cidadeNascimento = null;
        this.estadoNascimento = null;
    }
    PesquiS (int id, String nome, int altura, int peso, String univerdade, int anoNascimento, String cidadeNascimento, String estadoNascimento)
    {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = univerdade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getId() 
    {
        return id;
    }
    
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    
    public String getNome() 
    {
        return nome;
    }
    
    public void setAltura(int altura) 
    {
        this.altura = altura;
    }
    
    public int getAltura() 
    {
        return altura;
    }
    
    public void setPeso(int peso)
     {
        this.peso = peso;
    }
    
    public int getPeso() 
    {
        return peso;
    }
    
    public void setUniversidade(String universidade) 
    {
        this.universidade = universidade;
    }
    
    public String getUniversidade() 
    {
        return universidade;
    }
    
    public void setAno(int ano) 
    {
        this.anoNascimento = ano;
    }
    
    public int getAno() 
    {
        return anoNascimento;
    }

    public void setCidade(String cidade) 
    {
        this.cidadeNascimento = cidade;
    }

    public String getCidade() 
    {
        return cidadeNascimento;
    }

    public void setEstado(String estado) 
    {
        this.estadoNascimento = estado;
    }

    public String getEstado() 
    {
        return estadoNascimento;
    }
    
    @Override
    protected PesquiS clone() throws CloneNotSupportedException
    {
        return (PesquiS) super.clone();
    }
//le e salva as info do jogadores
    public void ler(String linha){              
        int indexVirgulas[] = new int[7];
        int contVirgulas = 0;
        for(int i=0; i<linha.length(); i++)
        {
            numComp++;
            if(linha.charAt(i)==','){
                indexVirgulas[contVirgulas]=i;
                contVirgulas++;
            }
        }
            id = Integer.parseInt(linha.substring(0, indexVirgulas[0]));
            nome = linha.substring(indexVirgulas[0]+1, indexVirgulas[1]);
            altura = Integer.parseInt(linha.substring(indexVirgulas[1]+1, indexVirgulas[2]));
            peso = Integer.parseInt(linha.substring(indexVirgulas[2]+1, indexVirgulas[3]));
        numComp++;
        if((indexVirgulas[4]-indexVirgulas[3]+1) == 2)
        {
            universidade = "nao informado";
            
        } else
        {
            universidade = linha.substring(indexVirgulas[3]+1, indexVirgulas[4]);
          
        }
        
            anoNascimento = Integer.parseInt(linha.substring(indexVirgulas[4]+1, indexVirgulas[5])); 
        numComp++;
        if(indexVirgulas[6]-indexVirgulas[5]+1 == 2)
        {
           cidadeNascimento = "nao informado";
           
        } else
        {
            cidadeNascimento = linha.substring(indexVirgulas[5]+1, indexVirgulas[6]);
            
        }
        numComp++;
        if(linha.length()-indexVirgulas[6]+1 == 2)
        {
            estadoNascimento = "nao informado";
            
        } else
        {
            estadoNascimento = linha.substring(indexVirgulas[6]+1, linha.length());
        }
    }
//metodo para verificar se o id teste e o id da linha lida
public static boolean Idontknow (String linha, int teste1)
{
        int teste2 = 0;
        int indexVirgulas[] = new int[7];
        int contVirgulas = 0;
        for(int i=0; i<linha.length(); i++)
        {
            numComp++;
            if(linha.charAt(i)==','){
                indexVirgulas[contVirgulas]=i;
                contVirgulas++;
            }
        }
            teste2 = Integer.parseInt(linha.substring(0, indexVirgulas[0]));
            numComp++;
            if(teste1 == teste2)
            {
                
                return true;
            }
            else
            { 
                return false; 
            }
}
public static void main (String[] args) throws IOException
{    
        long inicio = new Date().getTime();
        FileWriter file = new FileWriter("matrícula_sequencial.txt");
        List<String> IDS = new ArrayList<>();
        List<String> Names = new ArrayList<>();
        PrintWriter salva = new PrintWriter(file);

        PesquiS[] jogador = new PesquiS[3923];

        String ids = "";
        int idteste = 0;
        numComp++;
        while(!ids.equals("FIM"))
        {
        numComp++;
        ids = MyIO.readLine();
        numComp++;
        if(!ids.equals("FIM"))
        {
        IDS.add(ids);
        }
        }
        
        Arq.openRead("TP02_Breno_Amorim_Barbosa_815324\\players.csv");

        String linha = Arq.readLine();
        int index = 0;
        numComp++;
        while (index < IDS.size()) {    
            Arq.openRead("TP02_Breno_Amorim_Barbosa_815324\\players.csv");  
            linha = Arq.readLine();
            numComp++;     
            while (Arq.hasNext()) 
            {
                numComp++;
                linha = Arq.readLine();
                jogador[index] = new PesquiS();
                idteste = Integer.parseInt(IDS.get(index));
                numComp++;
                if(Idontknow(linha, idteste))
                {
                    jogador[index].ler(linha);
                    index++;
                    Arq.close();
                    break;
                }
            }
        }
        Arq.close();

        
        String nome = "";
        int j = 0;
        numComp++;
        while (!nome.equals("FIM")) 
        {
            numComp++;
            nome = MyIO.readLine();
            numComp++;
            if (!nome.equals("FIM")) 
            {
                Names.add(nome);
            }
        }
        numComp++;
        while(j < Names.size())
        {
            numComp++;
            try {
                for (int i = 0; i < jogador.length; i++) 
            {numComp++;
                if ((jogador[i].getNome()).equals(Names.get(j))) 
                {
                    numComp++;
                    MyIO.println("SIM");
                    break;
                }
            }
            } catch (Exception e) {
                MyIO.println("NAO");
            }
            j++;
        } 
        //arquivo
        long fim = new Date().getTime();
        salva.println("815324\t"+numComp+"\t"+(fim-inicio)/1000.0);
        file.close();
     }
}