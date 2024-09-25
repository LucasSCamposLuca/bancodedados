package org.example.bancoTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    public static Connection getConexao(){
        try {
            String url = "jdbc:mysql://localhost:3306/curso_java?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";// substitua pelo seu usuário
            String password = "senaigama";//substitua pela sua senha

            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }
}
