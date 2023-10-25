package algoritmosEscalonamento;

import java.util.List;
import trabalhopraticoso.Processo;

public abstract class AlgortimosEscalonamento {
    
    //para algoritmos não preemptivos e prioridade preemptivo
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //para algoritmos que necessitam do quantum
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados, int quantum){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void imprimirFilaProcessos(List<Processo> listaProcessosInformados){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            System.out.println(listaProcessosInformados.get(i).toString());
        }
        System.out.println(""); //quebra de linha
    }
    
    //para algoritmos não preemptivos
    //tempo de espera médio = (tempo em que o processo terminou - ingresso) - duração
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            tempoExecucaoMedio += (listaProcessosInformados.get(i).getFimDuracao() - listaProcessosInformados.get(i).getIngresso() - listaProcessosInformados.get(i).getDuracao());
        }
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    //para processos preemptivos
    //tempo de espera médio = (tempo em que o processo terminou - ingresso) - duração
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
    
    //para algoritmos preemptivos 
    //tempo de execução médio = tempo que o processo terminou - ingresso
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            tempoExecucaoMedio += listaProcessosInformados.get(i).getFimDuracao() - listaProcessosInformados.get(i).getIngresso();
        }
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    //para algoritmos preemptivos 
    //tempo de execução médio = tempo que o processo terminou - ingresso
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados, List<Processo> filaOrdenadaFinal) {
        int fimDuracaoPorProcesso[] = new int[listaProcessosInformados.size()];
        float tempoExecucaoMedio = 0;
        
        //descobrir quando o processo terminou de executar de fato
        for (int i = 0; i < filaOrdenadaFinal.size(); i++) {
            int indiceNome = getIndicePeloNome(listaProcessosInformados, filaOrdenadaFinal.get(i).getNome());
            fimDuracaoPorProcesso[indiceNome] = filaOrdenadaFinal.get(i).getFimDuracao();
        }
        
        //somar o tempo de execução de cada um dos processos
        for (int i = 0; i < fimDuracaoPorProcesso.length; i++) {
            tempoExecucaoMedio += (fimDuracaoPorProcesso[i] - listaProcessosInformados.get(i).getIngresso());
        }
        
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    //encontrar o índice do processo que está na fila final ordenada dados os nomes dos processos informados na tabela
    public int getIndicePeloNome(List<Processo> listaProcessosInformados, String nome){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            if(listaProcessosInformados.get(i).getNome().equalsIgnoreCase(nome)){
                return i;
            }
        }
        return -1;
    }
    
    //do modo como foi implementado, as trcoas de contexto são definidas pela quantidade de processos dentro da lista - 1
    public int calcularTrocasContexto(List<Processo> listaProcessosInformados){
        return listaProcessosInformados.size()-1;
    }         
}
