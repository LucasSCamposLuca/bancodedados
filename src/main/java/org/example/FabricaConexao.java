package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    public static Connection getConexao(){
        try {
            String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false"; // Substitua pelo seu URL do banco de dados
            String user = "root";// substitua pelo seu usuário
            String password = "senaigama";//substitua pela sua senha

            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }
}
