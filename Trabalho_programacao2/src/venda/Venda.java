package venda;

import loja.Loja;
import pessoa.Cliente;

import java.util.Locale;

public class Venda {
    private Cliente cliente;
    private Loja loja;

    public Venda(Cliente cliente, Loja loja) {
        this.cliente = cliente;
        this.loja = loja;
    }
}
