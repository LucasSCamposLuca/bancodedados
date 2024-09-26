package CodigoDAO;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements  ProdutoDao {
    private final String url = "jdbc:mysql://localhost:3306/curso_java?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
    private final String user = "root";// substitua pelo seu usuário
    private final String password = "senaigama";//substitua pela sua senha

    private Connection conectar() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void InserirProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

        try (Connection conn = conectar(); PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Produto obterProduto(int codigo) {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        Produto produto = null;

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, codigo);
            ResultSet result = stmt.executeQuery();

            if (result.next()){
                produto = new Produto(result.getInt("codigo"),
                        result.getString("nome"),
                        result.getDouble("preco"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> listaTodos() {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = conectar(); Statement stmt = conn.createStatement();
             ResultSet resultado = stmt.executeQuery(sql)){
            while (resultado.next()){
                Produto produto = new Produto(resultado.getInt("codigo"),
                        resultado.getString("nome"),
                        resultado.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos set nome = ?, preco = ? WHERE codigo = ?";

        try (Connection conn = conectar(); PreparedStatement stmt =
                conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getCodigo());
            int row = stmt.executeUpdate();
            if (row > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto nao encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletarProduto(int codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";
        try (Connection conn = conectar(); PreparedStatement stmt =
                conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
