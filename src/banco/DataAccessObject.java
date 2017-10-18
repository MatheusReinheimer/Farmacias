
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class DataAccessObject<T> {
    
    protected Connection conn;
    protected final String GET_ID = "select last_insert_id() as id;";
    public DataAccessObject() throws ClassNotFoundException,
                        SQLException
    {
        String url="jdbc:mysql://localhost/farmacia";
        String user="root";
        String pass="";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pass);
    }
    
    public int getId() throws SQLException{
        try{
            Statement stmt = conn.createStatement();
            try(ResultSet rs = stmt.executeQuery(GET_ID)){
                rs.next();
                return rs.getInt("id");
            }
        } catch(Exception e){
            System.err.println("Ocorreu um erro: "+e.getMessage());
            throw e;
        }
    }
    
    /**
     * Insere um registro no banco de dados
     * @param element
     * @return retorna o id do registro inserido
     */
    public abstract int insert(T element);
    public abstract boolean update(T element);
    public abstract boolean delete(T element);
    public abstract List<T> find();
    public abstract T findOne(int id);
}