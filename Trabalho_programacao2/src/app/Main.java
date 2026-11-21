package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import excecao.NaoEncontradoException;
import excecao.ValidacaoException;
import modelo.Cliente;
import modelo.Loja;
import modelo.Produto;
import modelo.Venda;
import persistencia.Arquivos;
import persistencia.ClienteDao;
import persistencia.LojaDao;
import persistencia.ProdutoDao;
import persistencia.VendaDao;
import util.Recursivo;

/**
 * Classe principal da aplicação.
 * Responsável pelos menus, interação com o usuário e chamadas às operações de CRUD.
 */
public class Main {
	// Scanner único para leitura de dados do usuário
	private static final Scanner SC = new Scanner(System.in);

	/**
	 * Método de entrada do sistema.
	 * Inicializa arquivos, carrega dados e exibe o menu principal.
	 */
	public static void main(String[] args) {
		try {
			// Inicializa diretórios/arquivos e carrega dados ao iniciar (persistência)
			Arquivos.inicializarDados();
			ClienteDao.carregar();
			ProdutoDao.carregar();
			LojaDao.carregar();
			VendaDao.carregar();
		} catch (IOException e) {
			System.out.println("Erro ao inicializar/carregar os dados: " + e.getMessage());
		}

		int opcao;
		do {
			mostrarMenuPrincipal();
			// Uso de função recursiva para validação/repetição da leitura da opção
			opcao = Recursivo.lerOpcaoRecursivo(SC, 0, 4, "Escolha uma opção: ");

			switch (opcao) {
			case 1:
				menuClientes();
				break;
			case 2:
				menuProdutos();
				break;
			case 3:
				menuLojas();
				break;
			case 4:
				menuVendas();
				break;
			case 0:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);

		SC.close();
	}

	// Exibe o menu principal
	private static void mostrarMenuPrincipal() {
		System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
		System.out.println("1 - Clientes");
		System.out.println("2 - Produtos");
		System.out.println("3 - Lojas");
		System.out.println("4 - Vendas");
		System.out.println("0 - Sair");
		System.out.println("==============================================");
	}

	// Exibe o submenu padrão de CRUD
	private static void mostrarSubmenu(String titulo) {
		System.out.println("\n---------- " + titulo + " ----------");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Listar");
		System.out.println("3 - Buscar");
		System.out.println("4 - Atualizar");
		System.out.println("5 - Remover");
		System.out.println("0 - Voltar");
	}

	// ========================= CLIENTES =========================

	// Controla o submenu de clientes
	private static void menuClientes() {
		int opcao;
		do {
			mostrarSubmenu("CLIENTES");
			opcao = Recursivo.lerOpcaoRecursivo(SC, 0, 5, "Escolha uma opção: ");
			switch (opcao) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				listarClientes();
				break;
			case 3:
				buscarCliente();
				break;
			case 4:
				atualizarCliente();
				break;
			case 5:
				removerCliente();
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);
	}

	// Cadastra um novo cliente
	private static void cadastrarCliente() {
		try {
			System.out.println("\n--- Cadastro de Cliente ---");
			int id = Arquivos.lerId("clientes") + 1;
			System.out.print("Nome: ");
			String nome = SC.nextLine();
			System.out.print("CPF: ");
			String cpf = SC.nextLine();
			System.out.print("Telefone: ");
			String telefone = SC.nextLine();

			Cliente cliente = new Cliente(id, nome, cpf, telefone);
			ClienteDao.create(cliente);
			Arquivos.incrementarId("clientes");
			System.out.println("Cliente cadastrado com sucesso! ID: " + id);
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao cadastrar cliente: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao cadastrar cliente: " + e.getMessage());
		}
	}

	// Lista todos os clientes cadastrados
	private static void listarClientes() {
		List<Cliente> clientes = ClienteDao.readAll();
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
		} else {
			System.out.println("\n--- Lista de Clientes ---");
			listarClientesRecursivo(clientes, 0); // Listagem recursiva
		}
	}

	// Método recursivo para listar clientes (demonstra recursividade na listagem)
	private static void listarClientesRecursivo(List<Cliente> clientes, int indice) {
		if (indice >= clientes.size()) {
			return; // Caso base
		}
		System.out.println(clientes.get(indice));
		listarClientesRecursivo(clientes, indice + 1); // Chamada recursiva
	}

	// Busca um cliente pelo ID (utiliza busca recursiva no DAO/utilitário)
	private static void buscarCliente() {
		try {
			int id = lerInteiro("Informe o ID do cliente: ");
			Cliente cliente = ClienteDao.readById(id);
			System.out.println("Cliente encontrado: " + cliente);
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente: " + e.getMessage());
		}
	}

	// Atualiza os dados de um cliente existente
	private static void atualizarCliente() {
		try {
			int id = lerInteiro("Informe o ID do cliente para atualizar: ");
			Cliente cliente = ClienteDao.readById(id);
			System.out.print("Novo nome: ");
			cliente.setNome(SC.nextLine());
			System.out.print("Novo CPF: ");
			cliente.setCpf(SC.nextLine());
			System.out.print("Novo telefone: ");
			cliente.setTelefone(SC.nextLine());

			ClienteDao.update(cliente);
			System.out.println("Cliente atualizado com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao atualizar cliente: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao atualizar cliente: " + e.getMessage());
		}
	}

	// Remove um cliente pelo ID
	private static void removerCliente() {
		try {
			int id = lerInteiro("Informe o ID do cliente para remover: ");
			ClienteDao.delete(id);
			System.out.println("Cliente removido com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao remover cliente: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao remover cliente: " + e.getMessage());
		}
	}

	// ========================= PRODUTOS =========================

	// Controla o submenu de produtos
	private static void menuProdutos() {
		int opcao;
		do {
			mostrarSubmenu("PRODUTOS");
			opcao = Recursivo.lerOpcaoRecursivo(SC, 0, 5, "Escolha uma opção: ");
			switch (opcao) {
			case 1:
				cadastrarProduto();
				break;
			case 2:
				listarProdutos();
				break;
			case 3:
				buscarProduto();
				break;
			case 4:
				atualizarProduto();
				break;
			case 5:
				removerProduto();
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);
	}

	// Cadastra um novo produto
	private static void cadastrarProduto() {
		try {
			System.out.println("\n--- Cadastro de Produto ---");
			int id = Arquivos.lerId("produtos") + 1;
			System.out.print("Nome: ");
			String nome = SC.nextLine();
			System.out.print("Categoria: ");
			String categoria = SC.nextLine();
			System.out.print("Tamanho: ");
			String tamanho = SC.nextLine();
			double preco = lerDouble("Preço: ");
			int quantidade = lerInteiro("Quantidade em estoque: ");

			Produto produto = new Produto(id, nome, categoria, tamanho, preco, quantidade);
			ProdutoDao.create(produto);
			Arquivos.incrementarId("produtos");
			System.out.println("Produto cadastrado com sucesso! ID: " + id);
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao cadastrar produto: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao cadastrar produto: " + e.getMessage());
		}
	}

	// Lista todos os produtos cadastrados
	private static void listarProdutos() {
		List<Produto> produtos = ProdutoDao.readAll();
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado.");
		} else {
			System.out.println("\n--- Lista de Produtos ---");
			for (Produto p : produtos) {
				System.out.println(p);
			}
		}
	}

	// Busca um produto pelo ID
	private static void buscarProduto() {
		try {
			int id = lerInteiro("Informe o ID do produto: ");
			Produto produto = ProdutoDao.readById(id);
			System.out.println("Produto encontrado: " + produto);
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao buscar produto: " + e.getMessage());
		}
	}

	// Atualiza os dados de um produto existente
	private static void atualizarProduto() {
		try {
			int id = lerInteiro("Informe o ID do produto para atualizar: ");
			Produto produto = ProdutoDao.readById(id);
			System.out.print("Novo nome: ");
			produto.setNome(SC.nextLine());
			System.out.print("Nova categoria: ");
			produto.setCategoria(SC.nextLine());
			System.out.print("Novo tamanho: ");
			produto.setTamanho(SC.nextLine());
			produto.setPreco(lerDouble("Novo preço: "));
			produto.setQuantidadeEstoque(lerInteiro("Nova quantidade em estoque: "));

			ProdutoDao.update(produto);
			System.out.println("Produto atualizado com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao atualizar produto: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao atualizar produto: " + e.getMessage());
		}
	}

	// Remove um produto pelo ID
	private static void removerProduto() {
		try {
			int id = lerInteiro("Informe o ID do produto para remover: ");
			ProdutoDao.delete(id);
			System.out.println("Produto removido com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao remover produto: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao remover produto: " + e.getMessage());
		}
	}

	// ========================= LOJAS =========================

	// Controla o submenu de lojas
	private static void menuLojas() {
		int opcao;
		do {
			mostrarSubmenu("LOJAS");
			opcao = Recursivo.lerOpcaoRecursivo(SC, 0, 5, "Escolha uma opção: ");
			switch (opcao) {
			case 1:
				cadastrarLoja();
				break;
			case 2:
				listarLojas();
				break;
			case 3:
				buscarLoja();
				break;
			case 4:
				atualizarLoja();
				break;
			case 5:
				removerLoja();
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);
	}

	// Cadastra uma nova loja
	private static void cadastrarLoja() {
		try {
			System.out.println("\n--- Cadastro de Loja ---");
			int id = Arquivos.lerId("lojas") + 1;
			System.out.print("Nome: ");
			String nome = SC.nextLine();
			System.out.print("Endereço: ");
			String endereco = SC.nextLine();

			Loja loja = new Loja(id, nome, endereco);
			LojaDao.create(loja);
			Arquivos.incrementarId("lojas");
			System.out.println("Loja cadastrada com sucesso! ID: " + id);
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao cadastrar loja: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao cadastrar loja: " + e.getMessage());
		}
	}

	// Lista todas as lojas cadastradas
	private static void listarLojas() {
		List<Loja> lojas = LojaDao.readAll();
		if (lojas.isEmpty()) {
			System.out.println("Nenhuma loja cadastrada.");
		} else {
			System.out.println("\n--- Lista de Lojas ---");
			for (Loja l : lojas) {
				System.out.println(l);
			}
		}
	}

	// Busca uma loja pelo ID
	private static void buscarLoja() {
		try {
			int id = lerInteiro("Informe o ID da loja: ");
			Loja loja = LojaDao.readById(id);
			System.out.println("Loja encontrada: " + loja);
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao buscar loja: " + e.getMessage());
		}
	}

	// Atualiza os dados de uma loja existente
	private static void atualizarLoja() {
		try {
			int id = lerInteiro("Informe o ID da loja para atualizar: ");
			Loja loja = LojaDao.readById(id);
			System.out.print("Novo nome: ");
			loja.setNome(SC.nextLine());
			System.out.print("Novo endereço: ");
			loja.setEndereco(SC.nextLine());

			LojaDao.update(loja);
			System.out.println("Loja atualizada com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao atualizar loja: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao atualizar loja: " + e.getMessage());
		}
	}

	// Remove uma loja pelo ID
	private static void removerLoja() {
		try {
			int id = lerInteiro("Informe o ID da loja para remover: ");
			LojaDao.delete(id);
			System.out.println("Loja removida com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao remover loja: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao remover loja: " + e.getMessage());
		}
	}

	// ========================= VENDAS =========================

	// Exibe o submenu de vendas (inclui cálculo recursivo do total)
	private static void mostrarSubmenuVendas() {
		System.out.println("\n---------- VENDAS ----------");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Listar");
		System.out.println("3 - Buscar");
		System.out.println("4 - Atualizar");
		System.out.println("5 - Remover");
		System.out.println("6 - Calcular total das vendas (recursivo)");
		System.out.println("0 - Voltar");
	}

	// Controla o submenu de vendas
	private static void menuVendas() {
		int opcao;
		do {
			mostrarSubmenuVendas();
			opcao = Recursivo.lerOpcaoRecursivo(SC, 0, 6, "Escolha uma opção: ");
			switch (opcao) {
			case 1:
				cadastrarVenda();
				break;
			case 2:
				listarVendas();
				break;
			case 3:
				buscarVenda();
				break;
			case 4:
				atualizarVenda();
				break;
			case 5:
				removerVenda();
				break;
			case 6:
				calcularTotalVendasRecursivo();
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);
	}

	// Cadastra uma nova venda
	private static void cadastrarVenda() {
		try {
			System.out.println("\n--- Cadastro de Venda ---");
			if (ClienteDao.readAll().isEmpty() || ProdutoDao.readAll().isEmpty()) {
				System.out.println("É necessário ter pelo menos um cliente e um produto cadastrados.");
				return;
			}

			int id = Arquivos.lerId("vendas") + 1;
			int idCliente = lerInteiro("ID do cliente: ");
			Cliente cliente = ClienteDao.readById(idCliente);

			int idProduto = lerInteiro("ID do produto: ");
			Produto produto = ProdutoDao.readById(idProduto);

			int idLoja = lerInteiro("ID da loja (0 para nenhuma): ");
			Loja loja = null;
			if (idLoja > 0) {
				try {
					loja = LojaDao.readById(idLoja);
				} catch (NaoEncontradoException e) {
					System.out.println("Loja não encontrada. A venda será registrada sem loja.");
				}
			}

			Venda venda = new Venda(id, cliente, produto, loja);
			VendaDao.create(venda);
			Arquivos.incrementarId("vendas");
			System.out.println("Venda cadastrada com sucesso! ID: " + id);
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao cadastrar venda: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao cadastrar venda: " + e.getMessage());
		}
	}

	// Lista todas as vendas cadastradas (utiliza listagem recursiva do utilitário)
	private static void listarVendas() {
		List<Venda> vendas = VendaDao.readAll();
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda cadastrada.");
		} else {
			System.out.println("\n--- Lista de Vendas ---");
			// Uso de função recursiva para listagem das vendas
			Recursivo.listarVendasRecursivo(vendas, 0);
		}
	}

	// Busca uma venda pelo ID (busca recursiva no DAO/utilitário)
	private static void buscarVenda() {
		try {
			int id = lerInteiro("Informe o ID da venda: ");
			Venda venda = VendaDao.readById(id);
			System.out.println("Venda encontrada: " + venda);
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao buscar venda: " + e.getMessage());
		}
	}

	// Atualiza os dados de uma venda existente
	private static void atualizarVenda() {
		try {
			int id = lerInteiro("Informe o ID da venda para atualizar: ");
			Venda venda = VendaDao.readById(id);

			int idCliente = lerInteiro("Novo ID do cliente: ");
			Cliente cliente = ClienteDao.readById(idCliente);

			int idProduto = lerInteiro("Novo ID do produto: ");
			Produto produto = ProdutoDao.readById(idProduto);

			int idLoja = lerInteiro("Novo ID da loja (0 para nenhuma): ");
			Loja loja = null;
			if (idLoja > 0) {
				try {
					loja = LojaDao.readById(idLoja);
				} catch (NaoEncontradoException e) {
					System.out.println("Loja não encontrada. A venda será registrada sem loja.");
				}
			}

			venda.setCliente(cliente);
			venda.setProduto(produto);
			venda.setLoja(loja);

			VendaDao.update(venda);
			System.out.println("Venda atualizada com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao atualizar venda: " + e.getMessage());
		} catch (ValidacaoException e) {
			System.out.println("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao atualizar venda: " + e.getMessage());
		}
	}

	// Remove uma venda pelo ID
	private static void removerVenda() {
		try {
			int id = lerInteiro("Informe o ID da venda para remover: ");
			VendaDao.delete(id);
			System.out.println("Venda removida com sucesso!");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro de arquivo ao remover venda: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro ao remover venda: " + e.getMessage());
		}
	}

	// Calcula e exibe o total das vendas usando soma recursiva
	private static void calcularTotalVendasRecursivo() {
		List<Venda> vendas = VendaDao.readAll();
		// Uso de função recursiva para somar o valor total das vendas
		double total = Recursivo.somarTotalVendasRecursivo(vendas, 0);
		System.out.println("Total das vendas (soma recursiva): R$ " + String.format("%.2f", total));
	}

	// ========================= LEITURAS AUXILIARES =========================

	// Lê um número inteiro com tratamento de InputMismatchException (repetição até entrada válida)
	private static int lerInteiro(String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				int valor = SC.nextInt();
				SC.nextLine(); // Consome a quebra de linha
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Digite um número inteiro.");
				SC.nextLine(); // Limpa o buffer
			}
		}
	}

	// Lê um número decimal com tratamento de InputMismatchException (repetição até entrada válida)
	private static double lerDouble(String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				double valor = SC.nextDouble();
				SC.nextLine(); // Consome a quebra de linha
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Digite um número válido (ex.: 10,5 ou 10.5 conforme locale).");
				SC.nextLine(); // Limpa o buffer
			}
		}
	}
}
