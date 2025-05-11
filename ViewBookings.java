import java.sql.*;

public class ViewBookings {
    public static void main(String[] args) {
        int trainId = 1;  // For example, viewing bookings for the first train
        
        String query = "SELECT * FROM bookings WHERE train_id = ?";
        
        try (Connection connection = App.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                System.out.println("Booking ID: " + resultSet.getInt("id") +
                        "\nPassenger Name: " + resultSet.getString("passenger_name") +
                        "\nTicket Number: " + resultSet.getString("ticket_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
