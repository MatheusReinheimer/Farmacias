
package banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import models.Cliente;
import models.Remedio;

public class RemedioDAO extends DataAccessObject<Remedio>{

    public RemedioDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    public int insert(Remedio element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Remedio element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Remedio element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Remedio> find() {
        try {
            Statement stmt = conn.createStatement();
            List<Remedio> lista = new LinkedList<>();

            try (ResultSet rs = stmt.executeQuery("select * from remedio;")) {
                while (rs.next()) {
                    Remedio r = new Remedio();
                    r.setId(rs.getInt("id"));
                    r.setBula(rs.getString("bula"));
                    r.setDescricao(rs.getString("descricao"));
                    r.setEstoque(rs.getInt("estoque"));
                    r.setPreco(rs.getDouble("preco"));
                    r.setTarja(rs.getInt("tarja"));
                    lista.add(r);
                }
            }
            return lista;
        } catch (Exception e) {
            System.err.println("Erros: " + e.getMessage());
            return null;
        }
    }
    @Override
    public Remedio findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
