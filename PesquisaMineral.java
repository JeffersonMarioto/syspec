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
public class PesquisaMineral extends javax.swing.JFrame {

    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmt;
    private String dbUrl;
    private SimpleDateFormat sdf;
    /**
     * Creates new form PesquisaMineral
     */
    public PesquisaMineral() {
        super("Consulta Minerais e Rações");
        initComponents();
        setVisible(true);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        buscar();
    }
    
    private void buscar() {                                         
        try{
            abrirBanco();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            rs = stmt.executeQuery 
            (
                "SELECT * FROM mineral"+
                " ORDER BY propriedade ASC, data_inicial ASC"
            );
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("idmineral"),
                    rs.getString("descricao"),rs.getString("quantidade"),
                        rs.getString("numero_animais"),rs.getString("propriedade"),
                        sdf.format(rs.getDate("data_inicial")),
                        sdf.format(rs.getDate("data_final")),
                        rs.getString("resultado")});
            }
        }catch(Exception err){
            JOptionPane.showMessageDialog(rootPane, err);
            fecharBanco();
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
    public void fecharBanco(){
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "Quantidade(kg)", "Nº animais", "Propriedade", "Data ínicio", "Data final", "Consumo dia"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Consulta minerais e rações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(317, 317, 317))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
