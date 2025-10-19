package Main;

import arvore.Aluno;
import arvore.ArvoreBinaria;
import arvore.ComparadorAlunoPorMatricula;
import arvore.ComparadorAlunoPorNome;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>(new ComparadorAlunoPorNome());
    ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();

    String menu = "Selecione uma modalidade:\n" +
            "1 - Inserir elemento\n" +
            "2 - Pesquisar elemento\n" +
            "3 - Pesquisar elemento de forma alternativa\n" +
            "4 - Remover elemento\n" +
            "5 - Caminhar em ordem\n" +
            "6 - Caminhar em nível\n" +
            "0 - Sair" ;

    Scanner s = new Scanner(System.in);

    int opcao =-1;
    // Menu com opções de teste do programa
    do {

        try {
            System.out.println(menu);
            opcao = Integer.parseInt(s.nextLine().trim());
            if (opcao == 1) {
                System.out.println("Inserindo elemento na Árvore");
                System.out.println("Digite a Matricula do Aluno: ");
                int matricula = Integer.parseInt(s.nextLine().trim());



                System.out.println("Digite o Nome do Aluno :");
                String nome = s.nextLine().trim();

                System.out.println("Digite a Nota do Aluno :");
                //double nota = s.nextDouble();


                arvore.adicionar(new Aluno(matricula,nome));

            } else if (opcao == 2) {
                System.out.println("Pesquisar elemento na Árvore");
                System.out.println("Digite o Nome do Aluno: ");
                String nome = s.nextLine().trim();


                System.out.println(arvore.pesquisar(new Aluno(11,nome)));

            }else if (opcao == 3) {
                System.out.println("Pesquisar elemento na Árvore com forma alternativa");
                int valor = Integer.parseInt(s.nextLine().trim());

                System.out.println(arvore.pesquisar(new Aluno(valor,null), comparador));


            }else if (opcao == 4) {
                System.out.println("Remover elemento da Árvore");
                String valor = s.nextLine().trim();
                arvore.remover(new Aluno(1, valor));

            }else if (opcao == 5) {
                System.out.println("Caminhar pela Árvore em ordem");
                System.out.println(arvore.caminharEmOrdem());

            }else if (opcao == 6) {
                System.out.println("Caminhar pela Árvore em nível");
                System.out.println(arvore.caminharEmNivel());

            }
        } catch (Exception e) {
            System.out.println("Erro ao executar o menu!");
            s.nextLine();
        }
    }while (opcao != 0);

    System.out.println("Exit");



}
}
