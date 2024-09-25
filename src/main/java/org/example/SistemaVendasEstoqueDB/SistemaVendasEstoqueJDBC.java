package org.example.SistemaVendasEstoqueDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SistemaVendasEstoqueJDBC {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/loja"; // Ajuste conforme o nome do banco de dados
    private static final String DB_USER = "root"; // Substitua pelo seu usuário do banco de dados
    private static final String DB_PASSWORD = "senha"; // Substitua pela sua senha

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            criarTabelas(statement);


            inserirDados(connection);


            gerarRelatorioVendas(connection);
            gerarRelatorioEstoque(connection);
            gerarPrevisaoDemanda(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para criar tabelas
    private static void criarTabelas(Statement statement) throws SQLException {
        // Criar tabela produtos
        String criarTabelaProdutos = "CREATE TABLE IF NOT EXISTS produtos (" +
                "produto_id INT PRIMARY KEY, " +
                "nome VARCHAR(255), " +
                "quantidade_disponivel INT)";
        statement.executeUpdate(criarTabelaProdutos);

        // Criar tabela vendas
        String criarTabelaVendas = "CREATE TABLE IF NOT EXISTS vendas (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "produto_id INT, " +
                "quantidade INT, " +
                "preco_total DECIMAL(10, 2), " +
                "data_venda DATE, " +
                "FOREIGN KEY (produto_id) REFERENCES produtos(produto_id))";
        statement.executeUpdate(criarTabelaVendas);

        // Criar tabela previsao_demanda
        String criarTabelaPrevisaoDemanda = "CREATE TABLE IF NOT EXISTS previsao_demanda (" +
                "produto_id INT PRIMARY KEY, " +
                "demanda_estimada INT, " +
                "periodo VARCHAR(20), " +
                "FOREIGN KEY (produto_id) REFERENCES produtos(produto_id))";
        statement.executeUpdate(criarTabelaPrevisaoDemanda);

        System.out.println("Tabelas criadas com sucesso.");
    }

    // Função para inserir dados de exemplo
    private static void inserirDados(Connection connection) throws SQLException {
        // Inserir dados na tabela produtos
        String inserirProdutos = "INSERT INTO produtos (produto_id, nome, quantidade_disponivel) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(inserirProdutos)) {
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Notebook");
            pstmt.setInt(3, 100);
            pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            pstmt.setString(2, "Smartphone");
            pstmt.setInt(3, 200);
            pstmt.executeUpdate();

            pstmt.setInt(1, 3);
            pstmt.setString(2, "Tablet");
            pstmt.setInt(3, 50);
            pstmt.executeUpdate();
        }

        // Inserir dados na tabela vendas
        String inserirVendas = "INSERT INTO vendas (produto_id, quantidade, preco_total, data_venda) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(inserirVendas)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 10);
            pstmt.setDouble(3, 15000.00);
            pstmt.setDate(4, java.sql.Date.valueOf("2024-09-01"));
            pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            pstmt.setInt(2, 20);
            pstmt.setDouble(3, 10000.00);
            pstmt.setDate(4, java.sql.Date.valueOf("2024-09-02"));
            pstmt.executeUpdate();

            pstmt.setInt(1, 3);
            pstmt.setInt(2, 5);
            pstmt.setDouble(3, 5000.00);
            pstmt.setDate(4, java.sql.Date.valueOf("2024-09-03"));
            pstmt.executeUpdate();
        }

        System.out.println("Dados de exemplo inseridos com sucesso.");
    }

    // Função para gerar relatório de vendas
    private static void gerarRelatorioVendas(Connection connection) {
        String sqlVendas = "SELECT produto_id, SUM(quantidade) AS total_vendido, SUM(preco_total) AS total_revenue " +
                "FROM vendas " +
                "GROUP BY produto_id";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlVendas);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Relatório de Vendas:");
            System.out.println("Produto ID | Quantidade Vendida | Receita Total");
            while (rs.next()) {
                int produtoId = rs.getInt("produto_id");
                int totalVendido = rs.getInt("total_vendido");
                double totalRevenue = rs.getDouble("total_revenue");
                System.out.println(produtoId + " | " + totalVendido + " | R$ " + totalRevenue);
            }
            System.out.println(); // Linha em branco para separar relatórios
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para gerar relatório de estoque
    private static void gerarRelatorioEstoque(Connection connection) {
        String sqlEstoque = "SELECT produto_id, nome, quantidade_disponivel " +
                "FROM produtos";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlEstoque);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Relatório de Estoque:");
            System.out.println("Produto ID | Nome | Quantidade Disponível");
            while (rs.next()) {
                int produtoId = rs.getInt("produto_id");
                String nome = rs.getString("nome");
                int quantidadeDisponivel = rs.getInt("quantidade_disponivel");
                System.out.println(produtoId + " | " + nome + " | " + quantidadeDisponivel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para gerar previsão de demanda
    private static void gerarPrevisaoDemanda(Connection connection) {
        String sqlPrevisao = "SELECT produto_id, AVG(quantidade) AS demanda_estimada " +
                "FROM vendas " +
                "WHERE data_venda >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) " + // Últimos 30 dias
                "GROUP BY produto_id";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlPrevisao);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Previsão de Demanda para os Próximos 30 dias:");
            System.out.println("Produto ID | Demanda Estimada (Média de Vendas)");
            while (rs.next()) {
                int produtoId = rs.getInt("produto_id");
                int demandaEstimada = rs.getInt("demanda_estimada");
                System.out.println(produtoId + " | " + demandaEstimada);

                // Atualiza a tabela de previsão de demanda no banco de dados
                atualizarPrevisaoDemanda(connection, produtoId, demandaEstimada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para atualizar a tabela de previsão de demanda
    private static void atualizarPrevisaoDemanda(Connection connection, int produtoId, int demandaEstimada) {
        String sqlUpdatePrevisao = "INSERT INTO previsao_demanda (produto_id, demanda_estimada, periodo) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE demanda_estimada = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdatePrevisao)) {
            pstmt.setInt(1, produtoId);
            pstmt.setInt(2, demandaEstimada);
            pstmt.setString(3, "Próximos 30 dias");
            pstmt.setInt(4, demandaEstimada);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
