public class Ligacao {
    private String destino;

    private double distancia;

    private double fatorTrafego;

    private int pedagios;

    public Ligacao(String destino, double distancia, double fatorTrafego, int pedagios) {

        this.destino = destino;

        this.distancia = distancia;

        this.fatorTrafego = fatorTrafego;

        this.pedagios = pedagios;
    }

    public String getDestino() {

        return destino;
    }

    public double calcularTempo() {

        return (distancia * fatorTrafego) + (pedagios * 2);
    }

    @Override
    public String toString() {

        return "→ " + destino + " | Distância: " + distancia +

               " | Tráfego: " + fatorTrafego + 

               " | Pedágios: " + pedagios +
               
               " | Tempo: " + String.format("%.2f", calcularTempo()) + " min";
    }
}
