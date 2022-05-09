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
		@WebServlet("/new_service")
		public class new_service extends HttpServlet {
			private static final long serialVersionUID = 1L;

//			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//				
//			}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException{
				HttpSession session = request.getSession();

				RequestDispatcher dispatcher=null;
				Connection con=null;
				
				String uemail=(String)session.getAttribute("uemail");
				String uname=(String)session.getAttribute("name");
				String uid=(String)session.getAttribute("uid");
				String f = request.getParameter("submit");
				if (f.equals("back"))
		        {
					dispatcher = request.getRequestDispatcher("user.jsp");
					dispatcher.forward(request, response);
		        }
				
				String urole=request.getParameter("role");
				String udesc=request.getParameter("desc");
				String uaddress=request.getParameter("address");
				System.out.println("role is "+urole);
				String order_id=null;
				String id=null;
				String email=null;
				String num=null;
				String status="applied";
				java.util.Date date=null;
				java.sql.Time u_time =null;
				
			try {

				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("udate"));
				
				LocalTime localTime = LocalTime.parse(request.getParameter("utime"));
				u_time = java.sql.Time.valueOf(localTime);

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			   java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
				
				    PreparedStatement st = con.prepareStatement("select * from users where id = ?");
				    st.setString(1, uid);
				    ResultSet r1=st.executeQuery();
				    while(r1.next())
				    {
				   
				   email = r1.getString("uemail");  
		            num = r1.getString("umobile");  
				    }
				 
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try {

		           // PreparedStatement ps =con.prepareStatement("INSERT INTO service (uname,uemail,umobile,urole,udesc,ulocation,udate,utime,uid,ustatus) VALUES (?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
					PreparedStatement ps =con.prepareStatement("INSERT INTO service (urole,udesc,ulocation,udate,utime,uid,ustatus) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);


//					ps.setString(1, uname);
//					ps.setString(2, email);
//					ps.setString(3, num);
					ps.setString(1, urole);
					ps.setString(2, udesc);
					ps.setString(3, uaddress);
					ps.setDate(4, sqlDate);
					ps.setObject(5, u_time);
					ps.setString(6, uid);
					ps.setString(7,status);
					
					int row_count=ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					
					
					while(rs.next())
					{
						System.out.println(row_count);
						session.setAttribute("order_id",rs.getString(1));
						session.setAttribute("uemail",uemail);
						//request.setAttribute("status", "success");
						session.setAttribute("role", urole);
						session.setAttribute("udate",date);
						System.out.println("index");
						System.out.println(urole);
						//dispatcher = request.getRequestDispatcher("MyOrders");
						response.sendRedirect("MyOrders");  
						dispatcher.forward(request, response);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
			}

		}
