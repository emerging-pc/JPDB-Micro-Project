import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String fullName = request.getParameter("fullName");
        String className = request.getParameter("class");
        String birthDate = request.getParameter("birthDate");
        String address = request.getParameter("address");
        String enrollmentDate = request.getParameter("enrollmentDate");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE student_table SET full_name = ?, class = ?, birth_date = ?, address = ?, enrollment_date = ? WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fullName);
            stmt.setString(2, className);
            stmt.setDate(3, Date.valueOf(birthDate));
            stmt.setString(4, address);
            stmt.setDate(5, Date.valueOf(enrollmentDate));
            stmt.setInt(6, Integer.parseInt(rollNo));
            stmt.executeUpdate();
            response.getWriter().write("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
