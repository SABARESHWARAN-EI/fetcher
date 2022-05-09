package ucregistratio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		int otpvalue = 0;
		HttpSession mySession = request.getSession();
		
		if(email!=null || !email.equals("")) {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(123678);

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ucdb?useSSL=false","root","@Sabaresh2101");
					PreparedStatement ps =con.prepareStatement("SELECT * from users WHERE uemail=?");
					ps.setString(1,email); 
		
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						String to = email;// change accordingly
						// Get the session object
						Properties props = new Properties();
						props.put("mail.smtp.host", "smtp.gmail.com");
						props.put("mail.smtp.socketFactory.port", "465");
						props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
						props.put("mail.smtp.auth", "true");
						props.put("mail.smtp.port", "465");
						Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("sabare2101@gmail.com", "wiwergajdnmojvrq");
							}
						});
						try {
							MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(email));
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
							message.setSubject("It is FETCHER !!!");
							message.setText("Your OTP to modify / create your password is: " + otpvalue);
							
							Transport.send(message);
							System.out.println("message sent successfully");
						}
		
						catch (MessagingException e) {
							throw new RuntimeException(e);
						}
						dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
						request.setAttribute("message","OTP is sent to your email id");
						//request.setAttribute("connection", con);
						mySession.setAttribute("otp",otpvalue); 
						mySession.setAttribute("email",email); 
						dispatcher.forward(request, response);
						//request.setAttribute("status", "success");
					}
					else {
						request.setAttribute("status","failed");
						dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
						dispatcher.forward(request, response);
					}
					
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}

}
