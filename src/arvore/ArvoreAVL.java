package arvore;
import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public T adicionar(T novoValor) {
        raiz = adicionarAVL(raiz, novoValor);
        return this.raiz.getValor();
    }

    private No<T> adicionarAVL(No<T> no, T valor){
        if(no == null){
            return new No<T>(valor);
        }
        int cmp = comparador.compare(valor, no.getValor());

        if (cmp < 0){
            // inserção subarvore esquerad
            no.setFilhoLeft(adicionarAVL(no.getFilhoLeft(), valor));
            // atualiza o pai do novo nó
            if(no.getFilhoLeft() != null){
                no.getFilhoLeft().setPai(no);
            }
            // inserção subarvore direita
        } else if (cmp > 0) {
            no.setFilhoRight(adicionarAVL(no.getFilhoRight(),valor));
            // atualiza o pai do novo nó
            if(no.getFilhoRight() != null){
                no.getFilhoRight().setPai(no);
            }
            // valor q ja existe na arvore nãp é inserido
        } else {
            return no;
        }
        // chamada de balanceamento para cada adição
        balanco(no);
        return no;
    }
    // confusao aqui
    private void balanco(No<T> no){
        int fator = fatorBalanceamento(no);
        // balanceamento, subarvore esquerda mais "pesada"
        if (fator == -2){
            // verifica balanceamento pro filho esquerdo
            int fatorFilhoLeft = fatorBalanceamento(no.getFilhoLeft());

            // se pai e filho estão desbalanceados pra esquerda
            if (fatorFilhoLeft <= 0){
                // rotação simples pra direita
                rotacaoDireita(no);
                // desbalancemento pra direções opostas esquerda pai esquerda e filho direita
            } else{
                // primeiro rotação à esquerda no filho esquerdo
                rotacaoEsquerda(no.getFilhoLeft());
                // rotação À direita do nó atual(pai)
                rotacaoDireita(no);
            }
        }
    }
    public void rotacaoDireita(No<T> no){
        No<T> aux = no.getFilhoLeft();
        no.setFilhoLeft(aux.getFilhoRight());
        no.getFilhoRight().setPai(no);
        aux.setFilhoRight(no);
        aux.setPai(no.getPai());
        if(no.getPai().getFilhoRight() == no){
            no.getPai().setFilhoRight(aux);
            no.setPai(aux);
        }else{
            no.getPai().setFilhoLeft(aux);
            no.setPai(aux);
        }
    }

    public void rotacaoEsquerda(No<T> no){
        No<T> aux = no.getFilhoRight();
        no.setFilhoRight(aux.getFilhoLeft());
        no.getFilhoRight().setPai(no);
        aux.setFilhoLeft(no);
        aux.setPai(no.getPai());
        if(no.getPai().getFilhoRight() == no){
            no.getPai().setFilhoRight(aux);
            no.setPai(aux);
        }else{
            no.getPai().setFilhoLeft(aux);
            no.setPai(aux);
        }
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