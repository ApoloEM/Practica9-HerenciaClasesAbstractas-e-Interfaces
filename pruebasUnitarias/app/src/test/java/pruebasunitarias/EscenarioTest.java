package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EscenarioTest {
    @Test
    void testAddElemento() {
        Escenario escenario = new Escenario("Prueba");
        Roca roca = new Roca(escenario, new Posicion(2, 3));
        escenario.addElemento(roca);

        String expectedGrid =
                "0000000000\n" +
                "0000000000\n" +
                "000R000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n";

        assertEquals(expectedGrid, escenario.toString());
    }

    @Test
    void testDestruirElementos() {
        Escenario escenario = new Escenario("Prueba de Destruccion");
        escenario.addElemento(new Terricola("Destruible", escenario, new Posicion(5, 5)));
        escenario.addElemento(new Roca(escenario, new Posicion(5, 6)));

        escenario.destruirElementos(new Posicion(5, 5), 1);

        String expectedGrid =
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "000000R000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n";
        
        assertEquals(expectedGrid, escenario.toString());
    }

    @Test
    void testRemoveElemento() {
        Escenario escenario = new Escenario("Prueba de Borrado");
        Roca roca = new Roca(escenario, new Posicion(8, 8));
        escenario.addElemento(roca);
        escenario.removeElemento(roca);

        String expectedGrid =
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n";
        
        assertEquals(expectedGrid, escenario.toString());
    }

    @Test
    void testToString() {
        Escenario escenario = new Escenario("Prueba de String");
        escenario.addElemento(new Bomba(escenario, new Posicion(0, 0), 1));
        escenario.addElemento(new Terricola("Test", escenario, new Posicion(9, 9)));

        String expectedGrid =
                "B000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "000000000T\n";

        assertEquals(expectedGrid, escenario.toString());
    }
}