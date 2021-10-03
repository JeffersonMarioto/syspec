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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author jeffe
 */
public class Pesagem extends javax.swing.JFrame {
    
        private static Connection conn;
	private static ResultSet rs;
	private static Statement stmt;
	private String dbUrl, data_anterior;
        private Date data;
        private SimpleDateFormat sdf;
        private int contador = 0;
    /**
     * Creates new form Pesagem
     */
    public Pesagem() {
        super("Pesagem");
        initComponents();
        setVisible(true);
        data = new Date();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        jFormattedTextField1.setText(sdf.format(data));
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

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextField1.setToolTipText("Digite o número do animal.");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Animal nº:");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Data:");

        jLabel3.setText("Peso anterior:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Peso atual:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
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

        jLabel5.setText("Resultado:");

        jLabel6.setText("ID pesagem:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jFormattedTextField1)
                            .addComponent(jTextField4)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jFormattedTextField1.getAccessibleContext().setAccessibleParent(jFormattedTextField1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Entre com o número do animal.",
                    "Atenção", JOptionPane.ERROR_MESSAGE);
        }else{
            if(verificarAnimalExiste(Integer.parseInt(jTextField1.getText()))==true){
                localizarPesoAnterior();
            }else{
                JOptionPane.showMessageDialog(null, "Animal não cadastrado", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
                CadastroAnimal ca = new CadastroAnimal();
            }

        }
    }//GEN-LAST:event_jTextField1ActionPerformed
    //gravar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            abrirBanco();
            stmt.executeUpdate
                    (
                            "INSERT INTO pesos "+
                            "(id, animais_idanimal, data, peso_anterior, peso_atual, resultado)"+
                            "VALUES ('"+Integer.parseInt(jTextField5.getText())+"','"+
                                    Integer.parseInt(jTextField1.getText())+"','"+
                                    formatarData(jFormattedTextField1.getText()) +"','"+
                                    Float.parseFloat(jTextField2.getText()) +"','"+
                                    Float.parseFloat(jTextField3.getText()) +"','"+
                                    Float.parseFloat(jTextField4.getText()) + "')"
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
    //buscar
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscar();
        if(contador<5){
            System.out.println("Tente de novo...");
            contador++;
        } else{
            System.out.println("Calma!!!");
            contador = 0;
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    //excluir
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(confirmarOperacao()==true){
            try{
                    abrirBanco();
                    int id = Integer.parseInt(jTextField5.getText());
                    stmt.executeUpdate
                    (
                            "DELETE FROM pesos " +
                            "WHERE id =" +
                            id
                    );
                    String s = "excluir este registro.";	
                    informarSucesso(s);
                    limparCampos();
            }catch(Exception err){
                    fecharBanco();
                    JOptionPane.showMessageDialog(null,
                            "Falha na tentativa de \n excluir esta pesagem:\n"+err,
                            "Atenção", JOptionPane.ERROR_MESSAGE);
            }
            fecharBanco();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    //limpar
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limparCampos();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        calcularResultado();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        buscar();
    }//GEN-LAST:event_jTextField5ActionPerformed
//alterar
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try{
           abrirBanco();
           stmt.executeUpdate
           (
                "UPDATE pesos SET " +
                "animais_idanimal ='" + Integer.parseInt(jTextField1.getText()) + 
                "',data ='" + formatarData(jFormattedTextField1.getText()) +
                "',peso_anterior ='" + Float.parseFloat(jTextField2.getText())+
                "',peso_atual ='" + Float.parseFloat(jTextField3.getText()) +
                "',resultado ='" + Float.parseFloat(jTextField4.getText()) +        
                "'WHERE id =" +
                Integer.parseInt(jTextField5.getText())
           );
           String s = "alterar.";
           informarSucesso(s);
           limparCampos();
       }catch(Exception err){
               fecharBanco();
               JOptionPane.showMessageDialog(null,
                       "Falha na tentativa de \n alterar este pesagem:\n"+err,
                       "Atenção", JOptionPane.ERROR_MESSAGE);

       }
       fecharBanco();
    }//GEN-LAST:event_jButton4ActionPerformed
    
    public void informarSucesso(String s){
        JOptionPane.showMessageDialog(null,"Sucesso ao "+ s + ".",
                "Parabéns", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limparCampos(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jFormattedTextField1.setText(sdf.format(data));
        data_anterior = "";
        jLabel7.setText("");
        carregarID();
    }
    private void buscar(){
        try{
            abrirBanco();
            int id = Integer.parseInt(jTextField5.getText());
            rs = stmt.executeQuery 
            (
                "SELECT * FROM pesos"+
                " WHERE id =" +
                id
            );
            rs.next();
            jTextField5.setText(rs.getString("id"));
            jTextField1.setText(rs.getString("animais_idanimal"));
            jFormattedTextField1.setText(sdf.format(rs.getDate("data")));
            jTextField2.setText(rs.getString("peso_anterior"));
            jTextField3.setText(rs.getString("peso_atual"));
            jTextField4.setText(rs.getString("resultado"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar:"+e, "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        }
        fecharBanco();
    }
    
    private boolean verificarAnimalExiste(int id){
        boolean b = false;
        abrirBanco();
        try{
            rs = stmt.executeQuery
                    (
                            "SELECT * FROM animais"+
                                    " WHERE idanimal ="+
                                    id
                    );
            if(rs.next()){
                b = true;
            }
        }catch(Exception e){
            b = false;
        }
        fecharBanco();
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
    
   private void localizarPesoAnterior(){
       try{
           abrirBanco();
           int idanimal = Integer.parseInt(jTextField1.getText());
           rs = stmt.executeQuery 
            (
                "SELECT * FROM pesos"+
                " WHERE animais_idanimal =" +
                idanimal +
                " ORDER BY id DESC LIMIT 1"
            );
           if(rs.next()){
               jTextField2.setText(rs.getString("peso_atual"));
               jTextField3.requestFocus();
               data_anterior =  rs.getString("data");
           }else{
            jTextField2.setText("0000");
            jTextField3.requestFocus();
            data_anterior = jFormattedTextField1.getText();
           }
       }catch(Exception e){
            fecharBanco();
            JOptionPane.showMessageDialog(null,
                    "Erro"+e,
                    "Atenção", JOptionPane.ERROR_MESSAGE);
       }
       fecharBanco();
   }
   
   private void calcularResultado(){
       try{
           float anterior = Float.parseFloat(jTextField2.getText());
           float atual = Float.parseFloat(jTextField3.getText());
           float resultado = 0;
           if(atual>0){
               resultado = atual - anterior;
               jTextField4.setText(String.valueOf(resultado));
               jButton1.requestFocus();
           }else{
               JOptionPane.showMessageDialog(null,"O peso atual não pode ser"
                       + "negativo", "Atenção", JOptionPane.ERROR);
           }
           calcularGanhoDiario();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Exceção:"+e, "Atenção",
                   JOptionPane.ERROR);
       }
   }
   
   public long calcularDias(){
       long difdias = 0;
       try{
           DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           LocalDate data1 = LocalDate.parse(data_anterior, formato1);
           LocalDate data2 = LocalDate.parse(jFormattedTextField1.getText(), formato);
           difdias = ChronoUnit.DAYS.between(data1, data2);
       }catch(Exception err){
           System.out.println("Erro calcular dias: "+err);
       }
       return difdias;
   }    
  
   public void calcularGanhoDiario(){
       try{
           float peso_ganho = Float.parseFloat(jTextField4.getText());
           long dias = calcularDias();
           float ganho_diario = peso_ganho/dias;
           jLabel7.setText("Ganho diário: "+String.valueOf(ganho_diario).substring(0,4).concat("kg/d"));
       }catch(Exception err){
           System.out.println(err);
       }
   }
   
    public void carregarID(){
	String s = "00";
	try{
		int maior = 0, x = 0;
		abrirBanco();
		rs = stmt.executeQuery 
		(
			"SELECT ID FROM PESOS"
		);
		while(rs.next()) {
			x = rs.getInt("ID");
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
	jTextField5.setText(s); 
        jTextField1.requestFocus();
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
            try{
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}