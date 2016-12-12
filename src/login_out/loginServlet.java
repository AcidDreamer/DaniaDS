package login_out;

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

import bean.roles;
import bean.user;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 8345605719292191551L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMsg = null;
		if (username == null || username.equals("")) {
			errorMsg = "Usename can't be null or empty";
		}
		if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
		}
		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"" + errorMsg + "\")</script>");
			rd.include(request, response);
		} else {
			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(
						"SELECT DISTINCT u.username,u.role,full_name,Elegxos,Kataxwrisi,Ypologismos,Tropopoiisi,Egkrisi,isAdmin,isClient FROM User u JOIN role r ON u.role = r.rolename  WHERE username=? and password=? and isClient=0 limit 1;");
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if (rs != null && rs.next()) {
					user user = new user(rs.getString("username"), rs.getString("full_name"), rs.getString("role"),
							rs.getInt("Elegxos"), rs.getInt("Kataxwrisi"), rs.getInt("Ypologismos"),
							rs.getInt("Tropopoiisi"), rs.getInt("Egkrisi"), rs.getInt("isAdmin"),
							rs.getInt("isClient"));
					HttpSession session = request.getSession();
					session.setAttribute("User", user);
                    response.sendRedirect("main.jsp");
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
					PrintWriter out = response.getWriter();
					System.out.println("User not found with username=" + username);
					out.println("<script>alert(\"Incorrect credentials\");</script>");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Database connection problem");
				throw new ServletException("DB Connection problem.");
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					System.out.println("SQLException in closing PreparedStatement or ResultSet");
				}
			}
		}
	}
}
