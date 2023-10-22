package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadePreemptivo extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados) {
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos         
        int execucaoAtual = 0;

        while (!listaProcessosInformados.isEmpty()) {

            for (int j = 0; j < listaProcessosInformados.size() - 1; j++) {

                //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                if (listaProcessosInformados.get(j).getIngresso() <= execucaoAtual || listaProcessosInformados.get(j + 1).getIngresso() <= execucaoAtual) {

                    //se ambos estão disputando o processador, é preciso verificar quem tem maior prioridade
                    if (listaProcessosInformados.get(j).getPrioridade() < listaProcessosInformados.get(j + 1).getPrioridade() && (listaProcessosInformados.get(j).getIngresso() <= execucaoAtual)) {
                        Collections.swap(listaProcessosInformados, j, j + 1); //trocar
                    }

                }
            }

            //evitar um erro
            if (listaProcessosInformados.get(0).getIngresso() > execucaoAtual) {
                Collections.swap(listaProcessosInformados, 0, 1); //trocar
            }
            
            Processo temp = null;
            int contDuracao = 1, duracaoProcesso = listaProcessosInformados.get(0).getDuracao();
            while (contDuracao <= duracaoProcesso ) {
                if (chegouProcessoComMaiorPrioridade(listaProcessosInformados, listaProcessosInformados.get(0), execucaoAtual)) {
                    break;
                }
                
                temp = new Processo(listaProcessosInformados.get(0));
                
                listaProcessosInformados.get(0).setDuracao((listaProcessosInformados.get(0).getDuracao() - 1));
                temp.setDuracao(contDuracao);
                execucaoAtual += 1;
                contDuracao++;
            }

            //System.out.println(lista);

            //fazer modificação aqui
            if (listaProcessosInformados.get(0).getDuracao() >= 0) {
                temp.setFimDuracao(execucaoAtual);
                novaFilaProcessos.add(temp);
            } 
            /*else {
                novaFilaProcessos.add(lista.get(0));
                System.out.println(7);
            }*/
            
            //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, sua posição na fila já está correta
            if (listaProcessosInformados.get(0).getDuracao() <= 0) {
                listaProcessosInformados.remove(0);
            }
        }

        return novaFilaProcessos;
    }

    public boolean chegouProcessoComMaiorPrioridade(List<Processo> listaProcessosInformados, Processo p, int tempoExecucao) {
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if (listaProcessosInformados.get(i).getIngresso() <= tempoExecucao && listaProcessosInformados.get(i).getPrioridade() > p.getPrioridade()) {
                return true;
            }
        }
        return false;
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

    //descobrir qual a posição do processo na fila final ordenada dados os nomes dos processos informados na tabela
    public int getIndicePeloNome(List<Processo> listaProcessosInformados, String nome){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getNome().equalsIgnoreCase(nome)){
                return i;
            }
        }
        return -1;
    }
    
    //do modo como foi implementado, as trocas de contexto serão as mesmas do algoritmo SJF e prioridade preemptiva
    @Override
    public int calcularTrocasContexto(List<Processo> listaProcessosInformados) {
        return listaProcessosInformados.size()-1;
    }
}


