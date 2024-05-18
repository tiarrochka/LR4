package services;

import java.sql.*;

public class DBService {

    private Connection getConnect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String password = "1234";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void insert(String sql) {
        try (Connection connection = getConnect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet select(String sql) {
        try {
            Connection connection = getConnect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
