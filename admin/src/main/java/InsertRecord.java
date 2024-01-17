import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertRecord")
public class InsertRecord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"status\": \"success\"}");

        try {
            // フォームから送られてきたデータを取得
            //int titleId = Integer.parseInt(request.getParameter("titleId"));
            String title = request.getParameter("title");
            String imgpath = request.getParameter("imgpath");
            String moviepath = request.getParameter("moviepath");
            String date = request.getParameter("date");
            int screenId = Integer.parseInt(request.getParameter("screenId"));
            String time1 = request.getParameter("time1");
            String time2 = request.getParameter("time2");
            String time3 = request.getParameter("time3");
            String time4 = request.getParameter("time4");
            String time5 = request.getParameter("time5");
            String time6 = request.getParameter("time6");
            String description = request.getParameter("description");

            // 他のフィールドも同様に取得

            // データベースに挿入
            try {
                // データベースに挿入
                insertRecord(title, imgpath, moviepath, date, screenId, time1, time2, time3, time4, time5, time6, description /* 他のフィールド */);
                out.print("{\"status\": \"success\"}");
            } catch (Exception e) {
                e.printStackTrace();
                out.print("{\"status\": \"error\", \"message\": \"Database error occurred.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\": \"error\", \"message\": \"Database error occurred.\"}");
        }
    }

    private void insertRecord(String title, String imgpath, String moviepath, String date, int screenId, String time1, String time2, String time3, String time4, String time5, String time6, String description /* 他のフィールド */) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", "postgres", "admin");
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO title (title, imgpath, moviepath, date, screenId, time1, time2, time3, time4, time5, time6, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {

        	//preparedStatement.setInt(1, titleId);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, imgpath);
            preparedStatement.setString(3, moviepath);
            preparedStatement.setString(4, date);
            preparedStatement.setInt(5, screenId);
            preparedStatement.setString(6, time1);
            preparedStatement.setString(7, time2);
            preparedStatement.setString(8, time3);
            preparedStatement.setString(9, time4);
            preparedStatement.setString(10, time5);
            preparedStatement.setString(11, time6);
            preparedStatement.setString(12, description);
            // 他のフィールドも同様に設定

            preparedStatement.executeUpdate();
        }
    }
}
