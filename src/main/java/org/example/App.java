package org.example;

import org.example.Vistas.VistaGeneral;

public class App {
    public static void main(String[] args) {
        try {
            VistaGeneral vistaGeneral = VistaGeneral.getInstancia();
            vistaGeneral.programa();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }


}
