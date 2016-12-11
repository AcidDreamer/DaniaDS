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

import bean.user;

@WebServlet("/checkYourPrivilages")
public class checkYourPrivilages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		user User = (user) session.getAttribute("User");
		String role = User.getRole();
		String username = User.getUsername();
		int isAdmin = User.getIsAdmin();
		if (request.getParameter("DiaxeirisiXristwn") != null || request.getParameter("DiaxeirisiRolwn") != null
				|| request.getParameter("DiaxeirisiYpiresiwn") != null) {
			if (isAdmin == 1) {
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/main_admin/main_admin.jsp");
				dispatcher.forward(request, response);
			}

		}

	}

}
