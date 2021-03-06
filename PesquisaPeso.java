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
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jeffe
 */
public class PesquisaPeso extends javax.swing.JFrame {
    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmt;
    private String dbUrl;
    private Date data;
    private SimpleDateFormat sdf;
    /**
     * Creates new form PesquisaPeso
     */
    public PesquisaPeso() {
        super("Pesquisa de pesagem");
        initComponents();
        setVisible(true);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Animal");

        jTextField1.setToolTipText("Digite a ID ");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("M??s:");

        jLabel4.setText("Ano (aaaa):");

        jTextField2.setToolTipText("digite o ano(aaaa)");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Pesagem", "N?? Animal", "Peso Anterior", "Peso Atual", "Resultado", "Data"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton4.setText("Pesquisar por animal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Pesquisar por data");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Mar??o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(66, 66, 66)
                                    .addComponent(jLabel3)
                                    .addGap(26, 26, 26)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        buscarData();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        buscarAnimal();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        buscarAnimal();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        buscarData();
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void buscarAnimal(){
        try{
            if(jTextField1.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Digite o n??mero da cria????o");
            }else{
                abrirBanco();
                jLabel5.setText("");
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                int idanimal = Integer.parseInt(jTextField1.getText());
                rs = stmt.executeQuery 
                 (
                     "SELECT * FROM pesos"+
                     " WHERE animais_idanimal =" +
                     idanimal
                 );
                while(rs.next()){
                    model.addRow(new Object[]{rs.getString("id"),
                        rs.getString("animais_idanimal"),
                        rs.getString("peso_anterior"),rs.getString("peso_atual"),
                        rs.getString("resultado"),sdf.format(rs.getDate("data"))});
                }
                fecharBanco();
            }
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(rootPane, e);
        }        
    }
    
    private void buscarData(){
        try{
            if(jTextField2.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Digite o ano");
            }else{
                abrirBanco();
                int con = 0;
                float pes = 0;
                float med = 0;
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                String ano = jTextField2.getText();
                int mes = jComboBox1.getSelectedIndex()+1;
                rs = stmt.executeQuery 
                 (
                     "SELECT * FROM pesos WHERE YEAR(data) ='" +ano +
                             "' AND MONTH(data) ='" +mes +"'"
                 );
                while(rs.next()){
                    model.addRow(new Object[]{rs.getString("id"),
                        rs.getString("animais_idanimal"),
                        rs.getString("peso_anterior"),rs.getString("peso_atual"),
                        rs.getString("resultado"),sdf.format(rs.getDate("data"))});
                    ++con;
                    pes = pes + Float.parseFloat(rs.getString("resultado"));
                }
                med = pes/con;
                jLabel5.setText("N??mero de registros: "+con +" M??dia: "+med);
                fecharBanco();
            }
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(rootPane, e);
        }        
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
                 "Driver n??o encontrado",
                 "Aten????o",
                  JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
        }catch(SQLException sqlE){
                JOptionPane.showMessageDialog(null,
                 sqlE.getMessage(),
                 "Erro durante conex??o com banco de dados",
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
             "Erro ao fechar a conex??o com banco de dados",
              JOptionPane.ERROR_MESSAGE);
              System.exit(0);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
