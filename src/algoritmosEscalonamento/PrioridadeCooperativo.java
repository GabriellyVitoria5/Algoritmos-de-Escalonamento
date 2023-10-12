package algoritmosEscalonamento;

import comparator.CompararDuracao;
import comparator.CompararIngresso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadeCooperativo extends AlgortimosEscalonamento{

    public PrioridadeCooperativo() {
    
    }
    
    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista) {
        //1° caso: todos os processos chegaram ao mesmo tempo e a prioridade é a mesma
        Collections.sort(lista, new CompararDuracao()); //ordenar processor pela duração
        
        //confirmar se todos os processos chegaram juntos 
        int controle = 0;
        for (int i = 0; i < lista.size()-1; i++) {
            if((lista.get(i).getIngresso() != lista.get(i+1).getIngresso()) || (lista.get(i).getPrioridade()!= lista.get(i+1).getPrioridade())){
                controle++;
            }
        }
        
        //criando a fila de processos 
        List<Processo> novaFilaProcessos = new ArrayList<>();
        
        //2° caso: é preciso considerar o tempo de ingresso e aprioridade para ordenar a fila de processo
        if(controle != 0){ 
            Collections.sort(lista, new CompararIngresso()); //ordenar de acordo com o ingresso
            int execucaoAtual = 0;

            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < lista.size()-1; j++) {
                    
                    //comparar se dois processos podem estão prontos para serem escolhidos pelo processador, se já chegaram no processador
                    if(lista.get(j).getIngresso() <= execucaoAtual && lista.get(j+1).getIngresso() <= execucaoAtual){

                        //se ambos estão disputando o processador, é preciso verificar quem é menor
                        if(lista.get(j).getPrioridade()> lista.get(j+1).getPrioridade()){
                            Collections.swap(lista, j, j+1); //trocar 
                        }  
                    }
                }
                
                //o primeiro elemento da lista já está na ordem certa e é enviado para a fila de processos
                novaFilaProcessos.add(lista.get(i));
                
                //atualiza o tempo de execução atual do "processador"
                execucaoAtual+= lista.get(i).getDuracao();
                
                //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, suo posição na fila já está correta
                lista.remove(i);
                
                //como um elemento foi tirado da lista, a variável i precisa atualizar para não comprometer o for
                i--;   
            }
            return novaFilaProcessos;
        }
        else{
            return novaFilaProcessos;
        }
        
    }

    //semelhante ao SJF, na prioridade cooperativa a troca de contexto é o número de processos menos 1
    @Override
    public int calcularTrocasContexto(List<Processo> lista) {
        return lista.size()-1;
    }
    
}
