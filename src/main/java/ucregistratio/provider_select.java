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
 * Servlet implementation class provider_select
 */
@WebServlet("/provider_select")
public class provider_select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public provider_select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;
		System.out.println("providerservice");
		
		
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
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
		
		
			butn=request.getParameter("do");
		    
		    System.out.println("butn is :"+butn);
		    if(butn!="")
		    {
		    	
		    	try{  
					Class.forName("com.mysql.cj.jdbc.Driver");
				    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
				
				    PreparedStatement s1 = con.prepareStatement("select * from service WHERE id= ?");
				    s1.setString(1, butn);
				    
				    ResultSet rs1=s1.executeQuery();
				    while(rs1.next())
				    {
				    	butn=rs1.getString("ustatus");
				    	o_id=rs1.getString("id");
				    	uid=rs1.getString("uid");
				    	role=rs1.getString("urole");
						    desc=rs1.getString("udesc");
						    location=rs1.getString("ulocation");
						    provider=rs1.getString("uprovider");
						    date=rs1.getString("udate");
						    start=rs1.getString("ustart");
						    end=rs1.getString("uend");
						    hour=rs1.getString("duration_hour");
						    minute=rs1.getString("duration_minute");
						    ahrs=rs1.getString("uapphrs");
						    
						    PreparedStatement s3 = con.prepareStatement("select * from users WHERE id= ?");
						    s3.setString(1, uid);
						    
						    ResultSet rs3=s3.executeQuery();
						    while(rs3.next())
						    {
						    	uname=rs3.getString("uname");
						    	user_email=rs3.getString("uemail");
						    	user_mobile=rs3.getString("umobile");
						    }
						    
				    }
				    
				    
				    System.out.println("butn= " +butn);
				    System.out.println("btn= " +btn);
				    System.out.println("desc= " +desc);
				    System.out.println("location= " +location);
				    
				    if(butn.equals("accepted"))
				    {
				    	session.setAttribute("role", role);
				    	session.setAttribute("order_id",o_id);
				    	session.setAttribute("uemail",uemail);
				    	session.setAttribute("udesc",desc);
				    	session.setAttribute("ulocation",location);
				    	session.setAttribute("udate",date);
				    	session.setAttribute("uprovider",provider);
				    	session.setAttribute("username", uname);
				    	session.setAttribute("user_email",user_email );
				    	session.setAttribute("user_mobile", user_mobile);
				    	System.out.println("REDIRECTING TO start");
				    	dispatcher = request.getRequestDispatcher("start.jsp");
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
				    	session.setAttribute("username", uname);
				    	session.setAttribute("user_email",user_email );
				    	session.setAttribute("user_mobile", user_mobile);
				    	System.out.println("REDIRECTING TO started");
				    	dispatcher = request.getRequestDispatcher("end.jsp");
						dispatcher.forward(request, response);
				    }
				    if(butn.equals("payment_done"))
				    {
				    	session.setAttribute("role", role);
				    	session.setAttribute("order_id",o_id);
				    	session.setAttribute("uemail",uemail);
				    	session.setAttribute("udesc",desc);
				    	session.setAttribute("ulocation",location);
				    	session.setAttribute("udate",date);
				    	session.setAttribute("uprovider",provider);
				    	session.setAttribute("ustart",start);
				    	session.setAttribute("username", uname);
				    	session.setAttribute("user_email",user_email );
				    	session.setAttribute("user_mobile", user_mobile);
				    	System.out.println("REDIRECTING TO received");
				    	dispatcher = request.getRequestDispatcher("received");
						dispatcher.forward(request, response);
				    }
				    else {
				    	session.setAttribute("role", role);
				    	session.setAttribute("order_id",o_id);
				    	session.setAttribute("uemail",uemail);
				    	session.setAttribute("udesc",desc);
				    	session.setAttribute("ulocation",location);
				    	session.setAttribute("udate",date);
				    	session.setAttribute("uprovider",provider);
				    	session.setAttribute("ustart",start);
				    	session.setAttribute("username", uname);
				    	session.setAttribute("user_email",user_email );
				    	session.setAttribute("user_mobile", user_mobile);
				    	System.out.println("REDIRECTING TO received");
				    	dispatcher = request.getRequestDispatcher("provider_services");
						dispatcher.forward(request, response);
				    }

		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		    }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
