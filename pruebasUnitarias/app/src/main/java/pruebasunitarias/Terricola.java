package pruebasunitarias;

public class Terricola extends Personaje {
    public Terricola(String nombre, Escenario escenario, Posicion posicion) {
        super(nombre, escenario, posicion);
    }

    @Override
    public char getRepresentacion() {
        return 'T';
    }

    @Override
    public String destruir() {
        return "Terricola " + this.getNombre() + " destruido";
    }
}