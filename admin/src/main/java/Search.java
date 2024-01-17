

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
 * Servlet implementation class LoginForm
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at:/admin/Search").append(request.getContextPath());
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		int memberId;
		try {
			memberId = Integer.parseInt(request.getParameter("memberId"));
		}catch(Exception e){
			memberId = 0;
		}
		

		try 
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", "postgres", "admin");
			
			String sql ="";
			if(memberId != 0) {
				sql = "select * from membertable where name=? or kana=? or memberId=? ";
			}else {
				sql = "select * from membertable where name=? or kana=?";
			}
			
			
			PreparedStatement ps = con.prepareStatement(sql) ;
			
			ps.setString(1, name);
			ps.setString(2, kana);
			if(memberId != 0) {
				ps.setInt(3, memberId);
			}
			

			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("session_memberId", rs.getInt("memberId"));
				session.setAttribute("session_userId", rs.getString("userId"));
				session.setAttribute("session_pass", rs.getString("pass"));
				session.setAttribute("session_name", rs.getString("name"));
				session.setAttribute("session_kana", rs.getString("kana"));
				session.setAttribute("session_tel", rs.getString("tel"));
				session.setAttribute("session_mail", rs.getString("mail"));
				session.setAttribute("session_address", rs.getString("address"));
				session.setAttribute("session_postNum", rs.getString("postNum"));
				session.setAttribute("session_gender", rs.getString("gender"));
				//session.setAttribute("session_adminId", rs.getInt("adminId")); 
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/memberlist.jsp");
				rd.include(request, response);
			}
			else
			{
				out.print("<h3 style='color:red'> userid or password is wrong </h3>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.include(request, response);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			
			out.print("<h3 style='color:red'> "+e.getMessage()+" </h3>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
			rd.include(request, response);
		}
	}

}
