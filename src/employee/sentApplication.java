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
import bean.client;

/**
 * Servlet implementation class sentApplication
 */
@WebServlet("/sentApplication")
public class sentApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("app_code") == null || request.getParameter("taxes") == null || request.getParameter("repayTime") == null
				|| request.getParameter("drivers_licence").equals("") || request.getParameter("username").equals("")
				|| request.getParameter("taxes") == null || request.getParameter("app_code") == null || request.getParameter("taxes").equals("") || request.getParameter("repayTime").equals("")
				|| request.getParameter("taxes").equals("") || request.getParameter("commentary").equals("")) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"" + "Form elements cannot be empty ." + "\")</script>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/makeApplication.jsp");
			rd.include(request, response);

		} else {
			int id = Integer.parseInt(request.getParameter("app_code"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int buy_type = Integer.parseInt(request.getParameter("buy_type"));
			int taxes = Integer.parseInt(request.getParameter("taxes"));
			int repayTime = Integer.parseInt(request.getParameter("repayTime"));

			String drivers_license = request.getParameter("drivers_licence");
			String username = request.getParameter("username");
			String tekmiriwsi = request.getParameter("commentary");
			String buyType;
			client Client;
			application Application;
			if (buy_type == 1) {
				buyType = "Used";
			} else {
				buyType = "Brand New";
			}
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				PrintWriter out = response.getWriter();
				ps = con.prepareStatement(
						"SELECT * FROM User u JOIN Client c  ON  u.username=c.username WHERE c.username=? and role=\"Client\"");
				ps.setString(1, username);
				rs = ps.executeQuery();
				if (rs != null && rs.next()) {
					Client = new client(rs.getString("username"), rs.getInt("salary"));
					Application = new application(id, amount, buyType, drivers_license, taxes, username, repayTime,tekmiriwsi);
					if (!(Application.canGetLoad(Client))) {
						out.println("<script>alert(\"" + "User cannot get a loan ." + "\")</script>");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					} else {
						ps = con.prepareStatement("INSERT INTO Application VALUES(?,?,?,?,?,?,?,0,0,?);");
						ps.setInt(1, Application.getApp_code());
						ps.setInt(2, Application.getAmount());
						ps.setInt(3, Application.getRepayTime());
						ps.setString(4, Application.getBuy_Type());
						ps.setString(5, Application.getDrivers_licence());
						ps.setInt(6, Application.getTaxes());
						ps.setString(7, Application.getTekmiriwsi());
						ps.setString(8, Application.getUsername());
						ps.executeUpdate();
						out.println("<script>alert(\"" + "Application Added Succesfully ." + "\")</script>");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					}
				} else {
					out.println("<script>alert(\"" + "User has not been found ." + "\")</script>");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/makeApplication.jsp");
					rd.include(request, response);
				}
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

	}

}
