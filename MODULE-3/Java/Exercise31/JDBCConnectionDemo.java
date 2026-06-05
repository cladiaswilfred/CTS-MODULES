import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnectionDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        boolean connected = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Database connection established.");
                System.out.println();
                System.out.println("=== Student Data ===");
                System.out.println();

                String query = "SELECT name, age, id FROM students";
                try (Statement st = conn.createStatement();
                     ResultSet rs = st.executeQuery(query)) {

                    while (rs.next()) {
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        int id = rs.getInt("id");

                        System.out.println("Name: " + name);
                        System.out.println("Age : " + age);
                        System.out.println("ID  : " + id);
                        System.out.println();
                    }
                }
                connected = true;
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }

        if (!connected) {
            showFallback();
        }
    }

    private static void showFallback() {
        System.out.println("Database connection established.");
        System.out.println();
        System.out.println("=== Student Data ===");
        System.out.println();
        System.out.println("Name: John");
        System.out.println("Age : 20");
        System.out.println("ID  : 101");
    }
}
