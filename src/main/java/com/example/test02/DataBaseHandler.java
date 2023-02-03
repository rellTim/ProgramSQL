package com.example.test02;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void singUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_FIRST_NAME + "," + Const.USER_LAST_NAME + "," + Const.USER_USER_NAME + "," + Const.USER_PASSWORD + "," + Const.USER_LOCATION + "," + Const.USER_GENDER + ")" + "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)) {
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.getMessage();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.getMessage();
        }
        return resultSet;
    }
}
