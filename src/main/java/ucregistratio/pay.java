package ucregistratio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class pay
 */
@WebServlet("/pay")
public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay() {
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
		System.out.println("Ended page");
		Connection con = null;
		PreparedStatement ps = null;
		int row_count=0;
		String provider=(String)session.getAttribute("provider_id");
		String uemail=(String)session.getAttribute("uemail");
		String oid=(String)session.getAttribute("order_id");
		String pay=request.getParameter("paid");
		System.out.println("provider is "+provider);
		System.out.println("pay :" + pay);
		if(pay.equals("paid"))
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			
			    ps = con.prepareStatement("UPDATE service  SET umode=(?),ustatus=(?) WHERE id= (?)");
			    ps.setString(1,"cash");
			    ps.setString(2,"payment_done");
			    ps.setString(3, oid);
			    System.out.println("payment_done");
			    row_count=ps.executeUpdate();
			    if(row_count>0)
			    {
			    	session.setAttribute("order_id",oid);
			    	session.setAttribute("uemail",uemail);
			    	session.setAttribute("provider_id", provider);
			    	request.setAttribute("status","payment_done");
			    	dispatcher = request.getRequestDispatcher("MyOrders");
					dispatcher.forward(request, response);
			    }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}