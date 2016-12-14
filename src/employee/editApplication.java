package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.application;

/**
 * Servlet implementation class editApplication
 */
@WebServlet("/editApplication")
public class editApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ετοιμάζουμε την σύνδεση και τα αντικείμενα που θα
		// χρησιμοποιήσουμε
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		application Application = null;
		if (request.getParameter("newAmount").equals("") || request.getParameter("newCommentary").equals("")
				|| (Integer.parseInt(request.getParameter("newAmount")) > 15000) || (Integer.parseInt(request.getParameter("newAmount")) < 0)) {
			// Έλενχει φόρμας και redirect με κατάλληλο μήνυμα σε περίπτωση
			// σφάλματος
			out.println(
					"<script>alert(\"Make sure to fill the fields and not surpass the 15000 euros threshold!\")</script>");
			rd = getServletContext().getRequestDispatcher("/main.jsp");
			rd.include(request, response);
		} else {
			// παίρνουμε τις μεταβλητές απο το request
			int app_code = Integer.parseInt(request.getParameter("app_code"));
			int newAmount = Integer.parseInt(request.getParameter("newAmount"));
			String newCommentary = request.getParameter("newCommentary");
			try {
				// Φτιάχνουμε το sql Statement
				ps = con.prepareStatement("UPDATE Application SET amount = ? WHERE app_code = ?;");
				ps.setInt(1, newAmount);
				ps.setInt(2, app_code);
				ps.executeUpdate();
				ps = con.prepareStatement("UPDATE Director SET tekmiriwsiDieuthinti = ? WHERE app_code = ?  ;");
				ps.setString(1, newCommentary);
				ps.setInt(2, app_code);
				// Εκτελούμε το statement
				ps.executeUpdate();
				out.println("<script>alert(\"" + "Application Modified!" + "\")</script>");
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				rd.include(request, response);
			} catch (SQLException e) {
				System.out.println("Database connection problem");
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out = response.getWriter();
				out.println("<script>alert(\"" + e + "\")</script>");
				rd.include(request, response);
				throw new ServletException("DB Connection problem.");
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("SQLException in closing PreparedStatement or ResultSet");
				}
			}

		}
	}

}
