package algoritmosEscalonamento;

import java.util.List;
import trabalhopraticoso.Processo;

public abstract class AlgortimosEscalonamento {
    
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Processo> ordenarFilaProcesso(List<Processo> listaProcessosInformados, int quantum){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void imprimirFilaProcessos(List<Processo> listaProcessosInformados){
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            System.out.println(listaProcessosInformados.get(i).toString());
        }
        System.out.println(""); //quebra de linha
    }
    
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            tempoExecucaoMedio += (listaProcessosInformados.get(i).getFimDuracao() - listaProcessosInformados.get(i).getIngresso() - listaProcessosInformados.get(i).getDuracao());
        }
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    public float calcularTempoEsperaMedio(List<Processo> listaProcessosInformados, List<Processo> filaFinalOrdenada) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados){
        float tempoExecucaoMedio = 0;
        for (int i = 0; i < listaProcessosInformados.size(); i++) {
            tempoExecucaoMedio += listaProcessosInformados.get(i).getFimDuracao() - listaProcessosInformados.get(i).getIngresso();
        }
        return tempoExecucaoMedio/listaProcessosInformados.size();
    }
    
    public float calcularTempoExecucaoMedio(List<Processo> listaProcessosInformados, List<Processo> filaFinalOrdenada) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public abstract int calcularTrocasContexto(List<Processo> listaProcessosInformados);         
}
