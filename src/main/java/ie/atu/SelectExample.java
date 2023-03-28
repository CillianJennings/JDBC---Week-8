package ie.atu;
import java.sql.*;

public class SelectExample {

    public static void main(String[] args) {
        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "root";

        String input = "FILM_actor";

        // SQL statement
        String selectSQL = "SELECT a.first_name, a.last_name, f.title FROM actor a " +
                "JOIN " + input + " i ON a.actor_id = i.actor_id " +
                "JOIN FILM f ON i.film_id = f.film_id " +
                "LIMIT 20";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String fName = resultSet.getString("a.first_name");
                String lName = resultSet.getString("a.last_name");
                String title = resultSet.getString("f.title");

                System.out.println("First Name: " + fName + ", Last name: " + lName + ", Title: " + title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
