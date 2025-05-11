import java.sql.*;

public class CreateTables {
    public static void main(String[] args) {
        String createTrainsTable = "CREATE TABLE IF NOT EXISTS trains (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "train_name VARCHAR(100)," +
                "source VARCHAR(50)," +
                "destination VARCHAR(50)," +
                "seats INT" +
                ")";
        
        String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "train_id INT," +
                "passenger_name VARCHAR(100)," +
                "ticket_number VARCHAR(50)," +
                "FOREIGN KEY (train_id) REFERENCES trains(id)" +
                ")";
        
        try (Connection connection = App.connect();
             Statement statement = connection.createStatement()) {
            
            statement.executeUpdate(createTrainsTable);
            statement.executeUpdate(createBookingsTable);
            System.out.println("Tables created successfully!");
            
        } catch (SQLException e) {
            System.out.println("Failed to create tables.");
            e.printStackTrace();
        }
    }
}
