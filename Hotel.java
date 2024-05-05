package main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Hotel {
    private List<Quarto> quartos;
    private BlockingQueue<Hospede> filaHospedes;
    private BlockingQueue<Quarto> filaQuartosLimpos;
    private BlockingQueue<Quarto> filaChaves;

    public Hotel(int quantidadeQuartos) {
        quartos = new ArrayList<>(quantidadeQuartos);
        for (int i = 0; i < quantidadeQuartos; i++) {
            quartos.add(new Quarto(i + 1));
        }
        filaHospedes = new ArrayBlockingQueue<>(50);
        filaQuartosLimpos = new ArrayBlockingQueue<>(quantidadeQuartos);
        for (Quarto quarto : quartos) {
            filaQuartosLimpos.add(quarto);
        }
        filaChaves = new ArrayBlockingQueue<>(quantidadeQuartos);
        for (Quarto quarto : quartos) {
            filaChaves.add(quarto);
        }
    }

    public synchronized Quarto checkIn(Hospede hospede) throws InterruptedException {
        while (true) {
            for (Quarto quarto : quartos) {
                if (quarto.isVago()) {
                    quarto.setOcupante(hospede);
                    filaChaves.remove(quarto);
                    return quarto;
                }
            }
            wait(); // Sem quartos vagos, aguarda um quarto ficar disponível
        }
    }

    public synchronized void checkOut(Quarto quarto) {
        quarto.limparOcupantes();
        filaQuartosLimpos.add(quarto);
        filaChaves.add(quarto);
        notifyAll(); // Notifica hospedes e camareiras que um quarto está disponível
    }

    public Quarto pegarProximoQuartoLimpo() throws InterruptedException {
        return filaQuartosLimpos.take();
    }

    public Quarto pegarProximaChave() throws InterruptedException {
        return filaChaves.take();
    }

    public void adicionarAFila(Hospede hospede) {
        filaHospedes.add(hospede);
    }

    public Hospede pegarProximoDaFila() throws InterruptedException {
        return filaHospedes.take();
    }
}
