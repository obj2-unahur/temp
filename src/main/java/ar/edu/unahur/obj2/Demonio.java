package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.List;

public abstract class Demonio {

    private int maldad;
    private List<Alma> almasCazadas = new ArrayList<>();

    public Demonio(int maldad) {
        this.maldad = maldad;
    }

    public void cazar(Lugar lugar) {
        lugar.getAlmas().stream().forEach(alma -> cazarOAtormentar(lugar, alma));
    }

    protected void cazarOAtormentar(Lugar lugar, AlmaSimple alma) {
        int almasAtormentadas=0;
        int almasCazadas=0;

        if (alma.getValor() < this.maldad && puedoCazarlo(alma)) {
            almasCazadas++;
            cazar(lugar, alma);
        } else {
            almasAtormentadas++;
            atormentar(alma);
        }
        incrementarNivelMaldad(almasCazadas, almasAtormentadas);
    }

    private void incrementarNivelMaldad(int almasCazadas, int almasAtormentadas) {
        maldad+=almasAtormentadas + 2*almasCazadas;
    }

    protected void cazar(Lugar lugar, Alma alma) {
        lugar.quitarAlma(alma);
        almasCazadas.add(alma);
    }
    protected void atormentarAlma(AlmaSimple alma) {
        alma.setValor(alma.getValor()-5);
        atormentar(alma);
    }

    protected abstract void atormentar(AlmaSimple a);
    protected abstract boolean puedoCazarlo(AlmaSimple a);

}