package org.example;

import junit.framework.TestCase;
import org.example.Dato.Jugadores.JugadorHumano;
import org.example.Logica.ControladorJugadores;
import org.junit.Assert;
import org.junit.Before;
import utilidades.Cifrado;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    ControladorJugadores controladorJugadores;
    JugadorHumano jugadorPrueba;

    @Before
    public void setUp() throws Exception {
        controladorJugadores = ControladorJugadores.getInstancia();
        jugadorPrueba = new JugadorHumano("Alicia", "alicia.garciab@alumnos.upm.es", "Choco12!", false);
    }


    public void testCorrecto() throws Exception {
        //CP1, nombre 3 caracteres y contrasenia de 6
        jugadorPrueba.setEmail("ruben@alumnos.upm.es");
        jugadorPrueba.setNombre("Ruben19");
        jugadorPrueba.setContrasenia("Choco12!");
        assertEquals("ruben@alumnos.upm.es", jugadorPrueba.getEmail());
        assertEquals("Ruben19", jugadorPrueba.getNombre());
        assertEquals(Cifrado.cifrar("Choco12!"), jugadorPrueba.getContrasenia());
    }

    public void testEmail() {
        //CP2 el email es errorneo
        String emailInvalido = "cristian@gmail.com";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setEmail(emailInvalido)); //se tira exception si se comprueba correctamente
    }

    public void testNombre1() {
        //CP3 el nombre tiene longitud < 3 caracteres
        String nombreInvalido = "yo";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setNombre(nombreInvalido));
    }

    public void testNombre2() {
        //CP4 el nombre tiene longitud > 10 caracteres
        String nombreInvalido = "Cristian19C";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setNombre(nombreInvalido));
    }

    public void testNombre3() {
        //CP5 el nombre esta en lista negra
        String nombreInvalido = "404";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setNombre(nombreInvalido));
    }

    public void testNombre4() {
        //CP6 el nombre tiene un caracter especial
        String nombreInvalido = "Daniel#";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setNombre(nombreInvalido));
    }

    public void testContrasenia1() {
        //CP7 Contrasenia con longitud < 6 caracteres
        String contraseniaInvalida = "Ch";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }

    public void testContrasenia2() {
        //CP8 Contrasenia con longitud < 12 caracteres
        String contraseniaInvalida = "Chocolate123!";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }

    public void testContrasenia3() {
        //CP9 Contrasenia no tiene al menos una minscula
        String contraseniaInvalida = "CHOCO12!";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }

    public void testContrasenia4() {
        //CP10 Contrasenia no tiene al menos una mayuscula
        String contraseniaInvalida = "choco12!";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }

    public void testContrasenia5() {
        //CP11 Contrasenia no tiene al menos un numero
        String contraseniaInvalida = "Choco!";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }

    public void testContrasenia6() {
        //CP12 Contrasenia no tiene al menos una caracter especial
        String contraseniaInvalida = "Choco12";
        Assert.assertThrows(Exception.class, () -> jugadorPrueba.setContrasenia(contraseniaInvalida));
    }
}

