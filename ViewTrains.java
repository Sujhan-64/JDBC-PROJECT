import java.sql.*;

public class ViewTrains {
    public static void main(String[] args) {
        String query = "SELECT * FROM trains";
        
        try (Connection connection = App.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                System.out.println("Train ID: " + resultSet.getInt("id") +
                        "\nTrain Name: " + resultSet.getString("train_name") +
                        "\nSource: " + resultSet.getString("source") +
                        "\nDestination: " + resultSet.getString("destination") +
                        "\nAvailable Seats: " + resultSet.getInt("seats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
