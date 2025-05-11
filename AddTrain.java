import java.sql.*;

public class AddTrain {
    public static void main(String[] args) {
        String insertTrainQuery = "INSERT INTO trains (train_name, source, destination, seats) " +
                "VALUES ('Express Train', 'New York', 'Chicago', 100)";
        
        try (Connection connection = App.connect();
             Statement statement = connection.createStatement()) {
            int rowsInserted = statement.executeUpdate(insertTrainQuery);
            System.out.println(rowsInserted + " train(s) added to the system!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
