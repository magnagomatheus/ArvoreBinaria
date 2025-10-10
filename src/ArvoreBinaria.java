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
        int cmp = comparador.compare(novoElemento.getValor(), currentNo.getValor());
        // Verifica se o valor novo elemento eh MAIOR do que o valor do elemento atual da arvore
        if (cmp > 0) {
            if(currentNo.getFilhoRight() == null) {
                currentNo.setFilhoRight(novoElemento);
                return true;
            }
            adicionar(novoElemento, currentNo.getFilhoRight());
        }
        // Verifica se o valor novo elemento eh MENOR do que o valor do elemento atual da arvore
        else if(cmp < 0) {
            if(currentNo.getFilhoLeft() == null) {
                currentNo.setFilhoLeft(novoElemento);
                return true;
            }
            adicionar(novoElemento, currentNo.getFilhoLeft());
        } else {
            currentNo.setFilhoRight(novoElemento);
            return true;
        }
        return false;
    }

    @Override
    public T pesquisar(T valor) {

        if(this.raiz == null) {
            System.out.println("Não há nenhum elemento na lista.");
            return null;
        }

        return pesquisar(valor, this.comparador, this.raiz);
    }

    @Override
    public T pesquisar(T valor, Comparator comparador, No<T> noAtual) {


        if(noAtual.getValor() == null) {
            System.out.println("Elemento " + valor + " não encontrado.");
            return null;
        }

        // Comparacao entre o elemento do no atual da arvore e o novo elemento
        int cmp = comparador.compare(valor, noAtual.getValor());

        // Se o novo elemento for maior que o elemento da Arvore
        if(cmp > 0) {
            // Entao va para o proximo No andando para a direita da arvore (filho direito daquele no)
            return pesquisar(valor, comparador, noAtual.getFilhoRight());
        } else if(cmp < 0) {
            return pesquisar(valor, comparador, noAtual.getFilhoLeft());
        } else {
            System.out.println("Elemento " + noAtual.getValor() +" encontrado!");
            return noAtual.getValor();
        }

    }

    @Override
    public T remover(T valor) {

        if (this.raiz == null) {
            System.out.println("Não há nenhum elemento na arvore.");
            return null;
        }

        return remover(valor, this.raiz);
    }

    private T remover(T valor, No<T> noAtual) {

        if(noAtual.getValor() == null) {
            System.out.println("Elemento não existe na arvore.");
            return null;
        }

        int cmp = comparador.compare(valor, noAtual.getValor());

        if(cmp > 0) {
            return remover(valor, noAtual.getFilhoRight());
        } else if (cmp < 0) {
            return remover(valor, noAtual.getFilhoLeft());
        } else {

            No<T> elemAnterior = noAtual.getPai();

            // Verifica se o No Atual eh filho esquerdo ou direito do No anterior
            char lado;
            cmp = comparador.compare(elemAnterior.getFilhoLeft().getValor(), noAtual.getValor());
            if(cmp == 0) {
                lado = 'l';
            } else {
                lado = 'r';
            }

            // Verifica se o No Atual não possui filhos.
            if(noAtual.getFilhoLeft() == null && noAtual.getFilhoRight() == null) {
                if(lado == 'l') {
                    elemAnterior.setFilhoLeft(null);
                } else {
                    elemAnterior.setFilhoRight(null);
                }
            } else {
                if(noAtual.getFilhoLeft() != null && noAtual.getFilhoRight() != null) {
                    if(lado == 'l') {
                        elemAnterior.setFilhoLeft(noAtual.getFilhoRight());
                        noAtual.getFilhoRight().setFilhoLeft(noAtual.getFilhoLeft());
                    } else {
                        elemAnterior.setFilhoRight(noAtual.getFilhoRight());
                        noAtual.getFilhoRight().setFilhoLeft(noAtual.getFilhoLeft());
                    }
                }
                if(noAtual.getFilhoLeft() == null) {
                    elemAnterior.setFilhoLeft(null);
                } else {
                    elemAnterior.setFilhoLeft(noAtual.getFilhoLeft());
                }
                if(noAtual.getFilhoRight() == null) {
                    elemAnterior.setFilhoRight(null);
                } else {
                    elemAnterior.setFilhoRight(noAtual.getFilhoRight());
                }


            }





            System.out.println("Elemento " + noAtual.getValor() + "removido com sucesso!");
            return noAtual.getValor();
        }

    }

    public T removerterts(T valor) {
        // buscando o nó que vai ser removido
        No<T> aRemover = pesquisar(valor);

        if(aRemover == null) {
            System.out.println("Não foi possível remover esse elemento!");
            return null;
        }
        // verifica se ele tem filhos a esquerda para substituir ele no pai. Se não houver o filho a direita fará isso
        if (aRemover.getFilhoLeft() == null) {
            if (this.comparador.compare(aRemover.getValor(),aRemover.getPai().getFilhoLeft().getValor() ) == 0) {
                aRemover.getPai().setFilhoLeft(aRemover.getFilhoRight());
                return aRemover.getValor();
            }
            aRemover.getPai().setFilhoRight(aRemover.getFilhoRight());
            return aRemover.getValor();
        }
        if (this.comparador.compare(aRemover.getValor(),aRemover.getPai().getFilhoLeft().getValor()) == 0){
            aRemover.getPai().setFilhoLeft(rremoverterts(aRemover.getFilhoLeft(),valor));
            return aRemover.getValor();
        }
        aRemover.getPai().setFilhoRight(rremoverterts(aRemover.getFilhoLeft(), valor));
        return aRemover.getValor();
    }

    private No<T> rremoverterts( No<T> noAtual, T valor){
        if (noAtual.getFilhoRight() == null) {
            if(noAtual.getPai().getValor()== valor) {
                return noAtual;
            }
            noAtual.getPai().setFilhoRight(noAtual.getFilhoLeft());
            return noAtual;
        }
        return rremoverterts(noAtual.getFilhoRight(), valor);

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
