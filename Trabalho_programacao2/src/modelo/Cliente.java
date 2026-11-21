package modelo;

/**
 * Representa um cliente do sistema.
 * Contém os dados básicos utilizados nas operações de cadastro e venda.
 */
public class Cliente {
	// Identificador único do cliente
	private int id;
	// Nome do cliente
	private String nome;
	// CPF do cliente
	private String cpf;
	// Telefone de contato do cliente
	private String telefone;

	/**
	 * Construtor padrão.
	 */
	public Cliente() {
	}

	/**
	 * Construtor com todos os atributos.
	 *
	 * @param id       identificador do cliente
	 * @param nome     nome do cliente
	 * @param cpf      CPF do cliente
	 * @param telefone telefone do cliente
	 */
	public Cliente(int id, String nome, String cpf, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	// Retorna o ID do cliente
	public int getId() {
		return id;
	}

	// Define o ID do cliente
	public void setId(int id) {
		this.id = id;
	}

	// Retorna o nome do cliente
	public String getNome() {
		return nome;
	}

	// Define o nome do cliente
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna o CPF do cliente
	public String getCpf() {
		return cpf;
	}

	// Define o CPF do cliente
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	// Retorna o telefone do cliente
	public String getTelefone() {
		return telefone;
	}

	// Define o telefone do cliente
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Retorna uma representação textual do cliente.
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + "]";
	}
}
