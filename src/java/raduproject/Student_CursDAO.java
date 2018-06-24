package raduproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class Student_CursDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public Student_CursDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertStudent_Curs(Student_Curs student_curs) throws SQLException {
        String sql = "INSERT INTO student_curs (ids, idc) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student_curs.getids());
        statement.setString(2, student_curs.getidc());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Student_Curs> listAllStudent_Curss() throws SQLException {
        List<Student_Curs> listStudent_Curs = new ArrayList<>();
         
        String sql = "SELECT * FROM student_curs";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("student_curs_id");
            String ids = resultSet.getString("ids");
            String idc = resultSet.getString("idc");
             
            Student_Curs student_curs = new Student_Curs(id, ids, idc);
            listStudent_Curs.add(student_curs);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listStudent_Curs;
    }
     
    public boolean deleteStudent_Curs(Student_Curs student_curs) throws SQLException {
        String sql = "DELETE FROM student_curs where student_curs_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateStudent_Curs(Student_Curs student_curs) throws SQLException {
        String sql = "UPDATE student_curs SET ids = ?, idc = ?";
        sql += " WHERE student_curs_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student_curs.getids());
        statement.setString(2, student_curs.getidc());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Student_Curs getStudent_Curs() throws SQLException {
        Student_Curs student_curs = null;
        String sql = "SELECT * FROM student_curs WHERE";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String ids = resultSet.getString("ids");
            String idc = resultSet.getString("idc");
             
            student_curs = new Student_Curs(ids, idc);
        }
         
        resultSet.close();
        statement.close();
         
        return student_curs;
    }
}