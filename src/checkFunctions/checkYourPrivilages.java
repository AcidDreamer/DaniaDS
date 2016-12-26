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
import bean.client;
import bean.roles;
import bean.user;

@WebServlet("/checkYourPrivilages")
public class checkYourPrivilages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Αντικείμενα και cookies που θα χρησιμοποιήσει το servlet
		HttpSession session = request.getSession();
		user User = (user) session.getAttribute("User");
		int isAdmin = User.getIsAdmin();
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();

		// Παίρνουμε την τιμή του κουμπιού που πατήθηκε και δρούμε ανάλογα
		// Σε όλα γίνετε έλεγχος αμα υπάρχουν δικαιώματα και μετά ανάλογα
		// redirect
		// Βοηθητικές μέθοδοι πιο κάτω
		if (request.getParameter("DiaxeirisiXristwn") != null) {
			if (isAdmin == 1) {
				String sqlInject = "Administrator";
				getRoleCookies(request, response, sqlInject);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_admin/main_admin.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
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
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
		if (request.getParameter("DiaxeirisiYpiresiwn") != null) {
			if (isAdmin == 1) {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Under construction!\");</script>");
				rd.include(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
		if (request.getParameter("Elegxos") != null) {
			if (User.getElegxos() == 1) {
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/main_employee.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
		if (request.getParameter("Kataxwrisi") != null) {
			if (User.getKataxwrisi() == 1) {
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/makeApplication.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
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
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
		if (request.getParameter("Listes") != null) {
			if (User.getYpologismos() == 1) {
				getApplicationApprovedCookies(request, response);
				getApplicationDisprovedCookies(request, response);
				getAllClientCookies(request, response);
				getApplicationCookies(request, response);
				getApplicationOnliner(request,response);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/main_employee/list.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
		if (request.getParameter("Tropopoiisi") != null) {
			if (User.getTropopoiisi() == 1) {
				getApplicationCookies(request, response);
				rd = getServletContext().getRequestDispatcher("/WEB-INF/mainDirector/editDirector.jsp");
				rd.forward(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/main.jsp");
				out.println("<script>alert(\"Check your privileges\");</script>");
				rd.include(request, response);
			}
		}
	}

	private void getRoleCookies(HttpServletRequest request, HttpServletResponse response, String sqlInject)
			throws ServletException, IOException {
		// Ετοιμάζουμε την σύνδεση και τα αντικείμενα που θα
		// χρησιμοποιήσουμε
		HttpSession session = request.getSession();
		session.removeAttribute("Roles");
		roles Roles = new roles();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Φτιάχνουμε το sql Statement
			ps = con.prepareStatement("SELECT rolename FROM role WHERE rolename <> ?; ");
			ps.setString(1, sqlInject);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Φτιάχνουμε τα cookies για τα roles
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

	// Παρορόμοιο αλλά με 2 Strings
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

	// Παρόμοιο με τα άλλα δύο
	// Δημιουργεί arrayList με applications και το περνάει σαν cookie
	private void getApplicationCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("appList");
		ArrayList<application> appList = new ArrayList<application>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM Application a LEFT OUTER JOIN Director d on d.app_code = a.app_code WHERE status = 0 AND accepted=0 AND isOnline=00;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				application Application = new application(rs.getInt("app_code"), rs.getInt("amount"),
						rs.getString("buy_type"), rs.getString("drivers_license"), rs.getInt("taxes"),
						rs.getString("username"), rs.getInt("repayTime"), rs.getString("tekmiriwsi"),
						rs.getString("tekmiriwsiDieuthinti"));
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

	// Παρόμοιο με τα άλλα τρία
	// Δημιουργεί arrayList με approved applications και το περνάει σαν cookie
	private void getApplicationApprovedCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("appListApproved");
		ArrayList<application> appList = new ArrayList<application>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM Application a LEFT OUTER JOIN Director d on d.app_code = a.app_code WHERE status = 1 AND accepted=1 AND isOnline=00;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				application Application = new application(rs.getInt("app_code"), rs.getInt("amount"),
						rs.getString("buy_type"), rs.getString("drivers_license"), rs.getInt("taxes"),
						rs.getString("username"), rs.getInt("repayTime"), rs.getString("tekmiriwsi"),
						rs.getString("tekmiriwsiDieuthinti"));
				appList.add(Application);
			}
			session.setAttribute("appListApproved", appList);
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

	// Παρόμοιο με τα άλλα τέσσερα
	// Δημιουργεί arrayList με disproved applications και το περνάει σαν cookie
	private void getApplicationDisprovedCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("appListDisproved");
		ArrayList<application> appList = new ArrayList<application>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM Application a LEFT OUTER JOIN Director d on d.app_code = a.app_code WHERE status = 1 AND accepted=0 AND isOnline=00;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				application Application = new application(rs.getInt("app_code"), rs.getInt("amount"),
						rs.getString("buy_type"), rs.getString("drivers_license"), rs.getInt("taxes"),
						rs.getString("username"), rs.getInt("repayTime"), rs.getString("tekmiriwsi"),
						rs.getString("tekmiriwsiDieuthinti"));
				appList.add(Application);
			}
			session.setAttribute("appListDisproved", appList);
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

	// Παρόμοιο με τα άλλα πέντε
	// Δημιουργεί arrayList με clients και το περνάει σαν cookie
	private void getAllClientCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("clientList");
		ArrayList<client> clientList = new ArrayList<client>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT User.username,full_name,id,atm,adt,salary,phone FROM User,Client WHERE User.username = Client.username ;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				client Client = new client(rs.getString("username"), rs.getString("full_name"), rs.getInt("atm"),
						rs.getInt("adt"), rs.getInt("salary"), rs.getInt("phone"), rs.getInt("id"));
				clientList.add(Client);

			}
			session.setAttribute("clientList", clientList);
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
	// Δημιουργεί arrayList με όλα τα application που γίναν online και το περνάει σαν cookie
	private void getApplicationOnliner(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("appListOnline");
		ArrayList<application> appList = new ArrayList<application>();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM Application WHERE isOnline=10 ;");
			rs = ps.executeQuery();
			while (rs.next() && rs != null) {
				application Application = new application(rs.getInt("app_code"), rs.getInt("amount"),
						rs.getString("buy_type"), rs.getString("drivers_license"), rs.getInt("taxes"),
						rs.getString("username"), rs.getInt("repayTime"), rs.getString("tekmiriwsi"),
						"  ");
				appList.add(Application);
			}
			session.setAttribute("appListOnline", appList);
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
