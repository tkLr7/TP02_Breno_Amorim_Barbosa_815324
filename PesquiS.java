import java.io.*;
import java.util.*;

public class PesquiS 
{
    ArrayList<Integer> IDs = new ArrayList<>();
    ArrayList<String> NOMES = new ArrayList<>();
    
    public void ler(int id)
    {           
        Arq.openRead("/tmp/players.csv");

        String linha = "";
        Arq.readLine();

        while ((linha = Arq.readLine()) != null) 
        {
            int indexVirgulas[] = new int[7];
            int contVirgulas = 0;
            for(int i=0; i<linha.length(); i++)
            {
                if(linha.charAt(i)==','){
                    indexVirgulas[contVirgulas]=i;
                    contVirgulas++;
                }
            }
                id = Integer.parseInt(linha.substring(0, indexVirgulas[0]));
                String nome = linha.substring(indexVirgulas[0]+1, indexVirgulas[1]);
                
                IDs.add(id);
                NOMES.add(nome);
        }
    }
    public boolean comparar(String entrada)
    {
        for (String nome : NOMES) {
            if (nome.equals(entrada)) {
                return true; // Elemento encontrado
            }
        }
        return false; // Elemento não encontrado
    }

    
    
    public static void main(String[] args) throws IOException
    {   
        PesquiS pesquisa = new PesquiS();
        String ids = "";
        ids = MyIO.readString();
        
        while (!ids.equals("FIM")) 
        {
            int lerID = Integer.parseInt(ids);
            pesquisa.ler(lerID);
        }
        
        String entrada = "";
        entrada = MyIO.readString();
        while (!entrada.equals("FIM")) 
        {
            boolean teste = pesquisa.comparar(entrada);

            if (teste) 
            {
                System.out.println("SIM");
            }
            else
            {
                System.out.println("NAO");
            }
        }
        String matricula = "815324";
        long tempoExecucao = System.currentTimeMillis(); // Tempo atual em milissegundos
        int numComparacoes = pesquisa.NOMES.size(); // Número de comparações é o tamanho da lista NOMES

        try {
            FileWriter logFile = new FileWriter("matricula_sequencial.txt");
            logFile.write(matricula + "\t" + tempoExecucao + "\t" + numComparacoes);
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
