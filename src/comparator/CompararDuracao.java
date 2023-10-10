package comparator;

import java.util.Comparator;
import trabalhopraticoso.Processo;

public class CompararDuracao implements Comparator<Processo>{

    //ordenar os processos de forma crescente de acordo com sua duração 
    @Override
    public int compare(Processo p1, Processo p2) {
        if(p1.getDuracao()> p2.getDuracao()){
            return 1;
        }
        return -1;
        
        // Comparar pela ordem de ingresso
        /*int resultadoOrdem = Integer.compare(p1.getIngresso(), p2.getIngresso());

        // Se a ordem de ingresso for diferente, retorna o resultado dessa comparação
        if (resultadoOrdem != 0) {
            return resultadoOrdem;
        } else {
            // Se a ordem de ingresso for a mesma, compara pela duração
            return Integer.compare(p1.getDuracao(), p2.getDuracao());
        }*/
    }
    
}
