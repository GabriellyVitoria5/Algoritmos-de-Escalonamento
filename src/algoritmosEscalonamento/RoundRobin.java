package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class RoundRobin extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {

        List<Processo> filaProcessosFinal = new ArrayList<>(); //criando a fila de processos        
        List<Processo> filaEspera = new ArrayList<>(); //criando a fila de processos        
        int execucaoAtual = 0;

        //ordenar lista de processos com base no ingresso
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size()-1; j++) {
                if(lista.get(j).getIngresso() > lista.get(j+1).getIngresso() ){
                    Collections.swap(lista, j, j+1); //trocar 
                }
            }
        }
        
        //adicionar processos prontos na fila de espera
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getIngresso() <= execucaoAtual){
                filaEspera.add(lista.get(i));
                lista.remove(i);
                i--;
            }
        }
        
        while (!filaEspera.isEmpty()) {
            
            //sempre o primeiro elemento já está na posição correta
            Processo primeiroElemento = new Processo(filaEspera.get(0));
            
            //1° caso) processo tem durção maior que o quantum, ou seja, precisa ser executado por um tempo e depois voltar para a lista de espera
            if(primeiroElemento.getDuracao() > quantum){
                
                //atualizar os valores do elemento antes de acrescentar na fila final, e decrementar o seu tempo de duração
                primeiroElemento.setDuracao(quantum);
                filaEspera.get(0).setDuracao((filaEspera.get(0).getDuracao() - quantum));
                execucaoAtual += quantum;
                primeiroElemento.setFimDuracao(execucaoAtual);
                filaProcessosFinal.add(primeiroElemento); //adiciona na fila final de processos
                
                //enquanto o processo executava, algum outro processo entrou na fila?
                if(!lista.isEmpty() && chegouProcessoNoProcessador(execucaoAtual, lista.get(0))){
                    filaEspera.add(lista.get(0));
                    lista.remove(0);
                }
                
                if(primeiroElemento.getDuracao() != 0){
                    filaEspera.add(filaEspera.get(0));
                    filaEspera.remove(0);
                }
            }
            //2° caso) processo não precisa de todo o tempo disponível no quantum para terminar a execução
            else{
                
                //atualizar os valores do elemento antes de acrescentar na fila final
                execucaoAtual += filaEspera.get(0).getDuracao();
                primeiroElemento.setFimDuracao(execucaoAtual);
                filaProcessosFinal.add(primeiroElemento);
                filaEspera.remove(0);
                
                //enquanto o processo executava, algum outro processo entrou na fila?
                if(!lista.isEmpty() && chegouProcessoNoProcessador(execucaoAtual, lista.get(0))){
                    filaEspera.add(lista.get(0));
                    lista.remove(0);
                }
            }
        }

        return filaProcessosFinal;
        
    }
    
    public boolean chegouProcessoNoProcessador(int tempoExecucao, Processo p) {
        return p.getIngresso() <= tempoExecucao;
    }

    //tempo de espera médio = (tempo em que o processo terminou - ingresso) - duração
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados, List<Processo> filaFinalOrdenada) {
        int fimDuracaoPorProcesso[] = new int[listaProcessosInformados.size()];
        float tempoEsperaMedio = 0;
        
        for (int i = 0; i < filaFinalOrdenada.size(); i++) {
            int indiceNome = getIndicePeloNome(listaProcessosInformados, filaFinalOrdenada.get(i).getNome());
            fimDuracaoPorProcesso[indiceNome] = filaFinalOrdenada.get(i).getFimDuracao();
        }
        
        for (int i = 0; i < fimDuracaoPorProcesso.length; i++) {
            tempoEsperaMedio += ((fimDuracaoPorProcesso[i] - listaProcessosInformados.get(i).getIngresso()) - listaProcessosInformados.get(i).getDuracao());
        }
        
        return tempoEsperaMedio/listaProcessosInformados.size();
    }
    
    public int getIndicePeloNome(List<Processo> listaProcessosInformados, String nome){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getNome().equalsIgnoreCase(nome)){
                return i;
            }
        }
        return -1;
    }
    
    //tempo de execução médio = tempo que o processo terminou - ingresso
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados, List<Processo> filaFinalOrdenada) {
        int fimDuracaoPorProcesso[] = new int[listaProcessosInformados.size()];
        float tempoExecucaoMedio = 0;
        
        for (int i = 0; i < filaFinalOrdenada.size(); i++) {
            int indiceNome = getIndicePeloNome(listaProcessosInformados, filaFinalOrdenada.get(i).getNome());
            fimDuracaoPorProcesso[indiceNome] = filaFinalOrdenada.get(i).getFimDuracao();
        }
        
        for (int i = 0; i < fimDuracaoPorProcesso.length; i++) {
            tempoExecucaoMedio += (fimDuracaoPorProcesso[i] - listaProcessosInformados.get(i).getIngresso());
        }
        
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }

    @Override
    public int calcularTrocasContexto(List<Processo> lista) {
        return lista.size()-1;
    }

}
