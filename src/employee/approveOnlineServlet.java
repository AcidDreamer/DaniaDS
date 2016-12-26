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
 * Servlet implementation class approveOnlineServlet
 */
@WebServlet("/approveOnlineServlet")
public class approveOnlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (request.getParameter("approval") == null) {
			// Έλενχει φόρμας και redirect με κατάλληλο μήνυμα σε περίπτωση
			// σφάλματος
			out.println("<script>alert(\"Pick approve or disapprove before hitting submit\")</script>");
			rd = getServletContext().getRequestDispatcher("/main.jsp");
			rd.include(request, response);
		} else {
			// παίρνουμε τις μεταβλητές
			int app_code = Integer.parseInt(request.getParameter("app_code"));
			String username = request.getParameter("username");
			String approved = request.getParameter("approval");
			String commentary = request.getParameter("moreCommentary");
			try {
				// Φτιάχνουμε το sql Statement
				ps = con.prepareStatement(
						"SELECT * FROM Application a JOIN User u ON u.username = a.username JOIN Client c ON c.username=u.username WHERE app_code=?  AND u.username = ? AND status = 0;");
				ps.setInt(1, app_code);
				ps.setString(2, username);
				// Εκτελούμε το statement
				rs = ps.executeQuery();
				// Ενημέρωση σε βαρύ σφάλμα
				if (rs == null) {
					out.println(
							"<script>alert(\"" + "Something went seriously wrong ,contact support !" + "\")</script>");
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					rd.include(request, response);
				}
				if(approved.equals("approve")){
					ps=con.prepareStatement("UPDATE Application SET isOnline=0 WHERE app_code=?;");
					ps.setInt(1,app_code);
					ps.executeUpdate();
					ps=con.prepareStatement("UPDATE Application SET tekmiriwsi=? WHERE app_code=?;");
					ps.setString(1, commentary);
					ps.setInt(2,app_code);
					ps.executeUpdate();
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out = response.getWriter();
					out.println("<script>alert(\"Updated succefully \")</script>");
					rd.include(request, response);

				}else if(approved.equals("disapprove")){
					ps=con.prepareStatement("UPDATE Application SET isOnline=0 WHERE app_code=?;");
					ps.setInt(1,app_code);
					ps.executeUpdate();
					ps=con.prepareStatement("UPDATE Application SET status=1 WHERE app_code=?;");
					ps.setInt(1,app_code);
					ps.executeUpdate();
					ps=con.prepareStatement("UPDATE Application SET tekmiriwsi=? WHERE app_code=?;");
					ps.setString(1, commentary);
					ps.setInt(2,app_code);
					ps.executeUpdate();
					rd = getServletContext().getRequestDispatcher("/main.jsp");
					out = response.getWriter();
					out.println("<script>alert(\"Updated succefully \")</script>");
					rd.include(request, response);
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
