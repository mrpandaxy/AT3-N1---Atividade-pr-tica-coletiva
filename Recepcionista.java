
class Recepcionista extends Thread {
    private Hotel hotel;

    public Recepcionista(Hotel hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (true) {
            try {
                Hospede hospede = hotel.pegarProximoDaFila();
                hotel.checkIn(hospede);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}