public class SimulacaoHotel {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);

        // Cria hóspedes
        for (int i = 0; i < 50; i++) {
            Hospede hospede = new Hospede("Hóspede " + (i + 1), hotel);
            hotel.adicionarAFila(hospede);
        }

        // Cria recepcionistas
        for (int i = 0; i < 5; i++) {
            Recepcionista recepcionista = new Recepcionista(hotel);
            recepcionista.start();
        }

        // Cria camareiras
        for (int i = 0; i < 10; i++) {
            Camareira camareira = new Camareira(hotel);
            camareira.start();
        }

        // Inicia simulação
        for (int i = 0; i < 50; i++) {
            try {
                hotel.pegarProximoDaFila().start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
