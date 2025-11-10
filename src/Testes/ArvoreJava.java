package Testes;

import java.util.TreeMap;
import java.util.TreeSet;
import arvore.Aluno;
import arvore.ComparadorAlunoPorMatricula;
import arvore.ComparadorAlunoPorNome;

public class ArvoreJava {
    public static void main(String[] args) {
        System.out.println("=== TreeSet de Alunos ===");

        //instanciando treesets
        TreeSet<Aluno> alunosMSet = new TreeSet<>(new ComparadorAlunoPorMatricula());
        TreeSet<Aluno> alunosNSet = new TreeSet<>(new ComparadorAlunoPorNome());

        // adicionando alunos
        alunosMSet.add(new Aluno(2023001, "João Silva"));
        alunosMSet.add(new Aluno(2023003, "Maria Santos"));
        alunosMSet.add(new Aluno(2023002, "Pedro Oliveira"));
        alunosMSet.add(new Aluno(2023002, "Pedro Oliveira")); // duplicado

        alunosNSet.add(new Aluno(2023002, "Pedro Oliveira"));
        alunosNSet.add(new Aluno(2023001, "João Silva"));
        alunosNSet.add(new Aluno(2023003, "Maria Santos"));
        alunosNSet.add(new Aluno(2023002, "Pedro Oliveira"));// duplicado

        System.out.println("Alunos no TreeSet (ordenados por matrícula):");
        alunosMSet.forEach((a) -> System.out.println("  " + a.getNome() + " - " + a.getMatricula()));

        System.out.println("\nAlunos no TreeSet (ordenados por nome):");
        alunosNSet.forEach((a) -> System.out.println("  " + a.getNome() + " - " + a.getMatricula()));

        // busca aluno por matrícula
        Aluno alunoBusca = new Aluno(2023002, "");
        System.out.println("\nBusca por matrícula '2023002': " + alunosMSet.contains(alunoBusca));

        // busca aluno por nome
        Aluno alunoBuscaNome = new Aluno(0, "Pedro Oliveira");
        System.out.println("Busca por nome 'Pedro Oliveira': " + alunosNSet.contains(alunoBuscaNome));

        //removendo aluno
        System.out.println("\nRemovendo aluno...");
        alunosNSet.remove(alunoBuscaNome); // remove por objeto Aluno
        alunosMSet.remove(alunoBusca);     // remove por objeto Aluno

        System.out.println("\nApós remoção - TreeSet (ordenados por matrícula):");
        alunosMSet.forEach((a) -> System.out.println("  " + a.getNome() + " - " + a.getMatricula()));

        System.out.println("Após remoção - TreeSet (ordenados por nome):");
        alunosNSet.forEach((a) -> System.out.println("  " + a.getNome() + " - " + a.getMatricula()));


        System.out.println("\nTreeMap de Alunos");

        // instanciando treemaps
        TreeMap<Aluno, String> alunosMMap = new TreeMap<>(new ComparadorAlunoPorMatricula());
        TreeMap<Aluno, String> alunosNMap = new TreeMap<>(new ComparadorAlunoPorNome());

        // adicionando alunos no Map
        Aluno joao = new Aluno(2023001, "João Silva");
        Aluno maria = new Aluno(2023003, "Maria Santos");
        Aluno pedro = new Aluno(2023002, "Pedro Oliveira");

        alunosMMap.put(joao, "Nota A");
        alunosMMap.put(maria, "Nota A+");
        alunosMMap.put(pedro, "Nota B");

        alunosNMap.put(joao, "Nota A");
        alunosNMap.put(maria, "Nota A+");
        alunosNMap.put(pedro, "Nota B");

        System.out.println("Alunos no TreeMap (ordenados por matrícula):");
        alunosMMap.forEach((aluno, nota) ->
                System.out.println("  " + aluno.getMatricula() + " - " + aluno.getNome() + " -> " + nota));

        System.out.println("\nAlunos no TreeMap (ordenados por nome):");
        alunosNMap.forEach((aluno, nota) ->
                System.out.println("  " + aluno.getNome() + " - " + aluno.getMatricula() + " -> " + nota));

        // busca no Map
        System.out.println("\nBusca pelo aluno com matrícula '2023002': " +
                alunosMMap.get(pedro));

        System.out.println("Busca pelo aluno com nome 'João Silva': " +
                alunosNMap.get(joao));

        //remocao no Map
        System.out.println("\n Removendo Maria na ordenação por matrícula");
        alunosMMap.remove(maria);
        alunosMMap.forEach((aluno, nota) ->
                System.out.println("  " + aluno.getMatricula() + " - " + aluno.getNome() + " -> " + nota));

        System.out.println("\n Removendo Maria na ordenação por nome");
        alunosNMap.remove(maria);
        alunosNMap.forEach((aluno, nota) ->
                System.out.println("  " + aluno.getMatricula() + " - " + aluno.getNome() + " -> " + nota));

        System.out.println("\nDemonstração da Ordenação Automática");
        System.out.println("Primeiro aluno (menor matrícula): " + alunosMSet.first().getNome() + " - " + alunosMSet.first().getMatricula());
        System.out.println("Último aluno (maior matrícula): " + alunosMSet.last().getNome() + " - " + alunosMSet.last().getMatricula());

        System.out.println("Primeiro aluno (ordem alfabética): " + alunosNSet.first().getNome() + " - " + alunosNSet.first().getMatricula());
        System.out.println("Último aluno (ordem alfabética): " + alunosNSet.last().getNome() + " - " + alunosNSet.last().getMatricula());
    }
}