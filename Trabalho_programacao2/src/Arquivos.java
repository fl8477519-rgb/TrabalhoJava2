import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arquivos {
    public  static String raiz = "./agenda/";
    public static String raizContatos = raiz + "contato/";
    public static String raizCompromisso = raiz + "compromisso/";
    public  static String raizIds = raiz + "ids";
    public static String IdContato = raizIds + "idcontatos.txt";
    public static String idCompromisso = raizIds + "idcompromisso.txt";
    public  static  void resetaInicializarAgenda() throws FileNotFoundException {
        File dir =new File(raiz);
        if (dir.isDirectory()){
            excluirDiretorio(raiz);
        }
        dir=new File(raiz);
        dir.mkdir();//criou a raiz
        dir =new File(raizContatos);
        dir.mkdir();
        dir =new File(raizIds);
        dir.mkdir();
        gravarId("contatos",0);
        gravarId("compromisso",0);

    }

    private static void gravarId(String nome , int i) throws FileNotFoundException {
        PrintWriter print = new PrintWriter(new File(raizIds+"id"+nome+".txt"));
        print.print(i);
    }
    private  static  int lerId(String nome ) throws FileNotFoundException {
        File  arquivo = new File(raizIds+"id"+nome+".txt");
        Scanner sc = new Scanner(arquivo);

                return sc.nextInt();
    }

    private static void excluirDiretorio(String nomeDir) {
        File dir =new File(nomeDir);
        String men=" ";
        if (dir.isDirectory()){
            if (dir.delete()){
                men=dir.getName();

            }

        }
    }
}


