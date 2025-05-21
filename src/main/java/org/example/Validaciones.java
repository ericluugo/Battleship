package org.example;

public class Validaciones {


    public static void checkStringInRange(int min, int max, String value, String parameterName) throws Exception {
        if (value == null) {
            throw new Exception("El parámetro '" + parameterName + "' no puede ser nulo.");
        }

        if (value.length() < min || value.length() > max) {
            throw new Exception("El parámetro '" + parameterName + "' debe tener entre " + min + " y " + max + " caracteres.");
        }

    }

}
