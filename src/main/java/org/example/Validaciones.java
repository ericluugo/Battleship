package org.example;

public class Validaciones {


    public static void checkStringInRange(int min, int max, String value, String parameterName) throws ModelException {
        if (value == null) {
            throw new ModelException("El parámetro '" + parameterName + "' no puede ser nulo.");
        }

        if (value.length() < min || value.length() > max) {
            throw new ModelException("El parámetro '" + parameterName + "' debe tener entre " + min + " y " + max + " caracteres.");
        }

    }


    public static void checkString(Object object, String value) throws ModelException {
        boolean error = object.equals(value);
        if (error) {
            StringBuilder msg = new StringBuilder("Error en el parametro ");
            msg.append(value);
            throw new ModelException(msg.toString());
        }
    }
}
