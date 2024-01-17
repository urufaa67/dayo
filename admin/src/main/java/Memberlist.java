

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class information
 */
@WebServlet("/Memberlist")
public class Memberlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at:/admin/Memberlist").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String postNum = request.getParameter("postNum");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		Object memberId =  session.getAttribute("session_memberId");
		
		try 
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", "postgres", "admin");
			
			PreparedStatement ps = con.prepareStatement("update membertable set userid = ?, pass = ?, name = ?, kana = ?, tel = ?, mail = ?, postnum = ?, address = ?, gender = ? where memberId = ? ") ;

			ps.setString(1, userId);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.setString(4, kana);
			ps.setString(5, tel);
			ps.setString(6, mail);
			ps.setString(7, postNum);
			ps.setString(8, address);
			ps.setString(9, gender);
			ps.setObject(10, memberId);

			
			int count = ps.executeUpdate();
			if(count > 0)
			{
				response.setContentType("text/html");
				out.print("<h3 style='color:green'> 会員情報変更完了 </h3>");
				
				
				//session.setAttribute("session_name", request.getParameter("name"));
				
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.include(request, response);
			}
			else
			{
				response.setContentType("text/html");
				out.print("<h3 style='color:red'> エラーが発生しました </h3>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/memberlist.jsp");
				rd.include(request, response);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			
			response.setContentType("text/html");
			out.print("<h3 style='color:red'> Error </h3>");
			System.out.println(" Exception Occured"+e.getMessage());
			
			RequestDispatcher rd = request.getRequestDispatcher("/memberlist.jsp");
			rd.include(request, response);
		}
		
	}

}

