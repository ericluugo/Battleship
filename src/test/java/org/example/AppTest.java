package org.example;

import junit.framework.TestCase;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.ControladorJugadores;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    ControladorJugadores controladorJugadores;
    JugadorHumano jugadorPrueba;

    @Before
    public void setUp() throws Exception {
        controladorJugadores = ControladorJugadores.getInstancia();
        jugadorPrueba = new JugadorHumano("Alicia","alicia.garciab@alumnos.upm.es","1234567",false);
    }

    public void testCorrecto() throws Exception
    {
        //CP1, nombre 3 caracteres y contrasenia de 6
        jugadorPrueba.setEmail("cristian@alumnos.upm.es");
        jugadorPrueba.setNombreTest("Nom");
        jugadorPrueba.setContraseniaTest("123456");
        assertEquals("cristian@alumnos.upm.es", jugadorPrueba.getEmail());
        assertEquals("Nom", jugadorPrueba.getNombre());
        assertEquals("123456", jugadorPrueba.getContrasenia());

    }
    public void testCorrecto2() throws Exception
    {
        //CP2 nombre de 10 caracteres y contrasenia de 12 
        jugadorPrueba.setNombreTest("Cristian10");
        jugadorPrueba.setEmail("cristian@alumnos.upm.es");
        jugadorPrueba.setContraseniaTest("123456789123");
        assertEquals("cristian@alumnos.upm.es", jugadorPrueba.getEmail());
        assertEquals("Cristian10", jugadorPrueba.getNombre());
        assertEquals("123456789123", jugadorPrueba.getContrasenia());
    }

    public void testEmail() throws Exception {
    //CP3 el email es errorneo
        String emailInvalido = "cristian@gmail.com" ;
        jugadorPrueba.setEmail(emailInvalido); //se tira exception si se comprueba correctamente
    }
    public void testNombre1() throws Exception {
        //CP4 el nombre tiene 2 caracteres
        String nombreInvalido = "yo";
        jugadorPrueba.setNombreTest(nombreInvalido);
    }
    public void testNombre2() throws Exception {
       //CP5 el nombre tiene 11 caracteres
        String nombreInvalido = "Cristian11C";
        jugadorPrueba.setNombreTest(nombreInvalido);
    }
    public void testContrasenia1() throws Exception {
        //CP6 Contrasenia con 5 ca
       String contraseñaInvalida = "12345";
       jugadorPrueba.setContraseniaTest(contraseñaInvalida); // TODO: puede cambiar
    }
    public void testContrasenia2() throws Exception {
        //CP7 Contrasenia con 13 caracteres
        String contraseñaInvalida = "1234567890123";
        jugadorPrueba.setContraseniaTest(contraseñaInvalida);
    }
}

