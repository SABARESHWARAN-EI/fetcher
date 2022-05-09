package ucregistratio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class Request_list
 */
@WebServlet("/Request_list")
public class Request_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,NullPointerException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con = null;
		PreparedStatement st;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		System.out.println("In request");
		out.println("<html><head><title>My Orders</title>");
		//String cssTag="<link rel='stylesheet' type='text/css' href='css/style.css'>";
		out.println("<style>"
				+ "#tb {"
				+ "  font-family: Arial, Helvetica, sans-serif;"
				+ "  border-collapse: collapse;"
				+ "  width: 100%;"
				+ "}"
				+ "#tb td, #customers th {"
				+ "  border: 1px solid #ddd;"
				+ "  padding: 8px;"
				+ "}"
				+ "#tb tr:nth-child(even){background-color: #f2f2f2;}"
				+ "#tb tr:hover {background-color: #ddd;}"
				+ "#tb th {"
				+ "  padding-top: 12px;"
				+ "  padding-bottom: 12px;"
				+ "  text-align: left;"
				+ "  background-color: #6dabe4;"
				+ "  color: white;"
				+ "}"
				+ "</style>");
		out.println("</head><body>");
		ResultSet r1 = null;
		ResultSet r2 = null;
		String name=(String)session.getAttribute("name");
		System.out.println(name);
		
		  out.print("<form><input type='submit' name='back' id='back' value='back'>");
		    
		
		try {
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			 
			st = con.prepareStatement("select * from service WHERE uprovider = ? AND ustatus=?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setString(1,name);
		    st.setString(2,"requested");
		    r1=st.executeQuery();
		    out.print(" <table id='tb' width=50% border=1  style='text-align:left' style='margin-left: auto; margin-right: auto;'>");
		    out.print("<caption>YOU HAVE THE FOLLOWING REQUESTS:</caption>"
		    		+ "    <form method=\"get\" action=\"Request_list\">"
		    		+ "    <b>"
		    		+ "    <tr>"
		    		+ "    <th>ORDER ID</th>"
		    		+ "    <th>NAME</th>"
		    		+ "	<th>EMAIL</th>"
		    		+ "	<th>NUMBER</th>"
		    		+ "	<th>DESCRIPTION</th>"
		    		+ "	<th>LOCATION</th>"
		    		+ "	<th>DATE</th>"
		    		+ "	<th>TIME</th>"
		    		+ "	<th>Appx. Hours / Reason </th>"
		    		+ "	<th>ACCEPT</th>"
		    		+ "	<th>DECLINE</th>"
		    		+ "	</tr>");
		    if(r1.next()==false)
		    {
		    	System.out.println("No requests");
		    	request.setAttribute("status","No requests");
		    	dispatcher = request.getRequestDispatcher("provider.jsp");
				dispatcher.forward(request, response);
		    }
		    r1.beforeFirst();
		    while(r1.next())
		    {
		    	String user_id=r1.getString("uid");
		    	PreparedStatement st2 = con.prepareStatement("select * from users WHERE id = ?");
		        st2.setString(1,user_id);
		        r2=st2.executeQuery();
		        while(r2.next())
		    	{
		        	out.print("<tr>\r\n"
		        			+ "<td>"+r1.getString("id") +"</td>"
		        			+ "<td>"+r2.getString("uname") +"</td>"
		        			+ "<td>"+r2.getString("uemail") +"</td>"
		        			+ "<td>"+r2.getString("umobile")+"</td>"
		        			+ "<td>"+r1.getString("udesc") +"</td>"
		        			+ "<td>"+r1.getString("ulocation")+"</td>"
		        			+ "<td>"+r1.getString("udate") +"</td>"
		        			+ "<td>"+r1.getString("utime")+"</td>"
		        			+ "<td><input type='text' name='hours' id='hours'></td>"
		        			+ "<td><button type='submit' name='accept' id='accept' value="+r1.getString("id")+">ACCEPT</button></td>"
		        			+ "<td><button type='submit' name='decline' id='decline' value="+r1.getString("id") +">DECLINE</button></td>"
		        			
		        			+ "</tr>"
		        			);	
		    	}
		        
		    } 
		    out.print("</table></form>");
		    
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
		String uname=(String)session.getAttribute("name");
		String uemail=(String)session.getAttribute("uemail");
		// int f =0;
		 String o_id;
//		 String acc=request.getParameter("accept");
//		 if(!(acc.equals(null)))
//		 f= Integer.parseInt(acc);
//		 System.out.println("f is ");
//		 System.out.println(f);
		 String hrs=request.getParameter("hours");
		 String ans=request.getParameter("accept");
		 String status="accepted";
		 int row_count=0;
		 if(ans==null)
		 {
			 ans=request.getParameter("decline");
			status="declined";
		 }
		 
		 o_id=ans;
		 
		 System.out.println(o_id);
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			 
			// out.print("<button onclick=\"location.href='?q="+ "back" + "\'\"> BACK </button>");
			
			 String b= request.getParameter("back");
	     	//String bac="back";
				
				if(b!=null)
				{
					 dispatcher = request.getRequestDispatcher("provider.jsp");

						dispatcher.forward(request, response);
				}
				
				if(b==null)
				{
					
			 PreparedStatement ps = con.prepareStatement("UPDATE service  SET ustatus=(?),uapphrs=(?) WHERE id= (?)");
			    ps.setString(1, status);
			    ps.setString(2, hrs);
			    ps.setString(3, o_id);	    
			 row_count=ps.executeUpdate();
			 if(row_count>0)
			 {
				 out.println("<p style='color:green;'>Request "+status+" successfully!</p>");
				 out.println("<meta http-equiv='refresh' content='1;URL=provider.jsp'>");//redirects after 3 seconds
					
				 System.out.println(row_count);
			 }
				}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}

}
