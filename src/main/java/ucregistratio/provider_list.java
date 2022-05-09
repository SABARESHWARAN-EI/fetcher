package ucregistratio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Servlet implementation class provider_list
 */
@WebServlet("/provider_list")
public class provider_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	/**
	 * @param uname 
	 * @param uemail 
	 * @param umobile 
	 * @param uexperience 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		//System.out.println("providerlist");
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String pname="NN";	
		String _null="null";
		String p_id=null;
		String status="applied";
		String udate,date=null;
		udate=(String)session.getAttribute("udate");
		String urole=(String) session.getAttribute("role");
		String order__id=(String)session.getAttribute("order_id");
		int order_id=Integer.parseInt(order__id);
		int row_count;
		   System.out.println("pname is "+pname);
		   System.out.println("urole is "+urole);
		//System.out.println(urole);
		
        
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    PreparedStatement st = con.prepareStatement("select * from users WHERE uservice_role= ?");
		    st.setString(1, urole);
		    //System.out.println(urole);
		    System.out.println("In provider list");
		    
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
			out.print("<form action=\"back\" method=\"get\">"
					+ "<input type=\"submit\" value=\"Back to My Orders\" class=\"back\" name=\"back\" id=\"back\">"
					+ "</form>");
			out.print("<div style =\"text-align: center\"> * Search results for "+ urole
					+ "<br>* Cost : Rs.100 per hour"
					+ "<br>*  Minimum charge: Rs.100"
					+"<br>* Additional charges may apply during the service based on requirements</div><br>");
		    out.print("<div align='center' ><table id='tb' width=50% border=1  style=\"text-align:left\"> <form method=\"get\" action=\"provider_list\">");  
		    out.print("<caption>THE PROVIDERS ARE:</caption>");  
		      
		    		    
		    ResultSet rs=st.executeQuery();
		    

		    out.print("<tr><th>"+"Provider id"
		    			+"</th><th>"+"Name"
		    			+"</th><th>"+"Years Of Experience"
		    			+"</th><th>"+"Ratings"
		    			+"</th><th>"+"Request"+"</th></tr>");  
				     
		    while(rs.next())  
		    {  
		    	String name=rs.getString("uname");	
		    	String id=rs.getString("id");
		    	

				   System.out.println("name is "+name);
		    	 PreparedStatement s1 = con.prepareStatement("select * from service WHERE provider_id= ? ");
				    s1.setString(1,id);
				    ResultSet rs1=s1.executeQuery();
				    while(rs1.next())
				    {
				    	date=rs1.getString("udate");
				    }
				    System.out.println("udate is "+udate);
				    System.out.println("date is "+date);
				   if(!(udate.equals(date)))
				   {
		    out.print("<tr><td>"+rs.getString("id")
		    			+"</td><td>"+rs.getString("uname")
		    			+"</td><td>"+rs.getString("uexperience")
		    			+"</td><td>"+rs.getString("rating")
		    			+" <td><button type='submit' name='R_but' id='R_but' value="+rs.getString("uname")+">Request</button></td></tr>");  
		    
		    }  
		    }
//		    String[] f = request.getQueryString().split("=");
//		    pname=f[f.length-1];
//		    pname=URLDecoder.decode(pname,"utf-8");
		    pname = request.getParameter("R_but");
		    
		   // pname=request.getParameter("Request_val");
		    System.out.println("pname is "+pname);
		   
		  //  System.out.println(pname);

		    out.print("</form></table></div>");  

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		    // out.println("<br><form method='get' action='provider_list'><input type='submit' id='back' class='back' value='back'></form>");
			//out.print("<button onclick=\"location.href='?q="+ "back" + "\'\"> BACK </button>");
//			String b= request.getParameter("back");
//     		String bac="back";
//			 String[] b = request.getQueryString().split("=");
//		    String ba=b[b.length-1];
//		    ba=URLDecoder.decode(ba,"utf-8");
		
		
				 if(pname!="")
				 {
					    PreparedStatement s1 = con.prepareStatement("select * from users WHERE uname= ?");
					    s1.setString(1, pname);
					    ResultSet r1=s1.executeQuery();
					    while(r1.next())
					    	p_id=r1.getString("id");
					    status="requested";
				 }
				 if(pname.equals(""))
						pname=null;
				 PreparedStatement ps = con.prepareStatement("UPDATE service  SET uprovider=(?),ustatus=(?),provider_id=(?) WHERE id= (?)");
				    ps.setString(1, pname);
				    ps.setString(2, status);
				    ps.setString(3, p_id);
				    ps.setInt(4, order_id);
		    row_count=ps.executeUpdate();
		    if(status.equals("requested")) {
		    out.println("<meta http-equiv='refresh' content='3;URL=MyOrders'>");//redirects after 3 seconds
		    out.println("<p style='color:green;'>Requested for provider successfully!</p>");
		    
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	}
}
