package ucregistratio;

import java.io.IOException;
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
 * Servlet implementation class end
 */
@WebServlet("/end")
public class end extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public end() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		
		System.out.println("Started java page");
		
		Connection con = null;
		PreparedStatement stm = null;
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		int row_count =0,rc2=0,rc3=0;
		ResultSet rs=null;
		int minute=0;
		int amount=0,hour=0,tamount=0;
		String role=null,provider=null,desc=null,location=null,start=null,end=null,provider_id=null;
		String uemail=(String)session.getAttribute("uemail");
		String oid=(String)session.getAttribute("order_id");
		String add_desc=request.getParameter("add_desc");
		int add_amt=Integer.parseInt(request.getParameter("add_amount"));
//		
//		String finished=request.getParameter("finished");
//		String work=request.getParameter("work");
//		String known=request.getParameter("known");
		
		
		String btn=request.getParameter("end");
//		String back=request.getParameter("back");
//		
//		if(back.equals("Back to My Orders"))
//		{
//			dispatcher = request.getRequestDispatcher("myOrders.jsp");
//			dispatcher.forward(request, response);
//		}
			
		
		System.out.println(btn);
		
		if(btn.equalsIgnoreCase("end"))
		{
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    stm = con.prepareStatement("UPDATE service  SET uend=(?),Additional_charges_desc=(?),Additional_charges_amount=(?) WHERE id= (?)");
		    stm.setTimestamp(1,date);
		    stm.setString(2, add_desc);
		    stm.setInt(3,add_amt);
		    stm.setString(4, oid);
		    System.out.println(date);
		    row_count=stm.executeUpdate();
		    
		    if(row_count>0)
		    {
		    	System.out.println("status: completed");
			    Class.forName("com.mysql.cj.jdbc.Driver");
			    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			
			    ps = con.prepareStatement("UPDATE service SET  duration_hour=(TIMESTAMPDIFF(HOUR,ustart,uend)),duration_minute =((TIMESTAMPDIFF(MINUTE,ustart,uend))-(duration_hour*60)) WHERE id=(?)");
			    ps.setString(1, oid);
			    
			    rc2=ps.executeUpdate();
			    
			    if(rc2>0)
			    {
			    	System.out.println("duration: calculated");
			    	  ps2 = con.prepareStatement("SELECT * from service  WHERE id=(?)");
					    ps2.setString(1, oid);
					   
					    rs=ps2.executeQuery();
					    while(rs.next())
					    {
					    role=rs.getString("urole");
					    desc=rs.getString("udesc");
					    location=rs.getString("ulocation");
					    provider=rs.getString("uprovider");
					    provider_id=rs.getString("provider_id");
					    start=rs.getString("ustart");
					    end=rs.getString("uend");
					    String h=rs.getString("duration_minute");
					    hour=Integer.parseInt(rs.getString("duration_hour"));
					    minute=Integer.parseInt(rs.getString("duration_minute"));
					    System.out.println("Minute : "+minute);
					    System.out.println("Hour : "+hour);
					    System.out.println("H : "+h);
					    Double m=60.0;
					    Double d=m/minute;
					    System.out.println("Double d : "+d);
					    amount=amount+((int) Math. round(100/d));
					    System.out.println("minute amount : "+amount);
					    amount=amount+(hour*100);
					    
					    if(amount<100)
					    	amount=100;
					    tamount=amount+add_amt;
					    }
					    ps3 = con.prepareStatement("UPDATE service SET ustatus=(?),uamount=(?),tot_amount=(?) WHERE id=(?)");
					    //System.out.println("string amount "+String.valueOf(amount));
					    System.out.println("amount : "+amount);
					    System.out.println("tamount : "+tamount);
					    ps3.setString(1, "completed");
					    ps3.setInt(2,amount);
					    ps3.setInt(3,tamount);
					    ps3.setString(4, oid);
					    
					    rc3=ps3.executeUpdate();
					    if(rc3>0) {
					    	System.out.println("rc3 :" +rc3);
		    	session.setAttribute("order_id",oid);
		    	session.setAttribute("uemail",uemail);
		    	session.setAttribute("urole",role);
		    	session.setAttribute("uprovider",provider);
		    	session.setAttribute("provider_id", provider_id);
		    	session.setAttribute("udesc",desc);
		    	session.setAttribute("ulocation",location);
		    	session.setAttribute("ustart",start);
		    	session.setAttribute("uend",end);
		    	session.setAttribute("hrs",hour);
		    	session.setAttribute("mins",minute);
		    	session.setAttribute("add_desc",add_desc);
		    	session.setAttribute("add_amt", add_amt);
		    	session.setAttribute("uamount",amount);
		    	session.setAttribute("tamount",tamount);
		    	dispatcher = request.getRequestDispatcher("provider_services");
				dispatcher.forward(request, response);
			    }
			    }
		    }
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//PrepStmt.setTimestamp(1, date);
		}
	}


	private String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
