/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import views.*;

/**
 *
 * @author Rodrigo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame janela = new JFrame("Início");
        janela.setLayout(new GridLayout(4,1));
        
        JLabel titulo = new JLabel();
        titulo.setText("INDEX");
        titulo.setFont(new Font("Calibri", 0, 26));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        
        JButton cliente = new JButton("CLIENTES");
        JButton remedio = new JButton("REMÉDIOS");
        JButton venda = new JButton("VENDAS");
        WindowListener window = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                janela.setEnabled(true);
                janela.setVisible(true);
            }
        };
        cliente.addActionListener((e)->{
            janela.setEnabled(false);
            ListaCliente list = new ListaCliente();
            list.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            list.setVisible(true);
            list.addWindowListener(window);
        });
        remedio.addActionListener((e)->{
            ListaRemedio list = new ListaRemedio();
            janela.setEnabled(false);
            list.setVisible(true);
            list.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            list.setVisible(true);
            list.addWindowListener(window);
        });
        venda.addActionListener((e)->{
            janela.setEnabled(false);
            ListaVenda list = new ListaVenda();
            list.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            list.addWindowListener(window);
            list.setVisible(true);
            
        });
        
        janela.add(titulo);
        janela.add(cliente);
        janela.add(remedio);
        janela.add(venda);
        janela.setSize(300,400);
        
        janela.setVisible(true);
    }   
}
