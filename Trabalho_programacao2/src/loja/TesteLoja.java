package loja;

import loja.Loja;
import produto.Produto;

public class TesteLoja {

    public static void main(String[] args) {

        // Criando uma loja
        Loja loja = new Loja(1, "Loja Central", "Rua A");

        // Criando produtos
        Produto p1 = new Produto(1, "Camisa", "Roupa", "M", 50.0, 10);
        Produto p2 = new Produto(2, "Calça", "Roupa", "G", 90.0, 5);

        // Adicionando produtos à loja
        loja.adicionarProduto(p1);
        loja.adicionarProduto(p2);

        // Listando produtos
        loja.listarProdutos();
    }
}
