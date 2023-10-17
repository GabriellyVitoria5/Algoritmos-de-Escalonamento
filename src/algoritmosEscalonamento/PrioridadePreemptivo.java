package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class PrioridadePreemptivo extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {
        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos         
        int execucaoAtual = 0;

        while (!lista.isEmpty()) {

            //System.out.println(execucaoAtual);

            for (int j = 0; j < lista.size() - 1; j++) {

                //comparar se pelo menos processos está pronto para serem escolhido pelo processador, se já chegou no processador
                if (lista.get(j).getIngresso() <= execucaoAtual || lista.get(j + 1).getIngresso() <= execucaoAtual) {

                    //se ambos estão disputando o processador, é preciso verificar quem é menor
                    if (lista.get(j).getPrioridade() < lista.get(j + 1).getPrioridade() && (lista.get(j).getIngresso() <= execucaoAtual)) {
                        Collections.swap(lista, j, j + 1); //trocar
                    }

                }
            }

            //evitar um erro
            if (lista.get(0).getIngresso() > execucaoAtual) {
                Collections.swap(lista, 0, 1); //trocar
            }

            //teste
            Processo temp = null;
            int contQuantum = 1;
            while (contQuantum <= quantum && lista.get(0).getDuracao() > 0) {
                //verificar se algum processo entrou no processador nesse meio tempo organizando a lista novamente
                if (chegouNoProcessador(lista, lista.get(0), execucaoAtual)) {
                    //novaFilaProcessos.add(temp);
                    break;
                }

                temp = new Processo(lista.get(0));

                //atualizar a duração da tarefa removendo o tempo que ela já ficou no processador
                lista.get(0).setDuracao((lista.get(0).getDuracao() - 1));
                temp.setDuracao(contQuantum);
                execucaoAtual += 1;

                contQuantum++;
            }

            //System.out.println(lista);

            if (lista.get(0).getDuracao() >= 0) {
                novaFilaProcessos.add(temp);
            } else {
                novaFilaProcessos.add(lista.get(0));
            }
            
            //o primeiro processo é retirado da lista pois ele não precisa ser comparado com mais ninguém, sua posição na fila já está correta
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

    //tem coisa errada aqui
    @Override
    public float calcularTempoEsperaMedio(List<Processo> lista) {
        int tempoExecucaoAtual;
        int tempoEspertaPorProcesso[] = new int[5];
        float tempoEsperaMedio = 0;
        int quantidadeProcessos = lista.size();
        for (int i = 0; i < lista.size(); i++) {
            tempoExecucaoAtual = 0;
            for (int j = 0; j < lista.size(); j++) {
                if(lista.get(i).getNome().equalsIgnoreCase(lista.get(j).getNome()) && lista.get(j).getIngresso() <= tempoExecucaoAtual){
                    tempoEspertaPorProcesso[i] += lista.get(j).getDuracao();
                }
                tempoExecucaoAtual += lista.get(i).getDuracao(); //atualizar tempo execução atual
            }

            //tempoEsperaMedio += (lista.get(i).getDuracao() + tempoExecucaoAtual) - lista.get(i).getIngresso();
            //tempoExecucaoAtual += lista.get(i).getDuracao(); //atualizar tempo execução atual
        }
        
        for (int i = 0; i < tempoEspertaPorProcesso.length; i++) {
            System.out.println(tempoEspertaPorProcesso[i]);
        }
        
        return tempoEsperaMedio/quantidadeProcessos;
    }

    @Override
    public float calcularTempoExecucaoMedio(List<Processo> lista) {
        int tempoExecucaoAtual = 0;
        float tempoExecucaoMedio = 0;
        int quantidadeProcessos = lista.size();
        for (int i = 0; i < lista.size(); i++) {
            tempoExecucaoMedio += tempoExecucaoAtual - lista.get(i).getIngresso();
            tempoExecucaoAtual += lista.get(i).getDuracao(); //atualizar tempo execução atual
        }
        return tempoExecucaoMedio/quantidadeProcessos;
    }

    @Override
    public int calcularTrocasContexto(List<Processo> lista) {
        return lista.size()-1;
    }

}
