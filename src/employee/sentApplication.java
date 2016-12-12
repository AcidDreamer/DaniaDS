package employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sentApplication
 */
@WebServlet("/sentApplication")
public class sentApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("searcher"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int buy_type = Integer.parseInt(request.getParameter("buy_type"));
		int drivers_license = Integer.parseInt(request.getParameter("drivers_licence"));
		int taxes = Integer.parseInt(request.getParameter("taxes"));
	}

}
