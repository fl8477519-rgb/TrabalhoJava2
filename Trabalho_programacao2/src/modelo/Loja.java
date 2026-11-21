package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma loja do sistema.
 * Uma loja pode estar associada a vendas e manter uma lista de produtos.
 */
public class Loja {
	// Identificador único da loja
	private int id;
	// Nome da loja
	private String nome;
	// Endereço da loja
	private String endereco;
	// Lista de produtos associados à loja
	private List<Produto> produtos;

	/**
	 * Construtor padrão.
	 * Inicializa a lista de produtos.
	 */
	public Loja() {
		this.produtos = new ArrayList<Produto>();
	}

	/**
	 * Construtor com atributos principais.
	 *
	 * @param id       identificador da loja
	 * @param nome     nome da loja
	 * @param endereco endereço da loja
	 */
	public Loja(int id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.produtos = new ArrayList<Produto>();
	}

	// Adiciona um produto à loja, evitando valores nulos e duplicados
	public void adicionarProduto(Produto produto) {
		if (produto != null && !produtos.contains(produto)) {
			produtos.add(produto);
		}
	}

	// Retorna um produto da lista pelo índice informado
	public Produto buscarProduto(int indice) {
		return produtos.get(indice);
	}

	// Retorna o ID da loja
	public int getId() {
		return id;
	}

	// Define o ID da loja
	public void setId(int id) {
		this.id = id;
	}

	// Retorna o nome da loja
	public String getNome() {
		return nome;
	}

	// Define o nome da loja
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna o endereço da loja
	public String getEndereco() {
		return endereco;
	}

	// Define o endereço da loja
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// Retorna a lista de produtos da loja
	public List<Produto> getProdutos() {
		return produtos;
	}

	// Define a lista de produtos da loja
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * Retorna uma representação textual da loja.
	 */
	@Override
	public String toString() {
		return "Loja [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
}
