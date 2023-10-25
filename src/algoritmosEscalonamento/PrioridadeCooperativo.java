package algoritmosEscalonamento;

import comparator.CompararDuracao;
import comparator.CompararIngresso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadeCooperativo extends AlgortimosEscalonamento{

    public PrioridadeCooperativo() {}
    
    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados) {
        
        //1° caso: todos os processos chegaram ao mesmo tempo e a prioridade é a mesma
        Collections.sort(listaProcessosInformados, new CompararDuracao()); //ordenar processos pela duração
        
        //confirmar se todos os processos chegaram juntos 
        int mesmoIngressoEPrioridade = 0;
        for (int i = 0; i < listaProcessosInformados.size()-1; i++) {
            if((listaProcessosInformados.get(i).getIngresso() != listaProcessosInformados.get(i+1).getIngresso()) || (listaProcessosInformados.get(i).getPrioridade()!= listaProcessosInformados.get(i+1).getPrioridade())){
                mesmoIngressoEPrioridade++;
            }
        }

        //2° caso: é preciso considerar o tempo de ingresso e aprioridade para ordenar a fila de processo
        if(mesmoIngressoEPrioridade != 0){ 
            
            List<Processo> filaProcessosFinal = new ArrayList<>();
            Collections.sort(listaProcessosInformados, new CompararIngresso()); //ordenar de acordo com o ingresso
            int execucaoAtual = 0;
            
            for (int i = 0; i < listaProcessosInformados.size(); i++) {
                for (int j = 0; j < listaProcessosInformados.size()-1; j++) {
                   
                    //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                    if(listaProcessosInformados.get(j).getIngresso() <= execucaoAtual || listaProcessosInformados.get(j+1).getIngresso() <= execucaoAtual){
                        
                        //se ambos estão disputando o processador, é preciso verificar quem é menor
                        if(listaProcessosInformados.get(j).getPrioridade() < listaProcessosInformados.get(j+1).getPrioridade() && (listaProcessosInformados.get(j).getIngresso() <= execucaoAtual)){
                            Collections.swap(listaProcessosInformados, j, j+1); //trocar
                            
                        } 
                    }
                }

                listaProcessosInformados.get(i).setInicioDuracao(execucaoAtual);
                
                //atualiza o tempo de execução atual do "processador"
                execucaoAtual+= listaProcessosInformados.get(i).getDuracao();
                
                //o primeiro elemento da lista já está na ordem certa e é enviado para a fila de processos
                listaProcessosInformados.get(i).setFimDuracao(execucaoAtual);
                filaProcessosFinal.add(listaProcessosInformados.get(i));
                
                //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, sua posição na fila já está correta
                listaProcessosInformados.remove(i);
                
                //como um elemento foi tirado da lista, a variável i precisa atualizar para não comprometer o for
                i--;   
            }
            return filaProcessosFinal;
        }
        else{
            //atualizando tempo em que o processo terminou de executar
            int execucao = 0;
            for (int i = 0; i < listaProcessosInformados.size(); i++) {
                if(i!=0){
                   execucao += listaProcessosInformados.get(i).getDuracao();
                   listaProcessosInformados.get(i).setFimDuracao(execucao); 
                }
                else{
                    listaProcessosInformados.get(i).setFimDuracao(listaProcessosInformados.get(i).getDuracao());
                    execucao += listaProcessosInformados.get(i).getDuracao();
                }
            }
            return listaProcessosInformados;
        }
        
    }
    
    //tempo de espera médio = (tempo em que o processo terminou - ingresso) - duração
    /*@Override
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            tempoExecucaoMedio += (listaProcessosInformados.get(i).getFimDuracao() - listaProcessosInformados.get(i).getIngresso() - listaProcessosInformados.get(i).getDuracao());
        }
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    //tempo de execução médio = tempo que o processo terminou - ingresso
    @Override
    public float calcularTempoExecucaoMedio(List<Processo> lista){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < lista.size(); i++) {
            tempoExecucaoMedio += lista.get(i).getFimDuracao() - lista.get(i).getIngresso();
        }
        return tempoExecucaoMedio/lista.size();
    }*/
}
