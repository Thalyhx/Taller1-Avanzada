/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.vista.VentanaPrincipal;
import edu.udistrital.taller1.modelo.Equipo;
import java.util.List;

/**
 * Controlador principal que controla el aplicativo
 * @author sebas, Nath
 */
public class ControlPrincipal {
    
    private VentanaPrincipal ventana;
    private ControlVentana cVentana;
    private ControlEquipo cEquipo;
    private ControlJugador cJugador;
    private ControlJuego cJuego;
    private ControlPanelJuego cPanelJuego;
    private List<Equipo> equiposCargados;


    public ControlPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventana = ventanaPrincipal;
        
        // Inicializar controladores
        this.cEquipo = new ControlEquipo(this);
        this.cJugador = new ControlJugador(this);
        this.cJuego = new ControlJuego(this);
        this.cPanelJuego = new ControlPanelJuego(this);
        

        this.cVentana = new ControlVentana(ventanaPrincipal, this);
    }
    
    // ============ GETTERS ============
    
    public ControlVentana getControlVentana() {
        return cVentana;
    }
    
    public ControlEquipo getControlEquipo() {
        return cEquipo;
    }
    
    public ControlJugador getControlJugador() {
        return cJugador;
    }
    
    public ControlJuego getControlJuego() {
        return cJuego;
    }
    
    public ControlPanelJuego getControlPanelJuego() {
        return cPanelJuego;
    }
    
    public VentanaPrincipal getVentana() {
        return ventana;
    }
    
    public List<Equipo> getEquiposCargados() {
        return equiposCargados;
    }
    
    public void setEquiposCargados(List<Equipo> equipos) {
        this.equiposCargados = equipos;
    }
}
