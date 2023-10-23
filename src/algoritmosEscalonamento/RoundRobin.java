package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class RoundRobin extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados, int quantum) {

        List<Processo> filaProcessosFinal = new ArrayList<>(); //criando a fila de processos        
        List<Processo> filaEspera = new ArrayList<>(); //criando a fila de processos        
        int execucaoAtual = 0;

        //ordenar lista de processos com base no ingresso
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            for (int j = 0; j < listaProcessosInformados.size()-1; j++) {
                if(listaProcessosInformados.get(j).getIngresso() > listaProcessosInformados.get(j+1).getIngresso() ){
                    Collections.swap(listaProcessosInformados, j, j+1); //trocar 
                }
            }
        }
        
        //adicionar processos prontos na fila de espera
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getIngresso() <= execucaoAtual){
                filaEspera.add(listaProcessosInformados.get(i));
                listaProcessosInformados.remove(i);
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
                primeiroElemento.setInicioDuracao(execucaoAtual);
                execucaoAtual += quantum; 
                primeiroElemento.setFimDuracao(execucaoAtual);
                filaProcessosFinal.add(primeiroElemento); //adiciona na fila final de processos
                
                //enquanto o processo executava, algum outro processo entrou na fila?
                if(!listaProcessosInformados.isEmpty() && chegouProcessoNoProcessador(execucaoAtual, listaProcessosInformados.get(0))){
                    filaEspera.add(listaProcessosInformados.get(0));
                    listaProcessosInformados.remove(0);
                }
                
                if(primeiroElemento.getDuracao() != 0){
                    filaEspera.add(filaEspera.get(0));
                    filaEspera.remove(0);
                }
            }
            //2° caso) processo não precisa de todo o tempo disponível no quantum para terminar a execução
            else{
                
                //atualizar os valores do elemento antes de acrescentar na fila final
                primeiroElemento.setInicioDuracao(execucaoAtual);
                execucaoAtual += filaEspera.get(0).getDuracao();
                primeiroElemento.setFimDuracao(execucaoAtual);
                filaProcessosFinal.add(primeiroElemento);
                filaEspera.remove(0);
                
                //enquanto o processo executava, algum outro processo entrou na fila?
                if(!listaProcessosInformados.isEmpty() && chegouProcessoNoProcessador(execucaoAtual, listaProcessosInformados.get(0))){
                    filaEspera.add(listaProcessosInformados.get(0));
                    listaProcessosInformados.remove(0);
                }
            }
        }

        return filaProcessosFinal;
        
    }
    
    public boolean chegouProcessoNoProcessador(int tempoExecucao, Processo p) {
        return p.getIngresso() <= tempoExecucao;
    }
    
    //tempo de espera médio = (tempo em que o processo terminou - ingresso) - duração
    @Override
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados, List<Processo> filaOrdenadaFinal) {
        int fimDuracaoPorProcesso[] = new int[listaProcessosInformados.size()];
        float tempoEsperaMedio = 0;
        
        //descobrir quando o processo terminou de executar
        for (int i = 0; i < filaOrdenadaFinal.size(); i++) {
            int indiceNome = getIndicePeloNome(listaProcessosInformados, filaOrdenadaFinal.get(i).getNome());
            fimDuracaoPorProcesso[indiceNome] = filaOrdenadaFinal.get(i).getFimDuracao();
        }
        
        for (int i = 0; i < fimDuracaoPorProcesso.length; i++) {
            tempoEsperaMedio += ((fimDuracaoPorProcesso[i] - listaProcessosInformados.get(i).getIngresso()) - listaProcessosInformados.get(i).getDuracao());
        }
        
        return tempoEsperaMedio/listaProcessosInformados.size();
    }

    //descobrir qual a posição do processo na fila final ordenada dados os nomes dos processos informados na tabela
    public int getIndicePeloNome(List<Processo> listaProcessosInformados, String nome){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getNome().equalsIgnoreCase(nome)){
                return i;
            }
        }
        return -1;
    }

    
    //tempo de execução médio = tempo que o processo terminou - ingresso
    @Override
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados, List<Processo> filaOrdenadaFinal) {
        int fimDuracaoPorProcesso[] = new int[listaProcessosInformados.size()];
        float tempoExecucaoMedio = 0;
        
        //descobrir quando o processo terminou de executar
        for (int i = 0; i < filaOrdenadaFinal.size(); i++) {
            int indiceNome = getIndicePeloNome(listaProcessosInformados, filaOrdenadaFinal.get(i).getNome());
            fimDuracaoPorProcesso[indiceNome] = filaOrdenadaFinal.get(i).getFimDuracao();
        }
        
        for (int i = 0; i < fimDuracaoPorProcesso.length; i++) {
            tempoExecucaoMedio += (fimDuracaoPorProcesso[i] - listaProcessosInformados.get(i).getIngresso());
        }
        
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    //do modo como foi implementado, as trocas de contexto serão as mesmas do algoritmo SJF e prioridade preemptiva
    @Override
    public int calcularTrocasContexto(List<Processo> lista) {
        return lista.size()-1;
    }

}
