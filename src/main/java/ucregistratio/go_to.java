package ucregistratio;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class go_to
 */
@WebServlet("/go_to")
public class go_to extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public go_to() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String uemail=(String)session.getAttribute("uemail");
		String uid=(String)session.getAttribute("uid");
		System.out.println("the user id in my orders "+uid);
		String status=null;
		String btn = "Done";
		String butn=(String)session.getAttribute("butn");;
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
		
		
		try {
			
			

			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		   
			
		PreparedStatement s1 = con.prepareStatement("select * from service WHERE id= ?");
	    s1.setString(1, butn);
	    
	    ResultSet rs1=s1.executeQuery();
	    while(rs1.next())
	    {
	    	butn=rs1.getString("ustatus");
	    	o_id=rs1.getString("id");;
	    	role=rs1.getString("urole");
			    desc=rs1.getString("udesc");
			    location=rs1.getString("ulocation");
			    provider=rs1.getString("uprovider");
			    date=rs1.getString("udate");
			    start=rs1.getString("ustart");
			    end=rs1.getString("uend");
			    hour=rs1.getString("duration_hour");
			    minute=rs1.getString("duration_minute");
			    
	    }
	    System.out.println("butn= " +butn);
	    System.out.println("btn= " +btn);
	    if(butn.equals("applied")||butn.equals("declined"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	dispatcher = request.getRequestDispatcher("provider_list");
			dispatcher.forward(request, response);
	    	
	    }
	    if(butn.equals("accepted"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	session.setAttribute("udesc",desc);
	    	session.setAttribute("ulocation",location);
	    	session.setAttribute("udate",date);
	    	session.setAttribute("uprovider",provider);
	    	System.out.println("REDIRECTING TO ONGOING");
	    	dispatcher = request.getRequestDispatcher("ongoing.jsp");
			dispatcher.forward(request, response);
	    	
	    }
	    if(butn.equals("started"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	session.setAttribute("udesc",desc);
	    	session.setAttribute("ulocation",location);
	    	session.setAttribute("udate",date);
	    	session.setAttribute("uprovider",provider);
	    	session.setAttribute("ustart",start);
	    	System.out.println("REDIRECTING TO started");
	    	dispatcher = request.getRequestDispatcher("started.jsp");
			dispatcher.forward(request, response);
	    }
	    if(butn.equals("completed"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	session.setAttribute("udesc",desc);
	    	session.setAttribute("ulocation",location);
	    	session.setAttribute("udate",date);
	    	session.setAttribute("uprovider",provider);
	    	session.setAttribute("ustart",start);
	    	session.setAttribute("uend",end);
	    	System.out.println("REDIRECTING TO Ended");
	    	dispatcher = request.getRequestDispatcher("Ended.jsp");
			dispatcher.forward(request, response);
	    }
	    if(butn.equals("paid"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	System.out.println("REDIRECTING TO rating");
	    	dispatcher = request.getRequestDispatcher("rating.jsp");
			dispatcher.forward(request, response);
	    	
	    }
	   
	    
	    if(butn.equalsIgnoreCase("done"))
	    {
	    	session.setAttribute("role", role);
	    	session.setAttribute("order_id",o_id);
	    	session.setAttribute("uemail",uemail);
	    	System.out.println("REDIRECTING TO user");
	    	dispatcher = request.getRequestDispatcher("user.jsp");
			dispatcher.forward(request, response);
	    }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	

}
