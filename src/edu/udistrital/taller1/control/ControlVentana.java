/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package edu.udistrital.taller1.control;

import edu.udistrital.taller1.vista.VentanaPrincipal;
import edu.udistrital.taller1.vista.PanelTiempo;
import edu.udistrital.taller1.vista.PanelJuego;
import edu.udistrital.taller1.modelo.Equipo;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * Controlador de la ventana principal
 * Gestiona toda la navegación y listeners de los paneles
 * @author sebas
 */
public class ControlVentana {
    
    private VentanaPrincipal ventana;
    private ControlPrincipal cPrincipal;
    
    public ControlVentana(VentanaPrincipal ventana, ControlPrincipal cPrincipal) {
        this.ventana = ventana;
        this.cPrincipal = cPrincipal;
        conectarListeners();
    }
    
    /**
     * Conecta todos los listeners de los paneles
     */
    private void conectarListeners() {
        // ============ PANEL INICIO ============
        ventana.getPanelInicio().getBtnPrecargarEquipos()
            .addActionListener(e -> cargarEquiposYMostrarTiempo());
        ventana.getPanelInicio().getBtnSalir()
            .addActionListener(e -> salirDelJuego());
        
        // ============ PANEL TIEMPO ============
        ventana.getPanelTiempo().getBtnJugar()
            .addActionListener(e -> iniciarJuego());
        ventana.getPanelTiempo().getBtnRegresar()
            .addActionListener(e -> ventana.mostrarPanel("inicio"));
        
        // ============ PANEL JUEGO ============
        ventana.getPanelJuego().getBtnRegresar()
            .addActionListener(e -> ventana.mostrarPanel("tiempo"));
        ventana.getPanelJuego().getBtnIntento()
            .addActionListener(e -> cPrincipal.getControlPanelJuego().registrarIntento());
        ventana.getPanelJuego().getBtnSiguiente()
            .addActionListener(e -> cPrincipal.getControlPanelJuego().terminarTurnoJugador());
        
        // ============ PANEL RESULTADOS ============
        ventana.getPanelResultados().getBtnSalir()
            .addActionListener(e -> salirDelJuego());
    }
    
    /**
     * ============ NAVEGACIÓN PANEL INICIO ============
     */
    
    /**
     * Carga equipos y muestra panel de tiempo
     */
    private void cargarEquiposYMostrarTiempo() {
        System.out.println("Cargando equipos...");
        
        List<Equipo> equipos = cPrincipal.getControlEquipo().cargarEquiposDesdeArchivo();
        
        if (equipos == null || equipos.isEmpty()) {
            System.err.println("No se cargaron equipos");
            return;
        }
        
        System.out.println("Equipos cargados: " + equipos.size());
        cPrincipal.setEquiposCargados(equipos);
        
        // Mostrar equipos en panel de tiempo
        mostrarEquiposEnPanelTiempo(equipos);
        
        // Cambiar a panel tiempo
        ventana.mostrarPanel("tiempo");
    }
    
    /**
     * Muestra tarjetas de equipos en el panel de tiempo
     */
    private void mostrarEquiposEnPanelTiempo(List<Equipo> equipos) {
        PanelTiempo panelTiempo = ventana.getPanelTiempo();
        panelTiempo.limpiarGrilla();
        
        for (Equipo equipo : equipos) {
            JPanel tarjeta = crearTarjetaEquipo(equipo);
            panelTiempo.agregarTarjetaEquipo(tarjeta);
        }
    }
    
    /**
     * Crea una tarjeta de equipo para mostrar
     */
    private JPanel crearTarjetaEquipo(Equipo equipo) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(java.awt.Color.WHITE);
        tarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        tarjeta.setPreferredSize(new java.awt.Dimension(300, 150));
        
        JLabel lblNombre = new JLabel(equipo.getNombreEquipo());
        lblNombre.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        lblNombre.setAlignmentX(javax.swing.JLabel.CENTER_ALIGNMENT);
        
        JLabel lblProyecto = new JLabel("Proyecto: " + equipo.getNombreProyecto());
        lblProyecto.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        lblProyecto.setAlignmentX(javax.swing.JLabel.CENTER_ALIGNMENT);
        
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(lblNombre);
        tarjeta.add(javax.swing.Box.createVerticalStrut(5));
        tarjeta.add(lblProyecto);
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        
        return tarjeta;
    }
    
    /**
     * ============ NAVEGACIÓN PANEL TIEMPO ============
     */
    
    /**
     * Inicia el juego con el tiempo configurado
     */
    private void iniciarJuego() {
        System.out.println("Iniciando juego...");
        
        int tiempo = ventana.getPanelTiempo().getTiempoIngresado();
        List<Equipo> equipos = cPrincipal.getEquiposCargados();
        
        if (equipos == null || equipos.isEmpty()) {
            System.err.println("No hay equipos cargados");
            return;
        }
        
        PanelJuego panelJuego = ventana.getPanelJuego();
        panelJuego.limpiarGrilla();
        
        // Iniciar la partida
        cPrincipal.getControlPanelJuego().iniciarPartida(panelJuego, equipos, tiempo);
        
        // Cambiar a panel juego
        ventana.mostrarPanel("juego");
    }
    
    /**
     * ============ NAVEGACIÓN PANEL JUEGO ============
     * (Los listeners ya están conectados en conectarListeners())
     */
    
    /**
     * ============ NAVEGACIÓN PANEL RESULTADOS ============
     */
    
    /**
     * Muestra los resultados finales
     */
    public void mostrarResultados(Equipo ganador) {
        ventana.getPanelResultados().limpiarJugadores();
        ventana.getPanelResultados().actualizarResultados(
            ganador.getNombreEquipo(),
            ganador.getPuntajeEquipo()
        );
        
        // Agregar jugadores ganadores
        if (ganador.getJugadoresEquipo() != null) {
            for (Object jugador : ganador.getJugadoresEquipo()) {
                // TODO: Implementar cuando modelo tenga método getNombre()
                ventana.getPanelResultados().agregarJugadorGanador("Jugador");
            }
        }
        
        ventana.mostrarPanel("resultados");
    }
    
    /**
     * ============ ACCIONES GLOBALES ============
     */
    
    /**
     * Sale del juego
     */
    private void salirDelJuego() {
        System.exit(0);
    }
    
    /**
     * Obtiene la ventana principal
     */
    public VentanaPrincipal getVentana() {
        return ventana;
    }
}