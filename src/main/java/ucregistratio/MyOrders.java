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

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		System.out.println(" In My Orders");
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
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
//		out.print("<button onclick=\"location.href='?q="+ "back" + "\'\"> BACK </button></body></html>	");

		out.print("<form action='back_p' method='get'>"
				+ "<input type='submit' value='Back to User' class='back_home' name='back_home' id='back_home'>"
				+ "</form>");
		String uemail=(String)session.getAttribute("uemail");
		String uid=(String)session.getAttribute("uid");
		//String udate=(String)session.getAttribute("udate");
		System.out.println("the user id in my orders "+uid);
		String status=null;
		String btn = "Done";
		String butn;
		String role=null;
		String o_id=null;
		String desc=null;
		String location=null;
		String provider=null;
		String hour=null;
		String minute=null;
		String amount=null;
		String start=null;
		String end=null;
		String date=null;
		String ahrs=null;
		String add_desc=null;
		String add_amt=null;
		String tamount=null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
	
	    out.print("<div align='center'><table id='tb' width=50% border=1  style='text-align:left' style='margin-left: auto; margin-right: auto;'>");  
	    out.print("<div style='color:#04AA6D'><caption> My orders are:</caption></div>");  
	    out.print("<tr><th>"+"Order Id"+"</th><th>"+"Role"+"</th><th>"+"Description" +"</th><th>"+"Address"+"</th><th>"+"Date" +"</th><th>"+"Time" +"</th><th>"+"Service Provider" +"</th><th>"+"Provider ID" +"</th><th>"+"Status" +"</th><th>"+"Appx. hours/ Reason"+"</th><th>"+"Amount Paid" +"</th><th>"+"Option" +"</th></tr>");  
		out.print("<form action=\"select\" method=\"get\">");
	    PreparedStatement st = con.prepareStatement("SELECT * FROM service WHERE uid=(?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    st.setString(1, uid);
	    
	    ResultSet rs=st.executeQuery();
	    if(rs.next()==false)
	    {
	    	System.out.println("No services");
	    	request.setAttribute("status","No Orders");
	    	dispatcher = request.getRequestDispatcher("user.jsp");
			dispatcher.forward(request, response);
	    }
	    rs.beforeFirst(); 
	    while(rs.next()) 
	    {  
	    	
	    	status=rs.getString("ustatus");
	    	System.out.println("In my orders: status: ");
	    	System.out.println(status);
	    	if(status.equalsIgnoreCase("applied")||status.equalsIgnoreCase("declined"))
	    		btn="Do Request";
	    	if(status.equalsIgnoreCase("requested"))
	    		btn="Requested";
	    	if(status.equalsIgnoreCase("accepted"))
	    		btn="Accepted";
	    	if(status.equalsIgnoreCase("started"))
	    		btn="Work Started";
	    	if(status.equalsIgnoreCase("completed"))
	    		btn="Go to payment";
	    	if(status.equalsIgnoreCase("payment_done"))
	    		btn="waiting for payment Confirmation";
	    	
	    	if(status.equalsIgnoreCase("payment_confirmed"))
	    		btn="Go to rating";
	    	if(status.equalsIgnoreCase("Done"))
	    		btn="Done";
	    		
	    	System.out.println("In my orders: btn: ");
	    	System.out.println(btn);
	        //out.print("<form><tr><td>"+rs.getString("id")+"</td><td>"+rs.getString("urole")+"</td><td>"+rs.getString("udesc")+"</td><td>"+rs.getString("ulocation")+"</td><td>"+rs.getString("udate")+"</td><td>"+rs.getString("utime")+"</td><td>"+rs.getString("uprovider")+"</td><td>"+rs.getString("provider_id") +"</td><td>"+rs.getString("ustatus")+"</td><td>"+rs.getString("uapphrs")+"</td><td>"+rs.getString("tot_amount")+"</td><td><button onclick=\"location.href='?q="+ rs.getString("id") + "\'\">"+ btn +"</button>"+"</td></tr></form>");  
	    	 out.print("<tr><td>"+rs.getString("id")+"</td><td>"+rs.getString("urole")+"</td><td>"+rs.getString("udesc")+"</td><td>"+rs.getString("ulocation")+"</td><td>"+rs.getString("udate")+"</td><td>"+rs.getString("utime")+"</td><td>"+rs.getString("uprovider")+"</td><td>"+rs.getString("provider_id") +"</td><td>"+rs.getString("ustatus")+"</td><td>"+rs.getString("uapphrs")+"</td><td>"+rs.getString("tot_amount")+"</td><td><button type='submit' name='do' id='do' value='"+rs.getString("id")+"'>"+btn+"</button>" +"</td></tr>");  
			 
	        
	          }
	    out.print("</form></div></table>"); 
	    System.out.println("In my orders outsiide table: status: ");
    	System.out.println(status);
	    System.out.println("In my orders: btn: ");
    	System.out.println(btn);
    	  
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
