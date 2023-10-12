package algoritmosEscalonamento;

import java.util.List;
import trabalhopraticoso.Processo;

public abstract class AlgortimosEscalonamento {
    
    
    public abstract List<Processo> ordenarFilaProcesso(List<Processo> lista);
    
    public void imprimirFilaProcessos(List<Processo> lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        System.out.println(""); //quebra de linha
    }
    
    public float calcularTempoEsperaMedio(List<Processo> lista){
        int tempoExecucaoAtual = 0;
        float tempoEsperaMedio = 0;
        int quantidadeProcessos = lista.size();
        for (int i = 0; i < lista.size(); i++) {
            tempoEsperaMedio += (lista.get(i).getDuracao() + tempoExecucaoAtual) - lista.get(i).getIngresso();
            tempoExecucaoAtual += lista.get(i).getDuracao(); //atualizar tempo execução atual
        }
        return tempoEsperaMedio/quantidadeProcessos;
    }
    
    public float calcularTempoExecucaoMedio(List<Processo> lista){
        int tempoExecucaoAtual = 0;
        float tempoExecucaoMedio = 0;
        int quantidadeProcessos = lista.size();
        for (int i = 0; i < lista.size(); i++) {
            tempoExecucaoMedio += tempoExecucaoAtual - lista.get(i).getIngresso();
            tempoExecucaoAtual += lista.get(i).getDuracao(); //atualizar tempo execução atual
        }
        return tempoExecucaoMedio/quantidadeProcessos;
    }
    
    public abstract int calcularTrocasContexto(List<Processo> lista);
}
