package algoritmosEscalonamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import trabalhopraticoso.Processo;

public class RoundRobin extends AlgortimosEscalonamento {

    @Override
    public List<Processo> ordenarFilaProcesso(List<Processo> lista, int quantum) {
        int tamanhoBuffer = 3; // Tamanho do buffer

        List<Processo> novaFilaProcessos = new ArrayList<>(); //criando a fila de processos
        Queue<Processo> fila = new LinkedList<>();
        Queue<Processo> buffer = new LinkedList<>();
        int tempoTotal = 0;

        // Adicione os processos à fila
        /*
        fila.add(new Processo("P1", 5,));
        fila.add(new Processo("P2", 4));
        fila.add(new Processo("P3", 3));
        fila.add(new Processo("P4", 2));
        */
        
        for (int i = 0; i < lista.size(); i++) {
            fila.add(lista.get(i));
        }

        /*
        System.out.println("Simulação do Round Robin com Buffer");
        System.out.println("Quantum: " + quantum);
        System.out.println("Tamanho do Buffer: " + tamanhoBuffer);
        System.out.println("Processos na fila:");
        */

        /*
        for (Processo processo : fila) {
            System.out.println(processo.getNome() + " (Duração: " + processo.getDuracao() + " segundos)");
        }
        */

        while (!fila.isEmpty() || !buffer.isEmpty()) {
            Processo processo = fila.poll();
            novaFilaProcessos.add(processo);
            
            if (processo.getDuracao() <= quantum) {
                //System.out.println("Executando processo " + processo.getNome() + " por " + processo.getDuracao() + " segundos");
                tempoTotal += processo.getDuracao();
            } else {
                //System.out.println("Executando processo " + processo.getNome() + " por " + quantum + " segundos");
                tempoTotal += quantum;
                //processo.duracao -= quantum;
                processo.setDuracao(processo.getDuracao()-quantum);
                buffer.add(processo);
            }

            while (!buffer.isEmpty()) {
                Processo bufferProcesso = buffer.poll();
                fila.add(bufferProcesso);
            }
        }

        //System.out.println("Simulação concluída. Tempo Total: " + tempoTotal + " segundos");

        return novaFilaProcessos;
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
        return lista.size()-1;
    }

}
