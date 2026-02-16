import java.sql.*;
import java.util.Scanner;

public class myjdbc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "Admin@123"
            );

            Statement stmt = connection.createStatement();

            // Get user input
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Use PreparedStatement for safe input
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO `users` (`username`, `password`) VALUES (?, ?)"
            );
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");

            pstmt.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
