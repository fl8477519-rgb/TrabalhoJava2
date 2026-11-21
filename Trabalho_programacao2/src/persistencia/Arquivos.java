package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe utilitária para gerenciamento de arquivos e diretórios do sistema.
 * Responsável pelos caminhos, inicialização, reset e controle de IDs em arquivos.
 */
public class Arquivos {
	// Diretório raiz dos dados persistidos (resolvido automaticamente)
	public static final String RAIZ;
	// Arquivos CSV de cada entidade
	public static final String ARQ_CLIENTES;
	public static final String ARQ_PRODUTOS;
	public static final String ARQ_LOJAS;
	public static final String ARQ_VENDAS;
	// Diretório e arquivos de controle de IDs
	public static final String RAIZ_IDS;

	static {
		RAIZ = resolverRaiz();
		ARQ_CLIENTES = RAIZ + "clientes.csv";
		ARQ_PRODUTOS = RAIZ + "produtos.csv";
		ARQ_LOJAS = RAIZ + "lojas.csv";
		ARQ_VENDAS = RAIZ + "vendas.csv";
		RAIZ_IDS = RAIZ + "ids/";
	}

	// Resolve o diretório base de dados para funcionar a partir de diferentes diretórios de execução
	private static String resolverRaiz() {
		String[] candidatos = {
				"./dados/",
				"dados/",
				"./Trabalho_programacao2/dados/",
				"Trabalho_programacao2/dados/"
		};
		for (String candidato : candidatos) {
			File dir = new File(candidato);
			File ids = new File(candidato + "ids");
			if (dir.exists() && dir.isDirectory()) {
				return normalizar(candidato);
			}
			if (ids.exists() && ids.isDirectory()) {
				return normalizar(candidato);
			}
		}
		// Padrão: usa ./dados/ (será criado na inicialização)
		return "./dados/";
	}

	// Garante separador no final do caminho
	private static String normalizar(String caminho) {
		if (caminho.endsWith("/") || caminho.endsWith("\\")) {
			return caminho.replace("\\", "/");
		}
		return caminho.replace("\\", "/") + "/";
	}

	/**
	 * Inicializa a estrutura de diretórios e arquivos necessários.
	 * Garante que os arquivos CSV e de IDs existam para carregar dados ao iniciar.
	 *
	 * @throws IOException se ocorrer falha ao criar diretórios ou arquivos
	 */
	public static void inicializarDados() throws IOException {
		criarDiretorioSeNecessario(RAIZ);
		criarDiretorioSeNecessario(RAIZ_IDS);

		criarArquivoSeNecessario(ARQ_CLIENTES);
		criarArquivoSeNecessario(ARQ_PRODUTOS);
		criarArquivoSeNecessario(ARQ_LOJAS);
		criarArquivoSeNecessario(ARQ_VENDAS);

		criarArquivoIdSeNecessario("clientes");
		criarArquivoIdSeNecessario("produtos");
		criarArquivoIdSeNecessario("lojas");
		criarArquivoIdSeNecessario("vendas");
	}

	/**
	 * Reseta os dados do sistema, recriando diretórios e arquivos vazios.
	 *
	 * @throws IOException se ocorrer falha ao recriar a estrutura
	 */
	public static void resetarDados() throws IOException {
		File dir = new File(RAIZ);
		if (dir.exists()) {
			excluirRecursivo(dir);
		}
		inicializarDados();
	}

	// Cria um diretório caso ele não exista
	private static void criarDiretorioSeNecessario(String caminho) throws IOException {
		File dir = new File(caminho);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Não foi possível criar o diretório: " + caminho);
		}
	}

	// Cria um arquivo vazio caso ele não exista
	private static void criarArquivoSeNecessario(String caminho) throws IOException {
		File arquivo = new File(caminho);
		if (!arquivo.exists()) {
			File pai = arquivo.getParentFile();
			if (pai != null && !pai.exists() && !pai.mkdirs()) {
				throw new IOException("Não foi possível criar o diretório pai do arquivo: " + caminho);
			}
			if (!arquivo.createNewFile()) {
				throw new IOException("Não foi possível criar o arquivo: " + caminho);
			}
		}
	}

	// Cria o arquivo de ID com valor inicial 0, se necessário
	private static void criarArquivoIdSeNecessario(String nome) throws IOException {
		String caminho = RAIZ_IDS + "id" + nome + ".txt";
		File arquivo = new File(caminho);
		if (!arquivo.exists()) {
			criarArquivoSeNecessario(caminho);
			gravarId(nome, 0);
		}
	}

	/**
	 * Incrementa o ID de uma entidade e persiste o novo valor.
	 *
	 * @param nome nome da entidade (ex.: clientes, produtos)
	 * @throws IOException se ocorrer erro de leitura/gravação do arquivo de ID
	 */
	public static void incrementarId(String nome) throws IOException {
		int id = lerId(nome);
		id++;
		gravarId(nome, id);
	}

	/**
	 * Grava o valor do ID no arquivo correspondente.
	 *
	 * @param nome  nome da entidade
	 * @param valor valor do ID
	 * @throws IOException se ocorrer erro de escrita
	 */
	public static void gravarId(String nome, int valor) throws IOException {
		String caminho = RAIZ_IDS + "id" + nome + ".txt";
		criarDiretorioSeNecessario(RAIZ_IDS);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
			writer.write(String.valueOf(valor));
		}
	}

	/**
	 * Lê o valor atual do ID do arquivo correspondente.
	 *
	 * @param nome nome da entidade
	 * @return valor do ID lido
	 * @throws IOException se ocorrer erro de leitura ou se o conteúdo for inválido
	 */
	public static int lerId(String nome) throws IOException {
		String caminho = RAIZ_IDS + "id" + nome + ".txt";
		File arquivo = new File(caminho);
		if (!arquivo.exists()) {
			criarArquivoIdSeNecessario(nome);
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
			String linha = reader.readLine();
			if (linha == null || linha.trim().isEmpty()) {
				return 0;
			}
			return Integer.parseInt(linha.trim());
		} catch (NumberFormatException e) {
			throw new IOException("Arquivo de ID inválido: " + caminho, e);
		}
	}

	/**
	 * Exclui um arquivo ou diretório recursivamente.
	 *
	 * @param arquivo arquivo ou diretório a ser excluído
	 * @return true se a exclusão foi concluída com sucesso
	 */
	public static boolean excluirRecursivo(File arquivo) {
		if (arquivo == null || !arquivo.exists()) {
			return false;
		}
		if (arquivo.isDirectory()) {
			File[] filhos = arquivo.listFiles();
			if (filhos != null) {
				for (File filho : filhos) {
					excluirRecursivo(filho);
				}
			}
		}
		return arquivo.delete();
	}
}
