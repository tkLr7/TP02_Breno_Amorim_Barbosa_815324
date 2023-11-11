import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SelecaoParcial {
    public static int comp = 0;
    public static int trocas = 0;
    public static int[] ids = new int[550];
    public static String[] nomes = new String[4000];
    public static int index = 0;


    public static void gravaNomes(int IDS) {
        Arq.openRead("/tmp/players.csv");
        Arq.readLine();
    
        while (Arq.hasNext()) {
            String linha = Arq.readLine();
            String[] find = linha.split(",");
            int test = Integer.parseInt(find[0]);
    
            if (IDS == test) {
                nomes[index] = find[1];
                index++;
                break;
            }
        }
    
        Arq.close();
    }
    public static void imprimir(String teste)
    {   
        Arq.openRead("/tmp/players.csv");

        Arq.readLine();

        String[] variaveis = null;
        
        while (Arq.hasNext()) 
        {
            comp++;
            
            String linha = Arq.readLine();
            variaveis = linha.split(",");
            String nome = variaveis[1];
            if (nome.equals(teste)) 
            {
            comp++;
            for (int i = 0; i < 6; i++) 
            {
                comp++;
                if (variaveis[i].isEmpty() || variaveis[i].equals("")) 
                {
                    comp++;
                    variaveis[i] = "nao informado";
                }
            } 
            comp++;
            int id = Integer.parseInt(variaveis[0]);
            int altura = Integer.parseInt(variaveis[2]);
            int peso = Integer.parseInt(variaveis[3]);
            String universidade = variaveis[4];
            int anoNascimento = Integer.parseInt(variaveis[5]);
            String cidadeNascimento = "nao informado";
            String estadoNascimento = "nao informado";
            
            if (variaveis.length > 6) {
                comp++;
                cidadeNascimento = variaveis[6];
            }
            if (variaveis.length > 7) {
                comp++;
                estadoNascimento = variaveis[7];
            }
            
            MyIO.print("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+"]\n");
            break;
            }
        }
        
        Arq.close();
    }
    
    public static void swap(int i, int j) 
    {
        String temp = nomes[i];
        nomes[i] = nomes[j]; 
        trocas++;
        nomes[j] = temp; 
        trocas++;
    }
    public static int compararString(String a, String b)
    {
        int retorno = 0;
        if (a!=null && b!=null) 
        {
            comp++;
            retorno = a.compareTo(b);
        }
        return retorno;
    }
    public static void ordenarNomes(int k) 
    {
        for (int i = 0; i < k - 1; i++) 
        {
            comp++;
            int menor = i;
            for (int j = (i + 1); j < k; j++) 
            {
                comp++;
                if (compararString(nomes[menor], nomes[j]) > 0) 
                {
                    comp++;
                    menor = j;
                }
            }
            swap(menor, i);
        }
    }
    public static void main(String[] args) throws IOException {
        long inicio = new Date().getTime();
        FileWriter file = new FileWriter("matrícula_selecao.txt");
        PrintWriter salva = new PrintWriter(file);

        String aff = "";
        int i = 0;
        while (!aff.equals("FIM") && i < ids.length) 
        {
            comp++;
            aff = MyIO.readLine();
            if (!aff.equals("FIM")) {
                ids[i] = Integer.parseInt(aff);
                gravaNomes(ids[i]);
                i++;
            }
        }

        ordenarNomes(i);

        for (i = 0; i < 10; i++) 
        {
            comp++;
            imprimir(nomes[i]);
        }

        Arq.close();

        long fim = new Date().getTime();
        salva.println("815324\t" + comp + trocas + "\t" + (fim - inicio) / 1000.0);
        file.close();
    }
}
