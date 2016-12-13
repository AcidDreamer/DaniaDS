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
		HttpSession session = request.getSession();
		session.removeAttribute("Client");
		if (request.getParameter("searcher") != null && !(request.getParameter("searcher").equals(""))) {
			int searchBy = Integer.parseInt(request.getParameter("searchBy"));
			int searcher = Integer.parseInt(request.getParameter("searcher"));
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				if (searchBy == 1) {
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone FROM User,Client WHERE User.username = Client.username AND ID=? LIMIT 1;");
					ps.setInt(1, searcher);
					rs = ps.executeQuery();
					client Client = null;
					while (rs.next()) {
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"));
					}
					session.setAttribute("Client", Client);
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
					rd.include(request, response);
				} else if (searchBy == 2) {
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone FROM User,Client WHERE User.username = Client.username AND atm=? LIMIT 1;");
					ps.setInt(1, searcher);
					rs = ps.executeQuery();
					client Client = null;
					while (rs.next()) {
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"));
					}
					session.setAttribute("Client", Client);
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
					rd.include(request, response);

				} else if (searchBy == 3) {
					ps = con.prepareStatement(
							"SELECT User.username,full_name,id,atm,adt,salary,phone FROM User,Client WHERE User.username = Client.username AND adt=? LIMIT 1;");
					ps.setInt(1, searcher);
					rs = ps.executeQuery();
					client Client = null;
					while (rs.next()) {
						Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
								rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"));
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
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Field cannot be empty\");</script>");
			rd.include(request, response);

		}
	}

}
