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
 * Servlet implementation class rating
 */
@WebServlet("/rating")
public class rating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		
		Connection con = null;
		PreparedStatement ps = null,st=null,pst=null;
		int row_count=0,r_c=0;
		double u_rating=0.0;
		System.out.println("RATING JAVA PAGE");
		String uemail=(String)session.getAttribute("uemail");
		String oid=(String)session.getAttribute("order_id");
		String p_id=(String)session.getAttribute("p_id");
		String rate=request.getParameter("rating");
		int rating=Integer.parseInt(request.getParameter("rating"));
		String submit=request.getParameter("submit");
		ResultSet rs=null;
	
		System.out.println("rate");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    System.out.println("p_id is"+p_id);
		    st=con.prepareStatement("SELECT * from users where id=(?)");
		    st.setString(1, p_id);
		    rs=st.executeQuery();
		    while(rs.next())
		    {
		    	u_rating=Double.parseDouble(rs.getString("rating"));

			    System.out.println(u_rating);
		    }
		    

		    ps = con.prepareStatement("UPDATE service  SET urating=(?),ustatus=(?) WHERE id= (?)");
		    ps.setString(1,rate);
		    ps.setString(2,"Done");
		    ps.setString(3, oid);
		    
		    row_count=ps.executeUpdate();
		     System.out.println(rating);
		    u_rating=(u_rating+rating)/2;
		    System.out.println(u_rating);
		    pst = con.prepareStatement("UPDATE users  SET rating=(?) WHERE id= (?)");
		    pst.setDouble(1,u_rating);
		    pst.setString(2,p_id);
		    
		    r_c=pst.executeUpdate();
		    if(row_count>0 && r_c>0)
		    {
		    	System.out.println("Done update");
		    	session.setAttribute("order_id",oid);
		    	session.setAttribute("uemail",uemail);
		    	request.setAttribute("status","Done");
		    	dispatcher = request.getRequestDispatcher("user.jsp");
				dispatcher.forward(request, response);
		    }

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
