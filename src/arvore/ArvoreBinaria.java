package arvore;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected No<T> raiz;
    protected Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comparador){
        this.raiz =null;
        this.comparador = comparador;
    }

    // Função Adicionar --> Adiciona um novo elemento de tipo T, ou seja, aceita qualquer tipo de valor na árvore binária.
    @Override
    public T adicionar(T novoValor) {
        // Criação do Nó (estrutura) que conterá o novo valor a ser adicionado na árvore.
        No<T> novoElemento = new No<T>(novoValor);
        // Verifica se a arvore esta vazia
        if (this.raiz == null) {
            // O novo elemento se torna a raiz da arvore.
            this.raiz = novoElemento;
            return this.raiz.getValor();
        }
        // Caso não esteja, chame a função adicionar recursiva, que percorre a árvore até encontrar o lugar ideal para o novo valor.
        //if(adicionar(novoElemento, this.raiz)) {
        //    System.out.println("Elemento adicionado com sucesso!");
        //} else {
        //    System.out.println("Não foi possível adicionar o elemento.");
        //}
        adicionar(novoElemento, this.raiz);
        return this.raiz.getValor();
    }



    /**
     * Método adicionar PRIVATE que faz de maneira recursiva a busca do local em que o novo elemento sera inserido.
     * @param novoElemento - arvore.No do elemento do Tipo T a ser armazenado na árvore.
     * @param currentNo - arvore.No do atual elemento da arvore.
     *
     */

    // Função Adicionar (Recursiva) --> Percorre toda a árvore comparando cada nó com o novo valor, com o objetivo de encontrar o lugar ideal para adição do novo elemento.
    private boolean adicionar(No<T> novoElemento, No<T> currentNo) {
        // Comparação entre o valor do novo elemento e o valor do elemento atual da lista.
        int cmp = comparador.compare(novoElemento.getValor(), currentNo.getValor());
        // Verifica se o valor novo elemento eh MAIOR do que o valor do elemento atual da arvore
        if (cmp > 0) {
            // Se for maior, verifica se o elemento atual possui um filho a direita, ou seja, se o elemento atual a arvore tem algum nó com valor maior do que ele próprio.
            if(currentNo.getFilhoRight() == null) {
                // Caso não tenha um próximo nó, o novo elemento se torna este próximo, se torna filho do nó atual da àrvore.
                currentNo.setFilhoRight(novoElemento);
                // return true (deu certo)
                novoElemento.setPai(currentNo);
                System.out.println("Elemento adicionado com sucesso!" + novoElemento.getValor());
                return true;
            }
            // Caso tenha um próximo elemento de valor maior, passe para o próximo nó chamando a própria função recursivamente, atualizando apenas o currentNo.
            adicionar(novoElemento, currentNo.getFilhoRight());
        }
        // Verifica se o valor novo elemento eh MENOR do que o valor do elemento atual da arvore
        else if(cmp < 0) {
            // Se for menor, verifica se o nó atual possui um filho a esquerda, ou seja, um elemento menor do que ele próprio
            if(currentNo.getFilhoLeft() == null) {
                // Se não tiver um elemento menor que o nó atual na lista, o novo elemento se torna filho a esquerda do nó atual.
                currentNo.setFilhoLeft(novoElemento);
                // return true (elemento adicionado)
                novoElemento.setPai(currentNo);
                System.out.println("Elemento adicionado com sucesso!" + novoElemento.getValor());
                return true;
            }
            // Caso tenha um próximo elemento menor que o nó atual (filho a esquerda), chame o método adicionar recursivamente atualizando o parametro currentNo.
            adicionar(novoElemento, currentNo.getFilhoLeft());
        }
        // Caso o novo elemento e o Nó atual forem iguais
        else {
            // O novo elemento se torna filho a direito do nó atual da árvore
            //currentNo.setFilhoRight(novoElemento);
            // retorna false (nao foi possível adicionar)
            System.out.println("Não foi possível adicionar o elemento: " + novoElemento.getValor());
            return false;
        }
        return false;
    }




    // Método pesquisar --> Recebe como parâmetro um valor do tipo T, ou seja, genérico e pode ser qualquer valor. Após isso, ele busca este elemento na árvore comparando nó a nó.
    @Override
    public T pesquisar(T valor) {
        // Verifica se a lista esta vazia
        if(this.raiz == null) {
            System.out.println("Não há nenhum elemento na lista.");
            return null;
        }
        // Caso não esteja, chame a função pesquisar que efetuará a busca recursivamente.
        return pesquisar(valor, this.raiz);
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        // Verifica se a lista esta vazia
        if(this.raiz == null) {
            System.out.println("Não há nenhum elemento na lista.");
            return null;
        }
        // Caso não esteja, chame a função pesquisar que efetuará a busca recursivamente.
        return pesquisarWithComparator(valor, comparador,this.raiz);
    }


    // Função pesquisar private --> Pesquisa o elemento recursivamente com o comparador da própria instancia da classe.
    private T pesquisar(T valor, No<T> noAtual) {

        // Verifica se a lista está vazia
        if(noAtual == null) {
            System.out.println("Elemento " + valor + " não encontrado.");
            return null;
        }

        // Comparacao entre o elemento do no atual da arvore e o novo elemento
        int cmp = this.comparador.compare(valor, noAtual.getValor());

        // Se o novo elemento for maior que o elemento da Arvore
        if(cmp > 0) {
            // Entao va para o proximo arvore.No andando para a direita da arvore (filho direito daquele no)
            return pesquisar(valor, noAtual.getFilhoRight());
        }
        // Se o novo elemento for MENOR que o elemento da Àrvore
        else if(cmp < 0) {
            // Chame a própria função novamente atualizando o valor do elemento atual (passa para o próximo nó a esquerda)
            return pesquisar(valor, noAtual.getFilhoLeft());
        }
        // Se forem iguais, retorne o próprio elemento pois ele foi encontrado.
        else {
            System.out.println("Elemento " + noAtual.getValor() +" encontrado!");
            return noAtual.getValor();
        }
    }


    // Função pesquisar com comparador como argumento --> Pesquisa o elemento recursivamente com o comparador da escolha do usuário, e não necessáriamente da instância da classe.
    private T pesquisarWithComparator(T valor, Comparator comparador, No<T> noAtual) {
        // Verifica se chegou ao fim da lista.
        if(noAtual == null) {
            System.out.println("Elemento " + valor + " não encontrado.");
            return null;
        }

        // Comparacao entre o elemento do no atual da arvore e o novo elemento
        int cmp = comparador.compare(valor, noAtual.getValor());

        // Se o novo elemento for maior que o elemento da Arvore
        if(cmp > 0) {
            // Entao va para o proximo arvore.No andando para a direita da arvore (filho direito daquele no)
            return pesquisar(valor, noAtual.getFilhoRight());
        }
        // Se o novo elemento for MENOR que o elemento da Àrvore
        else if(cmp < 0) {
            // Chame a própria função novamente atualizando o valor do elemento atual (passa para o próximo nó a esquerda)
            return pesquisar(valor, noAtual.getFilhoLeft());
        }
        // Se forem iguais, retorne o próprio elemento pois ele foi encontrado.
        else {
            System.out.println("Elemento " + noAtual.getValor() +" encontrado!");
            return noAtual.getValor();
        }

    }


    // Método público remover --> Chama a função recursiva que efetuará a busca recursivamente, remoção e modificações de maneira recursiva.
    @Override
    public T remover(T valor) {
        // Chame a funcao recursiva para remover o elemento.
        return remover(valor, this.raiz);
    }


    // Método remover private --> De maneira recursiva, busca o elemento a ser removido,
    //  busca o sucessor In-Order (elemento mais a direita do ramo esquerdo ou o elemento mais a esquerda do ramo direito da árvore)
    //  e efetua a remoção do elemento a ser removido e a adição do sucessor In-Order no exato lugar do no a ser removido.


    private T remover(T valor, No<T> noAtual) {
        // Funcao recursiva, cuja logica seguida eh o Sucessor In-Order
        // Ao inves de atribuir os nos novamente e pontar filho por filho,
        // Apenas substituimos o arvore.No a ser removido pelo Sucessor In-Order, que
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

            No<T> filhoEsquerdo = noAtual.getFilhoLeft();
            No<T> filhoDireito = noAtual.getFilhoRight();
            No<T> elemAnterior = noAtual.getPai();

            // Verifica se o no a ser removido nao tem nenhum filho
            if(filhoEsquerdo == null && filhoDireito == null) {
                // Verifica se o no a ser removido eh a raiz da arvore
                if(elemAnterior == null) {
                    this.raiz = null;
                    return noAtual.getValor();
                } else {
                    // Se nao for raiz da arvore, verifica
                    // Se ele for o filho a esquerda do elemento anterior
                    if(elemAnterior.getFilhoLeft() != null && comparador.compare(elemAnterior.getFilhoLeft().getValor(), noAtual.getValor()) == 0) {
                        elemAnterior.setFilhoLeft(null);
                    } else {
                        elemAnterior.setFilhoRight(null);
                    }

                    return noAtual.getValor();
                }
            }
            // Verifica se o no a ser removido tem apenas um filho a direita
            else if(filhoEsquerdo == null && filhoDireito != null) {
                System.out.println("ENTROU AQUI NO FILHO A DIREITA --> " + filhoDireito.getValor());
                if(elemAnterior != null) {
                    filhoDireito.setPai(elemAnterior);
                    // Veririca se o no atual eh filho direito do elemAnterior
                    if(elemAnterior.getFilhoRight() != null && comparador.compare(noAtual.getValor(), elemAnterior.getFilhoRight().getValor()) == 0) {
                        elemAnterior.setFilhoRight(filhoDireito);
                    } else {
                        elemAnterior.setFilhoLeft(filhoDireito);
                    }
                } else {
                    this.raiz = filhoDireito;
                    filhoDireito.setPai(null);
                }

                return noAtual.getValor();
            }
            // Verifica se o no ser removido tem apenas um filho a esquerda
            else if(filhoEsquerdo != null && filhoDireito == null) {
                if(elemAnterior != null) {
                    filhoEsquerdo.setPai(elemAnterior);
                    // Veririca se o no atual eh filho Esquerdo do elemAnterior
                    if(elemAnterior.getFilhoLeft() != null && comparador.compare(noAtual.getValor(), elemAnterior.getFilhoLeft().getValor()) == 0) {
                        elemAnterior.setFilhoLeft(filhoEsquerdo);
                    } else {
                        elemAnterior.setFilhoRight(filhoEsquerdo);
                    }
                } else {
                    this.raiz = filhoEsquerdo;
                    filhoEsquerdo.setPai(null);
                }
                return noAtual.getValor();
            }
            // Ele possui 2 filhos
            else {

                // Encontra sucessor
                No<T> sucessorInOrder = encontraSucessorInOrder(noAtual.getFilhoRight());

                // Substitui o valor do nó a ser removido pelo valor do sucessor em ordem.
                noAtual.setValor(sucessorInOrder.getValor());

                // Remove o No sucessor cujo valor foi copiado para o lugar do no (valor) que foi removido
                remover(sucessorInOrder.getValor(), noAtual.getFilhoRight());

                return noAtual.getValor();
                //No<T> paiSucessorInOrder = sucessorInOrder.getPai();
                //pai
            }

        }
        //return noAtual.getValor();
    }


    // Função encontraSucessorInOrder --> Recebe o elemento a ser removido e o lado em que o mesmo está na arvore, e encontra, dependendo do ramo,
    // o elemento mais a direita ou esquerda daquele ramo.


    private No<T> encontraSucessorInOrder(No<T> elementoAtual) {
        // Se estivermos no lado Esquerdo da arvore, o elemento Sucessor In-Order sera o filho mais a direita deste lado.
        // Portanto, percorra esta ramificacao ate encontrar o filho mais a direita deste lado (next Filho Right == null)
        // Se elementoAtual.getFilhoRight() == null, encontrou o Sucessor In-Order

        if(elementoAtual == null) {
            return null;
        }
        if(elementoAtual.getFilhoLeft() == null) {

            // Retorne o elemento
            System.out.println("ESTE EH O SUCESSOR IN ORDER LEFT-->" + elementoAtual.getValor());
            return elementoAtual;
        }
        // Caso nao tenha encontrado, percorra recursivamente.
        return encontraSucessorInOrder(elementoAtual.getFilhoLeft());
    }


    @Override
    public int altura() {
        return calcularAltura(this.raiz)-1;
    }

    private int calcularAltura(No<T> no){
        // condição de parada, se o nó for nulo a altura é 0
        if (no == null) {
            return 0;
        }

        // calculo recursivo da subarvore da esquerda
        int alturaEsquerda = calcularAltura(no.getFilhoLeft());

        // calculo recursivo da subarvore da direita
        int alturaDireita = calcularAltura(no.getFilhoRight());

        // retorna a altura da maior subarvore + 1 que é o nó atual
        if (alturaEsquerda > alturaDireita) {
            return 1 + alturaEsquerda;
        } else {
            return 1 + alturaDireita;
        }
    }


    @Override
    public int quantidadeNos() {
        return nodeCounter(this.raiz);
    }

    private int nodeCounter(No<T> no){
        // condição de parada, se o nó é nulo soma 0
        if(no == null){
            return 0;
        }
        // retorna o 1 do nó atual + contagem da esquerda + contagem da direita
        return 1 + nodeCounter(no.getFilhoLeft()) + nodeCounter(no.getFilhoRight());
    }

    @Override
    public String caminharEmNivel() {
        // Caso a arvore esteja vazia a função retorna a string vazia
        if (this.raiz == null){
            return "[]";
        }
        // Cria uma fila para a impressao dos nós de forma ordenada por nivel
        Queue<No<T>> fila = new LinkedList<>();
        // Adicionando a raiz ao começo da fila
        fila.add(this.raiz);
        // retirando a raiz para iniciar o processo
        No<T> noAtual = null;
        // iniciando uma StringBuilder para a concatenação de nós em um loop na String
        StringBuilder resultado = new StringBuilder("[\n");
        while(!fila.isEmpty()){
            noAtual = fila.remove();
            // Adiciona os filhos do nó no nivel atual ao fim da fila caso existam.
            if (noAtual.getFilhoLeft() != null){
                fila.add(noAtual.getFilhoLeft());
            }
            if (noAtual.getFilhoRight() != null){
                fila.add(noAtual.getFilhoRight());
            }
            // Adiciona o nó atual a String resultado
            resultado.append(noAtual.getValor().toString());
            resultado.append("\n");
            }
            // atualiza a variavel do loop pegando um novo no da fila


        // Adiciona a ] para finalizar a String
        resultado.append("]");
        // Transforma a StringBuilder em string e a retorna
        return resultado.toString();

    }


    @Override
    public String caminharEmOrdem() {
        // Inicia a String que será retornada como resposta.
        String resultado = "[ \n ";
        // chama a funçao auxiliar que é recursiva.
        resultado = auxEmOrdem(resultado, this.raiz);
        resultado+="]";
        return resultado;
    }

    private String auxEmOrdem(String parcial, No<T> noAtual){
        // Verifica se o nó atual é nulo. Caso seja, retorna o resultado parcial sem alteração. Isso ativa a linha 15 imprimindo o nó anterior ao nulo
        if (noAtual == null) {
            return parcial;
        }
        // Chama a função passando o filho a esquerda do no atual. Percorre os nós a esquerda para que sejam adicionado a baixo
        parcial = auxEmOrdem(parcial, noAtual.getFilhoLeft());
        // Imprime o nó atual. Isso ocorre após encontrar um no nulo a esquerda. Adiciona o nó atual na resposta
        parcial+= noAtual.getValor().toString() + " \n ";
        // Verifica se o nó possui um no a direita. Isso é feito para percorrer os nós a direita do nós a direita e adicionalos a resposta
        parcial = auxEmOrdem(parcial, noAtual.getFilhoRight());
        // Quando a verificação de um nó acaba, e ele e seus filhos tenham sido escritos o resultado parcial é retornado para quem chamou a função.
        return parcial;
    }


    // debug pra ver a árvore
    public void printArvore() {
        // chama o metodo privado, com nível 0 que representa a raiz mãe
        printArvore(this.raiz, 0);
    }

    private void printArvore(No<T> no, int nivel) {
        // condição de parada, se nó == null não printa
        if (no != null) {
            // recursão para processar primeiro o filho da direita
            printArvore(no.getFilhoRight(), nivel + 1);

            // Imprime espaços para indentação
            for (int i = 0; i < nivel; i++) {
                // espaço por nível de profundidade (talvez aumentar isso)
                System.out.print("    ");
            }
            // imprime o valor do nó atual
            System.out.println(no.getValor());

            //recursão para processar o filho da esquerda
            printArvore(no.getFilhoLeft(), nivel + 1);
        }
    }

    public Comparator<T> getComparator() {return this.comparador;}
}
