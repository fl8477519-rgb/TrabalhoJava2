import pessoa.Cliente;
import produto.Produto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static int clienteIdContador = 1;
    private static int produtoIdContador = 1;

    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n----- Loja de Roupas IF-Goiano -----");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = leia.nextInt();
                leia.nextLine(); // Limpa o buffer do teclado (evita o erro de pular linhas)

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Cadastro de Cliente ---");
                        System.out.print("Nome do cliente: ");
                        String nome = leia.nextLine();
                        System.out.print("CPF: ");
                        String cpf = leia.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = leia.nextLine();

                        clientes.add(new Cliente(clienteIdContador++, nome, cpf, telefone));
                        System.out.println("Cliente cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n-------- Lista de Clientes -------- ");
                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado.");
                        } else {
                            for (Cliente c : clientes) {
                                System.out.println(c);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Atualizar Cliente ---");
                        System.out.print("Digite o ID do cliente que deseja atualizar: ");
                        int idUcp = leia.nextInt();
                        leia.nextLine(); // Limpa o buffer

                        boolean encontrado = false;
                        for (Cliente c : clientes) {
                            if (c.getId() == idUcp) {
                                System.out.print("Novo Nome: ");
                                c.setNome(leia.nextLine());
                                System.out.print("Novo Telefone: ");
                                c.setTelefone(leia.nextLine());
                                System.out.println("Cliente atualizado com sucesso!");
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("Cliente com ID " + idUcp + " não encontrado.");
                        }
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha uma opção válida do menu.");
                }

            } catch (InputMismatchException erro) {
                System.out.println("Erro: Digite apenas números inteiros para o menu.");
                leia.nextLine(); // Limpa a entrada errada para não travar em loop infinito
                opcao = -1;
            }

        } while (opcao != 0);

        leia.close();
    }
}