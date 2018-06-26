package radu.project;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @nume xenot
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */
public class ControllerServletStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        studentDAO = new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
            case "/news":
                showNewForm(request, response);
                break;
            case "/inserts":
                insertStudent(request, response);
                break;
            case "/deletes":
                deleteStudent(request, response);
                break;
            case "/edits":
                showEditForm(request, response);
                break;
            case "/updates":
                updateStudent(request, response);
                break;
            default:
                listStudent(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent = studentDAO.listAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("StudentList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("StudentForm.jsp"); 
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("StudentForm.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
 
    }
 
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String cnp = request.getParameter("cnp");
        String nume = request.getParameter("nume");
 
        Student newStudent = new Student(cnp, nume);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("lists");
    }
 
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cnp = request.getParameter("cnp");
        String nume = request.getParameter("nume");
 
        Student student = new Student(id, cnp, nume);
        studentDAO.updateStudent(student);
        response.sendRedirect("lists");
    }
 
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Student student = new Student(id);
        studentDAO.deleteStudent(student);
        response.sendRedirect("lists");
 
    }
}
