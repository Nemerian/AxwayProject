package radu.project;

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
public class ControllerServletCurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursDAO cursDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		cursDAO = new CursDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGetCurs(request, response);
	}

	protected void doGetCurs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newc":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCurs(request, response);
				break;
			case "/deletec":
				deleteCurs(request, response);
				break;
			case "/editc":
				showEditForm(request, response);
				break;
			case "/updatec":
				updateCurs(request, response);
				break;
			default:
				listCurs(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCurs(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Curs> listCurs = cursDAO.listAllCurss();
		request.setAttribute("listCurs", listCurs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CursList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CursForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Curs existingCurs = cursDAO.getCurs(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CursForm.jsp");
		request.setAttribute("curs", existingCurs);
		dispatcher.forward(request, response);

	}

	private void insertCurs(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cod = request.getParameter("cod");
		String author = request.getParameter("author");

		Curs newCurs = new Curs(cod, author);
		cursDAO.insertCurs(newCurs);
		response.sendRedirect("listc");
	}

	private void updateCurs(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String cod = request.getParameter("cod");
		String author = request.getParameter("author");

		Curs curs = new Curs(id, cod, author);
		cursDAO.updateCurs(curs);
		response.sendRedirect("listc");
	}

	private void deleteCurs(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Curs curs = new Curs(id);
		cursDAO.deleteCurs(curs);
		response.sendRedirect("listc");

	}

}
