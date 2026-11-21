package excecao;

/**
 * Exceção personalizada para representar erros de validação de dados
 * informados pelo usuário ou detectados nas regras do sistema.
 */
public class ValidacaoException extends Exception {

	/**
	 * Cria uma nova exceção de validação com a mensagem informada.
	 *
	 * @param mensagem descrição do erro de validação
	 */
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
