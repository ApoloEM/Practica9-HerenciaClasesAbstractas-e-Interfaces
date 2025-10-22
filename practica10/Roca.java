public class Roca extends Elemento {
    public Roca(Escenario escenario, Posicion posicion) {
        super(escenario, posicion);
    }

    @Override
    public char getRepresentacion() {
        return 'R';
    }

    @Override
    public String toFileString() {
        return "Roca " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}