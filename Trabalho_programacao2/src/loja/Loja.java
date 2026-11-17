package loja;

import produto.Produto;

import java.util.ArrayList;
import java.util.List;



public class Loja {
    private  int id ;
    private  String nome;
    private  String endereco;
    private List<Produto>produtos;
    public  Loja(int id,String nome,String endereco){
       this.id=id;
        this.nome=nome;
        this.endereco=endereco;
        this.produtos=new ArrayList<>();

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        produtos.add(produto);

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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}

