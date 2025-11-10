package Main;

import arvore.Aluno;
import arvore.ArvoreBinaria;
import arvore.ComparadorAlunoPorMatricula;
import arvore.ComparadorAlunoPorNome;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ComparadorAlunoPorMatricula comparadorM = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome comparadorN = new ComparadorAlunoPorNome();

        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>(comparadorN);

        String menu = "Selecione uma modalidade:\n" +
                "-2 - Criar uma nova Árvore com comparador por Matricula\n" +
                "-1 - Criar uma nova Árvore com comparador por Nome\n" +
                " 1 - Inserir elemento\n" +
                " 2 - Pesquisar elemento\n" +
                " 3 - Pesquisar elemento de forma alternativa\n" +
                " 4 - Remover elemento\n" +
                " 5 - Caminhar em ordem\n" +
                " 6 - Caminhar em nível\n" +
                " 0 - Sair" ;

        Scanner s = new Scanner(System.in);

        int opcao =-1;
        // Menu com opções de teste do programa
        do {

            try {
                System.out.println(menu);
                opcao = Integer.parseInt(s.nextLine().trim());

                if (opcao == -2){
                    System.out.println("Criando uma nova Árvore com um comparador por matricula.");
                    arvore = new ArvoreBinaria<>(comparadorM);
                }
                if (opcao == -1){
                    System.out.println("Criando uma nova Árvore com um comparador por nome.");
                    arvore = new ArvoreBinaria<>(comparadorN);
                }
                if (opcao == 1) {
                    System.out.println("Inserindo elemento na Árvore");
                    System.out.println("Digite a Matricula do Aluno: ");
                    int matricula = Integer.parseInt(s.nextLine().trim());

                    System.out.println("Digite o Nome do Aluno :");
                    String nome = s.nextLine().trim();

                    arvore.adicionar(new Aluno(matricula,nome));

                } else if (opcao == 2) {
                    System.out.println("Pesquisar elemento na Árvore");
                    int valor = 0;
                    String nome = null;
                    if (arvore.getComparator() == comparadorN) {
                        System.out.println("Digite o Nome do Aluno: ");
                        nome = s.nextLine().trim();

                    }else{
                        System.out.println("Digite a matricula do Aluno: ");
                        valor = Integer.parseInt(s.nextLine().trim());

                    }
                    System.out.println(arvore.pesquisar(new Aluno(valor,nome)));


                }else if (opcao == 3) {
                    System.out.println("Pesquisar elemento na Árvore com forma alternativa");
                    int valor = 0;
                    String nome = null;
                    if (arvore.getComparator() == comparadorN) {
                        System.out.println("Digite a Matricula do Aluno :");
                        valor = Integer.parseInt(s.nextLine().trim());

                        System.out.println(arvore.pesquisar(new Aluno(valor, nome), comparadorM));
                    }else{
                        System.out.println("Digite a Nome do Aluno :");
                        nome = s.nextLine().trim();

                        System.out.println(arvore.pesquisar(new Aluno(valor, nome ), comparadorN));
                    }

                }else if (opcao == 4) {
                    System.out.println("Remover elemento da Árvore");
                    String nome = null;
                    int valor = 0;
                    if (arvore.getComparator() == comparadorN) {
                        System.out.println("Digite o Nome do Aluno :");
                        nome = s.nextLine().trim();

                    }else{
                        System.out.println("Digite a matricula do Aluno :");
                        valor = Integer.parseInt(s.nextLine().trim());
                    }
                    arvore.remover(new Aluno(valor, nome));

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
