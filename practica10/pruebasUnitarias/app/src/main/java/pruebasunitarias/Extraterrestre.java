package pruebasunitarias;

public class Extraterrestre extends Personaje {

    public Extraterrestre(String nombre, Escenario escenario, Posicion posicion) {
        super(nombre, escenario, posicion);
    }

    public Extraterrestre(Escenario escenario, Posicion posicion) {
        super("Extraterrestre", escenario, posicion);
    }

    @Override
    public String destruir() {
        return "Extraterrestre " + this.getNombre() + " destruido";
    }

    @Override
    public char getRepresentacion() {
        return 'E';
    }
    
    @Override
    public String toFileString() {
        return "Extraterrestre " + posicion.getRenglon() + " " + posicion.getColumna();
    }
}