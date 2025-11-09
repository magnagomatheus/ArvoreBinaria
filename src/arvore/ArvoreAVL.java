package arvore;
import java.util.Comparator;

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
        return calcularAltura(this.raiz);
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


}