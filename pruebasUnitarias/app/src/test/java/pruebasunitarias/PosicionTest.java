package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PosicionTest {
    @Test
    void testGetColumna() {
        Posicion posicion = new Posicion(5, 8);
        int columnaActual = posicion.getColumna();
        assertEquals(8, columnaActual);
    }

    @Test
    void testGetRenglon() {
        Posicion posicion = new Posicion(5, 8);
        int renglonActual = posicion.getRenglon();
        assertEquals(5, renglonActual);
    }
}
