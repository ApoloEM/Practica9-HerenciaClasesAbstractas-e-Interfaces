package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ExtraterrestreTest {
    @Test
    void testDestruir() {
        Extraterrestre alien = new Extraterrestre("Xenomorph", null, null);
        String resultado = alien.destruir();
        assertEquals("Extraterrestre Xenomorph destruido", resultado);
    }

    @Test
    void testGetRepresentacion() {
        Extraterrestre alien = new Extraterrestre("Predator", null, null);
        char resultado = alien.getRepresentacion();
        assertEquals('E', resultado);
    }
}