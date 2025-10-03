import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T>{

    protected No<T> raiz = null;
    protected Comparator<T> comparador;

    @Override
    public void adicionar(T novoValor) {
        // Criacao de um novo nó com o valor do novo elemento
        No<T> novoElemento = new No<T>(novoValor);

        // Verifica se a arvore esta vazia
        if (this.raiz == null) {
            // O novo elemento se torna a raiz da arvore.
            this.raiz = novoElemento;
        }

        // Arvore nao esta vazia
        else {
            // Criacao de um no auxiliar para percorrer a arvore.
            No<T> aux = null;

            // Compara se o valor do novo elemento eh maior ou menor do que o valor do elemento raiz da arvore
            int rComparacao = this.comparador.compare(this.raiz.getValor(), novoValor);
            // Se o resultado da comparacao for maior q zero, entao o valor
            // do elemento da raiz eh maior do que o valor do novo elemento
            if(rComparacao > 0) {
                aux = this.raiz.getFilhoLeft();
                // Verifica se o elemento da raiz tem um filho à esquerda,
                // caso não tenha, o novo elemento passa a ser.
                if (aux == null) {
                    // novo elemento eh setado como filho da esq da raiz da arvore.
                    this.raiz.setFilhoLeft(novoElemento);
                    System.out.println("Novo elemento adicionado!");
                }
                // Caso o elemento da raiz tenha um filho a esquerda,
                // percorra cada no abaixo, comparando-os com o valor do novo elemento.
                else {
                    // Loop para percorrer todos nos da arvore
                    while(aux != null) {
                        // Comparacao entre o no atual da arvore e o valor do novo elemento
                        rComparacao = this.comparador.compare(aux.getValor(), novoValor);
                        // Se for maior que 0, entao o no atual da arvore eh maior
                        // Logo, eh feita uma verificacao se este no possui filho a esquerda
                        if(rComparacao > 0) {
                            if (aux.getFilhoLeft() == null) {
                                aux.setFilhoLeft(novoElemento);
                                System.out.println("Novo elemento adicionado!");
                            }
                        }
                        // Se o novo elemento for maior que o elemento do no atual
                        // Verifique se o no atual possui filho a direita
                        else {
                            if(aux.getFilhoRight() == null) {
                                aux.setFilhoRight(novoElemento);
                                System.out.println("Novo elemento adicionado!");
                            }
                        }
                        aux = aux.getFilhoLeft();
                    }
                }
            }
            // Caso o elemento da raiz seja menor que o valor do novo elemento
            else {

            }
        }

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
