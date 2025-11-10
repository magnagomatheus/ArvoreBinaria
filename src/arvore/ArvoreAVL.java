package arvore;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public T adicionar(T novoValor) {
        No<T> novoElemento = new No<T>(novoValor);
        if (this.raiz == null) {
            // O novo elemento se torna a raiz da arvore.
            this.raiz = novoElemento;
            System.out.println("Elemento adicionado com sucesso!" + novoElemento.getValor());
            return this.raiz.getValor();
        }
        adicionarAVL(novoElemento,raiz);
        this.raiz = balanco(raiz);
        return this.raiz.getValor();
    }


    // Função Adicionar (Recursiva) --> Percorre toda a árvore comparando cada nó com o novo valor, com o objetivo de encontrar o lugar ideal para adição do novo elemento.
    private boolean adicionarAVL(No<T> novoElemento, No<T> currentNo) {
        // Comparação entre o valor do novo elemento e o valor do elemento atual da lista.
        int cmp = comparador.compare(novoElemento.getValor(), currentNo.getValor());
        // Verifica se o valor novo elemento eh MAIOR do que o valor do elemento atual da arvore
        if (cmp > 0) {
            // Se for maior, verifica se o elemento atual possui um filho a direita, ou seja, se o elemento atual a arvore tem algum nó com valor maior do que ele próprio.
            if(currentNo.getFilhoRight() == null) {
                // Caso não tenha um próximo nó, o novo elemento se torna este próximo, se torna filho do nó atual da àrvore.
                currentNo.setFilhoRight(novoElemento);
                novoElemento.setPai(currentNo);
                // return true (deu certo)
                System.out.println("Elemento adicionado com sucesso!" + novoElemento.getValor());
                return true;
            }
            // Caso tenha um próximo elemento de valor maior, passe para o próximo nó chamando a própria função recursivamente, atualizando apenas o currentNo.
            adicionarAVL(novoElemento, currentNo.getFilhoRight());
            currentNo.setFilhoRight(balanco(currentNo.getFilhoRight()));//balanco(currentNo.getFilhoRight());
        }
        // Verifica se o valor novo elemento eh MENOR do que o valor do elemento atual da arvore
        else if(cmp < 0) {
            // Se for menor, verifica se o nó atual possui um filho a esquerda, ou seja, um elemento menor do que ele próprio
            if(currentNo.getFilhoLeft() == null) {
                // Se não tiver um elemento menor que o nó atual na lista, o novo elemento se torna filho a esquerda do nó atual.
                currentNo.setFilhoLeft(novoElemento);
                novoElemento.setPai(currentNo);
                // return true (elemento adicionado)
                System.out.println("Elemento adicionado com sucesso!" + novoElemento.getValor());
                return true;
            }
            // Caso tenha um próximo elemento menor que o nó atual (filho a esquerda), chame o método adicionar recursivamente atualizando o parametro currentNo.
            adicionarAVL(novoElemento, currentNo.getFilhoLeft());
            currentNo.setFilhoLeft(balanco(currentNo.getFilhoLeft())); // balanco(currentNo.getFilhoLeft());
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

//    @Override
//    public T remover(T valor) {
//        // Chame a funcao recursiva para remover o elemento.
//        No<T> res = remover(valor, this.raiz);
//        if(res == null) {
//            System.out.println("Elemento não existe nesta árvore!");
//            return null;
//        }
//
//        return res.getValor();
//
//    }

//    private No<T> remover(T valor, No<T> noAtual) {
//
//        // Funcao recursiva, cuja logica seguida eh o Sucessor In-Order
//        // Ao inves de atribuir os nos novamente e pontar filho por filho,
//        // Apenas substituimos o arvore.No a ser removido pelo Sucessor In-Order, que
//        // Se o Elemento a ser removido estiver a esquerda, o Sucessor In-Order sera o elemento mais a direita do ramo.
//        // Se o Elemento a ser removido estiver a direita ou for a raiz, o Sucessor In-Order sera o elemento mais a esquerda do ramo.
//
//
//        // Verifica se a lista está vazia ou se chegou ao final da arvore e o elemento nao foi encontrado.
//        if (noAtual == null) {
//            System.out.println("Não há nenhum elemento na arvore.");
//            return null;
//        }
//
//        // Primeiro faremos a busca pelo elemento a ser removido.
//        // Comparacao entre o valor do novo elemento e o valor do elemento atual da arvore.
//        int cmp = comparador.compare(valor, noAtual.getValor());
//
//
//        // Se o novo elemento for MAIOR que o elemento atual da arvore, entao va para o proximo filho a direita
//        if(cmp > 0) {
//                return remover(valor, noAtual.getFilhoRight());
//        }
//        // Se o novo elemento for MENOR que o elemento atual da arvore, entao va para o proximo filho a esquerda
//        else if(cmp < 0) {
//            return remover(valor, noAtual.getFilhoLeft());
//        }
//        // Se forem iguais, encontrou o elemento a ser removido.
//        else {
//
//            No<T> filhoEsquerdo = noAtual.getFilhoLeft();
//            No<T> filhoDireito = noAtual.getFilhoRight();
//            No<T> elemAnterior = noAtual.getPai();
//
//            // Verifica se o no a ser removido nao tem nenhum filho
//            if(filhoEsquerdo == null && filhoDireito == null) {
//                // Verifica se o no a ser removido eh a raiz da arvore
//                if(elemAnterior == null) {
//                    this.raiz = null;
//                } else {
//                    // Se nao for raiz da arvore, verifica
//                    // Se ele for o filho a esquerda do elemento anterior
//                    if(elemAnterior.getFilhoLeft() != null && comparador.compare(elemAnterior.getFilhoLeft().getValor(), noAtual.getValor()) == 0) {
//                        elemAnterior.setFilhoLeft(null);
//                    } else {
//                        elemAnterior.setFilhoRight(null);
//                    }
//
//                }
//            }
//            // Verifica se o no a ser removido tem apenas um filho a direita
//            else if(filhoEsquerdo == null && filhoDireito != null) {
//                System.out.println("ENTROU AQUI NO FILHO A DIREITA --> " + filhoDireito.getValor());
//                if(elemAnterior != null) {
//                    if(comparador.compare(noAtual.getValor(), elemAnterior.getFilhoRight().getValor()) == 0) {
//                        elemAnterior.setFilhoRight(filhoDireito);
//                    } else {
//                        elemAnterior.setFilhoLeft(filhoDireito);
//                    }
//
//                } else {
//                    this.raiz = filhoDireito;
//                    filhoDireito.setPai(null);
//                    this.raiz.setFilhoRight(null);
//                }
//
//            }
//            // Verifica se tem apenas um filho a esquerda
//            else if(filhoEsquerdo != null && filhoDireito == null) {
//                if(elemAnterior != null) {
//                    if(comparador.compare(noAtual.getValor(), elemAnterior.getFilhoRight().getValor()) == 0) {
//                        elemAnterior.setFilhoRight(filhoEsquerdo);
//                    } else {
//                        elemAnterior.setFilhoLeft(filhoEsquerdo);
//                    }
//                } else {
//                    this.raiz = filhoEsquerdo;
//                    filhoEsquerdo.setPai(null);
//                    this.raiz.setFilhoLeft(null);
//                }
//            }
//            // Ele possui 2 filhos
//            else {
//
//                // Encontra sucessor
//                No<T> sucessorInOrder = encontraSucessorInOrder(noAtual.getFilhoRight());
//
//                // Substitui o valor do nó a ser removido pelo valor do sucessor em ordem.
//                noAtual.setValor(sucessorInOrder.getValor());
//
//                // Remove o No sucessor cujo valor foi copiado para o lugar do no (valor) que foi removido
//                remover(sucessorInOrder.getValor(), noAtual.getFilhoRight());
//
//            }
//
//        }
//        return balanco(noAtual);
//    }
//
//    private No<T> encontraSucessorInOrder(No<T> elementoAtual) {
//        // Se estivermos no lado Esquerdo da arvore, o elemento Sucessor In-Order sera o filho mais a direita deste lado.
//        // Portanto, percorra esta ramificacao ate encontrar o filho mais a direita deste lado (next Filho Right == null)
//        // Se elementoAtual.getFilhoRight() == null, encontrou o Sucessor In-Order
//
//        if(elementoAtual == null) {
//            return null;
//        }
//        if(elementoAtual.getFilhoLeft() == null) {
//            return elementoAtual;
//        }
//        // Caso nao tenha encontrado, percorra recursivamente.
//        return encontraSucessorInOrder(elementoAtual.getFilhoLeft());
//    }

    // confusao aqui
    private No<T> balanco(No<T> no){
        int fator = fatorBalanceamento(no);
        No<T> novaRaiz;
        // balanceamento, subarvore esquerda mais "pesada"
        if (fator == -2){
            // verifica balanceamento pro filho esquerdo
            int fatorFilhoLeft = fatorBalanceamento(no.getFilhoLeft());

            // se pai e filho estão desbalanceados pra esquerda
            if (fatorFilhoLeft <= 0){
                // rotação simples pra direita
                return rotacaoDireita(no);
                // desbalancemento pra direções opostas esquerda pai esquerda e filho direita
            } else{
                // primeiro rotação à esquerda no filho esquerdo
                novaRaiz = rotacaoEsquerda(no.getFilhoLeft());
                no.setFilhoLeft(novaRaiz);
                // rotação À direita do nó atual(pai)
                return rotacaoDireita(no);
            }
        }else if (fator == 2){
            int fatorFilhoRight = fatorBalanceamento(no.getFilhoRight());

            if (fatorFilhoRight >= 0){
                return rotacaoEsquerda(no);
            }
            else {
                novaRaiz = rotacaoDireita(no.getFilhoRight());
                no.setFilhoRight(novaRaiz);
                return rotacaoEsquerda(no);
            }
        }
        return no;
    }

    // aparentemente completo, é necessario ajustar o pai de aux na adicionar.
    public No<T> rotacaoDireita(No<T> no){
        No<T> aux = no.getFilhoLeft();
        no.setFilhoLeft(aux.getFilhoRight());
        if (no.getFilhoLeft()!= null){no.getFilhoLeft().setPai(no);}
        aux.setFilhoRight(no);
        aux.setPai(no.getPai());
        no.setPai(aux);
        return aux;
    }
    // aparentemente completo, é necessario ajustar o pai de aux na adicionar.
    public No<T> rotacaoEsquerda(No<T> no){
        No<T> aux = no.getFilhoRight();
        no.setFilhoRight(aux.getFilhoLeft());
        if (no.getFilhoRight() != null){no.getFilhoRight().setPai(no);}
        aux.setFilhoLeft(no);
        aux.setPai(no.getPai());
        no.setPai(aux);
        return aux;
    }

    public int fatorBalanceamento(No<T> no){
        if (no == null){
            return 0;
        }
        return calcularAltura(no.getFilhoRight()) - calcularAltura(no.getFilhoLeft());
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


    public String caminharEmNivel() {
        // Caso a arvore esteja vazia a função retorna a string vazia
        if (this.raiz == null){
            return "[]";
        }
        // Cria uma fila para a impressao dos nós de forma ordenada por nivel
        Queue<No<T>> fila = new LinkedList<>();
        // Adicionando a raiz ao começo da fila
        fila.add(this.raiz); fila.add(null);
        // retirando a raiz para iniciar o processo
        No<T> noAtual= fila.remove();
        // iniciando uma StringBuilder para a concatenação de nós em um loop na String
        StringBuilder resultado = new StringBuilder("[");
        while(!fila.isEmpty()){
            if (noAtual==null){
                fila.add(null);
                resultado.append("\n");

            }else{
                // Adiciona os filhos do nó no nivel atual ao fim da fila caso existam.
                if (noAtual.getFilhoLeft() != null){
                    fila.add(noAtual.getFilhoLeft());
                }
                if (noAtual.getFilhoRight() != null){
                    fila.add(noAtual.getFilhoRight());
                }
                // Adiciona o nó atual a String resultado
                resultado.append(noAtual.getValor().toString());
                resultado.append(" , ");
            }
            // atualiza a variavel do loop pegando um novo no da fila
            noAtual = fila.remove();
        }
        // Adiciona a ] para finalizar a String
        resultado.append("]");
        // Transforma a StringBuilder em string e a retorna
        return resultado.toString();

    }

}