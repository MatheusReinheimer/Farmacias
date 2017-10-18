/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import models.Cliente;

/**
 *
 * @author Rodrigo
 */
public class ClienteDAO extends DataAccessObject<Cliente> {

    public ClienteDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public int insert(Cliente cliente) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "INSERT INTO cliente(nome,datanascimento,cpf) VALUES ("+
                    cliente.getNome()+","+sdf.format(cliente.getDataNascimento())+","+cliente.getCpf()+");";
            Statement stmt = conn.createStatement();
            if(stmt.execute(sql)){
                return getId();
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Falhou Rollback");
            }
        }
        return 0;
    }

    @Override
    public boolean update(Cliente element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Cliente element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> find() {
        try {
            Statement stmt = conn.createStatement();
            List<Cliente> lista = new LinkedList<>();

            try (ResultSet rs = stmt.executeQuery("select * from cliente;")) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setCpf(rs.getString("cpf"));
                    c.setNome(rs.getString("nome"));
                    c.setDataNascimento(rs.getDate("datanascimento"));
                    c.setId(rs.getInt("id"));
                    lista.add(c);
                }
            }
            return lista;
        } catch (Exception e) {
            System.err.println("Erros: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
