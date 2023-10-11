package algoritmosEscalonamento;

import comparator.CompararDuracao;
import comparator.CompararIngresso;
import java.util.Collections;
import java.util.List;
import trabalhopraticoso.Processo;

public class MenorTarefa extends AlgortimosEscalonamento{

    private int trocaContexto = 0;
    private int tempoExecucaoAtual;
    private int tempoExecucaoMedio;
    private float tempoEsperaMedio;
    
    public MenorTarefa() {
        
    }

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista) {
        //1° caso: todos os processos chegaram ao mesmo tempo
        Collections.sort(lista, new CompararDuracao()); //ordenar processor pela duração
        
        int controle = 0;
        for (int i = 0; i < lista.size()-1; i++) {
            if(lista.get(i).getIngresso() != lista.get(i+1).getIngresso()){
                controle++;
            }
        }

        //2° caso: processos que chegaram depois podem executar primeiro do que processos que chegaram antes se forem menores
        if(controle != 0){ 
            Collections.sort(lista, new CompararIngresso()); //ordenar de acordo com o ingresso
            int tempoExecucaoAtual = 0;
            
            for (int i = 0; i < lista.size()-1; i++) {
                for (int j = 0; j < lista.size()-1; j++) {
                    
                    //comparar se dois processos podem estão prontos para serem escolhidos pelo processador, se já chegaram no processador
                    if(lista.get(j).getIngresso() <= tempoExecucaoAtual && lista.get(j+1).getIngresso() <= tempoExecucaoAtual){
                        
                        //se ambos estão disputando o processador, é preciso verificar quem é menor
                        if(lista.get(j).getDuracao() > lista.get(j+1).getDuracao()){
                            Collections.swap(lista, j, j+1); //trocar 
                            trocaContexto++;
                        }  
                    }
                    
                }
                
                //atualizar o tempo de execução atual do processador
                tempoExecucaoAtual+= lista.get(i).getDuracao();
                
            }
        }
        
        return lista;
    }

    @Override
    public float calcularTempoEsperaMedio(List<Processo> lista) {
        tempoExecucaoAtual = 0;
        int quantidadeProcessos = lista.size();
        for (int i = 0; i < lista.size(); i++) {
            tempoEsperaMedio += (lista.get(i).getDuracao() + tempoExecucaoAtual) - lista.get(i).getIngresso();
            tempoExecucaoAtual += lista.get(i).getDuracao();
        }
        return tempoEsperaMedio/quantidadeProcessos;
    }

    @Override
    public float calcularTempoExecucaoMedio(List<Processo> lista) {
        return 0;
    }
    
    
}
