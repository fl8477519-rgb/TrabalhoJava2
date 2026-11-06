import pessoa.Cliente;
import produto.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static  List<Cliente> clientes =new ArrayList<>();
    private  static  List <Produto> produtos= new ArrayList<>();
    private  static int clienteIdContador=1;
    private  static  int produtoIdContador=1;

    public static void main(String[] args) {
        Scanner leia =new Scanner(System.in);
        int opcao=1;
        do {
            try {
            System.out.println("--------Sistema de Roupas---------");
            System.out.println("1-Cadastar cliente (create");
            System.out.println("2-Listar pessoa.Cliente (read)");
            System.out.println("3-Atualizar cliente(update)");
            System.out.println("4-Deletar pessoa.Cliente (Delete)");

            System.out.println("5-Cadastrar  produto.Produto(create)");
            System.out.println("6-Listar Produtos (read)");
            System.out.println("7-Atualizar produto.Produto (update)");
            System.out.println("8-Deletar produto.Produto(Delete)");
            System.out.println("0 para sair");
            System.out.println("Escolha  uma opção :");
            opcao=leia.nextInt();
        }catch (Exception erro){
                System.out.println("Erro digite uma das opções acima");
            }


            switch (opcao){
                case  1 :
                    System.out.println("Nome do cliente:");
                    String nome=leia.nextLine();
                    System.out.println("Cpf:");
                    String cpf=leia.nextLine();
                    System.out.println("Telefone:");
                    String telefone =leia.nextLine();
                    clientes.add(new Cliente(clienteIdContador++, nome, cpf, telefone));
                    System.out.println("pessoa.Cliente cadastrado com Sucesso");
                    break;
                case 2 :
                    System.out.println("--------LIsta de Clientes ----------");
                    if (clientes.isEmpty())
                        System.out.println("Nenhum cliente cadastrado");
                    for (Cliente c: clientes){
                        System.out.println(c);

                    }
                    break;
                case 3 :
                    System.out.println("Id do cliente que precisar Cadastrar ");
                    int idUcp=leia.nextInt();
                    for (Cliente c :clientes) {
                        if (c.getId() == idUcp)
                            System.out.println("Novo Nome : ");
                        c.setNome(leia.nextLine());
                        System.out.println("Novo telefone :");
                        c.setTelefone(leia.nextLine());
                        System.out.println("Cadastrado com sucesso");


                    }



            }


        }while (opcao!=0);

    }
}
