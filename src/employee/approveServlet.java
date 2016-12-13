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
 * Servlet implementation class approveServlet
 */
@WebServlet("/approveServlet")
public class approveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		application Application = null;
		client Client = null;
		if (request.getParameter("approval") == null) {
			out.println("<script>alert(\"Pick approve or disapprove before hitting submit\")</script>");
			rd = getServletContext().getRequestDispatcher("/main.jsp");
			rd.include(request, response);
		} else {
			int app_code = Integer.parseInt(request.getParameter("app_code"));
			String username = request.getParameter("username");
			String approved = request.getParameter("approval");
			try {
				ps = con.prepareStatement(
						"SELECT * FROM Application a JOIN User u ON u.username = a.username JOIN Client c ON c.username=u.username WHERE app_code=?  AND u.username = ? AND status = 0;");
				ps.setInt(1, app_code);
				ps.setString(2, username);
				rs = ps.executeQuery();
				if (rs == null) {
					out.println(
							"<script>alert(\"" + "Something went seriously wrong ,contact support !" + "\")</script>");
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					rd.include(request, response);
				}
				while (rs.next() && rs != null) {
					Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
							rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"));
					Application = new application(rs.getInt("app_code"), rs.getInt("amount"), rs.getString("buy_type"),
							rs.getString("drivers_license"), rs.getInt("taxes"), rs.getString("username"),
							rs.getInt("repayTime"), rs.getString("tekmiriwsi"));
				}
				if (Application != null && Client != null) {
					if (Application.canBeDisproved(Client) && approved.equals("disapprove")) {
						ps = con.prepareStatement("UPDATE Application SET status = 1 WHERE app_code = ?;");
						ps.setInt(1, app_code);
						ps.executeUpdate();
						out.println("<script>alert(\"" + "Application disapproved!" + "\")</script>");
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					} else if (Application.canBeDisproved(Client) && approved.equals("approve")) {
						ps = con.prepareStatement("UPDATE Application SET status = 1,accepted =1  WHERE app_code = ?;");
						ps.setInt(1, app_code);
						ps.executeUpdate();
						out.println("<script>alert(\"" + "Application Approved!" + "\")</script>");
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					} else if (!(Application.canBeDisproved(Client)) && approved.equals("disapprove")) {
						out.println("<script>alert(\"" + "Application can't be disapproved" + "\")</script>");
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					} else if (!(Application.canBeDisproved(Client)) && approved.equals("approve")) {
						ps = con.prepareStatement("UPDATE Application SET status = 1,accepted =1  WHERE app_code = ?;");
						ps.setInt(1, app_code);
						ps.executeUpdate();
						out.println("<script>alert(\"" + "Application Approved!" + "\")</script>");
						rd = getServletContext().getRequestDispatcher("/main.jsp");
						rd.include(request, response);
					}
				}
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
