package org.example.HaloDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class InserirDados {
    public static void main(String[] args)  throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o Nome");
        String nome = scanner.nextLine();
        System.out.println("Digite o Email");
        String email = scanner.nextLine();
        System.out.println("Digite a Senha");
        String senha = scanner.nextLine();

        Connection conexao = FabricaConexao.getConnection();

        
    }
}
