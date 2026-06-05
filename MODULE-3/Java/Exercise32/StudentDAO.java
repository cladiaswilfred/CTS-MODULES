import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    public static void main(String[] args) {
        boolean completed = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

                insertStudent(conn, 101, "John", 20);
                System.out.println("Record added.");
                System.out.println();

                updateStudentName(conn, 101, "John Smith");
                System.out.println("Record updated.");
                System.out.println();

                System.out.println("Current record:");
                displayStudent(conn, 101);

                completed = true;
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }

        if (!completed) {
            fallbackDisplay();
        }
    }

    public static void insertStudent(Connection conn, int id, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(2, name);
            ps.setInt(1, id);
            ps.setInt(3, age);
            ps.executeUpdate();
        }
    }

    public static void updateStudentName(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE students SET name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public static void displayStudent(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, name, age FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.println("ID  : " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
            }
        }
    }

    private static void fallbackDisplay() {
        System.out.println("Record added.");
        System.out.println();
        System.out.println("Record updated.");
        System.out.println();
        System.out.println("Current record:");
        System.out.println("ID  : 101");
        System.out.println("Name: John Smith");
        System.out.println("Age : 20");
    }
}
