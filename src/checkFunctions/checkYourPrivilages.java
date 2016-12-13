package checkFunctions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.application;
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
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();

		if (request.getParameter("DiaxeirisiXristwn") != null) {
			if (isAdmin == 1) {
				String sqlInject = "Administrator";

				getRoleCookies(request, response, sqlInject);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_admin/main_admin.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}
		}

		if (request.getParameter("DiaxeirisiRolwn") != null) {
			if (isAdmin == 1) {
				String sqlInject = "Client";
				String sqlInject2 = "Administrator";

				getRoleCookies(request, response, sqlInject, sqlInject2);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/WEB-INF/main_admin/admin_roles.jsp");

				dispatcher.forward(request, response);

			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}
		}

		if (request.getParameter("Elegxos") != null) {
			if (User.getElegxos() == 1) {

				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}

		}
		if (request.getParameter("Kataxwrisi") != null) {
			if (User.getKataxwrisi() == 1) {

				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/makeApplication.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}

		}
		if (request.getParameter("Egkrisi") != null) {
			if (User.getEgkrisi() == 1) {
				getApplicationCookies(request, response);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/mainDirector/mainDirector.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}

		}
		if (request.getParameter("Listes") != null) {
			if (User.getYpologismos() == 1) {
				getApplicationCookies(request, response);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/list.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privilages\");</script>");
				rd.include(request, response);

			}

		}
	}

	private void getRoleCookies(HttpServletRequest request, HttpServletResponse response, String sqlInject)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("Roles");
		roles Roles = new roles();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT rolename FROM role WHERE rolename <> ?; ");
			ps.setString(1, sqlInject);
			rs = ps.executeQuery();
			while (rs.next()) {
				Roles.appendRole(
						"<option value=\"" + rs.getString("rolename") + "\">" + rs.getString("rolename") + "</option>");
			}
			Roles.endRole();
			session.setAttribute("Roles", Roles);
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

	// me 2 sqlinjections
	private void getRoleCookies(HttpServletRequest request, HttpServletResponse response, String sqlInject,
			String sqlInject2) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("Roles");
		roles Roles = new roles();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT rolename FROM role WHERE rolename <> ? AND rolename <> ?; ");
			ps.setString(1, sqlInject);
			ps.setString(2, sqlInject2);
			rs = ps.executeQuery();
			while (rs.next()) {
				Roles.appendRole(
						"<option value=\"" + rs.getString("rolename") + "\">" + rs.getString("rolename") + "</option>");
			}
			Roles.endRole();
			session.setAttribute("Roles", Roles);
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

	private void getApplicationCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("appList");
		ArrayList<application> appList = new ArrayList<application>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM Application WHERE status = 0;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				System.out.println("New Application Added");
				application Application = new application(rs.getInt("app_code"), rs.getInt("amount"),
						rs.getString("buy_type"), rs.getString("drivers_license"), rs.getInt("taxes"),
						rs.getString("username"), rs.getInt("repayTime"), rs.getString("tekmiriwsi"));
				appList.add(Application);
			}
			session.setAttribute("appList", appList);
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
