/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.vista.VentanaPrincipal;

/**
 *
 * @author sebas
 */
public class ControlVentana {
    
    private VentanaPrincipal ventana;
    
    public ControlVentana(VentanaPrincipal ventana) {
        
        this.ventana = ventana;
        ventana.getBtnSalir().addActionListener(e -> salirDelJuego());
    }
    
    private void salirDelJuego() {
    System.exit(0);
}
}
