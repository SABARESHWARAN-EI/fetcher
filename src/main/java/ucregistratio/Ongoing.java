package ucregistratio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ongoing
 */
@WebServlet("/Ongoing")
public class Ongoing extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws NullPointerException,ServletException,IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		
		System.out.println("Ongoing java page");
		
		Connection con = null;
		PreparedStatement stm = null,st=null;
		int row_count =0;
		String role=null,provider=null,desc=null,location=null,start_t=null;
		ResultSet rs=null;
		String uemail=(String)session.getAttribute("uemail");
		String oid=(String)session.getAttribute("order_id");
//		
//		String arrive=request.getParameter("arrive");
//		String ready=request.getParameter("ready");
//		String known=request.getParameter("known");
		String start="no";
		start=request.getParameter("start");
//		String back="no";
//		back=request.getParameter("back");
//		
//		if(back.equals("Back to My orders"))
//		{
//			dispatcher = request.getRequestDispatcher("myOrders.jsp");
//			dispatcher.forward(request, response);
//		}
		
		if(start.equals("START"))
		{
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		  
		    stm = con.prepareStatement("UPDATE service  SET ustart=(?),ustatus=(?) WHERE id= (?)");
		    stm.setTimestamp(1,date);
		    stm.setString(2, "started");
		    stm.setString(3, oid);
		    System.out.println(date);
		    row_count=stm.executeUpdate();
		    if(row_count>0)
		    {
		    	  
			    st = con.prepareStatement("SELECT * from service  WHERE id=(?)");
			    st.setString(1, oid);
			   
			    rs=st.executeQuery();
			    while(rs.next())
			    {
			    role=rs.getString("urole");
			    desc=rs.getString("udesc");
			    location=rs.getString("ulocation");
			    provider=rs.getString("uprovider");
			    start_t=rs.getString("ustart");
			    }
	    	session.setAttribute("order_id",oid);
	    	session.setAttribute("uemail",uemail);
	    	session.setAttribute("urole",role);
	    	session.setAttribute("uprovider",provider);
	    	session.setAttribute("udesc",desc);
	    	session.setAttribute("ulocation",location);
	    	session.setAttribute("ustart", start_t);
	    	request.setAttribute("status","started");
	    	dispatcher = request.getRequestDispatcher("started.jsp");
			dispatcher.forward(request, response);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//PrepStmt.setTimestamp(1, date);
		}
		else if(row_count==0)
		{
			session.setAttribute("order_id",oid);
	    	session.setAttribute("uemail",uemail);
	    	request.setAttribute("status","failed");
	    	dispatcher = request.getRequestDispatcher("ongoing.jsp");
			dispatcher.forward(request, response);
		}
	}

}
