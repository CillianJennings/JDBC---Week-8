package ie.atu;
import java.sql.*;

public class SelectExample {

    public static void main(String[] args) {
        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "root";

        // SQL statement
        String selectSQL = "SELECT a.first_name, a.last_name, f.title FROM actor a" +
                "JOIN FILM_ACTOR i ON a.actor_id = i.actor_id" +
                "JOIN FILM f ON i.film_id = f.film_id" +
                "LIMIT 20";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String fName = resultSet.getString("first name");
                String lName = resultSet.getString("last name");
                String title = resultSet.getString("title");

                System.out.println("First Name: " + fName + ", Last name: " + lName + ", Title: " + title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
