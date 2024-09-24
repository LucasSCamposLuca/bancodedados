package org.example.bancoTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultarDados {

    public static void main(String[] args) throws SQLException {
        Connection conexao = FabricaConexao.getConexao();
        String sql = "SELECT * FROM pessoas";
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(sql);

        List<Pessoa> pessoas = new ArrayList<>();

        while(resultado.next()) {
            int codigo  = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            int idade = resultado.getInt("idade");
            String email = resultado.getString("email");
            pessoas.add(new Pessoa(codigo, nome, idade, email));
        }

        for (Pessoa p: pessoas) {
            System.out.println(p.getCodigo() + " -> "+ p.getNome() +" "+ p.getIdade() +" "+ p.getEmail());
        }
        stmt.close();
        conexao.close();

    }
}