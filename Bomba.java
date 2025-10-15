public class Bomba extends Elemento implements Destruible {
    private int radio;

    public Bomba(Escenario escenario, Posicion posicion, int radio) {
        super(escenario, posicion);
        this.radio = radio;
    }

    public void explotar() {
        System.out.println("Explotando bomba!!");
        this.escenario.destruirElementos(this.posicion, this.radio);
    }

    @Override
    public String destruir() {
        return "Bomba destruida";
    }

    @Override
    public char getRepresentacion() {
        return 'B';
    }
}