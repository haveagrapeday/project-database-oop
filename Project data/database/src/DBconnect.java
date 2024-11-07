package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    private Connection conn = null;

    // Constructor to initialize the database connection
    public DBconnect() {
        try {
            // Database URL, username, and password (update these with your actual database details)
            String url = "jdbc:mysql://localhost:3306/your_database"; // Replace with your database URL
            String username = "your_username"; // Replace with your database username
            String password = "your_password"; // Replace with your database password

            // Establishing the connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }

    // Getter method to access the connection object
    public Connection getConnection() {
        return conn;
    }

    // Method to close the connection
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
