package CodigoDAO;

public class Principal {
    public static void main(String[] args) {
        ProdutoDao produtoDAO = new ProdutoDaoImpl();

        Produto novoProduto = new Produto(1




                , "Teclado", 150.00);
        produtoDAO.InserirProduto(novoProduto);


        Produto produto = produtoDAO.obterProduto(1);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto não encontrado!");
        }


        System.out.println("Lista de todos os produtos:");
        for (Produto p : produtoDAO.listaTodos()) {
            System.out.println(p);
        }


        Produto produtoParaAtualizar = produtoDAO.obterProduto(1);
        if (produtoParaAtualizar != null) {
            produtoParaAtualizar.setNome("mouse");
            produtoParaAtualizar.setPreco(200.00);
            produtoDAO.atualizarProduto(produtoParaAtualizar);
            System.out.println(produtoParaAtualizar);

        }


        produtoDAO.deletarProduto(2);
    }

}