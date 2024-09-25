

package org.example.bancoTest;

import java.sql.*;
import java.util.Scanner;

public class AtualizarDados {
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o código da pessoa: ");
        int codigo = entrada.nextInt();

        // Incluindo as colunas idade e email no SELECT
        String select = "SELECT codigo, nome, idade, email FROM pessoas WHERE codigo = ?";
        String update = "UPDATE pessoas SET nome = ?, idade = ?, email = ? WHERE codigo = ?";

        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(select);
        stmt.setInt(1, codigo);
        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            // Corrigido para pegar as colunas corretamente
            Pessoa p = new Pessoa(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4));
            System.out.println("O nome atual é " + p.getNome());
            entrada.nextLine(); // Consome a nova linha

            System.out.println("Informe o novo nome: ");
            String novoNome = entrada.nextLine();

            System.out.println("Nova idade: ");
            int novaIdade = entrada.nextInt();

            entrada.nextLine(); // Consome a nova linha
            System.out.println("Novo email: ");
            String novoEmail = entrada.nextLine();

            stmt.close(); // Fechar o primeiro PreparedStatement

            stmt = conexao.prepareStatement(update);
            stmt.setString(1, novoNome);
            stmt.setInt(2, novaIdade); // Corrigido para incluir a nova idade
            stmt.setString(3, novoEmail); // Corrigido para incluir o novo email
            stmt.setInt(4, codigo); // O código deve ser o último parâmetro

            stmt.executeUpdate(); // Use executeUpdate para UPDATE

            System.out.println("Pessoa alterada com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }

        // Fechando a conexão
        conexao.close();
    }
}


//package org.example.bancoTest;
//
//import javax.xml.transform.Result;
//import java.sql.*;
//import java.util.Scanner;
//
//public class AtualizarDados {
//    public static void main(String[] args)  throws SQLException {
//        Scanner entrada = new Scanner(System.in);
//        System.out.println("Informe o codigo da pessoa:  ");
//        int codigo  = entrada.nextInt();
//
//
//        String select = "SELECT codigo, nome FROM pessoas WHERE codigo = ?";
//        String update = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
//
//        Connection conexao = FabricaConexao.getConexao();
//        PreparedStatement stmt = conexao.prepareStatement(select);
//        stmt.setInt(1, codigo);
//        ResultSet result = stmt.executeQuery();
//
//        if (result.next()){
//            Pessoa p = new Pessoa(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4));
//            System.out.println("o nome atual é " + p.getNome());
//            entrada.nextLine();
//            System.out.println("Informe o novo nome: ");
//            String novoNome = entrada.nextLine();
//            System.out.println("Nova idade");
//            int novaIdade = entrada.nextInt();
//            System.out.println("Novo email");
//            String novoEmail = entrada.nextLine();
//            stmt.close();
//
//            stmt = conexao.prepareStatement(update);
//            stmt.setString(1, novoNome);
//            stmt.setInt(2, codigo);
//            stmt.setInt(3, novaIdade);
//            stmt.setString(4, novoEmail);
//            stmt.execute();
//
//            System.out.println("Pessoa alterada com sucesso!");
//        } else {
//            System.out.println("pessoa não encontrada!");
//        }
//
//        conexao.close();
//        conexao.close();
//    }
//}