    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.Date;
    
    public class MergeSort {
        public static int comp = 0;
        public static int trocas = 0;
        public static int[] ids = new int[550];
        public static String[] unis = new String[550];
        public static int index = 0;
    
    //metodo que pega o id e grava os seus respectivos anos
        public static void gravaUni(int IDS) {
            Arq.openRead("/tmp/players.csv");
            Arq.readLine();
        
            while (Arq.hasNext()) {
                String linha = Arq.readLine();
                String[] find = linha.split(",");
                int test = Integer.parseInt(find[0]);
        
                if (IDS == test) {
                    String anoteste = find[4];
                    anoteste += "," + find[1];
                    unis[index] = anoteste;
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
                while (!universidade.equals("nao informado")) {
                MyIO.print("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+"]\n");
                break;                    
                }

                }
            }
            
            Arq.close();
        }
        public static void imprimirNao(String teste)
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
                while (universidade.equals("nao informado")) {
                MyIO.print("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+anoNascimento+" ## "+universidade+" ## "+cidadeNascimento+" ## "+estadoNascimento+"]\n");
                break;                    
                }

                }
            }
            
            Arq.close();
        }
        public static void swap(int i, int j) 
        {
            String temp = unis[i];
            unis[i] = unis[j]; 
            trocas++;
            unis[j] = temp; 
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
        private static void ordenarUni(int esq, int dir) 
        {
                if (esq < dir){
                   int meio = (esq + dir) / 2;
                   ordenarUni(esq, meio);
                   ordenarUni(meio + 1, dir);
                   intercalar(esq, meio, dir);
                }
        }
        
        public static void intercalar(int esq, int meio, int dir) {
            int n1, n2, i, j, k;

            // Definir tamanho dos dois subarrays
            n1 = meio - esq + 1;
            n2 = dir - meio;
        
            String[] a1 = new String[n1 + 1];
            String[] a2 = new String[n2 + 1];
        
            // Inicializar primeiro subarray
            for (i = 0; i < n1; i++) {
                a1[i] = unis[esq + i];
            }
        
            // Inicializar segundo subarray
            for (j = 0; j < n2; j++) {
                a2[j] = unis[meio + j + 1];
            }
        
            // Intercalacao propriamente dita
            for (i = 0, j = 0, k = esq; k <= dir; k++) {
                if (i < n1 && (j >= n2 || a1[i].compareTo(a2[j]) <= 0)) {
                    unis[k] = a1[i++];
                } else {
                    unis[k] = a2[j++];
                }
            }
        }
        
        public static void main(String[] args) throws IOException {
            long inicio = new Date().getTime();
            FileWriter file = new FileWriter("matrícula_mergesort.txt");
            PrintWriter salva = new PrintWriter(file);
    
            String aff = "";
            int i = 0;
            while (!aff.equals("FIM") && i < ids.length) 
            {
                comp++;
                aff = MyIO.readLine();
                if (!aff.equals("FIM")) {
                    ids[i] = Integer.parseInt(aff);
                    gravaUni(ids[i]);
                    i++;
                }
            }
    
            ordenarUni(0, i-1);
    
            for (i = 0; i < unis.length; i++) 
            {
                comp++;
                imprimir(unis[i]);
            }
            for (i = 0; i < unis.length; i++) 
            {
                comp++;
                imprimirNao(unis[i]);
            }
    
            Arq.close();
    
            long fim = new Date().getTime();
            salva.println("815324\t" + comp + trocas + "\t" + (fim - inicio) / 1000.0);
            file.close();
        }
    }
     

