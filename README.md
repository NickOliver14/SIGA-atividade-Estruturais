
## Estrutura do projeto

```
siga-estruturais/
└── src/
    └── siga/
        ├── Aluno.java                 (entidade de domínio; pronta)
        ├── Matricula.java             (entidade de domínio; pronta)
        ├── AlunoDAO.java              (interface da Aula 7; pronta)
        ├── AlunoDAOMemoria.java       (implementação da Aula 8; pronta)
        ├── MatriculaDAO.java          (interface; pronta)
        ├── MatriculaDAOMemoria.java   (implementação; pronta)
        ├── SecretariaLegadoWS.java    (COMPONENTE EXTERNO — não alterar)
        ├── ValidadorCpf.java          (subsistema de matrícula; pronta)
        ├── ControleVagas.java         (subsistema de matrícula; pronta)
        ├── CalculadoraDesconto.java   (subsistema de matrícula; pronta)
        ├── NotificadorEmail.java      (subsistema de matrícula; pronta)
        ├── ImportadorAlunos.java      (deslizes 1A e 2)
        ├── RelatorioSituacao.java     (deslize 1B)
        ├── TelaMatricula.java         (deslize 3)
        └── Main.java                  (demonstra os três deslizes)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior (`java -version` para verificar).

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -encoding UTF-8 -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

Execute antes de ler o restante deste arquivo: a saída **mostra os três deslizes acontecendo**.

## Os três deslizes propositais

| # | Deslize | Onde | Por que é um problema |
|---|---|---|---|
| 1 | **Conversão duplicada e divergente** | `ImportadorAlunos` e `RelatorioSituacao` | O conhecimento do formato legado (coluna 0 é matrícula, 2 é a situação em letra) foi escrito duas vezes. Alguém corrigiu o tratamento da letra minúscula em um dos dois e não no outro: a aluna `2024003` aparece **inativa** no importador e **ativa** no relatório. |
| 2 | **Dependência de classe concreta** | `ImportadorAlunos` | O importador recebe `SecretariaLegadoWS` no construtor. Trocar a origem dos alunos exige alterar o importador, e testá-lo exige o serviço externo. Violação do DIP (Aula 3). |
| 3 | **Subsistema exposto à apresentação** | `TelaMatricula` | A tela recebe **seis colaboradores** e conhece a ordem correta de chamá-los — reservar a vaga só depois de gravar a matrícula. Essa ordem é regra de negócio e não deveria morar na tela. |


## Critério de sucesso

Ao final, verifique que: (a) a situação da aluna `2024003` é a **mesma** nos dois pontos do sistema; (b) `ImportadorAlunos` não menciona `SecretariaLegadoWS`; (c) `TelaMatricula` tem **um** atributo colaborador, não seis; (d) `SecretariaLegadoWS` continua **exatamente como veio**; e (e) o programa continua produzindo as mesmas matrículas e os mesmos erros de antes.

> O último item é importante: uma refatoração estrutural bem feita **não muda o comportamento externo** do sistema. Se a saída mudou — fora a correção da divergência do deslize 1 —, algo se perdeu no caminho.

## Padrão de entrega

Conforme a ficha de atividade prática: identificadores em português, um arquivo `.java` por classe pública, código formatado, entrega no repositório Git com README e commits descritivos. O uso de IA para gerar o código é proibido nesta atividade (ver seção 5.3 da ficha).

> **Projeto Integrador:** a Etapa 2, com entrega na Aula 13, exige a aplicação justificada de ao menos um padrão estrutural. O que você produzir aqui pode ser aproveitado nela — desde que a justificativa descreva o problema do SIGA, e não apenas o nome do padrão.
