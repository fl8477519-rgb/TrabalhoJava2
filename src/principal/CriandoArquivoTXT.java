package principal;

import java.io.File;
import java.io.IOException;

public class CriandoArquivoTXT {
    public static void main(String[] args) {
        File arquivo = new File("Arquivo.txt");
        try {
            arquivo.createNewFile();
            System.out.println("Arquivo criado com sucesso ");

        }catch (IOException e ) {
            e.printStackTrace();

        }
    }
}
