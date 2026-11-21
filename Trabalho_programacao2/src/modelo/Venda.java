package modelo;

/**
 * Representa uma venda realizada no sistema.
 * Associa um cliente, um produto e, opcionalmente, uma loja.
 */
public class Venda {
	// Identificador único da venda
	private int id;
	// Cliente associado à venda
	private Cliente cliente;
	// Produto associado à venda
	private Produto produto;
	// Loja associada à venda (pode ser nula)
	private Loja loja;

	/**
	 * Construtor padrão.
	 */
	public Venda() {
	}

	/**
	 * Construtor com todos os atributos.
	 *
	 * @param id      identificador da venda
	 * @param cliente cliente da venda
	 * @param produto produto da venda
	 * @param loja    loja da venda (opcional)
	 */
	public Venda(int id, Cliente cliente, Produto produto, Loja loja) {
		this.id = id;
		this.cliente = cliente;
		this.produto = produto;
		this.loja = loja;
	}

	// Retorna o ID da venda
	public int getId() {
		return id;
	}

	// Define o ID da venda
	public void setId(int id) {
		this.id = id;
	}

	// Retorna o cliente da venda
	public Cliente getCliente() {
		return cliente;
	}

	// Define o cliente da venda
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Retorna o produto da venda
	public Produto getProduto() {
		return produto;
	}

	// Define o produto da venda
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// Retorna a loja da venda
	public Loja getLoja() {
		return loja;
	}

	// Define a loja da venda
	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	/**
	 * Retorna uma representação textual da venda.
	 */
	@Override
	public String toString() {
		String nomeCliente = (cliente != null) ? cliente.getNome() + " (ID " + cliente.getId() + ")" : "N/A";
		String nomeProduto = (produto != null) ? produto.getNome() + " (ID " + produto.getId() + ")" : "N/A";
		String nomeLoja = (loja != null) ? loja.getNome() + " (ID " + loja.getId() + ")" : "Sem loja";
		return "Venda [id=" + id + ", cliente=" + nomeCliente + ", produto=" + nomeProduto + ", loja=" + nomeLoja + "]";
	}
}
