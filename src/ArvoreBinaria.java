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


        if(noAtual == null) {
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

        // Chame a funcao recursiva para remover o elemento.
        return remover(valor, this.raiz);
    }


    private T remover(T valor, No<T> noAtual) {
        // Funcao recursiva, cuja logica seguida eh o Sucessor In-Order
        // Ao inves de atribuir os nos novamente e pontar filho por filho,
        // Apenas substituimos o No a ser removido pelo Sucessor In-Order, que
        // Se o Elemento a ser removido estiver a esquerda, o Sucessor In-Order sera o elemento mais a direita do ramo.
        // Se o Elemento a ser removido estiver a direita ou for a raiz, o Sucessor In-Order sera o elemento mais a esquerda do ramo.


        // Verifica se a lista está vazia ou se chegou ao final da arvore e o elemento nao foi encontrado.
        if (noAtual == null) {
            System.out.println("Não há nenhum elemento na arvore.");
            return null;
        }



        // Primeiro faremos a busca pelo elemento a ser removido.
        // Comparacao entre o valor do novo elemento e o valor do elemento atual da arvore.
        int cmp = comparador.compare(valor, noAtual.getValor());

        // Se o novo elemento for MAIOR que o elemento atual da arvore, entao va para o proximo filho a direita
        if(cmp > 0) {
            return remover(valor, noAtual.getFilhoRight());
        }
        // Se o novo elemento for MENOR que o elemento atual da arvore, entao va para o proximo filho a esquerda
        else if(cmp < 0) {
            return remover(valor, noAtual.getFilhoLeft());
        }
        // Se forem iguais, encontrou o elemento a ser removido.
        else {
            // Criacao da copia do Sucessor In-Order, que substituira o lugar do elemento a ser removido.
            No<T> sucessorInOrder = null;
            // Criacao do No que contera o No pai (elemento anterior) do elemento a ser removido.
            No<T> elemAnterior = noAtual.getPai();
            // Criacao da variavel lado para identificar se o elemento a ser removido eh filho direito ou esquerdo do elemento anterior (pai).
            char lado;
            // Compara se o filho esquerdo do elemento anterior eh igual ao elemento a ser removido,
            // Se for, entao ele eh filho esquerdo,
            // Se nao, ele eh filho direito.
            cmp = comparador.compare(elemAnterior.getFilhoLeft().getValor(), noAtual.getValor());
            if(cmp == 0) {
                lado = 'l';
            } else {
                lado = 'r';
            }

            // VERIFICACAO DE QUAL LADO DA ARVORE ESTAMOS.
            // Variavel que armazena em qual lado da arvore (ramificacao) estamos.
            char ladoArvore;
            // Comparacao entre o elemento a ser removido e o elemento raiz da arvore.
            int cmpLado = comparador.compare(noAtual.getValor(), this.raiz.getValor());
            // Se o elemento a ser removido for MAIOR que o elemento raiz da arvore, estamos no lado direito da ramificacao.
            // Se o elemento a ser removido for MENOR que o elemento raiz da arvore, estamos no lado esquerdo da ramificacao.
            if(cmpLado > 0) {
                ladoArvore = 'r';
            }
            else {
                ladoArvore = 'l';
            }


            // Verifica se o elemento a ser removido eh um candidato a Sucessor In-Order (Se nao tem filhos)
            if(noAtual.getFilhoLeft() == null || noAtual.getFilhoRight() == null) {
                // Se nao tiver, apenas remova o elemento a ser removido.
                if(lado == 'l') {
                    elemAnterior.setFilhoLeft(null);
                } else {
                    elemAnterior.setFilhoRight(null);
                }

                System.out.println("Elemento removido com sucesso!");
                return noAtual.getValor();
            }
            // Atribuicao do No SucessorInOrder que sera colocado no lugar do No a ser removido
            sucessorInOrder = encontraSucessorInOrder(noAtual, ladoArvore);


            // Se o elemento a ser removido for filho a esquerda do elemento anterior a ele
            // O novo filho a esquerda do elemento anterior passa a ser o sucessorInOrder
            if(lado == 'l') {
                // Remova o elemento a ser removido e coloque o sucessorInOrder no lugar.
                elemAnterior.setFilhoLeft(sucessorInOrder);
            }
            // O novo filho a direita do elemento anterior passa a ser o sucessorInOrder
            else {
                // Remova o elemento a ser removido e coloque o sucessorInOrder no lugar.
                elemAnterior.setFilhoRight(sucessorInOrder);
            }

            // Criacao dos nos que contem os filhos do elemento a ser removido.
            No<T> filhoEsquerdo = noAtual.getFilhoLeft();
            No<T> filhoDireito = noAtual.getFilhoRight();
            // Se o filho esquerdo for diferente de null, atribua o Sucessor In-Order como o novo pai.
            if(filhoEsquerdo.getValor() != null) {
                filhoEsquerdo.setPai(sucessorInOrder);
            }
            // Se o filho direito for diferente de null, atribua o Sucessor In-Order como o novo pai.
            if(filhoDireito.getValor() != null) {
                filhoDireito.setPai(sucessorInOrder);
            }

            System.out.println("Elemento removido com sucesso!");
            return noAtual.getValor();

        }
    }

    private No<T> encontraSucessorInOrder(No<T> elementoAtual, char ladoArvore) {

        // Se estivermos no lado Esquerdo da arvore, o elemento Sucessor In-Order sera o filho mais a direita deste lado.
        // Portanto, percorra esta ramificacao ate encontrar o filho mais a direita deste lado (next Filho Right == null)
        if(ladoArvore == 'l') {
            // Se elementoAtual.getFilhoRight() == null, encontrou o Sucessor In-Order
            if(elementoAtual.getFilhoRight() == null) {
                // Remova este No do lugar original onde estava para coloca-lo no lugar do elemento a ser removido
                elementoAtual.getPai().setFilhoRight(null);
                // Retorne o elemento
                return elementoAtual;
            }
            // Caso nao tenha encontrado, percorra recursivamente.
            return encontraSucessorInOrder(elementoAtual.getFilhoRight(), ladoArvore);
        }
        // Se estivermos no lado Direito da arvore, o elemento Sucessor In-Order sera o filho mais a esquerda deste lado.
        // Portanto, percorra esta ramificacao ate encontrar o filho mais a esquerdad deste lado (next Filho Left == null)
        else {
            // Se elementoAtual.getFilhoLeft() == null, encontrou o Sucessor In-Order
            if(elementoAtual.getFilhoLeft() == null) {
                // Remova este No do lugar original onde estava para coloca-lo no lugar do elemento a ser removido
                elementoAtual.getPai().setFilhoLeft(null);

                // Retorne o elemento
                return elementoAtual;
            }
            // Caso nao tenha encontrado, percorra recursivamente.
            return encontraSucessorInOrder(elementoAtual.getFilhoLeft(), ladoArvore);
        }

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
