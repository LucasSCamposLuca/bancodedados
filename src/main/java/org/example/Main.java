package org.example;

import java.io.InvalidClassException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false"; // Substitua pelo seu URL do banco de dados
        String user = "root";
        String password = "senaigama";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão estabelecida com sucesso!");
            // Aqui você pode adicionar mais código para realizar operações no banco de dados
        } catch (SQLException e) {

            System.err.println("Erro ao conectar: " + e.getMessage());

        }
    }
}