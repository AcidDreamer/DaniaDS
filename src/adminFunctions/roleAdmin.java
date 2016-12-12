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
		if (request.getParameter("add") != null) {
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

			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement("INSERT INTO role VALUES(?,?,?,?,?,?,0,0);");
				ps.setString(1, rolename);
				ps.setInt(2, elegxos);
				ps.setInt(3, Kataxwrisi);
				ps.setInt(4, Ypologismos);
				ps.setInt(5, Tropopoiisi);
				ps.setInt(6, Egkrisi);
				int rowsUpdated = ps.executeUpdate();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"" + "Role added successfully" + "\")</script>");
				rd.include(request, response);
			} catch (SQLException e) {
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

		}

		if (request.getParameter("remove") != null) {
			String toRemove;
			toRemove = request.getParameter("allRoles");
			if (toRemove.equals("Administrator")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"You are not allowed to remove this role -> Administrator\");</script>");
				rd.include(request, response);

			} else {

				Connection con = (Connection) getServletContext().getAttribute("DBConnection");
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement("DELETE FROM role WHERE rolename=?;");
					ps.setString(1, toRemove);
					ps.executeUpdate();
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
					PrintWriter out = response.getWriter();
					out.println("<script>alert(\"" + "Role removed successfully" + "\")</script>");
					rd.include(request, response);
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
			}
		}

	}
}
