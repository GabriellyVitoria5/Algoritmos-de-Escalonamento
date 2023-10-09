package trabalhopraticoso;

public class Processo {
    
    private String nome;
    private float ingresso;
    private float duracao;
    private int prioridade;

    public Processo() {
    }

    public Processo(String nome, float ingresso, float duracao, int prioridade) {
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

    public float getIngresso() {
        return ingresso;
    }

    public void setIngresso(float ingresso) {
        this.ingresso = ingresso;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
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
