package ucregistratio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class back
 */
@WebServlet("/back")
public class back extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		String back="back";
		back=request.getParameter("back");
		if(back.equals(null))
			back="back";
		
						
		if(back.equals("Back to My Orders"))
		{
			dispatcher = request.getRequestDispatcher("MyOrders");
			dispatcher.forward(request, response);
			return;
		}
		if(back.equals("Back to My Services"))
		{
			dispatcher = request.getRequestDispatcher("provider_services");
			dispatcher.forward(request, response);
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		String back="back";
		back=request.getParameter("back");
		if(back.equals(null))
			back="back";
		
						
		if(back.equals("Back to My Orders"))
		{
			dispatcher = request.getRequestDispatcher("MyOrders");
			dispatcher.forward(request, response);
			return;
		}
		if(back.equals("Back to My Services"))
		{
			dispatcher = request.getRequestDispatcher("provider_services");
			dispatcher.forward(request, response);
			return;
		}
	}

}
