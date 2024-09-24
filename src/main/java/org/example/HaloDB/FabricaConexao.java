package org.example.HaloDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    public static Connection getConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
            String user = "root";
            String password = "sanaigama";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

}