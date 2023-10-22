package trabalhopraticoso;

import algoritmosEscalonamento.MenorTarefa;
import algoritmosEscalonamento.PrioridadeCooperativo;
import algoritmosEscalonamento.PrioridadePreemptivo;
import algoritmosEscalonamento.RoundRobin;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AlgoritmoEscalonamentoGUI extends javax.swing.JFrame {
    
    public AlgoritmoEscalonamentoGUI() {
        initComponents();

        //Adicionando todos os radioButtons a um buttonGroup 
        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rbMenorTarefaPrimeiro);
        buttonGroup1.add(rbPrioridadeCooperativo);
        buttonGroup1.add(rbRoundRobin);
        buttonGroup1.add(rbPrioridadePreemptivo);
        
        //deixar um botão pré-selecionado
        rbRoundRobin.setSelected(true);

    }
    
    private static void criarLinhasTabela(String campoLinhas, JTable tabela){
        int numRows = 0;
        try {
            numRows = Integer.parseInt(campoLinhas);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
        }

        // Obtenha o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        // Remova todas as linhas existentes da tabela
        model.setRowCount(0);

        // Adicione as novas linhas à tabela
        for (int i = 0; i < numRows; i++) {
            model.addRow(new Object[]{});
        }
    }

    //validar dados da tabela para não deixar campos em branco
    private static boolean verificarTabela(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        int contLinhas = model.getRowCount();
        int contColunas = model.getColumnCount();

        for (int linha = 0; linha < contLinhas; linha++) {
            for (int coluna = 0; coluna < contColunas; coluna++) {
                Object valorCelula = model.getValueAt(linha, coluna);

                //verificar se a célula está vazia
                if (valorCelula == null || valorCelula.toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A colula na linha " + (linha + 1) + ", coluna " + (coluna + 1) + " está vazia!");
                    return false;
                }

                //verificar se as colunas 2 a 4 são números
                if (coluna != 0) {
                    try {
                        Integer.parseInt(valorCelula.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "A célula na linha " + (linha + 1) + ", coluna " + (coluna + 1) + " possui valor inválido!");
                        return false;
                    }
                }

            }
        }

        return true;
    }
    
    //criar uma lista com todos os processos informados na tabela
    public static List<Processo> getListaProcessos(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        int contLinhas = model.getRowCount();
        
        List<Processo> listaProcessos = new ArrayList<Processo>();

        //cada linha da tabela é um novo processo
        for (int linha = 0; linha < contLinhas; linha++) {
            String nome = model.getValueAt(linha, 0).toString();
            int ingresso = Integer.parseInt(model.getValueAt(linha, 1).toString());
            int duracao = Integer.parseInt(model.getValueAt(linha, 2).toString());
            int prioridade = Integer.parseInt(model.getValueAt(linha, 3).toString());

            Processo p = new Processo(nome, ingresso, duracao, prioridade);
            listaProcessos.add(p);
        }

        return listaProcessos;
    }
    
    //organizar a fila de processos de acordo com a menor duração
    public static void menorTerefaPrimeiro(List<Processo> listaProcessos){
        System.out.println("\n--------------------------------------------");
        System.out.println("Resultado do algoritmo Menor tarefa primeiro:\n");
        
        MenorTarefa menorTarefa = new MenorTarefa();
        List<Processo> filaOrdenada = menorTarefa.ordenarFilaProcesso(listaProcessos);
        //menorTarefa.imprimirFilaProcessos(lista);
        
        int execucao = 1;
        for (int i = 0; i < filaOrdenada.size(); i++) {
            System.out.print(filaOrdenada.get(i).getNome() + ": ");
            for (int j = 0; j < filaOrdenada.get(i).getDuracao(); j++) {
                // Imprime o caractere
                System.out.print(execucao + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                execucao++;
            }
            System.out.println("");
        }
        
        System.out.println("\nNome | Ingresso | Duração | Prioridade | Inicio execução | Fim execução");
        menorTarefa.imprimirFilaProcessos(filaOrdenada);
        System.out.println("Tempo de espera médio: " + menorTarefa.calcularTempoEsperaMedio(filaOrdenada) + "s");
        System.out.println("Tempo de execução médio: " + menorTarefa.calcularTempoExecucaoMedio(filaOrdenada)+ "s");
        System.out.println("Trocas de contexto: " + menorTarefa.calcularTrocasContexto(filaOrdenada));
        
        System.out.println("--------------------------------------------");
    }
    
    public static void prioridadeCooperativa(List<Processo> listaProcessos){
        System.out.println("\n--------------------------------------------");
        System.out.println("Resultado do algoritmo Prioridade cooperativa:\n");
        
        PrioridadeCooperativo prioridadeCooperativo = new PrioridadeCooperativo();
        List<Processo> filaOrdenada = prioridadeCooperativo.ordenarFilaProcesso(listaProcessos);
        //prioridadeCooperativo.imprimirFilaProcessos(lista);
        
        int execucao = 1;
        for (int i = 0; i < filaOrdenada.size(); i++) {
            System.out.print(filaOrdenada.get(i).getNome() + ": ");
            for (int j = 0; j < filaOrdenada.get(i).getDuracao(); j++) {
                // Imprime o caractere
                System.out.print(execucao + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                execucao++;
            }
            System.out.println("");
        }
        
        System.out.println("\nNome | Ingresso | Duração | Prioridade | Inicio execução | Fim execução");
        prioridadeCooperativo.imprimirFilaProcessos(filaOrdenada);
        
        System.out.println("Tempo de espera médio: " + prioridadeCooperativo.calcularTempoEsperaMedio(filaOrdenada) + "s");
        System.out.println("Tempo de execução médio: " + prioridadeCooperativo.calcularTempoExecucaoMedio(filaOrdenada)+ "s");
        System.out.println("Trocas de contexto: " + prioridadeCooperativo.calcularTrocasContexto(filaOrdenada));
        
        System.out.println("--------------------------------------------");
    }

    public static void roundRobin(List<Processo> listaProcessos, int quantum) throws CloneNotSupportedException{
        System.out.println("\n--------------------------------------------");
        System.out.println("Resultado do algoritmo Round-Robin:\n");

        List<Processo> listaProcessosInformados = new ArrayList<>();
        for (Processo elemento : listaProcessos) {
            listaProcessosInformados.add((Processo) elemento.clone());
        }
        
        RoundRobin roundRobin = new RoundRobin();
        List<Processo> filaOrdenada = roundRobin.ordenarFilaProcesso(listaProcessos, quantum);
        //roundRobin.imprimirFilaProcessos(filaOrdenada);
        
        int execucao = 1;
        for (int i = 0; i < filaOrdenada.size(); i++) {
            System.out.print(filaOrdenada.get(i).getNome() + ": ");
            for (int j = 0; j < filaOrdenada.get(i).getDuracao(); j++) {
                // Imprime o caractere
                System.out.print(execucao + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                execucao++;
            }
            System.out.println("");
        }
        
        System.out.println("\nNome | Ingresso | Duração | Prioridade | Inicio execução | Fim execução");
        roundRobin.imprimirFilaProcessos(filaOrdenada);
        System.out.println("Tempo de espera médio: " + roundRobin.calcularTempoEsperaMedio(listaProcessosInformados, filaOrdenada) + "s");
        System.out.println("Tempo de execução médio: " + roundRobin.calcularTempoExecucaoMedio(listaProcessosInformados, filaOrdenada)+ "s");
        System.out.println("Trocas de contexto: " + roundRobin.calcularTrocasContexto(filaOrdenada));
        
        System.out.println("--------------------------------------------");
    }
    
    public static void prioridadePreemptiva(List<Processo> listaProcessos) throws CloneNotSupportedException{
        System.out.println("\n--------------------------------------------");
        System.out.println("Resultado do algoritmo Prioridade Preemptiva:\n");
        
        List<Processo> listaProcessosInformados = new ArrayList<>();
        for (Processo elemento : listaProcessos) {
            listaProcessosInformados.add((Processo) elemento.clone());
        }
        
        PrioridadePreemptivo prioridadePreemptiva = new PrioridadePreemptivo();
        List<Processo> filaOrdenada = prioridadePreemptiva.ordenarFilaProcesso(listaProcessos);
        //prioridadePreemptiva.imprimirFilaProcessos(filaOrdenada);
        
        int execucao = 1;
        for (int i = 0; i < filaOrdenada.size(); i++) {
            System.out.print(filaOrdenada.get(i).getNome() + ": ");
            for (int j = 0; j < filaOrdenada.get(i).getDuracao(); j++) {
                // Imprime o caractere
                System.out.print(execucao + " ");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                execucao++;
            }
            System.out.println("");
        }
        
        System.out.println("\nNome | Ingresso | Duração | Prioridade | Inicio execução | Fim execução");
        prioridadePreemptiva.imprimirFilaProcessos(filaOrdenada);
        System.out.println("Tempo de espera médio: " + prioridadePreemptiva.calcularTempoEsperaMedio(listaProcessosInformados, filaOrdenada) + "s");
        System.out.println("Tempo de execução médio: " + prioridadePreemptiva.calcularTempoExecucaoMedio(listaProcessosInformados, filaOrdenada)+ "s");
        System.out.println("Trocas de contexto: " + prioridadePreemptiva.calcularTrocasContexto(filaOrdenada));
        
        System.out.println("--------------------------------------------");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbMenorTarefaPrimeiro = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rbRoundRobin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbPrioridadeCooperativo = new javax.swing.JRadioButton();
        rbPrioridadePreemptivo = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProcessos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuantProcessos = new javax.swing.JTextField();
        txtQuantum = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Trabalho Prático");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("* Escolha um dos algoritmo de escalonamento a seguir:");

        rbMenorTarefaPrimeiro.setText("Shortest job first (Menor tarefa primeiro)");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Não preemptivo:");

        rbRoundRobin.setText("Round-Robin");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Preemptivo:");

        rbPrioridadeCooperativo.setText("Escalonamento por Prioridade Cooperativo");

        rbPrioridadePreemptivo.setText("Escalonamento por Prioridade Preemptivo");

        tabelaProcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P1", "0", "5", "2"},
                {"P2", "0", "2", "3"},
                {"P3", "1", "4", "1"},
                {"P4", "3", "1", "4"},
                {"P5", "5", "2", "5"}
            },
            new String [] {
                "Nome", "Ingresso", "Duração", "Prioridade"
            }
        ));
        jScrollPane1.setViewportView(tabelaProcessos);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("* Adicione a quantidade de processos e quantum desejados:");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel6.setText("Quantum:");

        jLabel7.setText("* Por padrão números maiores indicam maior prioridade");

        jLabel8.setText("Alterar quantidade de processos:");

        txtQuantum.setText("2");

        jButton1.setText("Alterar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbMenorTarefaPrimeiro)
                                    .addComponent(rbPrioridadeCooperativo))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbRoundRobin)
                                    .addComponent(rbPrioridadePreemptivo)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtQuantProcessos)
                                    .addComponent(txtQuantum, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(btnIniciar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rbMenorTarefaPrimeiro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbPrioridadeCooperativo))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbRoundRobin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbPrioridadePreemptivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtQuantProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIniciar)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        // chamando método para verificar se as informações da tabela são válidas
        if (verificarTabela(tabelaProcessos)) {
            JOptionPane.showMessageDialog(null, "Todos os dados estão corretos!");
            //List<Processo> listaProcessos = getListaProcessos(tabelaProcessos);

            //
            int quantum;
            Enumeration<AbstractButton> radioButtons = buttonGroup1.getElements();
            while (radioButtons.hasMoreElements()) {
                JRadioButton rb = (JRadioButton) radioButtons.nextElement();
                if (rb.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Opção escolhida: " + rb.getText());
                    String op = rb.getText();
                    switch (op) {
                        case "Shortest job first (Menor tarefa primeiro)":
                            menorTerefaPrimeiro(getListaProcessos(tabelaProcessos));
                            break;
                        case "Escalonamento por Prioridade Cooperativo":
                            prioridadeCooperativa(getListaProcessos(tabelaProcessos));
                            break;
                        case "Round-Robin":
                            if(txtQuantum.getText().trim().length() != 0){
                                try {
                                    quantum = Integer.parseInt(txtQuantum.getText());
                                    roundRobin(getListaProcessos(tabelaProcessos), quantum);
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Quantum inválido");
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Campo quantum vazio");
                            }
                            break;
                        case "Escalonamento por Prioridade Preemptivo":
                            if(txtQuantum.getText().trim().length() != 0){
                                try {
                                    quantum = Integer.parseInt(txtQuantum.getText());
                                    prioridadePreemptiva(getListaProcessos(tabelaProcessos));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Quantum inválido");
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Campo quantum vazio");
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Erro ao escolher o algoritmo de escalonamento!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Há dados inválidos na tabela!");
        }
        
        
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        criarLinhasTabela(txtQuantProcessos.getText(), tabelaProcessos);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlgoritmoEscalonamentoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlgoritmoEscalonamentoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbMenorTarefaPrimeiro;
    private javax.swing.JRadioButton rbPrioridadeCooperativo;
    private javax.swing.JRadioButton rbPrioridadePreemptivo;
    private javax.swing.JRadioButton rbRoundRobin;
    private javax.swing.JTable tabelaProcessos;
    private javax.swing.JTextField txtQuantProcessos;
    private javax.swing.JTextField txtQuantum;
    // End of variables declaration//GEN-END:variables
}
