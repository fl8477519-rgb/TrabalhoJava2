package util;

import pessoa.Cliente;
import produto.Produto;

import java.util.List;

public class Buscador {
    public  static Cliente buscarClienteRecursivo(List<Cliente>lista,int idProcurado,int indice){
        if (indice>=lista.size()){
            return  null;
        }
        return  buscarClienteRecursivo(lista,idProcurado,indice +1);
    }
    public static Produto buscarProdutoRecursivo(List<Produto>lista,int idProcurado,int indice){
        if (indice>=lista.size()){
            return  null;
        }
        if (lista.get(indice).getId()==idProcurado){
            return  lista.get(indice);
        }
        return  buscarProdutoRecursivo(lista,idProcurado,indice+1);
    }


}
