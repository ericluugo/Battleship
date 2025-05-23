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
        jugadorPrueba.setEmail("ruben@alumnos.upm.es");
        jugadorPrueba.setNombre("Ruben19");
        jugadorPrueba.setContrasenia("Choco12!");
        assertEquals("ruben@alumnos.upm.es", jugadorPrueba.getEmail());
        assertEquals("Ruben19", jugadorPrueba.getNombre());
        assertEquals("Choco12!", jugadorPrueba.getContrasenia());
    }

    public void testEmail() throws Exception {
    //CP2 el email es errorneo
        String emailInvalido = "cristian@gmail.com" ;
        jugadorPrueba.setEmail(emailInvalido); //se tira exception si se comprueba correctamente
    }
    public void testNombre1() throws Exception {
        //CP3 el nombre tiene longitud < 3 caracteres
        String nombreInvalido = "yo";
        jugadorPrueba.setNombre(nombreInvalido);
    }
    public void testNombre2() throws Exception {
       //CP4 el nombre tiene longitud > 10 caracteres
        String nombreInvalido = "Cristian19C";
        jugadorPrueba.setNombre(nombreInvalido);
    }
    public void testNombre3() throws Exception {
        //CP5 el nombre esta en lista negra
        String nombreInvalido = "404";
        jugadorPrueba.setNombre(nombreInvalido);
    }
    public void testNombre4() throws Exception {
        //CP6 el nombre tiene un caracter especial
        String nombreInvalido = "Daniel#";
        jugadorPrueba.setNombre(nombreInvalido);
    }

    public void testContrasenia1() throws Exception {
        //CP7 Contrasenia con 5 caracteres
       String contraseniaInvalida = "Choco";
       jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
    public void testContrasenia2() throws Exception {
        //CP8 Contrasenia con 13 caracteres
        String contraseniaInvalida = "Chocolate123!";
        jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
    public void testContrasenia3() throws Exception {
        //CP9 Contrasenia no tiene al menos una minscula
        String contraseniaInvalida = "CHOCO12!";
        jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
    public void testContrasenia4() throws Exception {
        //CP10 Contrasenia no tiene al menos una mayuscula
        String contraseniaInvalida = "choco12!";
        jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
    public void testContrasenia5() throws Exception {
        //CP11 Contrasenia no tiene al menos un numero
        String contraseniaInvalida = "Choco!";
        jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
    public void testContrasenia6() throws Exception {
        //CP12 Contrasenia no tiene al menos una caracter especial
        String contraseniaInvalida = "Choco12";
        jugadorPrueba.setContrasenia(contraseniaInvalida);
    }
}

