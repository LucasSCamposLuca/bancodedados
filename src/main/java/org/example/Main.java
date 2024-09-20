package org.example;

import java.io.InvalidClassException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false"; // Substitua pelo seu URL do banco de dados
//        String user = "root";
//        String password = "senaigama";

        Connection conexao = FabricaConexao.getConexao();
        Statement stmt = conexao.createStatement();
        stmt.execute("CREATE DATABASE IF NOT EXISTS curso_java");

        System.out.println("Banco criado com sucesso!");
        conexao.close();

    }
}