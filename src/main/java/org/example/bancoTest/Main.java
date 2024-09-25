package org.example.bancoTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) throws SQLException {

        // Conexão para criar o banco de dados
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root", "senaigama");
        Statement stmt = conexao.createStatement();
        stmt.execute("CREATE DATABASE IF NOT EXISTS curso_java");

        System.out.println("Banco criado com sucesso!");
        conexao.close();

    }
}