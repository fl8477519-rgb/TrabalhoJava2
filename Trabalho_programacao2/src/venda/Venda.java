package venda;

import loja.Loja;
import pessoa.Cliente;
import produto.Produto;

public class Venda {
    private  int id ;
    private Cliente cliente;
    private Loja loja;
    private Produto produto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }



    public Venda(int id, Cliente cliente, Produto produto) {
        this.id = id;
        this.cliente = cliente;
        this.loja = loja;
        this.produto = produto;


    }
    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", loja=" + loja +
                ", produto=" + produto +
                '}';
    }
}

