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
import modelo.Produto;
import util.Recursivo;

/**
 * DAO responsável pela persistência de produtos em arquivo CSV.
 * Implementa operações CRUD e tratamento de IOException.
 */
public class ProdutoDao {
	// Lista em memória com os produtos carregados do arquivo
	private static List<Produto> produtos = new ArrayList<Produto>();

	/**
	 * Carrega os dados de produtos do arquivo CSV para a memória.
	 *
	 * @throws IOException se ocorrer erro de leitura do arquivo
	 */
	public static void carregar() throws IOException {
		produtos.clear();
		Arquivos.inicializarDados();
		try (BufferedReader reader = new BufferedReader(new FileReader(Arquivos.ARQ_PRODUTOS))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue;
				}
				String[] campos = linha.split(";", -1);
				if (campos.length >= 6) {
					int id = Integer.parseInt(campos[0].trim());
					String nome = campos[1];
					String categoria = campos[2];
					String tamanho = campos[3];
					double preco = Double.parseDouble(campos[4].trim().replace(",", "."));
					int quantidadeEstoque = Integer.parseInt(campos[5].trim());
					produtos.add(new Produto(id, nome, categoria, tamanho, preco, quantidadeEstoque));
				}
			}
		} catch (NumberFormatException e) {
			throw new IOException("Erro ao converter dados do arquivo de produtos.", e);
		}
	}

	/**
	 * Salva todos os produtos da memória no arquivo CSV.
	 *
	 * @throws IOException se ocorrer erro de escrita no arquivo
	 */
	public static void salvar() throws IOException {
		Arquivos.inicializarDados();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Arquivos.ARQ_PRODUTOS, false))) {
			for (Produto p : produtos) {
				writer.write(p.getId() + ";" + escapar(p.getNome()) + ";" + escapar(p.getCategoria()) + ";"
						+ escapar(p.getTamanho()) + ";" + p.getPreco() + ";" + p.getQuantidadeEstoque());
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
	 * Cadastra (create) um novo produto e persiste no arquivo.
	 */
	public static void create(Produto produto) throws IOException, ValidacaoException {
		validar(produto);
		produtos.add(produto);
		try {
			salvar();
		} catch (IOException e) {
			produtos.remove(produto);
			throw e;
		}
	}

	/**
	 * Retorna a lista de produtos cadastrados (read).
	 */
	public static List<Produto> readAll() {
		return produtos;
	}

	/**
	 * Busca um produto pelo ID utilizando busca recursiva.
	 */
	public static Produto readById(int id) throws NaoEncontradoException {
		Produto encontrado = Recursivo.buscarProdutoRecursivo(produtos, id, 0);
		if (encontrado == null) {
			throw new NaoEncontradoException("Produto com ID " + id + " não encontrado.");
		}
		return encontrado;
	}

	/**
	 * Atualiza (update) os dados de um produto existente e persiste no arquivo.
	 */
	public static void update(Produto produtoAtualizado) throws IOException, NaoEncontradoException, ValidacaoException {
		validar(produtoAtualizado);
		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getId() == produtoAtualizado.getId()) {
				produtos.set(i, produtoAtualizado);
				salvar();
				return;
			}
		}
		throw new NaoEncontradoException("Produto com ID " + produtoAtualizado.getId() + " não encontrado.");
	}

	/**
	 * Remove (delete) um produto pelo ID e persiste no arquivo.
	 */
	public static void delete(int id) throws IOException, NaoEncontradoException {
		Produto encontrado = readById(id);
		produtos.remove(encontrado);
		try {
			salvar();
		} catch (IOException e) {
			produtos.add(encontrado);
			throw e;
		}
	}

	// Valida os dados obrigatórios do produto
	private static void validar(Produto produto) throws ValidacaoException {
		if (produto == null) {
			throw new ValidacaoException("Produto inválido.");
		}
		if (produto.getId() <= 0) {
			throw new ValidacaoException("ID do produto deve ser maior que zero.");
		}
		if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome do produto é obrigatório.");
		}
		if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
			throw new ValidacaoException("Categoria do produto é obrigatória.");
		}
		if (produto.getTamanho() == null || produto.getTamanho().trim().isEmpty()) {
			throw new ValidacaoException("Tamanho do produto é obrigatório.");
		}
		if (produto.getPreco() < 0) {
			throw new ValidacaoException("Preço do produto não pode ser negativo.");
		}
		if (produto.getQuantidadeEstoque() < 0) {
			throw new ValidacaoException("Quantidade em estoque não pode ser negativa.");
		}
	}

	// Retorna produto por ID sem lançar exceção (auxiliar para resolução de relacionamentos)
	public static Produto buscarPorIdInterno(int id) {
		return Recursivo.buscarProdutoRecursivo(produtos, id, 0);
	}
}
