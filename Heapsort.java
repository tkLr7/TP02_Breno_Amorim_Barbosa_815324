import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Heapsort {
    public static int comp = 0;
    public static int trocas = 0;
    public static int[] ids = new int[550];
    public static String[] alturas = new String[550];
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
                String anoteste = find[2];
                anoteste += "," + find[1];
                alturas[index] = anoteste;
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
        String temp = alturas[i];
        alturas[i] = alturas[j]; 
        trocas++;
        alturas[j] = temp; 
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
    public static void construir(int tamHeap)
    {
        for(int i = tamHeap; i > 1 && compararString(alturas[i], alturas[i/2]) > 0; i /= 2){
           swap(i, i/2);
        }
     }
  
  
     public static void reconstruir(int tamHeap){
        int i = 1;
        while(i <= (tamHeap/2)){
           int filho = getMaiorFilho(i, tamHeap);
           if(compararString(alturas[filho], alturas[i]) > 0){
              swap(i, filho);
              i = filho;
           }else{
              i = tamHeap;
           }
        }
     }
  
     public static int getMaiorFilho(int i, int tamHeap){
        int filho;
        if (2*i == tamHeap || compararString(alturas[2*i], alturas[2*i+1]) > 0 ){
           filho = 2*i;
        } else {
           filho = 2*i + 1;
        }
        return filho;
     }
  
    //metodo pra ordenar
    public static void ordenarNomes(int k) 
    {
        Integer n = alturas.length;
        String[] tmp = new String[n+1];
        for(int i = 0; i < n; i++){
           tmp[i+1] = alturas[i];
        }
        alturas = tmp;
  
        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
           construir(tamHeap);
        }
  
        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
           swap(1, tamHeap--);
           reconstruir(tamHeap);
        }
  
        //Alterar o vetor para voltar a posicao zero
        tmp = alturas;
        alturas = new String[n];
        for(int i = 0; i < n; i++){
           alturas[i] = tmp[i+1];
        }
	}

    public static void main(String[] args) throws IOException {
        long inicio = new Date().getTime();
        FileWriter file = new FileWriter("matrícula_heapsort.txt");
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

        for (i = 0; i < alturas.length; i++) 
        {
            comp++;
            imprimir(alturas[i]);
        }

        Arq.close();

        long fim = new Date().getTime();
        salva.println("815324\t" + comp + trocas + "\t" + (fim - inicio) / 1000.0);
        file.close();
    }
}
