package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class inserirDados {

    public static void main(String[] args) throws SQLException {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Digite o nome");
        String nome = sc1. nextLine();

        Connection conexao = FabricaConexao.getConexao();
        
        String sql = "INSERT INTO pessoas (nome) VALUES (?)";
        PreparedStatement ptmt = conexao.prepareStatement(sql);
        
//        ptmt.setString(1, nome);
        ptmt.setString(1, nome);

        
        ptmt.execute();

        System.out.println("Pessoa incluida com sucesso!");
        sc1.close();
        
        
    }
}
