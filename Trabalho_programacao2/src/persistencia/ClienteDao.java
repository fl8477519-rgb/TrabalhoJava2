package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excecao.NaoEncontradoException;
import excecao.ValidacaoException;
import modelo.Cliente;
import util.Recursivo;

/**
 * DAO responsável pela persistência de clientes em arquivo CSV.
 * Implementa operações CRUD e tratamento de IOException.
 */
public class ClienteDao {
	// Lista em memória com os clientes carregados do arquivo
	private static List<Cliente> clientes = new ArrayList<Cliente>();

	/**
	 * Carrega os dados de clientes do arquivo CSV para a memória.
	 * Deve ser chamado ao iniciar o sistema.
	 *
	 * @throws IOException se ocorrer erro de leitura do arquivo
	 */
	public static void carregar() throws IOException {
		clientes.clear();
		Arquivos.inicializarDados();
		try (BufferedReader reader = new BufferedReader(new FileReader(Arquivos.ARQ_CLIENTES))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue;
				}
				String[] campos = linha.split(";", -1);
				if (campos.length >= 4) {
					int id = Integer.parseInt(campos[0].trim());
					String nome = campos[1];
					String cpf = campos[2];
					String telefone = campos[3];
					clientes.add(new Cliente(id, nome, cpf, telefone));
				}
			}
		} catch (NumberFormatException e) {
			throw new IOException("Erro ao converter dados do arquivo de clientes.", e);
		}
	}

	/**
	 * Salva todos os clientes da memória no arquivo CSV.
	 * Chamado sempre que há alteração (create, update, delete).
	 *
	 * @throws IOException se ocorrer erro de escrita no arquivo
	 */
	public static void salvar() throws IOException {
		Arquivos.inicializarDados();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Arquivos.ARQ_CLIENTES, false))) {
			for (Cliente c : clientes) {
				writer.write(c.getId() + ";" + escapar(c.getNome()) + ";" + escapar(c.getCpf()) + ";" + escapar(c.getTelefone()));
				writer.newLine();
			}
		}
	}

	// Remove quebras de linha e separador para manter o formato CSV simples
	private static String escapar(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replace(";", ",").replace("\n", " ").replace("\r", " ");
	}

	/**
	 * Cadastra (create) um novo cliente e persiste no arquivo.
	 *
	 * @param cliente cliente a ser cadastrado
	 * @throws IOException        se ocorrer erro ao salvar o arquivo
	 * @throws ValidacaoException se os dados do cliente forem inválidos
	 */
	public static void create(Cliente cliente) throws IOException, ValidacaoException {
		validar(cliente);
		clientes.add(cliente);
		try {
			salvar();
		} catch (IOException e) {
			// Reverte a inclusão em memória caso a persistência falhe
			clientes.remove(cliente);
			throw e;
		}
	}

	/**
	 * Retorna a lista de clientes cadastrados (read).
	 *
	 * @return lista de clientes
	 */
	public static List<Cliente> readAll() {
		return clientes;
	}

	/**
	 * Busca um cliente pelo ID utilizando busca recursiva.
	 *
	 * @param id identificador do cliente
	 * @return cliente encontrado
	 * @throws NaoEncontradoException se o cliente não existir
	 */
	public static Cliente readById(int id) throws NaoEncontradoException {
		Cliente encontrado = Recursivo.buscarClienteRecursivo(clientes, id, 0);
		if (encontrado == null) {
			throw new NaoEncontradoException("Cliente com ID " + id + " não encontrado.");
		}
		return encontrado;
	}

	/**
	 * Atualiza (update) os dados de um cliente existente e persiste no arquivo.
	 *
	 * @param clienteAtualizado cliente com dados atualizados
	 * @throws IOException            se ocorrer erro ao salvar o arquivo
	 * @throws NaoEncontradoException se o cliente não existir
	 * @throws ValidacaoException     se os dados forem inválidos
	 */
	public static void update(Cliente clienteAtualizado) throws IOException, NaoEncontradoException, ValidacaoException {
		validar(clienteAtualizado);
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId() == clienteAtualizado.getId()) {
				clientes.set(i, clienteAtualizado);
				salvar();
				return;
			}
		}
		throw new NaoEncontradoException("Cliente com ID " + clienteAtualizado.getId() + " não encontrado.");
	}

	/**
	 * Remove (delete) um cliente pelo ID e persiste no arquivo.
	 *
	 * @param id identificador do cliente
	 * @throws IOException            se ocorrer erro ao salvar o arquivo
	 * @throws NaoEncontradoException se o cliente não existir
	 */
	public static void delete(int id) throws IOException, NaoEncontradoException {
		Cliente encontrado = readById(id);
		clientes.remove(encontrado);
		try {
			salvar();
		} catch (IOException e) {
			// Reverte a remoção em memória caso a persistência falhe
			clientes.add(encontrado);
			throw e;
		}
	}

	// Valida os dados obrigatórios do cliente
	private static void validar(Cliente cliente) throws ValidacaoException {
		if (cliente == null) {
			throw new ValidacaoException("Cliente inválido.");
		}
		if (cliente.getId() <= 0) {
			throw new ValidacaoException("ID do cliente deve ser maior que zero.");
		}
		if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome do cliente é obrigatório.");
		}
		if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
			throw new ValidacaoException("CPF do cliente é obrigatório.");
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
			throw new ValidacaoException("Telefone do cliente é obrigatório.");
		}
	}

	// Retorna cliente por ID sem lançar exceção (auxiliar para resolução de relacionamentos)
	public static Cliente buscarPorIdInterno(int id) {
		return Recursivo.buscarClienteRecursivo(clientes, id, 0);
	}
}
