 class Player implements Cloneable
{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int ano;
    private String cidade;
    private String estado;

    Player()
    {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.ano = -1;
        this.nome = null;
        this.universidade = null;
        this.cidade = null;
        this.estado = null;
    }
    Player (int a, String w, int b, int c, String x, int d, String y, String z)
    {
        this.id = a;
        this.altura = b;
        this.peso = c;
        this.ano = d;
        this.nome = w;
        this.universidade = x;
        this.cidade = y;
        this.estado = z;
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
        this.ano = ano;
    }
    
    public int getAno() {
        return ano;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public Object clonarPlayer(Player p) throws CloneNotSupportedException
    {
        Player clonez = (Player) p.clone();

        return clonez;
    }
}
