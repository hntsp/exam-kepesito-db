package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Database not reachable.");
        }
        return connection;
    }

    public List<String> checkOverpopulation() {
        List<String> dinosaur = new ArrayList();

        String query = "SELECT breed FROM dinosaur WHERE expected < actual ORDER BY breed";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                dinosaur.add(resultSet.getString("breed"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dinosaur;
    }


}
