package principal;

public class Pessoa {
    private String nome;
    private String cordoCabelo;
   private String biotipo;
   private int idade;

   public  Pessoa(){

   }
    public Pessoa(String nome,String cordoCabelo, String biotipo,int idade){
        this.nome=nome;
        this.cordoCabelo=cordoCabelo;
        this.biotipo=biotipo;
        this.idade=idade;
    }
    public   String getNome(){
        return nome;
    }
    public void  setNome(){
        this.nome=nome;
    }
    public  String getCordoCabelo(){
        return cordoCabelo;
    }
    public void setBiotipo(){
        this.biotipo=biotipo;
    }
    public int getIdade(){
        return  idade;
    }
    public  void setIdade(){
        this.idade=idade;

    }
}
