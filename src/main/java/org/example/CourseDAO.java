package org.example;

import java.sql.*;

public class CourseDAO {

    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=coursesDataBase;integratedSecurity=true;" +
                    "encrypt=true;trustServerCertificate=true";

    public static Course getCourseByCRN(String crn) {
        Course course = null;
        String query = "SELECT * FROM Courses WHERE CRN = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, crn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                course = new Course(
                        rs.getString("CRN"),
                        rs.getString("Subject"),
                        rs.getString("Department"),
                        rs.getString("Number"),
                        rs.getString("Section"),
                        rs.getString("Instructor"),
                        rs.getString("Days"),
                        rs.getString("Start_Time"),
                        rs.getString("End_Time"),
                        rs.getString("Building"),
                        rs.getString("Room"),
                        rs.getString("Campus"),
                        rs.getString("Class_Type"),
                        rs.getString("Method"),
                        rs.getString("DP_or_IN"),
                        rs.getString("PoT"),
                        rs.getShort("Actual"),
                        rs.getShort("Max")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

}
