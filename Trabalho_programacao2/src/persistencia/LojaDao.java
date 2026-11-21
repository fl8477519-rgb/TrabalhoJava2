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
import modelo.Loja;
import util.Recursivo;

/**
 * DAO responsável pela persistência de lojas em arquivo CSV.
 * Implementa operações CRUD e tratamento de IOException.
 */
public class LojaDao {
	// Lista em memória com as lojas carregadas do arquivo
	private static List<Loja> lojas = new ArrayList<Loja>();

	/**
	 * Carrega os dados de lojas do arquivo CSV para a memória.
	 *
	 * @throws IOException se ocorrer erro de leitura do arquivo
	 */
	public static void carregar() throws IOException {
		lojas.clear();
		Arquivos.inicializarDados();
		try (BufferedReader reader = new BufferedReader(new FileReader(Arquivos.ARQ_LOJAS))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.trim().isEmpty()) {
					continue;
				}
				String[] campos = linha.split(";", -1);
				if (campos.length >= 3) {
					int id = Integer.parseInt(campos[0].trim());
					String nome = campos[1];
					String endereco = campos[2];
					lojas.add(new Loja(id, nome, endereco));
				}
			}
		} catch (NumberFormatException e) {
			throw new IOException("Erro ao converter dados do arquivo de lojas.", e);
		}
	}

	/**
	 * Salva todas as lojas da memória no arquivo CSV.
	 *
	 * @throws IOException se ocorrer erro de escrita no arquivo
	 */
	public static void salvar() throws IOException {
		Arquivos.inicializarDados();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Arquivos.ARQ_LOJAS, false))) {
			for (Loja l : lojas) {
				writer.write(l.getId() + ";" + escapar(l.getNome()) + ";" + escapar(l.getEndereco()));
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
	 * Cadastra (create) uma nova loja e persiste no arquivo.
	 */
	public static void create(Loja loja) throws IOException, ValidacaoException {
		validar(loja);
		lojas.add(loja);
		try {
			salvar();
		} catch (IOException e) {
			lojas.remove(loja);
			throw e;
		}
	}

	/**
	 * Retorna a lista de lojas cadastradas (read).
	 */
	public static List<Loja> readAll() {
		return lojas;
	}

	/**
	 * Busca uma loja pelo ID utilizando busca recursiva.
	 */
	public static Loja readById(int id) throws NaoEncontradoException {
		Loja encontrada = Recursivo.buscarLojaRecursivo(lojas, id, 0);
		if (encontrada == null) {
			throw new NaoEncontradoException("Loja com ID " + id + " não encontrada.");
		}
		return encontrada;
	}

	/**
	 * Atualiza (update) os dados de uma loja existente e persiste no arquivo.
	 */
	public static void update(Loja lojaAtualizada) throws IOException, NaoEncontradoException, ValidacaoException {
		validar(lojaAtualizada);
		for (int i = 0; i < lojas.size(); i++) {
			if (lojas.get(i).getId() == lojaAtualizada.getId()) {
				lojas.set(i, lojaAtualizada);
				salvar();
				return;
			}
		}
		throw new NaoEncontradoException("Loja com ID " + lojaAtualizada.getId() + " não encontrada.");
	}

	/**
	 * Remove (delete) uma loja pelo ID e persiste no arquivo.
	 */
	public static void delete(int id) throws IOException, NaoEncontradoException {
		Loja encontrada = readById(id);
		lojas.remove(encontrada);
		try {
			salvar();
		} catch (IOException e) {
			lojas.add(encontrada);
			throw e;
		}
	}

	// Valida os dados obrigatórios da loja
	private static void validar(Loja loja) throws ValidacaoException {
		if (loja == null) {
			throw new ValidacaoException("Loja inválida.");
		}
		if (loja.getId() <= 0) {
			throw new ValidacaoException("ID da loja deve ser maior que zero.");
		}
		if (loja.getNome() == null || loja.getNome().trim().isEmpty()) {
			throw new ValidacaoException("Nome da loja é obrigatório.");
		}
		if (loja.getEndereco() == null || loja.getEndereco().trim().isEmpty()) {
			throw new ValidacaoException("Endereço da loja é obrigatório.");
		}
	}

	// Retorna loja por ID sem lançar exceção (auxiliar para resolução de relacionamentos)
	public static Loja buscarPorIdInterno(int id) {
		return Recursivo.buscarLojaRecursivo(lojas, id, 0);
	}
}
