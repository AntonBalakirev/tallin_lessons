package org.example.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DataBaseManager {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DataBaseManager(){
        try (InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            dbUrl = properties.getProperty("db_url");
            dbUser = properties.getProperty("db_username");
            dbPassword = properties.getProperty("db_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection getDbConnection(){
        Connection dbConnection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public ResultSet selectOrderById(String orderId){
        ResultSet resultSet = null;
        try(Connection dbConnection = getDbConnection()) {
            String query = "select * from orders where id = " + orderId;

            Statement statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1) + "\n" +
//                                resultSet.getString(3) + "\n" +
//                                resultSet.getString(4) + "\n" +
//                                resultSet.getString(5));
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
