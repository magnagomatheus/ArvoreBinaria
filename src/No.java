public class No<T> {

    // Valor do nó
    private T valor;
    // Outro elemento ou filho (nó) à esquerda de um determinado nó (menor).
    private No<T> filhoLeft;
    // Outro elemento ou filho (nó) à direita de um determinado nó (maior).
    private No<T> filhoRight;

    private No<T> pai;


    public No(T v) {
        this.valor = v;
        this.filhoRight = null;
        this.filhoLeft = null;
        this.pai = null;
    }

    // Funcao que retorna o valor deste no.
    public T getValor() {
        return this.valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getFilhoLeft() {
        return this.filhoLeft;
    }

    public void setFilhoLeft(No<T> fleft) {
        this.filhoLeft = fleft;
    }

    public No<T> getFilhoRight() {
        return this.filhoRight;
    }

    public void setFilhoRight(No<T> fright) {
        this.filhoRight = fright;
    }

    public No<T> getPai() {return this.pai;}
    public void setPai(No<T> pai) {this.pai = pai;}
}
