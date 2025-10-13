package arvore;

import java.util.Comparator;

public class ComparadorAlunoPorNome implements Comparator<Aluno> {
    @Override
    public int compare(Aluno a1, Aluno a2){
        return a1.getNome().toUpperCase().compareTo(a2.getNome().toUpperCase());
    }
}
