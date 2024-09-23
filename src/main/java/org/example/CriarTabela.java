package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela {

    public static void main(String[] args) throws SQLException {

        Connection conexao = FabricaConexao.getConexao();

               String sql = "CREATE TABLE pessoas (" +
               "codigo INT AUTO_INCREMENT PRIMARY KEY," +
               "nome VARCHAR(80) NOT NULL" +
                ")";

        Statement stmt = conexao.createStatement();

        stmt.execute(sql);

        System.out.println("Tabela criada com sucesso!!");

        //Adicionando nova coluna para idade
        String sqlAlterIdade = "ALTER TABLE pessoas ADD COLUMN idade INT";
        stmt.execute(sqlAlterIdade);
        System.out.println("Coluna 'idade' adicionada com sucesso!");

        // Adicionando nova coluna para email
        String sqlAlterEmail = "ALTER TABLE pessoas ADD COLUMN email VARCHAR(100)";
        stmt.execute(sqlAlterEmail);
        System.out.println("Coluna 'email' adicionada com sucesso!");

        conexao.close();
    }
}
