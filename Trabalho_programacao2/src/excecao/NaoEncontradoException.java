package excecao;

/**
 * Exceção personalizada para indicar que um registro solicitado
 * (cliente, produto, loja ou venda) não foi encontrado.
 */
public class NaoEncontradoException extends Exception {

	/**
	 * Cria uma nova exceção de registro não encontrado com a mensagem informada.
	 *
	 * @param mensagem descrição do registro não encontrado
	 */
	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
