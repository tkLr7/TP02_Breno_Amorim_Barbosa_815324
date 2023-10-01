 class Player 
{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Player()
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
    Player (int id, String nome, int altura, int peso, String univerdade, int anoNascimento, String cidadeNascimento, String estadoNascimento)
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
    
    @Override
    protected Player clone() throws CloneNotSupportedException
    {
        return (Player) super.clone();
    }

    public void imprimir()
    {
        MyIO.print(id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+universidade+" ## "+cidadeNascimento+ " ## "+estadoNascimento);
    }

    public void ler()
    {
        this.id = MyIO.readInt();

        this.nome = MyIO.readLine();

        this.altura = MyIO.readInt();

        this.peso = MyIO.readInt();

        this.universidade = MyIO.readLine();

        this.cidadeNascimento = MyIO.readLine();

        this.estadoNascimento = MyIO.readLine();
    }

}
