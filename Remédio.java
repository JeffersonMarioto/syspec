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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author jeffe
 */
public class Remédio extends javax.swing.JFrame {
    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmt;
    private String dbUrl;
    private Date data;
    private SimpleDateFormat sdf;
    /**
     * Creates new form Remédio
     */
    public Remédio() {
        super("Medicação");
        initComponents();
        setVisible(true);
        data = new Date();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        jTextField4.setText(sdf.format(data));
        carregarID();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Número do animal:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Remédio:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Data:");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton1.setText("Gravar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Alterar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Limpar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        buscar();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jButton1.requestFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(confirmarOperacao()==true){
            try{
                    abrirBanco();
                    int id = Integer.parseInt(jTextField1.getText());
                    stmt.executeUpdate
                    (
                            "DELETE FROM Remedio " +
                            "WHERE idRemedio =" +
                            id
                    );
                    String s = "excluir este registro.";	
                    informarSucesso(s);
                    limparCampos();
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,
                            "Falha na tentativa de \n excluir este registro remédio:\n"+err,
                            "Atenção", JOptionPane.ERROR_MESSAGE);
            }
            fecharBanco();
        }       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            abrirBanco();
            stmt.executeUpdate
                    (
                            "INSERT INTO Remedio "+
                            "(idRemedio, animais_idanimal, remedio, data)"+
                            " VALUES ('" +Integer.parseInt(jTextField1.getText())+"','"+
                                    Integer.parseInt(jTextField2.getText())+"','"+
                                    jTextField3.getText().toUpperCase() +"','"+
                                    formatarData(jTextField4.getText()) + "')"
                    );
                    String s = "ao gravar.";
                    informarSucesso(s);
                    fecharBanco();
                    limparCampos();
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(null,"Falha"+e, "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
           abrirBanco();
           stmt.executeUpdate
           (
                "UPDATE Remedio SET " +
                "animais_idanimal ='" + Integer.parseInt(jTextField2.getText()) + 
                "',remedio ='" + jTextField3.getText().toUpperCase() +
                "',data ='" + formatarData(jTextField4.getText()) +        
                "'WHERE idRemedio =" +
                Integer.parseInt(jTextField1.getText())
           );
           String s = "alterar.";
           informarSucesso(s);
           limparCampos();
       }catch(Exception err){
               fecharBanco();
               JOptionPane.showMessageDialog(null,
                       "Falha na tentativa de \n alterar este registro de medicação"
                               + ":\n"+err,
                       "Atenção", JOptionPane.ERROR_MESSAGE);

       }
       fecharBanco();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void buscar(){
        try{
            abrirBanco();
            int id = Integer.parseInt(jTextField1.getText());
            rs = stmt.executeQuery 
            (
                "SELECT * FROM Remedio"+
                " WHERE idRemedio =" +
                id
            );
            rs.next();
            jTextField1.setText(rs.getString("idRemedio"));
            jTextField2.setText(rs.getString("animais_idanimal"));
            jTextField3.setText(rs.getString("remedio"));
            jTextField4.setText(sdf.format(rs.getDate("data")));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar:"+e, "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        }
        fecharBanco();
    }
   
    private boolean confirmarOperacao(){
        Object []opcoes = {"Sim", "Não"};
        boolean b = true;
        int op = JOptionPane.showOptionDialog(null,
                "Deseja realmente excluir?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                opcoes, opcoes[0]);		
        if (op == 0) {
                b = true;
        }else{
                b = false;
        }
        return b;
    }   
    
    
    public String formatarData(String d){
        try{
                String ano = d.substring(6,10);
                String mes = d.substring(3,5);
                String dia = d.substring(0,2);
                d = ano + "-" + mes + "-" + dia;
        }catch(Exception err){JOptionPane.showMessageDialog(null,"O campo data está incorreto");}
        return d;
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
       
    private void carregarID(){
	String s = "00";
	try{
		int x = 0;
		abrirBanco();
		rs = stmt.executeQuery 
		(
			"SELECT idRemedio FROM Remedio"
                                + " ORDER BY idRemedio DESC LIMIT 1"
		);
		if(rs.next()) {
                    x = rs.getInt("idRemedio");
		}
		s = String.valueOf(++x);
		fecharBanco();
	}catch(Exception err) {
            fecharBanco();
            s = "01";
	}
	jTextField1.setText(s); 
        jTextField2.requestFocus();
    }
    
     private void limparCampos(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText(sdf.format(data));
        carregarID();
    }    
        
    public void informarSucesso(String s){
    JOptionPane.showMessageDialog(null,"Sucesso ao "+ s + ".",
            "Parabéns", JOptionPane.INFORMATION_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
