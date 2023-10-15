package algoritmosEscalonamento;

import comparator.CompararIngresso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadePreemptivo extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos   
        List<Processo> filaEspera = new ArrayList<>(); //criando a fila de espera   
        int execucaoAtual = 0;
        
        while(!lista.isEmpty()) {
            
            System.out.println(execucaoAtual);
            
            organizarPrioridade(lista, execucaoAtual);
            
            //for (int i = 0; i < lista.size(); i++) {
                /*for (int j = 0; j < lista.size()-1; j++) {
                   
                    //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                    if(lista.get(j).getIngresso() <= execucaoAtual || lista.get(j+1).getIngresso() <= execucaoAtual){
                        
                        //se ambos estão disputando o processador, é preciso verificar quem é menor
                        if(lista.get(j).getPrioridade() < lista.get(j+1).getPrioridade() && (lista.get(j).getIngresso() <= execucaoAtual)){
                            Collections.swap(lista, j, j+1); //trocar
                            
                        } 
                        
                    }
                }*/
                
                System.out.println(lista);
            
                
                //teste
                /*Processo temp = null;
                int contQuantum = 1;
                while (contQuantum <= quantum && lista.get(0).getDuracao() > 0) { 
                    temp = new Processo(lista.get(0));
                    //atualizar a duração da tarefa removendo o tempo que ela já ficou no processador
                    lista.get(0).setDuracao((lista.get(0).getDuracao() - 1));
                    temp.setDuracao(contQuantum);
                    execucaoAtual += 1;
                    
                    
                    organizarPrioridade(lista, execucaoAtual);
                    
                    if(!lista.get(0).getNome().equalsIgnoreCase(lista.get(1).getNome()) && lista.size() > 1){
                        novaFilaProcessos.add(temp);
                        contQuantum = 0;
                    }
                    
                    contQuantum++;
                }
                
                if(lista.get(0).getDuracao() > 0){
                    novaFilaProcessos.add(temp);
                }*/
                
                //fim teste
                
                Processo temp = new Processo(lista.get(0));
                
                //atualizar a duração da tarefa removendo o tempo que ela já ficou no processador
                if(lista.get(0).getDuracao() > quantum){
                    lista.get(0).setDuracao((lista.get(0).getDuracao() - quantum));
                    temp.setDuracao(quantum);
                    execucaoAtual += quantum;
                }
                else{
                    execucaoAtual += temp.getDuracao();
                    lista.get(0).setDuracao(lista.get(0).getDuracao() - quantum);
                }
                
                System.out.println(lista);
                
                novaFilaProcessos.add(temp);


                //atualiza o tempo de execução atual do "processador"
                //execucaoAtual+= lista.get(i).getDuracao();
                
                //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, suo posição na fila já está correta
                if(lista.get(0).getDuracao() <= quantum){
                   lista.remove(0); 
                }
            //}
            
            
            
        }

        return novaFilaProcessos;
    }
    
    public void organizarPrioridade(List<Processo> lista, int execucaoAtual){
        for (int j = 0; j < lista.size()-1; j++) {

            //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
            if(lista.get(j).getIngresso() <= execucaoAtual || lista.get(j+1).getIngresso() <= execucaoAtual){

                //se ambos estão disputando o processador, é preciso verificar quem é menor
                if(lista.get(j).getPrioridade() < lista.get(j+1).getPrioridade() && (lista.get(j).getIngresso() <= execucaoAtual)){
                    Collections.swap(lista, j, j+1); //trocar

                } 
            }
        }
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
