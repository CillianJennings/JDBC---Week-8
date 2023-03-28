package ie.atu;

import java.sql.*;

public class InsertExample {
    public static void main(String[] args) throws SQLException {

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/testdatabase", "root", "root");

        try {

            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO person (name, age, email) VALUES (?, ?, ?)");
            stmt.setString(1, "Ellen");
            stmt.setString(2, "100");
            stmt.setString(3, "ellen@atu.ie");
            stmt.executeUpdate();

            // Insert a new record into the "emails" table, referencing the new user
            stmt = conn.prepareStatement("INSERT INTO address (person_id, street, city, county) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, "Far Away St.");
            stmt.setString(3, "Offaly City");
            stmt.setString(4, "Offaly");
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
        // Close the connection
        conn.close();
    }

    // Helper method to get the ID of the last inserted record
    private static int getLastInsertId(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }
}
