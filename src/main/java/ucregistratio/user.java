package ucregistratio;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class user
 */
@WebServlet("/user")
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		String uid=(String)session.getAttribute("uid");
		String uname=(String)session.getAttribute("name");
		String uemail=(String)session.getAttribute("uemail");
		 String f = request.getParameter("bt");
		 System.out.println("User id in user: "+uid);
		 System.out.println("button clicked in user: ");
		// if (f.equals("logout"))
	       // {
//			 session.removeAttribute("uid");
//
//			 session.invalidate();
//
//			 response.setHeader("Pragma","no-cache");
//
//			 response.setHeader("Cache-Control","no-store");
//
//			 response.setHeader("Expires","0");
//
//			 response.setDateHeader("Expires",-1);
			// System.out.println("logout...");
		 //session.invalidate();
		    //    response.sendRedirect(request.getContextPath() + "/index.jsp");
			// response.sendRedirect("login.jsp");
			 //dispatcher.forward(request, response);
	      //  }
		 if (f.equals("Add a Service"))
	        {
			 session.setAttribute("uid",uid);
			 session.setAttribute("uemail",uemail);
			dispatcher = request.getRequestDispatcher("new_service.jsp");
			dispatcher.forward(request, response);
	        }
		 else if (f.equals("My Services"))
	        {
			 session.setAttribute("uid",uid);
			 session.setAttribute("uemail",uemail);
			 System.out.println("to orders");
			dispatcher = request.getRequestDispatcher("myOrders.jsp");
			dispatcher.forward(request, response);
	        }
	}

}
