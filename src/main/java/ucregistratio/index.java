package ucregistratio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail=request.getParameter("email");
		String upwd=request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		
		if(uemail==null || uemail.equals(""))
		{
			request.setAttribute("status","InvalidEmail");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd==null || upwd.equals(""))
		{
			request.setAttribute("status","InvalidPassword");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		//		PrintWriter out = response.getWriter();
//		out.println(uemail);
//		out.println(upwd);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
			PreparedStatement ps =con.prepareStatement("SELECT * from users WHERE uemail=? AND upwd=?");
			ps.setString(1,uemail); 
			ps.setString(2,upwd);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String role=rs.getString("urole");
				session.setAttribute("uid", rs.getString("id"));
				session.setAttribute("name",rs.getString("uname"));
				session.setAttribute("uemail",rs.getString("uemail"));
				if(role.equals("service_user"))
				dispatcher = request.getRequestDispatcher("user.jsp");
				if(role.equals("service_provider"))
					dispatcher = request.getRequestDispatcher("provider.jsp");
			}
			else {
				request.setAttribute("status","failed");
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
