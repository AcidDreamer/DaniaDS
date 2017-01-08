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
import javax.servlet.http.HttpSession;

import bean.client;

/**
 * Servlet implementation class searchCustomer
 */
@WebServlet("/searchCustomer")
public class searchCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ετοιμάζουμε τα αντικείμενα
		HttpSession session = request.getSession();
		session.removeAttribute("Client");
		// Έλενχει φόρμας  			
		if (request.getParameter("searcher") != null && !(request.getParameter("searcher").equals(""))) {
			// Ετοιμάζουμε την σύνδεση
			int searchBy = Integer.parseInt(request.getParameter("searchBy"));
			int searcher = Integer.parseInt(request.getParameter("searcher"));
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				//Έαν αναζητούμε με ID
				if (searchBy == 1) {
					// Φτιάχνουμε το sql Statement
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone,email FROM User,Client WHERE User.username = Client.username AND ID=? LIMIT 1;");
					ps.setInt(1, searcher);
					// Εκτελούμε το statement
					rs = ps.executeQuery();
					client Client = null;
					//Δημιοργούμε αντικείμενο τύπου πελάτη
					while (rs.next()) {
						//
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"),rs.getString("email"));
					}
					session.setAttribute("Client", Client);
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
					rd.include(request, response);
					//Έαν αναζητούμε με AFM
				} else if (searchBy == 2) {
					//Παρόμοιο με το παραπάνω
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone,email FROM User,Client WHERE User.username = Client.username AND atm=? LIMIT 1;");
					ps.setInt(1, searcher);
					rs = ps.executeQuery();
					client Client = null;
					while (rs.next()) {
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"),rs.getString("email"));
					}
					session.setAttribute("Client", Client);
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
					rd.include(request, response);
					//Έαν αναζητούμε με ADT
				} else if (searchBy == 3) {
					//Παρόμοιο με το πρώτο
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone,email FROM User,Client WHERE User.username = Client.username AND adt=? LIMIT 1;");
					ps.setInt(1, searcher);
					rs = ps.executeQuery();
					client Client = null;
					while (rs.next()) {
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"),rs.getString("email"));
					}
					session.setAttribute("Client", Client);
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				System.out.println("Database connection problem");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out = response.getWriter();
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

		} else {
			//redirect με κατάλληλο μήνυμα σε περίπτωση
			// σφάλματος
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Field cannot be empty\");</script>");
			rd.include(request, response);

		}
	}

}
