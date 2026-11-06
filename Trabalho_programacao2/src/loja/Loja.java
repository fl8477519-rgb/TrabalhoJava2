package loja;

import produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private  String nome;
    private  String endereco;
    private List<Produto>produtos;
    public  Loja(String nome,String endereco){
        this.nome=nome;
        this.endereco=endereco;
        this.produtos=new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    public  void adicionarProduto(Produto produto){
        Produto.add(produto);

    }
    public void listarProdutos(){
        System.out.println("Produtos da loja" +nome);
        if (produtos.isEmpty()){
            System.out.println("Nenhum produto Cadastrado");
        }
        for (Produto p:produtos){
            System.out.println(" " +p);
        }
    }

    @Override
    public String toString() {
        return "Loja{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
