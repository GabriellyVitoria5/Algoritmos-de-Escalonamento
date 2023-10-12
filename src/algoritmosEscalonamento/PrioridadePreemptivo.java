package algoritmosEscalonamento;

import comparator.CompararIngresso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadePreemptivo extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {
        Collections.sort(lista, new CompararIngresso()); //ordenar de acordo com o ingresso
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos        
        List<Processo> filaEspera = new ArrayList<>();
        int execucaoAtual = 0;
        
        //while(!lista.isEmpty()) {

            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < lista.size() - 1; j++) {

                    //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                    if (lista.get(j).getIngresso() <= execucaoAtual || lista.get(j + 1).getIngresso() <= execucaoAtual) {

                        //se ambos estão disputando o processador, é preciso verificar quem é menor
                        if (lista.get(j).getPrioridade() < lista.get(j + 1).getPrioridade() && (lista.get(j).getIngresso() <= execucaoAtual)) {
                            Collections.swap(lista, j, j + 1); //trocar

                        }

                    }
                }

                //o primeiro elemento da é enviado para a fila de processos e sua duração precisa ser alterada
                if(lista.get(i).getDuracao() >= quantum){
                    lista.get(i).setDuracao(lista.get(i).getDuracao() - quantum);
                }
                novaFilaProcessos.add(lista.get(i));
                filaEspera.add(lista.get(i));

                //atualiza o tempo de execução atual do "processador"
                execucaoAtual+= lista.get(i).getDuracao();

                //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, suo posição na fila já está correta
                lista.remove(i);


                //como um elemento foi tirado da lista, a variável i precisa atualizar para não comprometer o for
                i--;  
                
                
                
            }
            
            
            
        //}

        return novaFilaProcessos;
    }

    @Override
    public float calcularTempoEsperaMedio(List<Processo> lista) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float calcularTempoExecucaoMedio(List<Processo> lista) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int calcularTrocasContexto(List<Processo> lista) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
