import java.util.ArrayList;

public class Escenario {
    private String nombre;
    private Elemento[][] campoDeBatalla;
    private static final int TAMANO = 10;

    public Escenario(String nombre) {
        this.nombre = nombre;
        this.campoDeBatalla = new Elemento[TAMANO][TAMANO];
    }

    public void addElemento(Elemento elemento) {
        Posicion p = elemento.getPosicion();
        if (p.getRenglon() >= 0 && p.getRenglon() < TAMANO && p.getColumna() >= 0 && p.getColumna() < TAMANO) {
            this.campoDeBatalla[p.getRenglon()][p.getColumna()] = elemento;
        }
    }

    public void removeElemento(Elemento elemento) {
        Posicion p = elemento.getPosicion();
        if (p.getRenglon() >= 0 && p.getRenglon() < TAMANO && p.getColumna() >= 0 && p.getColumna() < TAMANO) {
            this.campoDeBatalla[p.getRenglon()][p.getColumna()] = null;
        }
    }

    public void destruirElementos(Posicion centro, int radio) {
        ArrayList<Elemento> aDestruir = new ArrayList<>();
        for (int i = centro.getRenglon() - radio; i <= centro.getRenglon() + radio; i++) {
            for (int j = centro.getColumna() - radio; j <= centro.getColumna() + radio; j++) {
                if (i >= 0 && i < TAMANO && j >= 0 && j < TAMANO && campoDeBatalla[i][j] != null) {
                    aDestruir.add(campoDeBatalla[i][j]);
                }
            }
        }

        for (Elemento elemento : aDestruir) {
            if (elemento instanceof Destruible) {
                Destruible d = (Destruible) elemento;
                System.out.println(d.destruir());
                removeElemento(elemento);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (campoDeBatalla[i][j] == null) {
                    sb.append("0");
                } else {
                    sb.append(campoDeBatalla[i][j].getRepresentacion());
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}