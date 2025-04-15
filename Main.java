import javax.swing.JOptionPane;

public class Main {

    static ListaDupla<Cidade> cidades = new ListaDupla<>();

    public static void main(String[] args) {
        while (true) {
            String opcao = JOptionPane.showInputDialog(

                "MENU EXPRESSLINE\n" +

                "1. Cadastrar cidade\n" +

                "2. Cadastrar ligação entre cidades\n" +

                "3. Listar cidades e ligações\n" +

                "4. Verificar ligação direta e tempo\n" +

                "5. Entregas com tempo ≤ X minutos\n" +
                
                "0. Sair"
            );

            if (opcao == null) break;

            switch (opcao) {
                case "1":
                    cadastrarCidade();
                    break;

                case "2":
                    cadastrarLigacao();
                    break;

                case "3":
                    listarTudo();
                    break;

                case "4":
                    verificarLigacao();
                    break;

                case "5":
                    entregasDentroDoTempo();
                    break;

                case "0":
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    static void cadastrarCidade() {
        String nome = JOptionPane.showInputDialog("Nome da cidade:");
        if (buscarCidade(nome) != null) {
            JOptionPane.showMessageDialog(null, "Cidade já cadastrada.");
            return;
        }
        cidades.adicionar(new Cidade(nome));
        JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso.");
    }

    static void cadastrarLigacao() {
        String origem = JOptionPane.showInputDialog("Cidade de origem:");
        String destino = JOptionPane.showInputDialog("Cidade de destino:");
        Cidade cidadeOrigem = buscarCidade(origem);
        if (cidadeOrigem == null) {
            JOptionPane.showMessageDialog(null, "Cidade de origem não encontrada.");
            return;
        }
        double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distância (km):"));
        double trafego = Double.parseDouble(JOptionPane.showInputDialog("Fator de tráfego (0 a 2):"));
        int pedagios = Integer.parseInt(JOptionPane.showInputDialog("Número de pedágios:"));
        cidadeOrigem.adicionarLigacao(new Ligacao(destino, distancia, trafego, pedagios));
        JOptionPane.showMessageDialog(null, "Ligação adicionada.");
    }

    static void listarTudo() {
        StringBuilder sb = new StringBuilder();
        No<Cidade> atual = cidades.getInicio();
        while (atual != null) {
            sb.append(atual.dado.toString()).append("\n");
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhuma cidade cadastrada.");
    }

    static void verificarLigacao() {
        String origem = JOptionPane.showInputDialog("Cidade de origem:");
        String destino = JOptionPane.showInputDialog("Cidade de destino:");
        Cidade cidadeOrigem = buscarCidade(origem);
        if (cidadeOrigem == null) {
            JOptionPane.showMessageDialog(null, "Cidade de origem não encontrada.");
            return;
        }
        No<Ligacao> lig = cidadeOrigem.getLigacoes().getInicio();
        while (lig != null) {
            if (lig.dado.getDestino().equalsIgnoreCase(destino)) {
                JOptionPane.showMessageDialog(null,
                    "Existe ligação direta.\nTempo estimado: " +
                    String.format("%.2f", lig.dado.calcularTempo()) + " minutos.");
                return;
            }
            lig = lig.proximo;
        }
        JOptionPane.showMessageDialog(null, "Não existe ligação direta entre as cidades.");
    }

    static void entregasDentroDoTempo() {
        double limite = Double.parseDouble(JOptionPane.showInputDialog("Tempo limite (em minutos):"));
        StringBuilder sb = new StringBuilder();
        No<Cidade> atual = cidades.getInicio();
        while (atual != null) {
            No<Ligacao> lig = atual.dado.getLigacoes().getInicio();
            while (lig != null) {
                double tempo = lig.dado.calcularTempo();
                if (tempo <= limite) {
                    sb.append(atual.dado.getNome()).append(" → ")
                      .append(lig.dado.getDestino())
                      .append(" (").append(String.format("%.2f", tempo)).append(" min)\n");
                }
                lig = lig.proximo;
            }
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhuma entrega possível nesse tempo.");
    }

    static Cidade buscarCidade(String nome) {
        No<Cidade> atual = cidades.getInicio();
        while (atual != null) {
            if (atual.dado.getNome().equalsIgnoreCase(nome)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }
}