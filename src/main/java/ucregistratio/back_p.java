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
 * Servlet implementation class back_p
 */
@WebServlet("/back_p")
public class back_p extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws NullPointerException,ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		String back="back";
		back=request.getParameter("back_home");
		if(back.equals(null))
			back="back";
			
		System.out.println(back);
		if(back.equals("Back to Provider"))
		{
			dispatcher = request.getRequestDispatcher("provider.jsp");
			dispatcher.forward(request, response);
		}
		if(back.equals("Back to User"))
		{
			dispatcher = request.getRequestDispatcher("user.jsp");
			dispatcher.forward(request, response);
		}
	}

}
