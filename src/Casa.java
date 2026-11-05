public class Casa {
    private   int numero;
    private  int qtd_quarto;
    private int  qtdCozinha;
    private  String corDacasa;
    public  Casa(int numero,int qtd_quarto,String corDacasa){
        this.numero=numero;
        this.qtd_quarto=qtd_quarto;
        this.qtdCozinha=qtdCozinha ;
        this.corDacasa=corDacasa;
    }

    public int getNumero() {
        return numero;
    }
    public void  setNumero(int numero){
        this.numero=numero;
    }
    public  int getQtd_quarto(){
        return  qtd_quarto;
    }
    public  void  setQtd_quarto(){
        this.qtd_quarto=qtd_quarto;

    }
    public int getQtdCozinha(){
        return  qtdCozinha;
    }
    public  void  setQtdCozinha(){
        this.qtdCozinha=qtdCozinha;
    }
    public  String getCorDacasa(){
        return  corDacasa;
    }
    public  void setCorDacasa(){
        this.corDacasa=corDacasa;
    }
}


