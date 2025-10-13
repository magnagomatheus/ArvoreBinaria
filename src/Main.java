import java.util.Comparator;

public static void main(String[] args) {

    ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
    arvore.comparador = Comparator.naturalOrder();

    arvore.adicionar(50);
    arvore.adicionar(30);
    arvore.adicionar(70);
    arvore.adicionar(20);
    arvore.adicionar(40);
    arvore.adicionar(60);
    arvore.adicionar(80);
    arvore.adicionar(80);
    arvore.adicionar(90);

    arvore.printArvore();

    System.out.println("NÓS:");
    System.out.println(arvore.quantidadeNos());
    System.out.println(arvore.altura());
}