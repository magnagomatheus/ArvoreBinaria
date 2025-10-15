package arvore;

public class Aluno implements Comparable<Aluno>{
    private int matricula;
    private String nome;
    private double nota;

    public Aluno (int m, String n, double no){
        this.matricula = m;
        this.nome = n;
        this.nota = no;
    }
    @Override
    public String toString() {
        return Integer.toString(matricula) + " - " + nome;
    }
    @Override
    public int compareTo(Aluno a){
        return this.nome.compareTo(a.nome);
    }

    public int getMatricula() {
        return this.matricula;
    }

    public String getNome() {
        return this.nome;
    }
}
