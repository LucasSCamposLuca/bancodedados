package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class inserirDados {
    public static void main(String[] args) throws SQLException {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Digite o nome");
        String nome = sc1.nextLine();
        System.out.println("Digite a idade");
        int idade = sc1.nextInt();
        sc1.nextLine(); // Consumir a quebra de linha após o nextInt()
        System.out.println("Digite o email");
        String email = sc1.nextLine();

        Connection conexao = FabricaConexao.getConexao();

        String sql = "INSERT INTO pessoas (nome, idade, email) VALUES (?, ?, ?)";
        PreparedStatement ptmt = conexao.prepareStatement(sql);

        ptmt.setString(1, nome);
        ptmt.setInt(2, idade);
        ptmt.setString(3, email);

        ptmt.execute();

        System.out.println("Pessoa incluída com sucesso!");
        sc1.close();
    }

}
