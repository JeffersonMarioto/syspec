package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeffe
 */
public class Sanitário extends javax.swing.JFrame {

    private static Connection conn;
    private static ResultSet rs;
    private static Statement stmt;
    private String dbUrl;
    private Date data;
    private SimpleDateFormat sdf;
    /**
     * Creates new form Sanitário
     */
    public Sanitário() {
        super("Sanitário");
        initComponents();
        setVisible(true);
        data = new Date();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        jTextField5.setText(sdf.format(data));
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
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID da vacinação:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Produto:");

        jLabel3.setText("Quantidade de animais:");

        jLabel4.setText("Propriedade:");

        jLabel5.setText("Data da aplicação:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField5)
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(10, 10, 10)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(confirmarOperacao()==true){
            try{
                    abrirBanco();
                    int id = Integer.parseInt(jTextField1.getText());
                    stmt.executeUpdate
                    (
                            "DELETE FROM Sanitario " +
                            "WHERE idSanitario =" +
                            id
                    );
                    String s = "excluir este registro.";	
                    informarSucesso(s);
                    limparCampos();
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,
                            "Falha na tentativa de \n excluir este registro sanitário:\n"+err,
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
                            "INSERT INTO Sanitario "+
                            "(idSanitario, remedio, quantidade, propriedade, data)"+
                            " VALUES ('" +Integer.parseInt(jTextField1.getText())+"','"+
                                    jTextField2.getText().toUpperCase()+"','"+
                                    Integer.parseInt(jTextField3.getText()) +"','"+
                                    jTextField4.getText().toUpperCase() +"','"+
                                    formatarData(jTextField5.getText()) + "')"
                    );
                    String s = "ao gravar.";
                    informarSucesso(s);
                    fecharBanco();
                    limparCampos();
        }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(null,"Falha"+e, "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        buscar();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
           abrirBanco();
           stmt.executeUpdate
           (
                "UPDATE Sanitario SET " +
                "remedio ='" + jTextField2.getText().toUpperCase() + 
                "',quantidade ='" + Integer.parseInt(jTextField3.getText()) +
                "',propriedade ='" + jTextField4.getText().toUpperCase()+
                "',data ='" + formatarData(jTextField5.getText()) +        
                "'WHERE idSanitario =" +
                Integer.parseInt(jTextField1.getText())
           );
           String s = "alterar.";
           informarSucesso(s);
           limparCampos();
       }catch(Exception err){
               fecharBanco();
               JOptionPane.showMessageDialog(null,
                       "Falha na tentativa de \n alterar este registro sanitário:\n"+err,
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
                "SELECT * FROM Sanitario"+
                " WHERE idSanitario =" +
                id
            );
            rs.next();
            jTextField1.setText(rs.getString("idSanitario"));
            jTextField2.setText(rs.getString("remedio"));
            jTextField3.setText(rs.getString("quantidade"));
            jTextField4.setText(rs.getString("propriedade"));
            jTextField5.setText(sdf.format(rs.getDate("data")));
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
			"SELECT idSanitario FROM Sanitario"
                                + " ORDER BY idSanitario DESC LIMIT 1"
		);
		if(rs.next()) {
                    x = rs.getInt("idSanitario");
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
        jTextField4.setText("");
        jTextField5.setText(sdf.format(data));
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
