import pessoa.Cliente;
import produto.Produto;
import venda.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private   static List<Cliente> clientes= new ArrayList<>();
    private static List<Produto>produtos=new ArrayList<>();
    private  static  List<Venda>vendas= new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcaoPrincipal=1;

        do {
            System.out.println("\n=================================");
            System.out.println("       SISTEMA DE GERENCIAMENTO   ");
            System.out.println("=================================");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Gerenciar Vendas");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            try {

            opcaoPrincipal = sc.nextInt();
            sc.nextLine();

            switch (opcaoPrincipal){
                case 1 :
                    menuClientes();
                    break;
                case 2 :
                    menuProdutos();
                    break;
                case  3 :
                    menuVendas();
                    break;
                case 4:
                    System.out.println("Saindo.......");
                    break;
                default:
                    System.out.println("Opção Invalida ...");

            }


            } catch (Exception e) {
                System.out.println("Erro Entrada invalida ! Digite apenas números inteiros ");
                sc.nextLine();
            }



        }while (opcaoPrincipal!=4);

    }

    private static void menuVendas() {
        int opcao = 1;
        do {
            System.out.println("------------SUBMENU VENDAS----------");
            System.out.println("1-Registrar Vendas");
            System.out.println("2-Listar Vendas");
            System.out.println("3-Voltar ao Menu Principal");
            System.out.println("Escolha uma opção :");

            try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        if (clientes.isEmpty() || produtos.isEmpty()) {
                            System.out.println("[Aviso] você precisa cadastrar pelo menos um cliente e um Produto antes de vender!");
                            break;
                        }

                        System.out.println("Id Venda :");
                        int idVenda = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Id do comprador :");
                        int idCliete = sc.nextInt();
                        sc.nextLine();

                        Cliente clienteEncontrado = null;
                        for (Cliente c : clientes) {
                            if (c.getId() == idCliete) {
                                clienteEncontrado= c;
                                break;
                            }
                        }
                        if (clienteEncontrado == null) {
                            System.out.println("[Erro] Cliente não encontrado  com esse ID");
                            break;
                        }
                        System.out.println("Id do Produto :");
                        int idProduto = sc.nextInt();
                        sc.nextLine();
                        Produto produtoEncontrado =null;
                        for (Produto p :produtos){
                            if (p.getId()==idProduto){
                                produtoEncontrado =p;
                                break;
                            }
                        }
                        if (produtoEncontrado ==null){
                            System.out.println("[Erro] Produto não Encontrado");
                            break;
                        }
                        vendas.add(new Venda(idVenda,clienteEncontrado,produtoEncontrado));
                        System.out.println();
                        break;
                    case 2 :
                        System.out.println("---------Lista de Vendas--------");
                        if (vendas.isEmpty()){
                            System.out.println("Nenhuma Venda Registrada");

                        }else {
                            for (Venda v :vendas){
                                System.out.println(v);
                            }
                        }
                        break;
                    case  3 :
                        System.out.println("Voltando ao Menu Principal ");
                        break;
                    default:
                        System.out.println("[Aviso] Opção Invalida");




                }


                }catch (Exception erro) {
                System.out.println("Erro: Entrada inválida! Digite apenas números inteiros.");
            }

            } while (opcao != 3) ;


        }

    private static void menuProdutos() {
        int opcao1=1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("=-------SUBMENU Produto-------");
            System.out.println("1-Cadastar Produtos  ");
            System.out.println("2 -Listar Produtos");
            System.out.println("3 -Excluir Produtos ");
            System.out.println("4- Voltar ao menu Principal");
            System.out.println("5-saindo.......");
            try {
                opcao1 =sc.nextInt();

                switch (opcao1){
                    case  1 :
                        System.out.println("Id:");
                        int id = sc.nextInt();
                        System.out.print("Nome :");
                        String nome = sc.next();
                        System.out.print("Categoria :");
                        String categoria =sc.next();
                        System.out.print("Tamanho :");
                        String tamanho=sc.next();
                        System.out.println("Preço:");
                        double preco = sc.nextDouble();
                        System.out.print("Quantidade :");
                        int quantidade = sc.nextInt();
                        produtos.add(new Produto(id,nome,categoria,tamanho,preco,quantidade ));
                        System.out.println("[Sucesso]Produto Cadastrado com sucesso");
                        break;
                    case 2 :
                        System.out.println("---------Lista de Produtos------------");
                        if (produtos.isEmpty()){
                            System.out.println("Nenhum Produto Cadastrado ");
                        }else {
                            for (Produto p :produtos){
                                System.out.println(p);
                            }
                        }
                        break;
                    case 3 :
                        System.out.println("[Atualizar]Funcionalidade Pronta para Receber a buscar Recursiva ");
                        break;
                    case 4 :
                  System.out.println("[Excluir] Funcionalidade Pronta  para receber a exclusão");
                  break;
               case 5 :
                  System.out.println("Voltando ao Menu Principal");
                        break;
                    default:
                        System.out.println("[Aviso] Opção invalida");

                }



        }catch (Exception erro){
                System.out.println("Erro Entrada invalida ! Digite apenas números inteiros ");
            }
        }while (opcao1!=5);


    }

    private static void menuClientes() {
        Scanner sc = new Scanner(System.in);
        int opcao =1;
        do {
            System.out.println("=-------SUBMENU CLIENTES-------");
            System.out.println("1-Cadastar Clientes ");
            System.out.println("2 -Listar clientes ");
            System.out.println("3-Atualizar Clientes");
            System.out.println("4 -Excluir Clientes ");
            System.out.println("5- Voltar ao menu Principal");
            System.out.println("Escolha uma opção:");
            try {

            opcao= sc.nextInt();
            sc.nextLine();
            switch (opcao){
                case 1 :
                    System.out.print("Id :");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome :");
                    String nome = sc.nextLine();
                    System.out.print("Cpf :");
                    String cpf =sc.nextLine();
                    System.out.print("Telefone :");
                    String telefone =sc.nextLine();

                    clientes.add(new Cliente(id,nome,cpf,telefone));
                    System.out.println("[Sucesso]Cliente Cadastrado com Sucesso");
                    break;
                case 2 :
                    System.out.println("-----Lista De Clientes------");
                    if (clientes.isEmpty()){
                        System.out.println("Nenhum Cliente Cadastrado");
                    }else {
                        for (Cliente c:clientes){
                            System.out.println(c);

                        }
                    }
                    break;
                case 3 :
                    System.out.println("[Atualizar]Funcionalidade Pronta para Receber a buscar Recursiva ");
                    break;

             case 4 :
                   System.out.println("[Excluir] Funcionalidade Pronta  para receber a exclusão");
                   break;
                case 5 :
                   System.out.println("Voltando ao Menu Principal");
                    break;
                default:
                    System.out.println("[Aviso] Opção invalida");


            }
        }catch (Exception erro){

                System.out.println("Erro Entrada invalida ! Digite apenas números inteiros ");
            }
    }while (opcao!=5);
    }
}
