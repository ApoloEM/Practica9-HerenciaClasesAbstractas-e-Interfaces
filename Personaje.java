public abstract class Personaje extends Elemento implements Destruible {
    protected String nombre;
    protected int puntosDeVida;

    public Personaje(String nombre, Escenario escenario, Posicion posicion) {
        super(escenario, posicion);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public abstract String destruir();
}