package pruebasunitarias;

public class Terricola extends Personaje {
    
    public Terricola(String nombre, Escenario escenario, Posicion posicion) {
        super(nombre, escenario, posicion);
    }

    public Terricola(Escenario escenario, Posicion posicion) {
        super("Terricola", escenario, posicion);
    }

    @Override
    public char getRepresentacion() {
        return 'T';
    }

    @Override
    public String destruir() {
        return "Terricola " + this.getNombre() + " destruido";
    }

    @Override
    public String toFileString() {
        return "Terricola " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}