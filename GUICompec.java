/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;
import java.awt.Container;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;


public class GUICompec extends JFrame implements ActionListener {
    
    private JButton B1, B2, B3, B4, B5, B6, B7;
    
    public GUICompec(){
        super("Controle pecuária");
        Container c = getContentPane();
        setBounds(50,50,400,120);
        B1 = new JButton("Cadastro de animais");
        B2 = new JButton("Pesagem");
        B3 = new JButton("Inseminação");
        B4 = new JButton("Sanitário");
        B5 = new JButton("Medicação");
        B6 = new JButton("Minerais & Rações");
        B7 = new JButton("Pesquisas");
        JPanel p = new JPanel();
        GridLayout g = new GridLayout(7,1);
        g.setHgap(5);
        g.setVgap(5);
        p.setLayout(g);
        p.add(B1);
        p.add(B2);
        p.add(B3);
        p.add(B4);
        p.add(B5);
        p.add(B6);
        p.add(B7);
        c.add(p);
        B1.addActionListener(this);
        B2.addActionListener(this);
        B3.addActionListener(this);
        B4.addActionListener(this);
        B5.addActionListener(this);
        B6.addActionListener(this);
        B7.addActionListener(this);
        addWindowListener(new WindowAdapter()
                        {
                                public void windowClosing(WindowEvent e)
                                {
                                            System.exit(0);
                                }
                        });        
        setBounds(20,20,180,300);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == B1) {
            CadastroAnimal ca = new CadastroAnimal();
        }
        if(e.getSource() == B2) {
            Pesagem peso = new Pesagem();
        }
        if(e.getSource() == B3) {
            Inseminação in = new Inseminação();
        }
        if(e.getSource() == B4) {
            Sanitário sa = new Sanitário();
        }
        if(e.getSource() == B5) {
            Remédio re = new Remédio();
        } 
         if(e.getSource() == B6) {
            Mineral mi = new Mineral();
        }
        if(e.getSource() == B7) {
            Pesquisas pesquisa = new Pesquisas();
        } 
   }
}
