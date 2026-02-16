
    import java.sql.*;
import java.util.Scanner;

    public class login_crud {

        private static final String URL = "jdbc:mysql://127.0.0.1:3306/login_schema";
        private static final String USER = "root";
        private static final String PASS = "Admin@123";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {

                while (true) {
                    System.out.println("\n1. Insert");
                    System.out.println("2. Update");
                    System.out.println("3. Delete");
                    System.out.println("4. Exit");
                    System.out.print("Choose option: ");
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            insertUser(connection, scanner);
                            break;
                        case 2:
                            updateUser(connection, scanner);
                            break;
                        case 3:
                            deleteUser(connection, scanner);
                            break;
                        case 4:
                            System.out.println("Bye");
                            return;
                        default:
                            System.out.println("Invalid choice");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }

        // CREATE
        private static void insertUser(Connection connection, Scanner scanner) throws SQLException {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String sql = "INSERT INTO `users` (`username`, `password`) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " row(s) inserted.");
            }
        }

        // UPDATE
        private static void updateUser(Connection connection, Scanner scanner) throws SQLException {
            System.out.print("Enter username to update: ");
            String username = scanner.nextLine();

            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();

            String sql = "UPDATE `users` SET `password` = ? WHERE `username` = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newPassword);
                pstmt.setString(2, username);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " row(s) updated.");
            }
        }

        // DELETE
        private static void deleteUser(Connection connection, Scanner scanner) throws SQLException {
            System.out.print("Enter username to delete: ");
            String username = scanner.nextLine();

            String sql = "DELETE FROM `users` WHERE `username` = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " row(s) deleted.");
            }
        }
    }


