package trabalhopraticoso;

import java.util.Comparator;

public class CompararDuracao implements Comparator<Processo>{

    //ordenar os processos de forma crescente de acordo com sua duração 
    @Override
    public int compare(Processo p1, Processo p2) {
        if(p1.getDuracao()> p2.getDuracao()){
            return 1;
        }
        return -1;
    }
    
}
