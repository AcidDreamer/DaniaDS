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
 * Servlet implementation class roleAdmin
 */
@WebServlet("/roleAdmin")
public class roleAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();

		if (request.getParameter("add") != null) {
			// Σε περίπτωση που δημιουργούμαι νέο ρόλο παίρνουμε τις τιμές που
			// έδωσε ο χρήστης
			String rolename = request.getParameter("rolename");
			int elegxos;
			if (request.getParameter("Elegxos") == null) {
				elegxos = 0;
			} else {
				elegxos = 1;
			}

			int Kataxwrisi;
			if (request.getParameter("Kataxwrisi") == null) {
				Kataxwrisi = 0;
			} else {
				Kataxwrisi = 1;
			}

			int Ypologismos;
			if (request.getParameter("Ypologismos") == null) {
				Ypologismos = 0;
			} else {
				Ypologismos = 1;
			}

			int Tropopoiisi;
			if (request.getParameter("Tropopoiisi") == null) {
				Tropopoiisi = 0;
			} else {
				Tropopoiisi = 1;
			}

			int Egkrisi;
			if (request.getParameter("Egkrisi") == null) {
				Egkrisi = 0;
			} else {
				Egkrisi = 1;
			}
			// Ετοιμάζουμε την σύνδεση και τα αντικείμενα που θα
			// χρησιμοποιήσουμε
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			try {
				// Φτιάχνουμε το sql Statement
				ps = con.prepareStatement("INSERT INTO role VALUES(?,?,?,?,?,?,0,0);");
				ps.setString(1, rolename);
				ps.setInt(2, elegxos);
				ps.setInt(3, Kataxwrisi);
				ps.setInt(4, Ypologismos);
				ps.setInt(5, Tropopoiisi);
				ps.setInt(6, Egkrisi);
				// Εκτελούμε το statement
				ps.executeUpdate();
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				// Ενημερώνουμε τον χρήστη και κάνουμε redirect
				out.println("<script>alert(\"" + "Role added successfully" + "\")</script>");
				rd.include(request, response);
			} catch (SQLException e) {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"' Something went wrong. '\")</script>");
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
		if (request.getParameter("edit") != null) {
			// Σε περίπτωση που δημιουργούμαι νέο ρόλο παίρνουμε τις τιμές που
			// έδωσε ο χρήστης
			String rolename = request.getParameter("allRoles");
			int elegxos;
			if (request.getParameter("Elegxos") == null) {elegxos = 0;} else {elegxos = 1;}
			int Kataxwrisi;
			if (request.getParameter("Kataxwrisi") == null) {Kataxwrisi = 0;} else {Kataxwrisi = 1;	}
			int Ypologismos;
			if (request.getParameter("Ypologismos") == null) {Ypologismos = 0;} else {Ypologismos = 1;}
			int Tropopoiisi;
			if (request.getParameter("Tropopoiisi") == null) {Tropopoiisi = 0;} else {Tropopoiisi = 1;}
			int Egkrisi;
			if (request.getParameter("Egkrisi") == null) {Egkrisi = 0;} else {Egkrisi = 1;}
			System.out.println(rolename);
			// Ετοιμάζουμε την σύνδεση και τα αντικείμενα που θα
			// χρησιμοποιήσουμε
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			try {
				// Φτιάχνουμε το sql Statement
				ps = con.prepareStatement("UPDATE role SET Elegxos=?,Kataxwrisi=?,Ypologismos=?,Tropopoiisi=?,Egkrisi=? WHERE rolename = ?;");
				ps.setInt(1, elegxos);
				ps.setInt(2, Kataxwrisi);
				ps.setInt(3, Ypologismos);
				ps.setInt(4, Tropopoiisi);
				ps.setInt(5, Egkrisi);
				ps.setString(6, rolename);
				// Εκτελούμε το statement
				ps.executeUpdate();
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				// Ενημερώνουμε τον χρήστη και κάνουμε redirect
				out.println("<script>alert(\"" + "Role editted successfully" + "\")</script>");
				rd.include(request, response);
			} catch (SQLException e) {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"' Something went wrong. '\")</script>");
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
		// παρόμοιο με το παραπάνω
		if (request.getParameter("remove") != null) {
			// Σε περίπτωση που διαγράφουμε έναν ρόλο παίρνουμε το όνομα του .
			String toRemove;
			toRemove = request.getParameter("allRoles");
			if (toRemove.equals("Administrator")) {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"You are not allowed to remove this role -> Administrator\");</script>");
				rd.include(request, response);
			} else {
				Connection con = (Connection) getServletContext().getAttribute("DBConnection");
				PreparedStatement ps = null;
				try {
					ps = con.prepareStatement("DELETE FROM role WHERE rolename=?;");
					ps.setString(1, toRemove);
					ps.executeUpdate();
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out.println("<script>alert(\"" + "Role removed successfully" + "\")</script>");
					rd.include(request, response);
				} catch (SQLException e) {
					System.out.println("Database connection problem");
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out.println("<script>alert(\"' Something went wrong. '\")</script>");
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
