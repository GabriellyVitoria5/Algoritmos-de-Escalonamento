package comparator;

import java.util.Comparator;
import trabalhopraticoso.Processo;

public class CompararIngresso implements Comparator<Processo>{
    //ordenar os processos de forma crescente de acordo com seu ingresso 
    @Override
    public int compare(Processo p1, Processo p2) {
        if(p1.getIngresso()> p2.getIngresso()){
            return 1;
        }
        return -1;
    }
}
