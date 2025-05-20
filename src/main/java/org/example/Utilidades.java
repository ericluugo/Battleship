package org.example;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Utilidades {

    private static final Scanner teclado = new Scanner(System.in);

    public static int lecturaCoordenada(String mensaje) {
        boolean correcto = false;
        int coordenada = -1;

        while (!correcto) {
            System.out.println(mensaje);
            try {
                coordenada = teclado.nextInt();
                if (coordenada >= 0 && coordenada <= 9) {
                    correcto = true;
                } else {
                    System.out.println("Introduzca una coordenada entre 0 y 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduzca un número.");
                teclado.next(); // limpia el valor incorrecto del buffer
            }
        }
        return coordenada;
    }

    public static String lecturaDecision(String mensaje) {
        boolean correcto = false;
        String respuesta = "";

        while (!correcto) {
            System.out.println(mensaje);
            try {
                respuesta = teclado.nextLine().trim();

                if (respuesta.equalsIgnoreCase("Si") ||
                        respuesta.equalsIgnoreCase("Sí") ||
                        respuesta.equalsIgnoreCase("No")) {
                    correcto = true;
                } else {
                    System.out.println("Escriba una respuesta válida (Si o No).");
                }

            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Error al leer la entrada");
            }
        }

        return respuesta;
    }
    public static String leerCadena(String s){
        Scanner keyboard = new Scanner(System.in);
        String string = "";
        do {
           System.out.println(s);
            string = keyboard.nextLine();
        } while (string==null);


        return string;
    }

    public static int leerNumeroIntervalo(String mensaje,int min, int max) {
        boolean correcto = false;
        int numero = 0;
        while (!correcto) {
            System.out.print(mensaje);
            try {
                numero = teclado.nextInt();
                if (numero >= min && numero <= max) {
                    correcto = true;
                } else {
                    System.out.println("Introduzca una opcion valida...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduzca un número.");
                teclado.next(); // limpia el valor incorrecto del buffer
            }
        }
        return numero;
    }
}
