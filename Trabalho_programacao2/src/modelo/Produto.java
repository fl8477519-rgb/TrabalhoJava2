package modelo;

/**
 * Representa um produto comercializado no sistema.
 */
public class Produto {
	// Identificador único do produto
	private int id;
	// Nome do produto
	private String nome;
	// Categoria do produto (ex.: camisa, calça)
	private String categoria;
	// Tamanho do produto
	private String tamanho;
	// Preço unitário do produto
	private double preco;
	// Quantidade disponível em estoque
	private int quantidadeEstoque;

	/**
	 * Construtor padrão.
	 */
	public Produto() {
	}

	/**
	 * Construtor com todos os atributos.
	 *
	 * @param id                identificador do produto
	 * @param nome              nome do produto
	 * @param categoria         categoria do produto
	 * @param tamanho           tamanho do produto
	 * @param preco             preço do produto
	 * @param quantidadeEstoque quantidade em estoque
	 */
	public Produto(int id, String nome, String categoria, String tamanho, double preco, int quantidadeEstoque) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.tamanho = tamanho;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	// Retorna o ID do produto
	public int getId() {
		return id;
	}

	// Define o ID do produto
	public void setId(int id) {
		this.id = id;
	}

	// Retorna o nome do produto
	public String getNome() {
		return nome;
	}

	// Define o nome do produto
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna a categoria do produto
	public String getCategoria() {
		return categoria;
	}

	// Define a categoria do produto
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	// Retorna o tamanho do produto
	public String getTamanho() {
		return tamanho;
	}

	// Define o tamanho do produto
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	// Retorna o preço do produto
	public double getPreco() {
		return preco;
	}

	// Define o preço do produto
	public void setPreco(double preco) {
		this.preco = preco;
	}

	// Retorna a quantidade em estoque
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	// Define a quantidade em estoque
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	/**
	 * Retorna uma representação textual do produto.
	 */
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", tamanho=" + tamanho
				+ ", preco=" + preco + ", quantidadeEstoque=" + quantidadeEstoque + "]";
	}
}
