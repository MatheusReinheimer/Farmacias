
package banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import models.Remedio;

public class RemedioDAO extends DataAccessObject<Remedio>{

    public RemedioDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    public int insert(Remedio remedio) {
        try {
            String sql = "INSERT INTO `remedio` (`descricao`, `bula`, `estoque`, `preco`) VALUES ('"+
                remedio.getDescricao()+"','"+remedio.getBula()+"','"+remedio.getEstoque()+"','"+remedio.getPreco()+"');";
            Statement stmt = conn.createStatement();
            if(stmt.executeUpdate(sql) > 0){
            }
            return getId();
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
    public boolean update(Remedio remedio) {
        try {
            String sql = "UPDATE remedio SET descricao='"+remedio.getDescricao()+"',bula='"+remedio.getBula()+"',estoque='"+remedio.getEstoque()+"',preco='"+remedio.getPreco()+"' WHERE id="+remedio.getId()+";";
            Statement stmt = conn.createStatement();
            if(stmt.executeUpdate(sql) > 0){
            }
            return getId() != 0;
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Falhou Rollback");
            }
        }
        return false;
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
