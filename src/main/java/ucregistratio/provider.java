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

@WebServlet("/provider")
public class provider extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		String id=(String)session.getAttribute("uid");
		String uname=(String)session.getAttribute("name");
		System.out.println(" In provider first pid is"+ id);
		 String f = request.getParameter("bt");
		 System.out.println(f);
//		 if (f.equals("logout"))
//	        {
//			 session.removeAttribute("uid");
//			 session.invalidate();
//			 response.setHeader("Pragma","no-cache");
//			 response.setHeader("Cache-Control","no-store");
//			 response.setHeader("Expires","0");
//			 response.setDateHeader("Expires",-1);
//			 //response.sendRedirect("login.jsp");
//			// session.invalidate();
//		        response.sendRedirect(request.getContextPath() + "/index.jsp");
//			  //  dispatcher.forward(request, response);
//	        }
//		 else if (f.equals("request list"))
//	        {
//			 session.setAttribute("name",uname);
//			 session.setAttribute("uid",id);
//			System.out.println("redirecting to request list");	
//			dispatcher = request.getRequestDispatcher("request_list.jsp");
//			dispatcher.forward(request, response);
//	        }
//		
//		 else if (f.equals("my services"))
//	        {
//			 session.setAttribute("name",uname);
//			 session.setAttribute("uid",id);
//			 System.out.println(" In provider pid is"+ id);
//			dispatcher = request.getRequestDispatcher("provider_services.jsp");
//		    dispatcher.forward(request, response);
//	        }
//	
	}

}
