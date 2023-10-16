package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class RoundRobin extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos         
        int execucaoAtual = 0;

        
        
        while (!lista.isEmpty()) {

            System.out.println(lista);
            
            for (int j = 0; j < lista.size()-1; j++) {
                    
                //comparar se dois processos podem estão prontos para serem escolhidos pelo processador, se já chegaram no processador
                if(lista.get(j).getIngresso() <= execucaoAtual && lista.get(j+1).getIngresso() <= execucaoAtual){

                    //se ambos estão disputando o processador, é preciso verificar quem é menor
                    if(lista.get(j).getDuracao() > lista.get(j+1).getDuracao()){
                        Collections.swap(lista, j, j+1); //trocar 
                    }  
                }
            }
            
            System.out.println(lista);

            //evitar um erro
            /*if (lista.get(0).getIngresso() > execucaoAtual) {
                Collections.swap(lista, 0, 1); //trocar
            }*/

            //teste
            Processo temp = null;
            int contQuantum = 1;
            while (contQuantum <= quantum && lista.get(0).getDuracao() > 0) {
                //verificar se algum processo entrou no processador nesse meio tempo organizando a lista novamente
                if (chegouNoProcessador(lista, lista.get(0), execucaoAtual)) {
                    break;
                }

                temp = new Processo(lista.get(0));

                //atualizar a duração da tarefa removendo o tempo que ela já ficou no processador
                lista.get(0).setDuracao((lista.get(0).getDuracao() - 1));
                temp.setDuracao(contQuantum);
                execucaoAtual += 1;

                contQuantum++;
            }

            if (lista.get(0).getDuracao() > 0) {
                novaFilaProcessos.add(temp);
            } else {
                novaFilaProcessos.add(lista.get(0));
            }
            
            //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, suo posição na fila já está correta
            if (lista.get(0).getDuracao() <= quantum) {
                lista.remove(0);
            }
        }

        return novaFilaProcessos;
    }

    public boolean chegouNoProcessador(List<Processo> lista, Processo p, int tempoExecucao) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIngresso() <= tempoExecucao && lista.get(i).getPrioridade() > p.getPrioridade()) {
                return true;
            }
        }
        return false;
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
        return lista.size()-1;
    }

}
