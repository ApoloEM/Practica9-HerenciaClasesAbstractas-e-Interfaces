package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BombaTest {

    @Test
    void testDestruir() {
        Bomba bomba = new Bomba(null, new Posicion(1, 1), 1);
        String resultado = bomba.destruir();
        assertEquals("Bomba destruida", resultado);
    }

    @Test
    void testExplotar() {
        Escenario escenario = new Escenario("Campo de Pruebas");
        Posicion posicionBomba = new Posicion(4, 4);
        Bomba bomba = new Bomba(escenario, posicionBomba, 1);
        escenario.addElemento(bomba);
        escenario.addElemento(new Extraterrestre("Alien", escenario, new Posicion(4, 5)));
        escenario.addElemento(new Terricola("Ripley", escenario, new Posicion(0, 0)));

        bomba.explotar();

        String escenarioEsperado =
                "T000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n";

        assertEquals(escenarioEsperado, escenario.toString());
    }

    @Test
    void testGetRepresentacion() {
        Bomba bomba = new Bomba(null, new Posicion(0, 0), 1);
        char resultado = bomba.getRepresentacion();
        assertEquals('B', resultado);
    }
}