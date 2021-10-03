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
import javax.swing.JOptionPane;
import java.text.*;
import javax.swing.*;

/**
 *
 * @author jeffe
 */
public class CadastroAnimal extends javax.swing.JFrame{

    /**
     * Creates new form CadastroAnimal
     */
    	private static Connection conn;
	private static ResultSet rs;
	private static Statement stmt;
	private String dbUrl;
        private ButtonGroup bg;
    
    public CadastroAnimal() {
        super("Cadastro de animais");
        initComponents();
        carregarID();
        setVisible(true);
        bg = new ButtonGroup();
        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
    }
    
    public void carregarID(){
	String s = "00";
	try{
		int maior = 0, x = 0;
		abrirBanco();
		rs = stmt.executeQuery 
		(
			"SELECT IDANIMAL FROM ANIMAIS"
		);
		while(rs.next()) {
			x = rs.getInt("IDANIMAL");
			if(x>maior) {
				maior = x;
			}
		}
		s = String.valueOf(++maior);
			fecharBanco();
	} catch(Exception err) {
		fecharBanco();
		JOptionPane.showMessageDialog(null, err);
		s = "01";
	}
	jTextField6.setText(s); 
        jTextField1.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Cadastro de animal"); // NOI18N

        jLabel3.setText("Descrição:");

        jLabel4.setText("Propriedade:");

        jLabel5.setText("Sexo:");

        jLabel6.setText("Ano:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
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

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Macho");

        jRadioButton2.setText("Fêmea");

        jLabel7.setText("ID:");

        jTextField6.setToolTipText("Digite o número do animal.");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel8.setText("Pai:");
        jLabel8.setToolTipText("Digite o nome do pai.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel8)))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2))
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
// botão gravar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //chamar um método para verificar os campos antes de tentar gravar
        if(verificarCampos()==true){
            try{
                    abrirBanco();
                    stmt.executeUpdate
                    (
                        "INSERT INTO animais" +
                        "(idanimal, pai, descricao, propriedade, sexo, ano)" +
                        "VALUES('"+ Integer.parseInt(jTextField6.getText()) + "','" +
                                jTextField1.getText().toUpperCase()+ "','" +
                                jTextField3.getText().toUpperCase()+ "','" +
                                jTextField4.getText().toUpperCase()+ "','" +
                                getSexo().toUpperCase()+ "','" +
                                jTextField5.getText() + "')"
                    );
                    String s = "ao gravar.";
                    informarSucesso(s);
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,"Falha"+err, "Atenção",
                            JOptionPane.ERROR_MESSAGE);
            }
            fecharBanco();
            limparCampos();
        }else{
            JOptionPane.showMessageDialog(null,"Verifique os campos", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private String getSexo(){
        String sexo = "Macho";
        if(jRadioButton1.isSelected()){
            sexo = "Macho";
        }
        if(jRadioButton2.isSelected()){
            sexo = "Fêmea";
        }
        return sexo;
    }
 
    public void informarSucesso(String s){
            JOptionPane.showMessageDialog(null,"Sucesso ao "+ s + ".",
                    "Parabéns", JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(confirmarOperacao()==true){
            try{
                    abrirBanco();
                    int id = Integer.parseInt(jTextField6.getText());
                    stmt.executeUpdate
                    (
                            "DELETE FROM animais " +
                            "WHERE idanimal =" +
                            id
                    );
                    String s = "excluir este registro.";	
                    informarSucesso(s);
                    limparCampos();
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,
                            "Falha na tentativa de \n excluir este animal:\n"+err,
                            "Atenção", JOptionPane.ERROR_MESSAGE);
            }
            fecharBanco();
	}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        carregarID();
        limparCampos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jButton1.requestFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        buscar();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(verificarCampos()==true){
            try{
                abrirBanco();
                stmt.executeUpdate
                (
                        "UPDATE animais SET " +
                        "pai ='" + jTextField1.getText().toUpperCase() + 
                        "',descricao ='" + jTextField3.getText().toUpperCase() + 
                        "',propriedade ='" + jTextField4.getText().toUpperCase() +
                        "',sexo ='" +getSexo().toUpperCase()+
                        "',ano ='" + jTextField5.getText()+
                        "'WHERE idanimal =" +
                        Integer.parseInt(jTextField6.getText())
                );
                String s = "alterar.";
                informarSucesso(s);
                limparCampos();
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,
                            "Falha na tentativa de \n alterar este animal:\n"+err,
                            "Atenção", JOptionPane.ERROR_MESSAGE);
    
            }
            fecharBanco();
	}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscar();
    }//GEN-LAST:event_jButton3ActionPerformed
    private void buscar(){
        try{
            abrirBanco();
            int id = Integer.parseInt(jTextField6.getText());
            rs = stmt.executeQuery 
            (
                "SELECT * FROM animais"+
                " WHERE idanimal =" +
                id
            );
            rs.next();
            jTextField1.setText(rs.getString("pai"));
            jTextField3.setText(rs.getString("descricao"));
            jTextField4.setText(rs.getString("propriedade"));
            String sexo = rs.getString("sexo");
            if(sexo.equalsIgnoreCase("macho")){
                jRadioButton1.setSelected(true);
                jRadioButton2.setSelected(false);
            }
            if(sexo.equalsIgnoreCase("fêmea")){
                jRadioButton2.setSelected(true);
                jRadioButton1.setSelected(false);
            }
            jTextField5.setText(rs.getString("ano").substring(0, 4));
        }catch(Exception err){
                fecharBanco();
                JOptionPane.showMessageDialog(null,
                        "Falha na tentativa de \n buscar este animal:\n"+err,
                        "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        fecharBanco();
    }
    private boolean verificarCampos(){
        boolean b = true;
        if(jTextField1.getText().equals("")){
            b = false;
        }
        if(jTextField3.getText().equals("")){
            b = false;
        }
        if(jTextField4.getText().equals("")){
            b = false;
        }
        if(jTextField5.getText().equals("")){
            b = false;
        }
        if(jTextField6.getText().equals("")){
            b = false;
        }
        return b;
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
    
    public void abrirBanco() {
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
		} catch (SQLException sqlE) {
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
		} catch (SQLException sqlE) {
			JOptionPane.showMessageDialog(null,
			 sqlE.getMessage(),
			 "Erro ao fechar a conexão com banco de dados",
			  JOptionPane.ERROR_MESSAGE);
			  System.exit(0);
		}
	}
        private void limparCampos(){
            jTextField1.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            carregarID();
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}