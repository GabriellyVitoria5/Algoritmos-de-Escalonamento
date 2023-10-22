package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadePreemptivo extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista) {
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos         
        int execucaoAtual = 0;

        while (!lista.isEmpty()) {

            for (int j = 0; j < lista.size() - 1; j++) {

                //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                if (lista.get(j).getIngresso() <= execucaoAtual || lista.get(j + 1).getIngresso() <= execucaoAtual) {

                    //se ambos estão disputando o processador, é preciso verificar quem tem maior prioridade
                    if (lista.get(j).getPrioridade() < lista.get(j + 1).getPrioridade() && (lista.get(j).getIngresso() <= execucaoAtual)) {
                        Collections.swap(lista, j, j + 1); //trocar
                    }

                }
            }

            //evitar um erro
            if (lista.get(0).getIngresso() > execucaoAtual) {
                Collections.swap(lista, 0, 1); //trocar
            }
            
            Processo temp = null;
            int contDuracao = 1, duracaoProcesso = lista.get(0).getDuracao();
            while (contDuracao <= duracaoProcesso ) {
                if (chegouProcessoComMaiorPrioridade(lista, lista.get(0), execucaoAtual)) {
                    break;
                }
                
                temp = new Processo(lista.get(0));
                
                lista.get(0).setDuracao((lista.get(0).getDuracao() - 1));
                temp.setDuracao(contDuracao);
                execucaoAtual += 1;
                contDuracao++;
            }

            //System.out.println(lista);

            //fazer modificação aqui
            if (lista.get(0).getDuracao() >= 0) {
                temp.setFimDuracao(execucaoAtual);
                novaFilaProcessos.add(temp);
            } 
            /*else {
                novaFilaProcessos.add(lista.get(0));
                System.out.println(7);
            }*/
            
            //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, sua posição na fila já está correta
            if (lista.get(0).getDuracao() <= 0) {
                lista.remove(0);
            }
        }

        return novaFilaProcessos;
    }

    public boolean chegouProcessoComMaiorPrioridade(List<Processo> lista, Processo p, int tempoExecucao) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIngresso() <= tempoExecucao && lista.get(i).getPrioridade() > p.getPrioridade()) {
                return true;
            }
        }
        return false;
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

    public int getIndicePeloNome(List<Processo> listaProcessosInformados, String nome){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getNome().equalsIgnoreCase(nome)){
                return i;
            }
        }
        return -1;
    }
    
    public boolean temProcessosNaFila(List<Processo> lista, int execucao){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getIngresso() <= execucao){
                return true;
            }
        }
        return false;
    }
}


