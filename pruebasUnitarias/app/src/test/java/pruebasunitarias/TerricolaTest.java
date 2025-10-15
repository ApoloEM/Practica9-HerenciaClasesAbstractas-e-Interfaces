package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TerricolaTest {
    @Test
    void testDestruir() {
        Terricola terricola = new Terricola("Ripley", null, null);
        String resultado = terricola.destruir();
        assertEquals("Terricola Ripley destruido", resultado);
    }

    @Test
    void testGetRepresentacion() {
        Terricola terricola = new Terricola("Zoe", null, null);
        char resultado = terricola.getRepresentacion();
        assertEquals('T', resultado);
    }
}