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
 * Servlet implementation class received
 */
@WebServlet("/received")
public class received extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public received() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		
		System.out.println("Started java page");
		
		Connection con = null;
		PreparedStatement ps=null;
		int row_count=0;
		String uemail=(String)session.getAttribute("uemail");
		String oid=(String)session.getAttribute("order_id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
		
		    ps = con.prepareStatement("UPDATE service  SET ustatus=(?) WHERE id= (?)");
		    ps.setString(1,"payment_confirmed");
		    ps.setString(2, oid);
		    System.out.println("payment_confirmed");
		    row_count=ps.executeUpdate();
		    if(row_count>0)
		    {
		    	session.setAttribute("order_id",oid);
		    	session.setAttribute("uemail",uemail);
		    	request.setAttribute("status","payment_done");
		    	dispatcher = request.getRequestDispatcher("provider_services");
				dispatcher.forward(request, response);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
