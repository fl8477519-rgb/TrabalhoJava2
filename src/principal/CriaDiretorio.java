package principal;

import java.io.File;

public class CriaDiretorio {
    public static void main(String[] args) {
        String raiz = "c://programacao/loja/";
        File dir =new File(raiz);
        if (!dir.exists()){
            dir.mkdir();
        }
        dir = new File(raiz+"/site");
        if (!dir.exists()) {
            dir.mkdir();
        }
        dir = new File(raiz+"/Imagens");
        if (!dir.exists()) {
            dir.mkdir();
        }
        dir = new File(raiz+"/Videos");
        if (!dir.exists()) {
            dir.mkdir();
        }
        dir = new File(raiz+"/sons");
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("Final do processo de criação ");
    }
}
