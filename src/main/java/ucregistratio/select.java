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
 * Servlet implementation class select
 */
@WebServlet("/select")
public class select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("In select page");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		Connection con=null;

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
	//	String udate=(String)session.getAttribute("udate");
		String uemail=(String)session.getAttribute("uemail");
		String uid=(String)session.getAttribute("uid");
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
		String p_id=null;
    	butn=request.getParameter("do");
	    System.out.println("butn is :"+butn);
	    if(butn!="")
	    {
	    	
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
					    p_id=rs1.getString("provider_id");
					    location=rs1.getString("ulocation");
					    provider=rs1.getString("uprovider");
					    date=rs1.getString("udate");
					    start=rs1.getString("ustart");
					    end=rs1.getString("uend");
					    hour=rs1.getString("duration_hour");
					    minute=rs1.getString("duration_minute");
					    ahrs=rs1.getString("uapphrs");
					    add_desc=rs1.getString("Additional_charges_desc");
					    add_amt=rs1.getString("Additional_charges_amount");
					    amount=rs1.getString("uamount");
					    tamount=rs1.getString("tot_amount");
					    
			    }
			    
			    System.out.println("role is "+role);
			    System.out.println("butn= " +butn);
			    System.out.println("btn= " +btn);
			    if(butn.equalsIgnoreCase("applied")||butn.equalsIgnoreCase("declined"))
			    {
			    	session.setAttribute("role", role);
			    	session.setAttribute("order_id",o_id);
			    	session.setAttribute("uemail",uemail);
			    	session.setAttribute("udate",date);
			    	dispatcher = request.getRequestDispatcher("provider_list");
					dispatcher.forward(request, response);
			    	
			    }
			    if(butn.equalsIgnoreCase("accepted") ||butn.equalsIgnoreCase("payment_done")||butn.equalsIgnoreCase("started") || butn.equalsIgnoreCase("done") ||butn.equalsIgnoreCase("requested"))
			    {
			    	session.setAttribute("role", role);
			    	session.setAttribute("order_id",o_id);
			    	session.setAttribute("uemail",uemail);
			    	session.setAttribute("udesc",desc);
			    	session.setAttribute("ulocation",location);
			    	session.setAttribute("udate",date);
			    	session.setAttribute("uprovider",provider);
			    	System.out.println("REDIRECTING TO MyOrders");
			    	dispatcher = request.getRequestDispatcher("MyOrders");
					dispatcher.forward(request, response);
			    	
			    }
			   
			    if(butn.equalsIgnoreCase("completed"))
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
			    	session.setAttribute("hrs",hour);
			    	session.setAttribute("mins",minute);
			    	session.setAttribute("add_desc",add_desc);
			    	session.setAttribute("add_amt", add_amt);
			    	session.setAttribute("uamount",amount);
			    	session.setAttribute("tamount",tamount);
			    	System.out.println("REDIRECTING TO pay");
			    	dispatcher = request.getRequestDispatcher("Pay.jsp");
					dispatcher.forward(request, response);
			    }
			    if(butn.equalsIgnoreCase("payment_confirmed"))
			    {
			    	session.setAttribute("role", role);
			    	session.setAttribute("order_id",o_id);
			    	session.setAttribute("uemail",uemail);
			    	session.setAttribute("p_id", p_id);
			    	System.out.println("REDIRECTING TO rating");
			    	dispatcher = request.getRequestDispatcher("rating.jsp");
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
