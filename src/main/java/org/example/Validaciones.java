package org.example;

import org.example.Logica.ControladorJugadores;

public class Validaciones {


    public static void checkStringInRange(int min, int max, String value, String parameterName) throws Exception {
        if (value == null) {
            throw new Exception("El parámetro '" + parameterName + "' no puede ser nulo.");
        }

        if (value.length() < min || value.length() > max) {
            throw new Exception("El parámetro '" + parameterName + "' debe tener entre " + min + " y " + max + " caracteres.");
        }
    }

    public static void checkNombre(String nombre) throws Exception{
        if (ControladorJugadores.getInstancia().getListaNegra().contains(nombre) || nombre.length() < 3 || nombre.length() >10 || nombre.matches(".*[^a-zA-Z0-9\\s].*")){
            throw new Exception("Error de formato en nombre");
        }
    }

    public static void checkContrasenia(String contrasenia) throws Exception{
        if (contrasenia.length() < 6 || contrasenia.length() > 12 || !contrasenia.matches(".*[a-z].*")
                || !contrasenia.matches(".*[A-Z].*") || !contrasenia.matches(".*[0-9].*") ||!contrasenia.matches(".*[^a-zA-Z0-9].*")){
            throw new Exception("Error de formato en contrasenia");
        }
    }

}
