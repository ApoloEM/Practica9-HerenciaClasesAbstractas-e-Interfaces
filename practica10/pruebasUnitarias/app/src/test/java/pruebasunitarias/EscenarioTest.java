package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;

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

    @Test
    void testCargarConfiguracion(@TempDir Path tempDir) throws IOException {
        Path configFile = tempDir.resolve("config_test.txt");
        String fileContent = "Roca 3 4\n" +
                             "Extraterrestre 4 4\n" +
                             "Bomba 4 3 1";
        Files.writeString(configFile, fileContent);

        Escenario escenario = new Escenario("Prueba Carga");
        escenario.cargarConfiguracion(configFile.toString());

        String expectedGrid =
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000R00000\n" +
                "000BE00000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n" +
                "0000000000\n";
        
        assertEquals(expectedGrid, escenario.toString());
    }

    @Test
    void testGuardarConfiguracion(@TempDir Path tempDir) throws IOException {
        Path configFile = tempDir.resolve("config_guardado.txt");
        
        Escenario escenario = new Escenario("Prueba Guardado");
        escenario.addElemento(new Roca(escenario, new Posicion(1, 1)));
        escenario.addElemento(new Bomba(escenario, new Posicion(2, 2), 2));
        
        escenario.guardarConfiguracion(configFile.toString());
        
        String fileContent = Files.readString(configFile);
        
        String expectedContent = "Roca 1 1" + System.lineSeparator() +
                                 "Bomba 2 2 2" + System.lineSeparator();
        
        assertEquals(expectedContent, fileContent);
    }
}