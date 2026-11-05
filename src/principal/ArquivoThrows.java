package principal;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
public class ArquivoThrows {
    public static void main(String[] args) throws IOException {
        String frase = JOptionPane.showInputDialog("Digite uma frase:");
        try {
            FileWriter file =new FileWriter("c:/temp/frase.txt",true);
            PrintWriter out = new PrintWriter(file);
            out.print(frase);
            file.close();
            out.close();
            JOptionPane.showMessageDialog(null,"Frase armazenada no Arquivo");
        }catch (FileNotFoundException erro){
            JOptionPane.showMessageDialog(null,"Erro ,verifique  se a pasta existir");
        }

    }
}
