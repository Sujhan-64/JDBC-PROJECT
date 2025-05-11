import java.sql.*;

public class CancelTicket {
    public static void main(String[] args) {
        String ticketNumber = "your_ticket_number_here";  // Use the actual ticket number to cancel
        
        try (Connection connection = App.connect()) {
            // Get the train ID for this booking
            String selectTrainQuery = "SELECT train_id FROM bookings WHERE ticket_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectTrainQuery);
            preparedStatement.setString(1, ticketNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int trainId = resultSet.getInt("train_id");
                
                // Delete the booking record
                String deleteBookingQuery = "DELETE FROM bookings WHERE ticket_number = ?";
                preparedStatement = connection.prepareStatement(deleteBookingQuery);
                preparedStatement.setString(1, ticketNumber);
                preparedStatement.executeUpdate();
                
                // Increment seat count in the train
                String updateSeatsQuery = "UPDATE trains SET seats = seats + 1 WHERE id = ?";
                preparedStatement = connection.prepareStatement(updateSeatsQuery);
                preparedStatement.setInt(1, trainId);
                preparedStatement.executeUpdate();
                
                System.out.println("Ticket " + ticketNumber + " has been cancelled and seat has been freed.");
            } else {
                System.out.println("Ticket not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
