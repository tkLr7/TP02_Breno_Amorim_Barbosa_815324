import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Insercao {
    public static int comp = 0;
    public static int trocas = 0;
    public static int[] ids = new int[550];
    public static String[] anos = new String[550];
    public static int index = 0;

//metodo que pega o id e grava os seus respectivos anos
    public static void gravaAno(int IDS) {
        Arq.openRead("/tmp/players.csv");
        Arq.readLine();
    
        while (Arq.hasNext()) {
            String linha = Arq.readLine();
            String[] find = linha.split(",");
            int test = Integer.parseInt(find[0]);
    
            if (IDS == test) {
                String anoteste = find[5];
                anoteste += "," + find[1];
                anos[index] = anoteste;
                index++;
            }
        }
    
        Arq.close();
    }
    //metodo para imprimir as linhas do arquivo pra cada ano ordenado
    public static void imprimir(String teste)
    {   
        if (teste == null) {
            // Handle the case when 'teste' is null, for example, by returning or throwing an exception.
            return;
        }
        Arq.openRead("/tmp/players.csv");

        Arq.readLine();

        String[] variaveis = null;
        String[] separarTest = null;
        while (Arq.hasNext()) 
        {
            comp++;
            
            String linha = Arq.readLine();
            variaveis = linha.split(",");
            separarTest = teste.split(",");
            String nome1 = variaveis[1];
            String nome2 = separarTest[1]; 
            if (nome1.equals(nome2)) 
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
            String nome = variaveis[1];
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
        String temp = anos[i];
        anos[i] = anos[j]; 
        trocas++;
        anos[j] = temp; 
        trocas++;
    }
    public static int compararString(String a, String b)
    {
        int retorno = 0;
        if (a != null && b != null) 
        {
            comp++;
            retorno = a.compareTo(b);
        }
        return retorno;
    }
    //metodo pra ordenar
    public static void ordenarNomes(int k) 
    {
		for (int i = 1; i < anos.length; i++) {
		String tmp = anos[i];
        int j = i - 1;

         while ((j >= 0) && (compararString(anos[j], tmp) > 0)) {
            anos[j + 1] = anos[j];
            j--;
        }
         anos[j + 1] = tmp;
      }
	}

    public static void main(String[] args) throws IOException {
        long inicio = new Date().getTime();
        FileWriter file = new FileWriter("matrícula_insercao.txt");
        PrintWriter salva = new PrintWriter(file);

        String aff = "";
        int i = 0;
        while (!aff.equals("FIM") && i < ids.length) 
        {
            comp++;
            aff = MyIO.readLine();
            if (!aff.equals("FIM")) {
                ids[i] = Integer.parseInt(aff);
                gravaAno(ids[i]);
                i++;
            }
        }

        ordenarNomes(i);

        for (i = 0; i < anos.length; i++) 
        {
            comp++;
            imprimir(anos[i]);
        }

        Arq.close();

        long fim = new Date().getTime();
        salva.println("815324\t" + comp + trocas + "\t" + (fim - inicio) / 1000.0);
        file.close();
    }
}

