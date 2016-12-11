package checkFunctions;

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

@WebServlet("/checkYourPrivilages")
public class checkYourPrivilages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		user User = (user) session.getAttribute("User");
		int isAdmin = User.getIsAdmin();
		if (request.getParameter("DiaxeirisiXristwn") != null) {
			if (isAdmin == 1) {
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/main_admin/main_admin.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}
		}
		if (request.getParameter("DiaxeirisiRolwn") != null) {
			if (isAdmin == 1) {

				roles Roles = new roles();
				Connection con = (Connection) getServletContext().getAttribute("DBConnection");
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					// kathe fora pou kanei login pernoume krifa ta roles
					ps = con.prepareStatement("SELECT rolename FROM role; ");
					rs = ps.executeQuery();
					while (rs.next()) {
						Roles.appendRole("<option value=\"" + rs.getString("rolename") + "\">"
								+ rs.getString("rolename") + "</option>");
						session.setAttribute("Roles", Roles);
					}
					Roles.endRole();
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/WEB-INF/main_admin/admin_roles.jsp");

					dispatcher.forward(request, response);
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
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}
		}
	}

}
