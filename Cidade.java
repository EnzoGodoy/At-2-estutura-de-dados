public class Cidade {

    private String nome;

    private ListaDupla<Ligacao> ligacoes;

    public Cidade(String nome) {

        this.nome = nome;

        this.ligacoes = new ListaDupla<>();
    }

    public String getNome() {

        return nome;
    }

    public ListaDupla<Ligacao> getLigacoes() {

        return ligacoes;
    }

    public void adicionarLigacao(Ligacao ligacao) {

        ligacoes.adicionar(ligacao);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Cidade: " + nome + "\n");

        No<Ligacao> atual = ligacoes.getInicio();

        while (atual != null) {

            sb.append("  ").append(atual.dado.toString()).append("\n");
            
            atual = atual.proximo;
        }
        return sb.toString();
    }
}
