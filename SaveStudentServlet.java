import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SaveStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String fullName = request.getParameter("fullName");
        String className = request.getParameter("class");
        String birthDate = request.getParameter("birthDate");
        String address = request.getParameter("address");
        String enrollmentDate = request.getParameter("enrollmentDate");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO student_table (roll_no, full_name, class, birth_date, address, enrollment_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(rollNo));
            stmt.setString(2, fullName);
            stmt.setString(3, className);
            stmt.setDate(4, Date.valueOf(birthDate));
            stmt.setString(5, address);
            stmt.setDate(6, Date.valueOf(enrollmentDate));
            stmt.executeUpdate();
            response.getWriter().write("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
