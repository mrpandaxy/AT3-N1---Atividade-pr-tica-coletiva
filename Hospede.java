package main;
class Hospede extends Thread {
    private String nome;
    private Hotel hotel;

    public Hospede(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    public void run() {
        try {
            Quarto quarto = hotel.checkIn(this);
            System.out.println(nome + " fez check-in no quarto " + quarto.getNumero());
            Thread.sleep(5000); // Simula estadia no quarto
            hotel.checkOut(quarto);
            System.out.println(nome + " fez check-out do quarto " + quarto.getNumero());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
