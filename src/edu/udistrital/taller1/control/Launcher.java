/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.vista.VentanaPrincipal;

/**
 *
 * @author Nath
 */
public class Launcher {
    /**
     * @param args 
     */
    public static void main(String[] args) {
        
        
         javax.swing.SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana    = new VentanaPrincipal();
            ControlVentana controlador = new ControlVentana(ventana);
            ventana.setVisible(true);
        });
    }
}
    

