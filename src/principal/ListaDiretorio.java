package principal;

import javax.swing.*;
import java.io.File;

public class ListaDiretorio {
    public static void main(String[] args) {
        String path = JOptionPane.showInputDialog("Forneça o Caminho do Diretorio (utilize/entre os diretórios");
        File dir =new File(path);
        if (dir.isDirectory()){
            System.out.println("Conteudo do Diretório "+path);
            String s[]= dir.list();
            for (int i = 0;i<s.length;i++){
                System.out.println(s[i]);
            }
        }else {
            System.out.println(path+"Não é um diretório Válido");
        }
    }
}
