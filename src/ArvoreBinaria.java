import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T>{

    protected No<T> raiz = null;
    protected Comparator<T> comparador;

    @Override
    public boolean adicionar(T novoValor, No<T> currentNo, char lado) {
        // Criacao de um novo nó com o valor do novo elemento
        No<T> novoElemento = new No<T>(novoValor);
        No<T> aux = null;
        // Verifica se a arvore esta vazia
        if (this.raiz == null) {
            // O novo elemento se torna a raiz da arvore.
            this.raiz = novoElemento;
            return true;
        }

        if(currentNo == null) {
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
        }

        int cmp = comparador.compare(currentNo.getValor(), novoElemento.getValor());
        if (cmp > 0) {
            adicionar(novoValor, currentNo.getFilhoLeft(), 'l');
        } else {
            adicionar(novoValor, currentNo.getFilhoRight(), 'r');
        }

        return false;

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
