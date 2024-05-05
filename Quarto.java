package main;
import java.util.ArrayList;
import java.util.List;

class Quarto {
    private int numero;
    private List<Hospede> ocupantes;

    public Quarto(int numero) {
        this.numero = numero;
        ocupantes = new ArrayList<>();
    }

    public boolean isVago() {
        return ocupantes.isEmpty();
    }

    public boolean estaCheio() {
        return ocupantes.size() >= 4;
    }

    public void setOcupante(Hospede hospede) {
        ocupantes.add(hospede);
    }

    public void limparOcupantes() {
        ocupantes.clear();
    }

    public int getNumero() {
        return numero;
    }
}
