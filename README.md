# Trabalho Prático: Implementação de Algoritmos de Escalonamento

Este projeto tem como objetivo a implementação de quatro algoritmos de escalonamento de processos, sendo dois preemptivos e dois não preemptivos. O foco principal é exercitar a lógica desses algoritmos, garantindo sua correta execução e análise de desempenho. Além disso, o projeto pode ser acompanhado de uma interface gráfica para melhorar a interação com o usuário.

**Acesse a branch master para visualizar os arquivos.**

## Algoritmos Escolhidos
Os algoritmos de escalonamento escolhidos para este projeto foram:

1. **Round-Robin (Preemptivo)**  
   Um dos algoritmos de escalonamento preemptivo mais simples e amplamente utilizado. Ele distribui os processos em fatias de tempo iguais (quantum), alternando entre eles.

2. **Shortest Remaining Time First (Menor Tempo Restante Primeiro) - Preemptivo**  
   Uma variação preemptiva do Shortest Job First, onde o processo em execução pode ser interrompido se um processo com menor tempo restante chegar na fila.

3. **Shortest Job First (Menor Tarefa Primeiro) - Não Preemptivo**  
   Algoritmo não preemptivo que escolhe o processo com a menor duração para ser executado em primeiro lugar.

4. **Escalonamento por Prioridade Cooperativo (Não Preemptivo)**  
   Algoritmo não preemptivo onde os processos são escalonados com base na prioridade. Processos com maior prioridade são executados antes dos de menor prioridade.

## Funcionalidades da Interface Gráfica
O projeto oferece uma interface gráfica que permite ao usuário:

1. **Inserir parâmetros personalizados**  
   - Número de processadores.
   - Quantidade de processos na fila de prontos.
   - Ordem de chegada temporal dos processos.
   - Duração do tempo de processamento.
   - Prioridade (quando aplicável).
   - Quantum (para o algoritmo Round-Robin).
   - Outros parâmetros relevantes.

2. **Visualização dinâmica dos algoritmos**  
   A execução dos algoritmos é exibida graficamente ou por meio de uma saída no console, mostrando o comportamento dos processos ao longo do tempo.

3. **Resultado comparativo dos algoritmos**  
   Uma tabela comparativa é gerada ao final da execução, contendo:
   - Tempo médio de execução.
   - Tempo médio de espera.
   - Número de trocas de contexto.
