package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class RocaTest {
    @Test
    void testGetRepresentacion() {
        Roca roca = new Roca(null, null);
        char resultado = roca.getRepresentacion();
        assertEquals('R', resultado);
    }
}