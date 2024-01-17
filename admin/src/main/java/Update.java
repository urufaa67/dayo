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

@WebServlet("/Update")
public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Retrieve updated data from the request
        int titleId = Integer.parseInt(request.getParameter("titleId"));
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

        // Perform database update
        String updateQuery = "UPDATE title SET titleId = ?, title = ?, imgpath = ?, moviepath = ?, date = ?, " +
                             "screenId = ?, time1 = ?, time2 = ?, time3 = ?, time4 = ?, time5 = ?, time6 = ?, description = ? " +
                             "WHERE titleId = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema?characterEncoding=UTF-8", "postgres", "admin");
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {

        	preparedStatement.setInt(1, titleId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, imgpath);
            preparedStatement.setString(4, moviepath);
            preparedStatement.setString(5, date);
            preparedStatement.setInt(6, screenId);
            preparedStatement.setString(7, time1);
            preparedStatement.setString(8, time2);
            preparedStatement.setString(9, time3);
            preparedStatement.setString(10, time4);
            preparedStatement.setString(11, time5);
            preparedStatement.setString(12, time6);
            preparedStatement.setString(13, description);
            preparedStatement.setInt(14, titleId);
           

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                out.print("{\"status\": \"success\"}");
            } else {
                out.print("{\"status\": \"error\", \"message\": \"No rows were updated.\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"status\": \"error\", \"message\": \"Database error occurred.\"}");
        }
    }
}
