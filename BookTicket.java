import java.sql.*;
import java.util.UUID;

public class BookTicket {
    public static void main(String[] args) {
        int trainId = 1; // For example, booking for the first train
        String passengerName = "John Doe";
        String ticketNumber = UUID.randomUUID().toString();  // Generating a unique ticket number
        
        // Decrement seat count in the train
        try (Connection connection = App.connect()) {
            String selectSeatsQuery = "SELECT seats FROM trains WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSeatsQuery);
            preparedStatement.setInt(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int seats = resultSet.getInt("seats");
                if (seats > 0) {
                    // Decrease seat count
                    String updateSeatsQuery = "UPDATE trains SET seats = seats - 1 WHERE id = ?";
                    preparedStatement = connection.prepareStatement(updateSeatsQuery);
                    preparedStatement.setInt(1, trainId);
                    preparedStatement.executeUpdate();
                    
                    // Insert booking
                    String insertBookingQuery = "INSERT INTO bookings (train_id, passenger_name, ticket_number) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(insertBookingQuery);
                    preparedStatement.setInt(1, trainId);
                    preparedStatement.setString(2, passengerName);
                    preparedStatement.setString(3, ticketNumber);
                    int rowsInserted = preparedStatement.executeUpdate();
                    System.out.println(rowsInserted + " ticket(s) booked successfully for " + passengerName + "!");
                } else {
                    System.out.println("No seats available on this train.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
