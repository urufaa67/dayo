
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class History3
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String dbURL = "jdbc:postgresql://localhost:5432/cinema";
		String username = "postgres";
		String password = "admin";

		// データベースの接続
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			// JDBCドライバのロード
			Class.forName("org.postgresql.Driver");

			// データベースに接続
			conn = DriverManager.getConnection(dbURL, username, password);

			// クエリの作成と実行
			String query = "SELECT * FROM title";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			List<Cinema> editerxxx = new ArrayList<Cinema>();
			while (rs.next()) {
				int titleId = rs.getInt("titleId");
				String title = rs.getString("title");
				String imgpath = rs.getString("imgpath");
				String moviepath = rs.getString("moviepath");
				String date = rs.getString("date");
				int screenId = rs.getInt("screenId");
				String time1 = rs.getString("time1");
				String time2 = rs.getString("time2");
				String time3 = rs.getString("time3");
				String time4 = rs.getString("time4");
				String time5 = rs.getString("time5");
				String time6 = rs.getString("time6");
				String description = rs.getString("description");


				editerxxx.add(new Cinema(titleId, title, imgpath, moviepath, date, screenId, time1, time2, time3, time4, time5, time6, description ));
			}

			EditData data = new EditData(editerxxx);
			String yourJsonString = data.toJson();
			out.print(yourJsonString);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// リソースの解放
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}


