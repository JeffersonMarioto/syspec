/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author jeffe
 */
public class PesquisaPartos extends javax.swing.JFrame {
    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmt;
    private String dbUrl;
    private Date data;
    private SimpleDateFormat sdf;
    /**
     * Creates new form PesquisaPartos
     */
    public PesquisaPartos() {
        super("Previsão dos partos");
        initComponents();
        setVisible(true);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    private void buscar(){
        try{
            if(jTextField1.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Digite o ano");
            }else{
                abrirBanco();
                int con = 0;
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                model.setRowCount(0);
                String ano = jTextField1.getText();
                rs = stmt.executeQuery 
                 (
                     "SELECT inseminacoes.animais_idanimal, inseminacoes.data_inseminacao,"
                             + " inseminacoes.diagnostico, animais.propriedade"
                             + " FROM inseminacoes INNER JOIN animais ON "
                             + " inseminacoes.animais_idanimal = animais.idanimal WHERE "
                             + " inseminacoes.ano='" +ano +
                             "' ORDER BY animais.propriedade, inseminacoes.data_inseminacao ASC"
                 );
                while(rs.next()){
                    if(rs.getString("inseminacoes.diagnostico").equalsIgnoreCase("Prenha")){
                        model.addRow(new Object[]{rs.getString("inseminacoes.animais_idanimal"),
                            sdf.format(rs.getDate("inseminacoes.data_inseminacao")),
                            adicionarMeses(rs.getDate("inseminacoes.data_inseminacao")),
                            rs.getString("animais.propriedade")});
                        ++con;
                    }
                }
                fecharBanco();
            }
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(rootPane, e);
        }        
    }

    private void buscarMes(){
        try{
            abrirBanco();
            int con = 0;
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            rs = stmt.executeQuery 
             (
                 "SELECT ano FROM INSEMINACOES ORDER BY ano DESC LIMIT 1"
             );
            rs.next();
            String ano = rs.getString("ano");
            rs.clearWarnings();
            rs = stmt.executeQuery 
             (
                 "SELECT inseminacoes.animais_idanimal, inseminacoes.data_inseminacao,"
                         + " inseminacoes.diagnostico, animais.propriedade"
                         + " FROM inseminacoes INNER JOIN animais ON "
                         + " inseminacoes.animais_idanimal = animais.idanimal WHERE "
                         + " inseminacoes.ano='" +ano +
                         "' AND inseminacoes.diagnostico LIKE '%Prenha%' "
                                 + "ORDER BY animais.propriedade, inseminacoes.data_inseminacao ASC"
             );
            while(rs.next()){
                if(mesParto(rs.getDate("inseminacoes.data_inseminacao"))==true){
                    model.addRow(new Object[]{rs.getString("inseminacoes.animais_idanimal"),
                        sdf.format(rs.getDate("inseminacoes.data_inseminacao")),
                        adicionarMeses(rs.getDate("inseminacoes.data_inseminacao")),
                        rs.getString("animais.propriedade")});
                    ++con;
                }
            }
            fecharBanco();
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(rootPane, e);
        }        
    }
   
    private boolean mesParto(Date d){
        boolean b = true;
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, 9);     
        String mes = sdf.format(cal.getTime()).substring(3,5);    
        if((jComboBox1.getSelectedIndex()+1)==Integer.parseInt(mes)){
            b = true;
        }else{
            b = false;
        }
        return b;
    }
    
    private String adicionarMeses(Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, 9);
        return sdf.format(cal.getTime());
    }
    
    public void abrirBanco(){
        dbUrl = "jdbc:mysql://127.0.0.1/bancopecuaria";
        String user = "root";
        String pass = "root";
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dbUrl, user, pass);
                stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                 "Driver não encontrado",
                 "Atenção",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
        }catch(SQLException sqlE){
                JOptionPane.showMessageDialog(null,
                 sqlE.getMessage(),
                 "Erro durante conexão com banco de dados",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
        }
    }
    public void fecharBanco() {
        try {
            stmt.close();
            conn.close();
        }catch (SQLException sqlE) {
            JOptionPane.showMessageDialog(null,
             sqlE.getMessage(),
             "Erro ao fechar a conexão com banco de dados",
              JOptionPane.ERROR_MESSAGE);
              System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estação:");

        jButton1.setText("Pesquisar Estação");
        jButton1.setToolTipText("Pesquisa todos os partos da estação.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Mês:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        jButton2.setText("Pesquisa Mensal");
        jButton2.setToolTipText("Pesquisa partos do mês selecionado da última estação.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Vaca", "Data Inseminação", "Data Parto aprox.", "Propriedade"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Imprimir Estação");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton3)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        buscar();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        buscarMes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            String arquivo = "C:/Users/jeffe/OneDrive/Documentos/NetBeansProjects/Conpec/Partos.jasper";
            JRTableModelDataSource tabela = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint printer = null;
            printer = JasperFillManager.fillReport(arquivo, null, tabela);
            JasperViewer visual = new JasperViewer(printer, false);
            visual.setVisible(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
