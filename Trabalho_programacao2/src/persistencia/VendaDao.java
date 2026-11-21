package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excecao.NaoEncontradoException;
import excecao.ValidacaoException;
import modelo.Cliente;
import modelo.Loja;
import modelo.Produto;
import modelo.Venda;
import util.Recursivo;

/**
 * DAO responsável pela persistência de vendas em arquivo CSV.
 * Armazena IDs das entidades relacionadas e resolve as referências ao carregar.
 */
public class VendaDao {
	// Lista em memória com as vendas carregadas do arquivo
	private static List<Venda> vendas = new ArrayList<Venda>();

	/**
	 * Carrega os dados de vendas do arquivo CSV para a memória.
	 * Requer que clientes, produtos e lojas já estejam carregados para resolver referências.
	 *
	 * @throws IOException se ocorrer erro de leitura do arquivo
	 */
	public static void carregar() throws IOException {
		vendas.clear();
		Arquivos.inicializarDados();
		try (BufferedReader reader = new BufferedReader(new FileReader(Arquivos.ARQ_VENDAS))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue;
				}
				String[] campos = linha.split(";", -1);
				if (campos.length >= 4) {
					int id = Integer.parseInt(campos[0].trim());
					int clienteId = Integer.parseInt(campos[1].trim());
					int produtoId = Integer.parseInt(campos[2].trim());
					int lojaId = Integer.parseInt(campos[3].trim());

					Cliente cliente = ClienteDao.buscarPorIdInterno(clienteId);
					Produto produto = ProdutoDao.buscarPorIdInterno(produtoId);
					Loja loja = null;
					if (lojaId > 0) {
						loja = LojaDao.buscarPorIdInterno(lojaId);
					}

					// Mantém a venda apenas se as referências obrigatórias existirem
					if (cliente != null && produto != null) {
						vendas.add(new Venda(id, cliente, produto, loja));
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new IOException("Erro ao converter dados do arquivo de vendas.", e);
		}
	}

	/**
	 * Salva todas as vendas da memória no arquivo CSV.
	 *
	 * @throws IOException se ocorrer erro de escrita no arquivo
	 */
	public static void salvar() throws IOException {
		Arquivos.inicializarDados();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Arquivos.ARQ_VENDAS, false))) {
			for (Venda v : vendas) {
				int clienteId = (v.getCliente() != null) ? v.getCliente().getId() : 0;
				int produtoId = (v.getProduto() != null) ? v.getProduto().getId() : 0;
				int lojaId = (v.getLoja() != null) ? v.getLoja().getId() : 0;
				writer.write(v.getId() + ";" + clienteId + ";" + produtoId + ";" + lojaId);
				writer.newLine();
			}
		}
	}

	/**
	 * Cadastra (create) uma nova venda e persiste no arquivo.
	 */
	public static void create(Venda venda) throws IOException, ValidacaoException {
		validar(venda);
		vendas.add(venda);
		try {
			salvar();
		} catch (IOException e) {
			vendas.remove(venda);
			throw e;
		}
	}

	/**
	 * Retorna a lista de vendas cadastradas (read).
	 */
	public static List<Venda> readAll() {
		return vendas;
	}

	/**
	 * Busca uma venda pelo ID utilizando busca recursiva.
	 */
	public static Venda readById(int id) throws NaoEncontradoException {
		Venda encontrada = Recursivo.buscarVendaRecursivo(vendas, id, 0);
		if (encontrada == null) {
			throw new NaoEncontradoException("Venda com ID " + id + " não encontrada.");
		}
		return encontrada;
	}

	/**
	 * Atualiza (update) os dados de uma venda existente e persiste no arquivo.
	 */
	public static void update(Venda vendaAtualizada) throws IOException, NaoEncontradoException, ValidacaoException {
		validar(vendaAtualizada);
		for (int i = 0; i < vendas.size(); i++) {
			if (vendas.get(i).getId() == vendaAtualizada.getId()) {
				vendas.set(i, vendaAtualizada);
				salvar();
				return;
			}
		}
		throw new NaoEncontradoException("Venda com ID " + vendaAtualizada.getId() + " não encontrada.");
	}

	/**
	 * Remove (delete) uma venda pelo ID e persiste no arquivo.
	 */
	public static void delete(int id) throws IOException, NaoEncontradoException {
		Venda encontrada = readById(id);
		vendas.remove(encontrada);
		try {
			salvar();
		} catch (IOException e) {
			vendas.add(encontrada);
			throw e;
		}
	}

	// Valida os dados obrigatórios da venda
	private static void validar(Venda venda) throws ValidacaoException {
		if (venda == null) {
			throw new ValidacaoException("Venda inválida.");
		}
		if (venda.getId() <= 0) {
			throw new ValidacaoException("ID da venda deve ser maior que zero.");
		}
		if (venda.getCliente() == null) {
			throw new ValidacaoException("Cliente da venda é obrigatório.");
		}
		if (venda.getProduto() == null) {
			throw new ValidacaoException("Produto da venda é obrigatório.");
		}
	}
}
