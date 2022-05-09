package ucprj;

import java.sql.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				String url="jdbc:mysql://localhost:3306/newdb";
				String uname="root";
				String pass="@Sabaresh2101";
				String query = "SELECT uname FROM login WHERE uname=? AND upass=?";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(url,uname,pass);
				
				String n=request.getParameter("txtname");
				String p=request.getParameter("txtpwd");
				
				PreparedStatement ps= con.prepareStatement(query);
				ps.setString(1, n);
				ps.setString(2, p);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
					rd.forward(request, response);
				}
				else
				{
					System.out.println("<font color=red size=18> LOGIN FAILED!!! ");
					System.out.println("<a href=login.jsp>TRY AGAIN!!!" );
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}

}
