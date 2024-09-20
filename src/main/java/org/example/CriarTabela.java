package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela {

    public static void main(String[] args) throws SQLException {

        Connection conexao = FabricaConexao.getConexao();

        String sql = "CREATE TABLE pessoas (" +
                "codigo INT AUTO_INCREMENT PRYMARY KEY," +
                "nome VARCHAR(80) NOT NULL" +
                ")";

        Statement stmt = conexao.createStatement();
        stmt.execute(sql);

        System.out.println("Tabela criada com sucesso!!");
        conexao.close();
    }
}
