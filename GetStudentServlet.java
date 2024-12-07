import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CheckStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM student_table WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, rollNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.getWriter().write("exists");
            } else {
                response.getWriter().write("not_exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
