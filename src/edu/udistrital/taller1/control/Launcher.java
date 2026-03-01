/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.vista.VentanaPrincipal;
import edu.udistrital.taller1.control.ControlPrincipal;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación
 * @author Nath
 */
public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();

            // Controlador principal (debe encargarse de:
            // 1) escuchar el botón "PRECARGAR EQUIPOS"
            // 2) cargar Equipo.properties y Jugador.properties
            // 3) instanciar los objetos volátiles
            // 4) si la carga es exitosa, pasar a "tiempo"
            ControlPrincipal controlPrincipal = new ControlPrincipal(ventana);

            // Mostrar UI
            ventana.mostrarPanel("inicio");
            ventana.setVisible(true);
        });
    }
}
    

