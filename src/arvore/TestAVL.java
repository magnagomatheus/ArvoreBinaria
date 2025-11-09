package arvore;

public class TestAVL {
    public static void main(String[] args){
        // iniciando comparadores
        ComparadorAlunoPorMatricula compPorMatricula = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compPorNome = new ComparadorAlunoPorNome();

        //iniciando gerador de arvores
        GeradorArvore gerador = new GeradorArvore();

        //Inicinando contrato
        IArvoreBinaria<Aluno> arv;

        arv = new ArvoreAVL<>(compPorMatricula);

        gerador.geraArvoreDegenerada(100,arv);

        System.out.println(arv.caminharEmNivel());
    }
}
