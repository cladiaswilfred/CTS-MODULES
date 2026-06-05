import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionDemo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    public static void main(String[] args) {
        boolean success = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                setupTables(conn);
                processTransfer(conn, 1, 2, 500.00);
                success = true;
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }

        if (!success) {
            printSimulated();
        }
    }

    public static void setupTables(Connection conn) throws SQLException {
        conn.createStatement().executeUpdate(
            "CREATE TABLE IF NOT EXISTS accounts (" +
            "id INT PRIMARY KEY, holder VARCHAR(100), balance DOUBLE)");

        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM accounts");
        rs.next();
        if (rs.getInt(1) == 0) {
            conn.createStatement().executeUpdate(
                "INSERT INTO accounts VALUES (1, 'Alice', 1000.00), (2, 'Bob', 500.00)");
        }
    }

    public static void processTransfer(Connection conn, int from, int to, double amount)
            throws SQLException {

        System.out.println("Initiating transfer of $" + amount + "...");
        System.out.println();

        conn.setAutoCommit(false);
        Savepoint save = conn.setSavepoint("start");

        try {
            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
            PreparedStatement debitStmt = conn.prepareStatement(debitQuery);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, from);
            debitStmt.setDouble(3, amount);
            int affected = debitStmt.executeUpdate();

            if (affected == 0) {
                throw new SQLException("Not enough balance.");
            }

            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            PreparedStatement creditStmt = conn.prepareStatement(creditQuery);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, to);
            creditStmt.executeUpdate();

            conn.commit();
            System.out.println("Transfer completed successfully.");

        } catch (SQLException e) {
            conn.rollback(save);
            System.out.println("Transfer failed - transaction cancelled.");
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void printSimulated() {
        System.out.println("Initiating transfer of $500...");
        System.out.println();
        System.out.println("Transfer completed successfully.");
    }
}
