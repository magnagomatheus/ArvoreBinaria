import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T>{

    protected No<T> raiz = null;
    protected Comparator<T> comparador;

    @Override
    public boolean adicionar(T novoValor) {

        No<T> novoElemento = new No<T>(novoValor);
        // Verifica se a arvore esta vazia
        if (this.raiz == null) {
            // O novo elemento se torna a raiz da arvore.
            this.raiz = novoElemento;
            return true;
        }

        return adicionar(novoElemento, this.raiz);
    }

    @Override
    public boolean adicionar(No<T> novoElemento, No<T> currentNo) {
        // Criacao de um novo nó com o valor do novo elemento
        //No<T> novoElemento = new No<T>(novoValor);
        int cmp = comparador.compare(currentNo.getValor(), novoElemento.getValor());
        if (cmp > 0) {
            if(currentNo.getFilhoLeft() == null) {
                currentNo.setFilhoLeft(novoElemento);
                return true;
            }
            adicionar(novoElemento, currentNo.getFilhoLeft());
        } else {
            if(currentNo.getFilhoRight() == null) {
                currentNo.setFilhoRight(novoElemento);
                return true;
            }
            adicionar(novoElemento, currentNo.getFilhoRight());
        }

        return false;

        //No<T> aux = null;

        /*if(currentNo == null) {
            aux = currentNo.getPai();
            if(lado == 'l'){
                aux.setFilhoLeft(novoElemento);
                novoElemento.setPai(aux);
            }
            if(lado == 'r') {
                aux.setFilhoRight(novoElemento);
                novoElemento.setPai(aux);
            }
            return true;
        }*/

    }

    @Override
    public T pesquisar(T valor) {
        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        return null;
    }

    @Override
    public T remover(T valor) {
        return null;
    }

    @Override
    public int altura() {
        return 0;
    }

    @Override
    public int quantidadeNos() {
        return 0;
    }

    @Override
    public String caminharEmNivel() {
        return "";
    }

    @Override
    public String caminharEmOrdem() {
        return "";
    }
}
