package ucregistratio;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class registrationServlet
 */
@WebServlet("/register")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//			PrintWriter out = response.getWriter();
//			out.print("Working");
		
		
		String uname=request.getParameter("name");
		String uemail=request.getParameter("email");
		String upwd=request.getParameter("pass");
		String umobile=request.getParameter("contact");
		String urole=request.getParameter("role");
		String uservice_role=request.getParameter("service_role");
		String uexperience=request.getParameter("experience");
		

		int l=umobile.length();
		RequestDispatcher dispatcher=null;
		Connection con=null;
//		
//		PrintWriter out = response.getWriter();
//		out.println(uname);
//		out.println(uemail);
//		out.println(upwd);
//		out.println(umobile);
		
		if(uemail==null || uemail.equals(""))
		{
			request.setAttribute("status","InvalidEmail");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(uname==null || uname.equals(""))
		{
			request.setAttribute("status","InvalidName");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(upwd==null || upwd.equals(""))
		{
			request.setAttribute("status","InvalidPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		else if(umobile==null || umobile.equals(""))
		{
			request.setAttribute("status","InvalidMobile");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		else if(l<10 ||l>10)
		{
			request.setAttribute("status","InvalidMobileLength");
			System.out.println("InvalidMobileLength");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		else 
		{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			
		    boolean uemailExists=false;
		    
		    PreparedStatement st = con.prepareStatement("select * from users where uemail = ?");
		    st.setString(1, uemail);
		    ResultSet r1=st.executeQuery();
		    if(r1.next()) {
		      uemailExists = true;
		    }
		    System.out.println(uemailExists);
		    if(uemailExists==true)
		    {
		    	request.setAttribute("status","alreadyRegistered");
		    	dispatcher=request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
		    }

		    else {
		    	 PreparedStatement ps;
		    	if(urole.equals("service_provider"))
		    	{
		    	ps =con.prepareStatement("INSERT INTO users (uname,upwd,uemail,umobile,urole,uexperience,uservice_role,rating) VALUES (?,?,?,?,?,?,?,'4')");
		    	}
		    	else {
		         ps =con.prepareStatement("INSERT INTO users (uname,upwd,uemail,umobile,urole,uexperience,uservice_role) VALUES (?,?,?,?,?,?,?)");
			
		    	}
		    ps.setString(1, uname);
			ps.setString(2, upwd);
			ps.setString(3, uemail);
			ps.setString(4, umobile);
			ps.setString(5, urole);
			ps.setString(6, uexperience);
			ps.setString(7, uservice_role);
			int row_count=ps.executeUpdate();
			dispatcher=request.getRequestDispatcher("registration.jsp");
			if(row_count>0)
			{
				request.setAttribute("status", "success");
			}
			else
			{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request,response);
			
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		}
	}
	
	

}
