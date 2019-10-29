package ru.lanit.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionFactory {

    private static DBConnectionFactory instance;

    private DBConnectionFactory(){}

    public static DBConnectionFactory getInstance(){
        if(instance == null){
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection create() throws SQLException, ClassNotFoundException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(configureUrl(), username, password);

    }

    private String configureUrl() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String host = resourceBundle.getString("host");
        String port = resourceBundle.getString("port");
        String schema = resourceBundle.getString("schema");
        return "jdbc:mysql://" + host + ":" + port + "/" + schema;
    }
}
