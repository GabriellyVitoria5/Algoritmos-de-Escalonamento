package algoritmosEscalonamento;

import java.util.List;
import trabalhopraticoso.Processo;

public abstract class AlgortimosEscalonamento {
    
    
    public abstract List<Processo> ordenarFilaProcesso(List<Processo> lista);
    
    public void imprimirFilaProcessos(List<Processo> lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.toString());
        }
    }
    
    public abstract float calcularTempoEsperaMedio(List<Processo> lista);
    
    public abstract float calcularTempoExecucaoMedio(List<Processo> lista);
    
    public abstract int calcularTrocasContexto(List<Processo> lista);
}
