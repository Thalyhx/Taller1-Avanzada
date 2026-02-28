/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.udistrital.taller1.control;

/**
 *
 * @author sebas
 */
public class ControlPrincipal {
    private ControlVentana cVentana;
    private ControlEquipo cEquipo;
    private ControlJugador cJugador;
    private ControlJuego cJuego;
    private ControlArchivoAleatorio cArchivoAleatorio;
    private ControlSerializado cSerializado;
    private ControlDesSerializado cDesSerializado;

    public ControlPrincipal() {
        cEquipo = new ControlEquipo(this);
        cJuego = new ControlJuego(this);
        cArchivoAleatorio = new ControlArchivoAleatorio(this);
    }  
}
