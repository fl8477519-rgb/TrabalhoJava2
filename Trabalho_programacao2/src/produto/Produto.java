package produto;

public class Produto {
    private  int id ;
    private String nome;
    private String categoria;//camisa ,calça
    private String tamanho;
    private  double  preco;
    private  int quantidadeEstoque;
    public Produto(int id ,String nome ,String categoria,String tamanho,double preco,int quantidadeEstoquee){
        this.id=id;
        this.nome=nome;
        this.categoria=categoria;
        this.tamanho=tamanho;
        this.preco=preco;
    }

    public static void add(Produto produto) {
    }


    public  int getId(){
        return id;
    }
    public  void setId(int id){
        this.id=id;
    }
    public  String getNome(){
        return  nome;
    }
    public  void setNome (String nome ){
        this.nome =nome;
    }
    public String getCategoria(){
        return categoria;
    }
    public void  setCategoria(String categoria){
        this.categoria=categoria;
    }
    public  String getTamanho(){
        return  tamanho;
    }
    public  void  setTamanho(String tamanho){
        this.tamanho =tamanho;
    }
    public  double getPreco(){
        return  preco;
    }
    public void setPreco(double preco){
        this.preco=preco;
    }
    public  int getQuantidadeEstoquee(){
        return  quantidadeEstoque;
    }
    public  void setQuantidadeEstoquee(int quantidadeEstoquee){
        this.quantidadeEstoque=quantidadeEstoquee;
    }
    @Override
    public String toString(){
        return "Id:"+id+"Nome:"+nome+"Categoria:"+categoria+"Tamanho"+tamanho+"Preço:"+preco+"Quantidade de Estoque:"+quantidadeEstoque;
    }
}
