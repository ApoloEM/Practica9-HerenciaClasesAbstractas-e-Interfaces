package pruebasunitarias;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MisionPosibleMain {
    
    private static final String NOMBRE_ARCHIVO = "config.txt";

    public static void main(String[] args) {
        try (Scanner scannerTeclado = new Scanner(System.in)) {
            Escenario escenario = new Escenario("Nostromo");
            
            System.out.println("--- Cargando configuración ---");
            escenario.cargarConfiguracion(NOMBRE_ARCHIVO);
            
            System.out.println("--- Estado Inicial del Escenario ---");
            System.out.println(escenario);

            ArrayList<Bomba> bombas = escenario.getBombas();
            
            if (bombas.isEmpty()) {
                System.out.println("No hay bombas en el escenario. Terminando simulación.");
            } else {
                detonarBomba(scannerTeclado, bombas);
                System.out.println("--- Estado Final del Escenario ---");
                System.out.println(escenario);
            }
            
            System.out.println("--- Guardando configuración ---");
            escenario.guardarConfiguracion(NOMBRE_ARCHIVO);
            System.out.println("Configuración guardada en " + NOMBRE_ARCHIVO);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }

    private static void detonarBomba(Scanner scanner, ArrayList<Bomba> bombas) {
        System.out.println("Bombas disponibles para detonar:");
        for (int i = 0; i < bombas.size(); i++) {
            Posicion p = bombas.get(i).getPosicion();
            System.out.printf("%d. Bomba en (%d, %d)\n", (i + 1), p.getRenglon(), p.getColumna());
        }

        int seleccion = -1;
        while (seleccion < 1 || seleccion > bombas.size()) {
            System.out.print("Seleccione el número de la bomba (1-" + bombas.size() + "): ");
            try {
                seleccion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
        
        bombas.get(seleccion - 1).explotar();
    }
}