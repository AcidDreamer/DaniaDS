package adminFunctions;

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

/**
 * Servlet implementation class adminUsers
 */
@WebServlet("/adminUsers")
public class adminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;

		if (request.getParameter("username").equals("") || request.getParameter("username") == null) {
			// Έλενχει φόρμας και redirect με κατάλληλο μήνυμα σε περίπτωση
			// σφάλματος
			out.println("<script>alert(\"" + "Form elements cannot be empty ." + "\")</script>");
			rd = getServletContext().getRequestDispatcher("/WEB-INF/main_admin/main_admin.jsp");
			rd.include(request, response);
		} else {
			if (request.getParameter("ClientAddBtn") != null) {
				// Σε περίπτωση που πατηθεί το submit στην φόρμα addClient
				// παίρνουμε τις μεταβλητές
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String full_name = request.getParameter("full_name");
				String email = request.getParameter("email");
				int atm = Integer.parseInt(request.getParameter("atm"));
				int adt = Integer.parseInt(request.getParameter("adt"));
				int salary = Integer.parseInt(request.getParameter("salary"));
				int phone = Integer.parseInt((request.getParameter("phone")));

				String errorMsg = null;
				// Περισσότεροι έλεγχοι
				if (username == null || username.equals("") || password == null || password.equals("")
						|| full_name == null || full_name.equals("") || adt == 0 || salary == 0 || phone == 0) {
					errorMsg = "Fields can't be null or empty";
				}

				if (errorMsg != null) {
					// ενημέρωση για σφάλμα και redirect.
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out.println("<script>alert(\"" + errorMsg + "\")</script>");
					rd.include(request, response);
				} else {
					// Ετοιμάζουμε την σύνδεση και τα αντικείμενα που θα
					// χρησιμοποιήσουμε
					Connection con = (Connection) getServletContext().getAttribute("DBConnection");
					PreparedStatement ps = null;
					try {
						// Φτιάχνουμε το sql Statement
						ps = con.prepareStatement("INSERT INTO User VALUES(?,?,?,\"Client\");");
						ps.setString(1, username);
						ps.setString(2, password);
						ps.setString(3, full_name);
						ps.executeUpdate();
						ps = con.prepareStatement(
								"INSERT INTO Client(atm,adt,salary,phone,username,email) VALUES(?,?,?,?,?,?);");
						ps.setInt(1, atm);
						ps.setInt(2, adt);
						ps.setInt(3, salary);
						ps.setInt(4, phone);
						ps.setString(5, username);
						ps.setString(6,email);
						// Εκτελούμε το statement
						ps.executeUpdate();
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						// Ενημερώνουμε τον χρήστη και κάνουμε redirect
						out.println("<script>alert(\"" + "User added successfully" + "\")</script>");
						rd.include(request, response);
					} catch (SQLException e) {
						rd = getServletContext().getRequestDispatcher("/main.jsp");
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
			if (request.getParameter("OtherAddBtn") != null) {
				// Παρόμοιο με το παραπάνω ,αλλά το χρησιμοποιούμε για
				// δημιουργεία χρήστη που δεν είναι πελάτης
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String full_name = request.getParameter("full_name");
				String role = request.getParameter("allRoles");
				String errorMsg = null;
				if (username == null || username.equals("") || password == null || password.equals("")
						|| full_name == null || full_name.equals("") || role.equals("Client")) {
					errorMsg = "Fields can't be null or empty and you cannot select client as an account type";
				}
				if (errorMsg != null) {
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out.println("<script>alert(\"" + errorMsg + "\")</script>");
					rd.include(request, response);
				} else {
					Connection con = (Connection) getServletContext().getAttribute("DBConnection");
					PreparedStatement ps = null;
					try {
						ps = con.prepareStatement("INSERT INTO User VALUES(?,?,?,?);");
						ps.setString(1, username);
						ps.setString(2, password);
						ps.setString(3, full_name);
						ps.setString(4, role);
						ps.executeUpdate();
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						out.println("<script>alert(\"" + "User added successfully" + "\")</script>");
						rd.include(request, response);
					} catch (SQLException e) {
						rd = getServletContext().getRequestDispatcher("/main.jsp");
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
			if (request.getParameter("deleteBtn") != null) {
				// Παρόμοιο με το πρώτο μόνο που κάνουμε delete
				String username = request.getParameter("username");
				String accountType = request.getParameter("allRoles");
				Connection con = (Connection) getServletContext().getAttribute("DBConnection");
				PreparedStatement ps = null;
				try {
					if (!(accountType.equals("Client"))) {
						// Σε περίπτωση που είναι χρήστης
						ps = con.prepareStatement("DELETE FROM User WHERE username=?;");
						ps.setString(1, username);
						ps.executeUpdate();
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						out.println("<script>alert(\"" + "User removed successfully" + "\")</script>");
						rd.include(request, response);
					} else {
						// Σε περίπτωση που δεν είναι χρήστης
						ps = con.prepareStatement("DELETE FROM Client WHERE username=?;");
						ps.setString(1, username);
						ps.executeUpdate();
						ps = con.prepareStatement("DELETE FROM User WHERE username=?;");
						ps.setString(1, username);
						ps.executeUpdate();

						rd = getServletContext().getRequestDispatcher("/main.jsp");
						out.println("<script>alert(\"" + "User removed successfully" + "\")</script>");
						rd.include(request, response);

					}
				} catch (SQLException e) {
					System.out.println("Database connection problem");
					rd = getServletContext().getRequestDispatcher("/main.jsp");
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
}
