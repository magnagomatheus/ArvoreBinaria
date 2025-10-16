package arvore;

import arvore.GeradorArvore;

public class testGeradorArvores {

    public void main(String[] args) {
        ArvoreBinaria<Aluno> arvoreAlunoNome = new ArvoreBinaria<>(new ComparadorAlunoPorNome());
        ArvoreBinaria<Aluno> arvoreAlunoMatricula = new ArvoreBinaria<>(new ComparadorAlunoPorMatricula());
        GeradorArvore creator = new GeradorArvore();


        //creator.geraArvoreDegenerada(10, arvoreAlunoNome);
        //creator.geraArvoreDegenerada(10,  arvoreAlunoMatricula);

        creator.geraArvorePerfeitamenteBalanceada(0, 10, arvoreAlunoNome);
        //creator.geraArvorePerfeitamenteBalanceada(0, 10, arvoreAlunoMatricula);

        arvoreAlunoNome.printArvore();
        //arvoreAlunoMatricula.printArvore();

    }

}
