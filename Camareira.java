
class Camareira extends Thread {
    private Hotel hotel;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (true) {
            try {
                Quarto quarto = hotel.pegarProximoQuartoLimpo();
                limparQuarto(quarto);
                hotel.checkOut(quarto); // Após limpar, quarto está disponível para check-in
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void limparQuarto(Quarto quarto) throws InterruptedException {
        System.out.println("Limpando quarto " + quarto.getNumero());
        Thread.sleep(3000); // Simula tempo de limpeza
        System.out.println("Quarto " + quarto.getNumero() + " limpo");
    }
}