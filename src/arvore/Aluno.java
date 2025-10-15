package arvore;

public class Aluno implements Comparable<Aluno> {
    private int matricula;
    private String nome;
    // double nota;

    public Aluno (int m, String n){
        this.matricula = m;
        this.nome = n;
        //this.nota = nota;
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
