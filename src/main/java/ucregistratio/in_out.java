package ucregistratio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class in_out
 */
@WebServlet("/in_out_slt")
public class in_out extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		//System.out.println("providerlist");
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		PreparedStatement ps=null;
		int row_count=0;
		String name=(String)session.getAttribute("name");
		String uid=(String)session.getAttribute("uid");
		String val=(String)request.getParameter("btn");
		
		try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    ps = con.prepareStatement("UPDATE users SET  check_status =(?) WHERE id=(?)");
		    ps.setString(1, val);
		    ps.setString(2, uid);
		    
		    row_count=ps.executeUpdate();
		    if(row_count>0)
		    {
		    	request.setAttribute("status",val);
		    	dispatcher = request.getRequestDispatcher("provider.jsp");
				dispatcher.forward(request, response);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}


}
