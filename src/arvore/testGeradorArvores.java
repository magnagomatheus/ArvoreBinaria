package arvore;

import arvore.GeradorArvore;

public class testGeradorArvores {

    public static void main(String[] args) {
        // iniciando comparadores
        ComparadorAlunoPorMatricula compPorMatricula = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compPorNome = new ComparadorAlunoPorNome();

        //iniciando gerador de arvores
        GeradorArvore gerador = new GeradorArvore();

        //Inicinando contrato
        IArvoreBinaria<Aluno> arv;

        //------Início do trecho a ser considerado nas questões 1, 2 e 3 do relatório-------------------------------
        //Instancio uma árvore binária. Lembre de ajustar o import para sua classe de árvore binária
        arv = new ArvoreBinaria<>(compPorMatricula);
        //Chamo o gerador para inserir 100 elementos nessa árvore de forma que fique degenerada
        gerador.geraArvoreDegenerada(15, arv);
        System.out.println("Árvore Degenerada Criada");


    }

}
