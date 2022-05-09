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


@WebServlet("/provider_services")
public class provider_services extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		System.out.println("providerservice");
		
		
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		out.println("<html><head><title>My Services</title>");
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
		
		String uemail=(String)session.getAttribute("uemail");
		String pname=(String)session.getAttribute("name");
		String pid=(String)session.getAttribute("uid");
		String status=null;
		String btn = "Done";
		String butn;
		String uid=null;
		String role=null;
		String o_id=null;
		String desc=null;
		String location=null;
		String provider=null;
		String uname=null;
		String user_email=null;
		String user_mobile=null;
		String hour=null;
		String minute=null;
		String amount=null;
		String start=null;
		String end=null;
		String date=null;
		String ahrs=null;
		System.out.println(pid);
	//	String _null="null";
	
	//	String status="applied";
		
	//	int order_id=Integer.parseInt(order__id);
		int row_count;
		//System.out.println(urole);
		
		
		out.print("<form action='back_p' method='get'>"
				+ "<input type='submit' value='Back to Provider' class='back_home' name='back_home' id='back_home'>"
				+ "</form>");
		out.print("<form action=\"provider_select\" method=\"get\">");
		   
        
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    PreparedStatement st = con.prepareStatement("select * from service WHERE provider_id= ? AND ustatus !=(?) OR (?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    st.setString(1, pid);
		    st.setString(2,"requested");
		    st.setString(3,"declined");
		    //System.out.println(urole);
		    
		    out.print("<table id='tb' width=50% border=1  style='text-align:left' style='margin-left: auto; margin-right: auto;'>");  
		    out.print("<caption>THE SERVICES MAPPED AND UNDERGONE ARE:</caption>");  
		      
		    		    
		    ResultSet rs=st.executeQuery();

		    out.print("<b><tr><th>"+"ORDER ID"+"</th><th>"+"NAME"+"</th><th>"+"Email"+"</th><th>"+"Mobile" +"</th><th>"+"DESCRIPTION"+"</th><th>"+"LOCATION"+"</th><th>"+"DATE"+"</th><th>"+"START TIME"+"</th><th>"+"END TIME"+"</th><th>"+"AMOUNT"+"</th><th>"+"PAYMENT MODE"+"</th><th>"+"STATUS"+"</th><th>"+"OPTION"+"</th></tr></b>");  
				        
		    if(rs.next()==false)
		    {
		    	System.out.println("No services");
		    	request.setAttribute("status","No services");
		    	dispatcher = request.getRequestDispatcher("provider.jsp");
				dispatcher.forward(request, response);
		    }
		    rs.beforeFirst(); 
		    while(rs.next())  
		    {  
		    	uid=rs.getString("uid");
		    	status=rs.getString("ustatus");
		    	
		    	if(status.equalsIgnoreCase("accepted"))
		    		btn="Go to start";
		    	else if(status.equalsIgnoreCase("started"))
		    		btn="Go to end";
		    	else if(status.equalsIgnoreCase("payment_done"))
		    		btn="Payment received";
		    	else 
		    		btn="Done";
			    PreparedStatement st1 = con.prepareStatement("select * from users WHERE id= ?");
			    st1.setString(1, uid);
			    ResultSet rs2=st1.executeQuery();
			    while(rs2.next())
		   // out.print("<tr><td>"+rs.getString("id")+"</td><td>"+rs2.getString("uname")+"</td><td>"+rs2.getString("uemail")+"</td><td>"+rs2.getString("umobile")+"</td><td>"+rs.getString("udesc")+"</td><td>"+rs.getString("ulocation")+"</td><td>"+rs.getString("udate")+"</td><td>"+rs.getString("ustart")+"</td><td>"+rs.getString("uend")+"</td><td>"+rs.getString("uamount")+"</td><td>"+rs.getString("umode")+"</td><td>"+rs.getString("ustatus")+"</td><td><button type='submit' onclick=\"location.href='?q="+ rs.getString("id") + "\'\">"+ btn +"</button>"+"</td></tr>");  
			    	 out.print("<tr><td>"+rs.getString("id")+"</td><td>"+rs2.getString("uname")+"</td><td>"+rs2.getString("uemail")+"</td><td>"+rs2.getString("umobile")+"</td><td>"+rs.getString("udesc")+"</td><td>"+rs.getString("ulocation")+"</td><td>"+rs.getString("udate")+"</td><td>"+rs.getString("ustart")+"</td><td>"+rs.getString("uend")+"</td><td>"+rs.getString("tot_amount")+"</td><td>"+rs.getString("umode")+"</td><td>"+rs.getString("ustatus")+"<td><button type='submit' name='do' id='do' value='"+rs.getString("id")+"'>"+btn+"</button></td>"+"</tr>");  
				   

			    
			       }  
		    out.print("</form></table>");  
		    System.out.println("DONE");
		    
//		    
//		    String[] f = request.getQueryString().split("=");
//		    butn=f[f.length-1];
//		    butn=URLDecoder.decode(butn,"utf-8");
		  
//			out.print("<button onclick=\"location.href='?q="+ "back" + "\'\"> BACK </button>");
////			String b= request.getParameter("back");
//     		String bac="back";
//			 String[] b = request.getQueryString().split("=");
//		    String ba=b[b.length-1];
//		    ba=URLDecoder.decode(ba,"utf-8");
//
//		    
//			if(ba.equals(bac))
//			{
//				 dispatcher = request.getRequestDispatcher("provider.jsp");
//					dispatcher.forward(request, response);
//			}
		  //  System.out.println(pname);

		  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
