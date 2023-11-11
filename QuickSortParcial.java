import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class QuickSortParcial {
    public static int comp = 0;
    public static int trocas = 0;
    public static int[] ids = new int[550];
    public static String[] estado = new String[4000];
    public static int index = 0;


    public static void gravaNomes(int IDS) {
        Arq.openRead("/tmp/players.csv");
        Arq.readLine();
    
        while (Arq.hasNext()) {
            String linha = Arq.readLine();
            String[] find = linha.split(",");
            int test = Integer.parseInt(find[0]);
    
            if (IDS == test) 
            {
                if(find.length < 7)
                {
                    estado[index] = "nao informado";
                    estado[index] += "," + find[1];
                    index++;
                    break;
                }
                else
                {
                    estado[index] = find[7];
                    estado[index] += "," + find[1];
                    index++;
                    break;
                }
            }
        }
    
        Arq.close();
    }
    public static void imprimir(String teste)
    {   
        Arq.openRead("/tmp/players.csv");

        Arq.readLine();

        String[] variaveis = null;
        String[] variaveisteste = null;
        
        while (Arq.hasNext()) 
        {
            comp++;
            
            String linha = Arq.readLine();
            variaveis = linha.split(",");
            variaveisteste = teste.split(",");
            String nome = variaveis[1];
            String nometeste = variaveisteste[1];
            if (nome.equals(nometeste)) 
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
        String temp = estado[i];
        estado[i] = estado[j]; 
        trocas++;
        estado[j] = temp; 
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
    public static void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        String pivo = estado[(dir+esq)/2];
        while (i <= j) {
            while (i <= dir && compararString(estado[i], pivo) < 0) i++;
            while (j >= esq && compararString(estado[j], pivo) > 0) j--;
            if (i <= j) {
                swap(i, j);
                trocas++;
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(esq, j);
        if (i < dir)  quicksort(i, dir);
    }

    public static void main(String[] args) throws IOException {


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
        
        quicksort(0, i-1);

        for (i = 0; i < 10; i++) 
        {
            comp++;
            imprimir(estado[i]);
        }

        Arq.close();

    }
}
