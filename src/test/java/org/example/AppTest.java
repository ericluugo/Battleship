package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.ControladorJugadores;

import javax.management.modelmbean.ModelMBean;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    ControladorJugadores controladorJugadores;

    public AppTest( String testName )
    {
        super( testName );
    }

    public void testCorrecto() throws ModelException
    {
        //CP1, nombre 3 caracteres y contrasenia de 6
        JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "Nom", "123456", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Nom", jugadorHumano.getNombre());
        assertEquals("123456", jugadorHumano.getContrasenia());

    }
    public void testCorrecto2() throws ModelException
    {
        //CP2 nombre de 10 caracteres y contrasenia de 12 
        JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "Cristian10", "123456789123", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Cristian10", jugadorHumano.getNombre());
        assertEquals("123456789123", jugadorHumano.getContrasenia());
    }

    public void testEmail() throws ModelException {
    //CP3 el email es errornero
        String emailInvalido = "cristian@gmail.com" ;
        JugadorHumano jugadorHumano = controladorJugadores.darAlta(emailInvalido, "Nom", "123456", false);
        assertEquals(emailInvalido, jugadorHumano.getEmail());
        assertEquals("Nombrede10", jugadorHumano.getNombre());
        assertEquals("123456", jugadorHumano.getContrasenia());
    }
    public void testNombre1() throws ModelException {
        //CP4 el nombre tiene 2 caracteres
        JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "yo", "123456", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Yo", jugadorHumano.getNombre());
        assertEquals("123456", jugadorHumano.getContrasenia());
    }
    public void testNombre2() throws ModelException {
       //CP5 el nombre tiene 11 caracteres
        JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "Cristian11c", "123456", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Cristian11c", jugadorHumano.getNombre());
        assertEquals("123456", jugadorHumano.getContrasenia());
    }
    public void testContrasenia1() throws ModelException {
        //CP6 Contrasenia con 5 ca
       JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "Nom", "12345", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Yo", jugadorHumano.getNombre());
        assertEquals("123456", jugadorHumano.getContrasenia());
    }
    public void testContrasenia2() throws ModelException {
        //CP7 Contrasenia con 13 caracteres
        JugadorHumano jugadorHumano = controladorJugadores.darAlta("cristian@alumnos.upm.es", "Cristian", "1234567891234", false);
        assertEquals("cristian@alumnos.upm.es", jugadorHumano.getEmail());
        assertEquals("Yo", jugadorHumano.getNombre());
        assertEquals("1234567891234", jugadorHumano.getContrasenia());
    }
}

