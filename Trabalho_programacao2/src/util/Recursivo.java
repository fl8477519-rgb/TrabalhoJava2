package util;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import modelo.Cliente;
import modelo.Loja;
import modelo.Produto;
import modelo.Venda;

/**
 * Classe utilitária com métodos recursivos utilizados no sistema.
 * Atende ao requisito de uso de recursividade.
 */
public class Recursivo {

	/**
	 * Busca recursiva de cliente por ID em uma lista.
	 *
	 * @param clientes    lista de clientes
	 * @param idProcurado ID a ser localizado
	 * @param indice      índice atual da busca
	 * @return cliente encontrado ou null se não existir
	 */
	public static Cliente buscarClienteRecursivo(List<Cliente> clientes, int idProcurado, int indice) {
		// Caso base: índice fora da lista significa que não encontrou
		if (clientes == null || indice >= clientes.size()) {
			return null;
		}
		// Verifica o elemento atual
		if (clientes.get(indice).getId() == idProcurado) {
			return clientes.get(indice);
		}
		// Chamada recursiva para o próximo índice
		return buscarClienteRecursivo(clientes, idProcurado, indice + 1);
	}

	/**
	 * Busca recursiva de produto por ID em uma lista.
	 */
	public static Produto buscarProdutoRecursivo(List<Produto> produtos, int idProcurado, int indice) {
		if (produtos == null || indice >= produtos.size()) {
			return null;
		}
		if (produtos.get(indice).getId() == idProcurado) {
			return produtos.get(indice);
		}
		return buscarProdutoRecursivo(produtos, idProcurado, indice + 1);
	}

	/**
	 * Busca recursiva de loja por ID em uma lista.
	 */
	public static Loja buscarLojaRecursivo(List<Loja> lojas, int idProcurado, int indice) {
		if (lojas == null || indice >= lojas.size()) {
			return null;
		}
		if (lojas.get(indice).getId() == idProcurado) {
			return lojas.get(indice);
		}
		return buscarLojaRecursivo(lojas, idProcurado, indice + 1);
	}

	/**
	 * Busca recursiva de venda por ID em uma lista.
	 */
	public static Venda buscarVendaRecursivo(List<Venda> vendas, int idProcurado, int indice) {
		if (vendas == null || indice >= vendas.size()) {
			return null;
		}
		if (vendas.get(indice).getId() == idProcurado) {
			return vendas.get(indice);
		}
		return buscarVendaRecursivo(vendas, idProcurado, indice + 1);
	}

	/**
	 * Soma recursiva do valor total das vendas.
	 * Considera o preço do produto associado a cada venda.
	 *
	 * @param vendas lista de vendas
	 * @param indice índice atual
	 * @return soma total dos preços dos produtos das vendas
	 */
	public static double somarTotalVendasRecursivo(List<Venda> vendas, int indice) {
		// Caso base: fim da lista
		if (vendas == null || indice >= vendas.size()) {
			return 0.0;
		}
		double valorAtual = 0.0;
		Venda venda = vendas.get(indice);
		if (venda != null && venda.getProduto() != null) {
			valorAtual = venda.getProduto().getPreco();
		}
		// Soma o valor atual com o resultado da soma do restante da lista
		return valorAtual + somarTotalVendasRecursivo(vendas, indice + 1);
	}

	/**
	 * Listagem recursiva de vendas (impressão no console).
	 *
	 * @param vendas lista de vendas
	 * @param indice índice atual
	 */
	public static void listarVendasRecursivo(List<Venda> vendas, int indice) {
		// Caso base: fim da lista
		if (vendas == null || indice >= vendas.size()) {
			return;
		}
		System.out.println(vendas.get(indice));
		// Chamada recursiva para imprimir o próximo elemento
		listarVendasRecursivo(vendas, indice + 1);
	}

	/**
	 * Leitura recursiva de opção inteira do menu.
	 * Repete a leitura em caso de entrada inválida (não numérica) ou fora do intervalo.
	 *
	 * @param sc      scanner para leitura
	 * @param minimo  valor mínimo aceito
	 * @param maximo  valor máximo aceito
	 * @param mensagem mensagem exibida ao solicitar a opção
	 * @return opção válida informada pelo usuário
	 */
	public static int lerOpcaoRecursivo(Scanner sc, int minimo, int maximo, String mensagem) {
		System.out.print(mensagem);
		try {
			int opcao = sc.nextInt();
			sc.nextLine(); // Consome a quebra de linha
			if (opcao < minimo || opcao > maximo) {
				System.out.println("Opção inválida! Informe um valor entre " + minimo + " e " + maximo + ".");
				// Chamada recursiva para repetir a leitura
				return lerOpcaoRecursivo(sc, minimo, maximo, mensagem);
			}
			return opcao;
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida! Digite apenas números inteiros.");
			sc.nextLine(); // Limpa o buffer do scanner
			// Chamada recursiva para repetir a leitura
			return lerOpcaoRecursivo(sc, minimo, maximo, mensagem);
		}
	}
}
