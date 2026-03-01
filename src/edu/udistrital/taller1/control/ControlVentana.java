/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.modelo.Equipo;
import edu.udistrital.taller1.util.PropertiesFileLoader;
import edu.udistrital.taller1.vista.PanelJuego;
import edu.udistrital.taller1.vista.PanelTiempo;
import edu.udistrital.taller1.vista.VentanaPrincipal;

import java.util.List;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

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
     * Carga equipos + jugadores desde properties y muestra panel de tiempo
     */
    private void cargarEquiposYMostrarTiempo() {
        try {
            System.out.println("Cargando equipos y jugadores...");

            // Cargar AMBOS archivos (están en la raíz; si no se encuentran, se pedirá escogerlos)
            Properties equipoProps = PropertiesFileLoader.loadOrChoose(ventana, "Equipo.properties");
            Properties jugadorProps = PropertiesFileLoader.loadOrChoose(ventana, "Jugador.properties");

            // Instanciar objetos volátiles (req. 2, 3, 4)
            PrecargaService service = new PrecargaService();
            List<Equipo> equipos = service.cargarEquiposYJugadores(equipoProps, jugadorProps);

            if (equipos == null || equipos.isEmpty()) {
                throw new IllegalStateException("La precarga no generó equipos.");
            }

            System.out.println("Equipos cargados: " + equipos.size());
            cPrincipal.setEquiposCargados(equipos);

            // Mostrar en PanelTiempo
            mostrarEquiposEnPanelTiempo(equipos);

            // Cambiar a panel tiempo
            ventana.mostrarPanel("tiempo");

        } catch (Exception ex) {
            System.err.println("No se cargaron equipos: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    ventana,
                    "No se cargó: " + ex.getMessage(),
                    "Error de precarga",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarEquiposEnPanelTiempo(List<Equipo> equipos) {
        PanelTiempo panelTiempo = ventana.getPanelTiempo();
        panelTiempo.limpiarGrilla();

        for (Equipo equipo : equipos) {
            JPanel tarjeta = crearTarjetaEquipo(equipo);
            panelTiempo.agregarTarjetaEquipo(tarjeta);
        }
    }

    private JPanel crearTarjetaEquipo(Equipo equipo) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(java.awt.Color.WHITE);
        tarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
        tarjeta.setPreferredSize(new java.awt.Dimension(300, 150));

        JLabel lblNombre = new JLabel(equipo.getNombreEquipo());
        lblNombre.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        lblNombre.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel lblProyecto = new JLabel("Proyecto: " + equipo.getNombreProyecto());
        lblProyecto.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        lblProyecto.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(lblNombre);
        tarjeta.add(javax.swing.Box.createVerticalStrut(5));
        tarjeta.add(lblProyecto);
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));

        return tarjeta;
    }

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

        cPrincipal.getControlPanelJuego().iniciarPartida(panelJuego, equipos, tiempo);

        ventana.mostrarPanel("juego");
    }

    public void mostrarResultados(Equipo ganador) {
        ventana.getPanelResultados().limpiarJugadores();
        ventana.getPanelResultados().actualizarResultados(
                ganador.getNombreEquipo(),
                ganador.getPuntajeEquipo()
        );

        if (ganador.getJugadoresEquipo() != null) {
            ganador.getJugadoresEquipo().forEach(j -> ventana.getPanelResultados().agregarJugadorGanador("Jugador"));
        }

        ventana.mostrarPanel("resultados");
    }

    private void salirDelJuego() {
        System.exit(0);
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }
}