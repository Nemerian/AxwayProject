package raduproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class StudentDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public StudentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (cnp, nume) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getcnp());
        statement.setString(2, student.getnume());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Student> listAllStudents() throws SQLException {
        List<Student> listStudent = new ArrayList<>();
         
        String sql = "SELECT * FROM student";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String cnp = resultSet.getString("cnp");
            String nume = resultSet.getString("nume");
             
            Student student = new Student(id, cnp, nume);
            listStudent.add(student);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listStudent;
    }
     
    public boolean deleteStudent(Student student) throws SQLException {
        String sql = "DELETE FROM student where student_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, student.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE student SET cnp = ?, nume = ?";
        sql += " WHERE student_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getcnp());
        statement.setString(2, student.getnume());
        statement.setInt(4, student.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Student getStudent(int id) throws SQLException {
        Student student = null;
        String sql = "SELECT * FROM student WHERE student_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String cnp = resultSet.getString("cnp");
            String nume = resultSet.getString("nume");
             
            student = new Student(id, cnp, nume);
        }
         
        resultSet.close();
        statement.close();
         
        return student;
    }
}