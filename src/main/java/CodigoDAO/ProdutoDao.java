package CodigoDAO;

import java.util.List;

public interface ProdutoDao {
    void InserirProduto(Produto produto);
    Produto obterProduto(int codigo);
    List<Produto> listaTodos();
    void  atualizarProduto(Produto produto);
    void deletarProduto(int codigo);
}
