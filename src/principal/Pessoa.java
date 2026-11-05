package principal;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;
    private String cordoCabelo;
   private String biotipo;
   private int idade;
    public Pessoa(String nome, String cordoCabelo, String biotipo, int idade) {
        this.nome = nome;
        this.cordoCabelo = cordoCabelo;
        this.biotipo = biotipo;
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCordoCabelo() {
        return cordoCabelo;
    }

    public void setCordoCabelo(String cordoCabelo) {
        this.cordoCabelo = cordoCabelo;
    }

    public String getBiotipo() {
        return biotipo;
    }

    public void setBiotipo(String biotipo) {
        this.biotipo = biotipo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cordoCabelo='" + cordoCabelo + '\'' +
                ", biotipo='" + biotipo + '\'' +
                ", idade=" + idade +
                '}';
    }
}

