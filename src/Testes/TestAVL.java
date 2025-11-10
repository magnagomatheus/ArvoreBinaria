package Testes;

import arvore.*;

import java.util.Scanner;

public class TestAVL {
    public static void main(String[] args){
        // iniciando comparadores
        ComparadorAlunoPorMatricula compPorMatricula = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compPorNome = new ComparadorAlunoPorNome();

        Scanner s = new Scanner(System.in);
        int opcao = -1;
        String menu =  " 1 - Inserir elemento \n" + " 2 - Imprimir em nível \n"+" 3 - Imprimir em ordem\n"+ " 0 - Sair";

        //iniciando gerador de arvores
        GeradorArvore gerador = new GeradorArvore();

        //Inicinando contrato
        IArvoreBinaria<Aluno> arv;

        arv = new ArvoreAVL<>(compPorMatricula);

        gerador.geraArvoreDegenerada(7,arv);

        System.out.println(((ArvoreAVL<?>) arv).caminharEmNivel());

        do{
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

                    arv.adicionar(new Aluno(matricula, nome));


                }
                if (opcao == 2) {
                    System.out.println(arv.caminharEmNivel());
                }
                if (opcao == 3) {
                    System.out.println(arv.caminharEmOrdem());
                }
            }catch ( Exception e){
                System.out.println("Erro ao executar o menu!");
            }

        }while (opcao!= 0);

    }
}
