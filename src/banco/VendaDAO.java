/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import models.*;

/**
 *
 * @author Rodrigo
 */
public class VendaDAO extends DataAccessObject<Venda> {

    public VendaDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public int insert(Venda venda) {
        String[] sql = montaSql(venda);
        if (sql != null) {
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                stmt.addBatch(sql[0]);
                stmt.addBatch(sql[1]);
                stmt.addBatch(sql[2]);
                stmt.executeBatch();
                conn.commit();
                return getId();
            } catch (Exception e) {
                System.err.println("Ocorreu um erro: "+e.getMessage());
                try {
                    conn.rollback();
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Falhou Rollback");
                }
            }
        }
        System.out.println(Arrays.toString(sql));
        return 0;
    }

    @Override
    public boolean update(Venda element) {
        throw new UnsupportedOperationException("Não é possível fazer update nesse modelo"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Venda element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> find() {
        List<Venda> lista = new LinkedList<>();
        try {
            String sql = this.select+";";
            System.out.println("Executando comando:\n"+sql);
            Statement stmt = conn.createStatement();
            try(ResultSet rs = stmt.executeQuery(sql)){
                while(true){
                    Venda v = getVendaFromRS(rs);
                    if(v == null){
                        break;
                    }
                    lista.add(v);
                }
            }
            return lista;
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Venda findOne(int id) {
        try {
            String sql = this.select+" WHERE v.id="+id+";";
            Statement stmt = conn.createStatement();
            try(ResultSet rs = stmt.executeQuery(sql)){
                Venda v = getVendaFromRS(rs);
                return v;
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
            return null;
        }
    }

    public String[] montaSql(Venda venda) {
        String insertItens = "";
        for (ItemVenda item : venda.getItens()) {
            String x = "(";
            x += item.getQuantidade() + ",";
            x += item.getValor() + ",";
            x += item.getRemedio().getId();
            x += ",@idvenda)";
            insertItens += x + ",";
        }
        insertItens = insertItens.substring(0, insertItens.length() - 1);
        String[] sql = new String[3];
        sql[0] = "INSERT INTO venda(total, clienteid) VALUES (" + venda.getTotal()+"," + venda.getCliente().getId() + ");";
        sql[1] = "SET @idvenda = last_insert_id();";
        sql[2] = "INSERT INTO itemvenda(quantidade,valor,remedioid,vendaid) VALUES " + insertItens + ";";
        return sql;
    }
    
    public final String select = "SELECT v.id as 'v.id', v.datavenda as 'v.datavenda',\n"+
                            " i.iditem as 'i.iditem', i.valor as 'i.valor', i.quantidade as 'i.quantidade',\n"+
                            " c.id as 'c.id',c.cpf as 'c.cpf', c.nome as 'c.nome',c.datanascimento as 'c.datanascimento',\n"+
                            " r.id as 'r.id', r.descricao as 'r.descricao', r.bula as 'r.bula', r.preco as 'r.preco', r.tarja as 'r.tarja', r.estoque as 'r.estoque'\n FROM venda v "+
                            "RIGHT JOIN itemvenda i ON v.id = i.vendaid "+
                            "INNER JOIN cliente c ON v.clienteid = c.id "+
                            "INNER JOIN remedio r ON i.remedioid = r.id";
    
    private Venda getVendaFromRS(ResultSet rs) throws SQLException {
        if(!rs.next()){
            return null;
        }
        Venda v = new Venda();
        v.setCliente(new Cliente());
        v.setId(rs.getInt("v.id"));
        v.setDataVenda(rs.getDate("v.datavenda"));
        
        v.getCliente().setNome(rs.getString("c.nome"));
        v.getCliente().setCpf(rs.getString("c.cpf"));
        v.getCliente().setDataNascimento(rs.getDate("c.datanascimento"));
        v.getCliente().setId(rs.getInt("c.id"));
        do {
            if(rs.getInt("v.id") != v.getId()){
                break;
            }
            ItemVenda i = new ItemVenda();
            i.setValor(rs.getDouble("i.valor"));
            i.setQuantidade(rs.getInt("i.quantidade"));
            i.setIdItem(rs.getInt("i.iditem"));
            Remedio r = new Remedio();
            r.setId(rs.getInt("r.id"));
            r.setDescricao(rs.getString("r.descricao"));
            r.setBula(rs.getString("r.bula"));
            r.setEstoque(rs.getInt("r.estoque"));
            r.setPreco(rs.getDouble("r.preco"));
            i.setRemedio(r);
            v.addItem(i);
        } while (rs.next());
        rs.previous();
        return v;
    }
}
