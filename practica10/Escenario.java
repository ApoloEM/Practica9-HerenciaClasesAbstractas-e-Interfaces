import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Bomba> getBombas() {
        ArrayList<Bomba> bombas = new ArrayList<>();
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (campoDeBatalla[i][j] instanceof Bomba) {
                    bombas.add((Bomba) campoDeBatalla[i][j]);
                }
            }
        }
        return bombas;
    }
    
    private void limpiar() {
        this.campoDeBatalla = new Elemento[TAMANO][TAMANO];
    }

    public void cargarConfiguracion(String nombreArchivo) {
        limpiar();
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            System.out.println("No se encontró el archivo de configuración. Empezando escenario vacío.");
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] tokens = fileScanner.nextLine().split(" ");
                if (tokens.length < 3) continue;

                String tipo = tokens[0];
                int r = Integer.parseInt(tokens[1]);
                int c = Integer.parseInt(tokens[2]);
                Posicion p = new Posicion(r, c);
                Elemento nuevo = null;

                switch (tipo) {
                    case "Roca":
                        nuevo = new Roca(this, p);
                        break;
                    case "Terricola":
                        nuevo = new Terricola(this, p);
                        break;
                    case "Extraterrestre":
                        nuevo = new Extraterrestre(this, p);
                        break;
                    case "Bomba":
                        if (tokens.length > 3) {
                            int radio = Integer.parseInt(tokens[3]);
                            nuevo = new Bomba(this, p, radio);
                        }
                        break;
                }
                if (nuevo != null) {
                    this.addElemento(nuevo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al procesar la línea de configuración: " + e.getMessage());
        }
    }

    public void guardarConfiguracion(String nombreArchivo) {
        try (PrintWriter out = new PrintWriter(nombreArchivo)) {
            for (int i = 0; i < TAMANO; i++) {
                for (int j = 0; j < TAMANO; j++) {
                    if (campoDeBatalla[i][j] != null) {
                        out.println(campoDeBatalla[i][j].toFileString());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
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