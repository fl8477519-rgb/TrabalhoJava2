package principal;

public class Carro {
   private String tipo;
    private String cor;
   protected String placa;
  private   int numporta;
  private Pessoa dono;
    public Carro(){

    }

  public  Carro(String tipo,String cor,String placa,int numporta){
        this.tipo=tipo;
        this.cor=cor;
        this.placa=placa;
        this.numporta=numporta;

    }
    public String getCor(){
        return  cor;
    }

    public  void setCor(String cor){
         this.cor=cor;

    }

    public String getPlaca(){
        return  placa;
    }
    public  String getTipo(){
        return  tipo;
    }


    public  void setTipo(String tipo){
        this.tipo=tipo;

    }
    public int getnumporta(){
        return  numporta;
    }


    public  void setNumporta(int numporta){
        this.numporta=numporta;

    }
   public Pessoa getDono(){
        return dono;
   }
   public  void setDono(Pessoa dono){
        this.dono=dono;
   }





}
