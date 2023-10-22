package trabalhopraticoso;

public class Processo implements Cloneable{
    
    private String nome;
    private int ingresso;
    private int duracao;
    private int prioridade;
    private int inicioDuracao;
    private int fimDuracao;

    public Processo() {
    }

    public Processo(String nome, int ingresso, int duracao, int prioridade) {
        this.nome = nome;
        this.ingresso = ingresso;
        this.duracao = duracao;
        this.prioridade = prioridade;
    }
    
    // Construtor de cópia
    public Processo(Processo outro) {
        this.nome = outro.nome;
        this.ingresso = outro.ingresso;
        this.duracao = outro.duracao;
        this.prioridade = outro.prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIngresso() {
        return ingresso;
    }

    public void setIngresso(int ingresso) {
        this.ingresso = ingresso;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getInicioDuracao() {
        return inicioDuracao;
    }

    public void setInicioDuracao(int inicioDuracao) {
        this.inicioDuracao = inicioDuracao;
    }

    public int getFimDuracao() {
        return fimDuracao;
    }

    public void setFimDuracao(int fimDuracao) {
        this.fimDuracao = fimDuracao;
    }
    
    @Override
    public String toString() {
        return "Processo " + nome + " | " + ingresso + " | " + duracao + " | " + prioridade + " | " + inicioDuracao + " | " + fimDuracao;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}