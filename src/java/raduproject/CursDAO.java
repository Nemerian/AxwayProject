package raduproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class CursDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CursDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertCurs(Curs curs) throws SQLException {
        String sql = "INSERT INTO curs (cod, titlu) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, curs.getcod());
        statement.setString(2, curs.gettitlu());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Curs> listAllCurss() throws SQLException {
        List<Curs> listCurs = new ArrayList<>();
         
        String sql = "SELECT * FROM curs";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("curs_id");
            String cod = resultSet.getString("cod");
            String titlu = resultSet.getString("titlu");
             
            Curs curs = new Curs(id, cod, titlu);
            listCurs.add(curs);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCurs;
    }
     
    public boolean deleteCurs(Curs curs) throws SQLException {
        String sql = "DELETE FROM curs where curs_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, curs.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateCurs(Curs curs) throws SQLException {
        String sql = "UPDATE curs SET cod = ?, titlu = ?";
        sql += " WHERE curs_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, curs.getcod());
        statement.setString(2, curs.gettitlu());
        statement.setInt(4, curs.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Curs getCurs(int id) throws SQLException {
        Curs curs = null;
        String sql = "SELECT * FROM curs WHERE curs_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String cod = resultSet.getString("cod");
            String titlu = resultSet.getString("titlu");
             
            curs = new Curs(id, cod, titlu);
        }
         
        resultSet.close();
        statement.close();
         
        return curs;
    }
}