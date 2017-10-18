/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import banco.VendaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;

/**
 *
 * @author Rodrigo
 */
public class VendaDAOTeste {
    public static void main(String[] args){
        testaInsert();
    }   
    
    public static void testaInsert(){
        try {
            VendaDAO dao = new VendaDAO();
            Venda v = new Venda();
            Cliente c = new Cliente();
            c.setId(10);
            v.setCliente(c);
            ItemVenda item = new ItemVenda();
            item.setQuantidade(5);
            item.setValor(10);
            Remedio r = new Remedio();
            r.setId(1);
            r.setPreco(2);
            item.setRemedio(r);
            v.addItem(item);
            item = new ItemVenda();
            item.setQuantidade(5);
            item.setValor(12);
            r = new Remedio();
            r.setId(1);
            r.setPreco(2.4);
            item.setRemedio(r);
            v.addItem(item);
            dao.insert(v);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOTeste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDAOTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
