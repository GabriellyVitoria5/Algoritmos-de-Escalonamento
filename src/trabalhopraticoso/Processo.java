package trabalhopraticoso;

public class Processo {
    
    private String nome;
    private int ingresso;
    private int duracao;
    private int prioridade;

    public Processo() {
    }

    public Processo(String nome, int ingresso, int duracao, int prioridade) {
        this.nome = nome;
        this.ingresso = ingresso;
        this.duracao = duracao;
        this.prioridade = prioridade;
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

    @Override
    public String toString() {
        return "Processo " + nome + " / " + ingresso + " / " + duracao + " / " + prioridade;
    }
    
}
