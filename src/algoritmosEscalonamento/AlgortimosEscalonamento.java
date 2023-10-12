package algoritmosEscalonamento;

import java.util.List;
import trabalhopraticoso.Processo;

public abstract class AlgortimosEscalonamento {
    
    
    public List<Processo> ordenarFilaProcesso(List<Processo> lista){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void imprimirFilaProcessos(List<Processo> lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
        System.out.println(""); //quebra de linha
    }
    
    public abstract float calcularTempoEsperaMedio(List<Processo> lista);
    
    public abstract float calcularTempoExecucaoMedio(List<Processo> lista);
    
    public abstract int calcularTrocasContexto(List<Processo> lista);
}
