package org.example.HaloDB;

import java.sql.Connection;
import java.sql.SQLException;

public class CriarDB {
    public static void main(String[] args) throws SQLException {
        Connection conexao = FabricaConexao.getConnection();

        String sql = "CREATE TABLE HaloDB ";
    }
}
