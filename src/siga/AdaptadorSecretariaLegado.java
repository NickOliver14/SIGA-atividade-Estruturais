package siga;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorSecretariaLegado implements FonteDeAlunos {

    private static final int COLUNA_MATRICULA = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_SITUACAO = 2;

    private final SecretariaLegadoWS servico;

    public AdaptadorSecretariaLegado(SecretariaLegadoWS servico) {
        this.servico = servico;
    }

    @Override
    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        for (String[] linha : servico.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[COLUNA_MATRICULA], linha[COLUNA_NOME]);
            aluno.setAtivo("A".equalsIgnoreCase(linha[COLUNA_SITUACAO]));
            alunos.add(aluno);
        }
        return alunos;
    }
}