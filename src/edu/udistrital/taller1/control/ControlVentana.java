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
import edu.udistrital.taller1.modelo.Jugador;
import edu.udistrital.taller1.modelo.TipoEmbocada;
import edu.udistrital.taller1.util.PropertiesFileLoader;
import edu.udistrital.taller1.vista.PanelEquipo;
import edu.udistrital.taller1.vista.PanelJuego;
import edu.udistrital.taller1.vista.PanelTiempo;
import edu.udistrital.taller1.vista.VentanaPrincipal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ControlVentana {

    private final VentanaPrincipal ventana;
    private final ControlPrincipal cPrincipal;

    // ===== Estado del juego (antes estaba en ControlPanelJuego) =====
    private PanelJuego panelJuego;
    private List<Equipo> equipos;
    private final List<PanelEquipo> uiEquipos = new ArrayList<>();

    private int equipoActual;
    private int jugadorActual;

    private Timer timerJugador;
    private int tiempoRestanteJugador;
    private int tiempoPorJugador;

    private boolean juegoEnCurso;
    private final Random rnd = new Random();

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

        // INTENTO: avanza jugador/equipo
        ventana.getPanelJuego().getBtnIntento()
                .addActionListener(e -> registrarIntento());

        // SIGUIENTE: solo al final -> resultados
        ventana.getPanelJuego().getBtnSiguiente()
                .addActionListener(e -> finalizarYMostrarResultados());

        // ============ PANEL RESULTADOS ============
        ventana.getPanelResultados().getBtnSalir()
                .addActionListener(e -> salirDelJuego());
    }

    // ===================== PRECARGA / PANEL TIEMPO =====================

    private void cargarEquiposYMostrarTiempo() {
        try {
            Properties equipoProps = PropertiesFileLoader.loadOrChoose(ventana, "Equipo.properties");
            Properties jugadorProps = PropertiesFileLoader.loadOrChoose(ventana, "Jugador.properties");

            PrecargaService service = new PrecargaService();
            List<Equipo> equipos = service.cargarEquiposYJugadores(equipoProps, jugadorProps);

            if (equipos == null || equipos.isEmpty()) {
                throw new IllegalStateException("La precarga no generó equipos.");
            }

            cPrincipal.setEquiposCargados(equipos);
            mostrarEquiposEnPanelTiempo(equipos);
            ventana.mostrarPanel("tiempo");

        } catch (Exception ex) {
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
            JPanel tarjeta = crearTarjetaEquipoTiempo(equipo);
            panelTiempo.agregarTarjetaEquipo(tarjeta);
        }
    }

    private JPanel crearTarjetaEquipoTiempo(Equipo equipo) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new java.awt.Color(245, 245, 245));
        tarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 4));
        tarjeta.setPreferredSize(new java.awt.Dimension(420, 180));

        JLabel lblNombre = new JLabel(equipo.getNombreEquipo());
        lblNombre.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 26));
        lblNombre.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel lblProyecto = new JLabel("Proyecto: " + equipo.getNombreProyecto());
        lblProyecto.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        lblProyecto.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(lblNombre);
        tarjeta.add(javax.swing.Box.createVerticalStrut(5));
        tarjeta.add(lblProyecto);
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));

        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
        panelJugadores.setOpaque(false);
        panelJugadores.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        if (equipo.getJugadoresEquipo() != null && !equipo.getJugadoresEquipo().isEmpty()) {
            equipo.getJugadoresEquipo().forEach(j -> {
                JLabel lblJugador = new JLabel("•  " + j.getNombreJugador());
                lblJugador.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                lblJugador.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 20, 2, 10));
                panelJugadores.add(lblJugador);
            });
        } else {
            JLabel lblSin = new JLabel("•  (sin jugadores cargados)");
            lblSin.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
            lblSin.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 20, 2, 10));
            panelJugadores.add(lblSin);
        }

        tarjeta.add(panelJugadores);
        tarjeta.add(javax.swing.Box.createVerticalGlue());
        return tarjeta;
    }

    // ===================== INICIAR JUEGO =====================

    private void iniciarJuego() {
        int tiempoPorEquipo = ventana.getPanelTiempo().getTiempoIngresado();
        List<Equipo> equipos = cPrincipal.getEquiposCargados();

        if (equipos == null || equipos.isEmpty()) {
            JOptionPane.showMessageDialog(ventana, "No hay equipos cargados", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tiempoPorEquipo <= 0) {
            JOptionPane.showMessageDialog(ventana, "El tiempo debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.panelJuego = ventana.getPanelJuego();
        this.equipos = equipos;

        // Estado inicial
        this.equipoActual = 0;
        this.jugadorActual = 0;
        this.juegoEnCurso = true;

        // Req: tiempoPorEquipo / 3 por jugador
        this.tiempoPorJugador = Math.max(1, tiempoPorEquipo / 3);

        construirGrillaEquiposJuego();

        panelJuego.setSiguienteHabilitado(false);
        panelJuego.setIntentoHabilitado(true);

        ventana.mostrarPanel("juego");
        iniciarTurnoActual();
    }

    private void construirGrillaEquiposJuego() {
        panelJuego.limpiarGrilla();
        uiEquipos.clear();

        panelJuego.configurarGrilla(equipos.size());

        Icon iconoJugador = UIManager.getIcon("OptionPane.informationIcon"); // imagen estándar

        for (Equipo eq : equipos) {
            PanelEquipo pe = new PanelEquipo(eq, iconoJugador);
            uiEquipos.add(pe);
            panelJuego.agregarTarjetaEquipo(pe);
        }
    }

    private void iniciarTurnoActual() {
        if (!juegoEnCurso) return;

        if (equipoActual >= equipos.size()) {
            finalizarPartida();
            return;
        }

        Equipo eq = equipos.get(equipoActual);
        if (eq.getJugadoresEquipo() == null || eq.getJugadoresEquipo().isEmpty()) {
            equipoActual++;
            jugadorActual = 0;
            iniciarTurnoActual();
            return;
        }

        if (jugadorActual >= eq.getJugadoresEquipo().size()) {
            equipoActual++;
            jugadorActual = 0;
            iniciarTurnoActual();
            return;
        }

        refrescarResaltadoTurno();

        tiempoRestanteJugador = tiempoPorJugador;
        panelJuego.actualizarJuego(equipoActual, jugadorActual, tiempoRestanteJugador);

        iniciarTimerJugador();
    }

    private void refrescarResaltadoTurno() {
        for (int i = 0; i < uiEquipos.size(); i++) {
            PanelEquipo comp = uiEquipos.get(i);
            boolean activo = (i == equipoActual);
            comp.setActivo(activo);
            comp.setJugadorEnTurno(activo ? jugadorActual : -1);
        }
    }

    private void iniciarTimerJugador() {
        if (timerJugador != null) timerJugador.stop();

        timerJugador = new Timer(1000, e -> {
            tiempoRestanteJugador--;
            panelJuego.actualizarTiempo(tiempoRestanteJugador);

            if (tiempoRestanteJugador <= 0) {
                avanzarTurno();
            }
        });
        timerJugador.start();
    }

    // ===================== BOTÓN INTENTO =====================

    private void registrarIntento() {
        if (!juegoEnCurso) return;

        Equipo eq = equipos.get(equipoActual);
        Jugador jug = eq.getJugadoresEquipo().get(jugadorActual);

        // contar intento
        jug.setIntentosJugador(jug.getIntentosJugador() + 1);
        eq.setIntentosEquipo(eq.getIntentosEquipo() + 1);

        // embocada aleatoria (con opción sin embocada)
        boolean emboca = rnd.nextBoolean();
        int puntos = 0;
        String nombreEmbocada = "SIN_EMBOCADA";

        if (emboca) {
            TipoEmbocada[] vals = TipoEmbocada.values();
            TipoEmbocada t = vals[rnd.nextInt(vals.length)];
            nombreEmbocada = t.name();

            puntos = switch (t) {
                case SIMPLE -> 10;
                case DOBLE -> 20;
                case VERTICAL -> 30;
                case MARIQUITA -> 40;
                case PUÑALADA -> 50;
                case PURTIÑA -> 60;
                case DOMINIO_REVES -> 70;
            };

            jug.setPuntosJugador(jug.getPuntosJugador() + puntos);
            eq.setPuntajeEquipo(eq.getPuntajeEquipo() + puntos);
        }

        // actualizar UI
        uiEquipos.get(equipoActual).actualizarPuntosJugador(jugadorActual, jug.getPuntosJugador());
        uiEquipos.get(equipoActual).actualizarPuntajeEquipo(eq.getPuntajeEquipo());
        panelJuego.mostrarEmbocada(nombreEmbocada, puntos);

        // avanzar a siguiente jugador/equipo
        avanzarTurno();
    }

    private void avanzarTurno() {
        if (timerJugador != null) timerJugador.stop();

        jugadorActual++;

        if (jugadorActual >= equipos.get(equipoActual).getJugadoresEquipo().size()) {
            jugadorActual = 0;
            equipoActual++;
        }

        iniciarTurnoActual();
    }

    // ===================== FIN / SIGUIENTE =====================

    private void finalizarPartida() {
        juegoEnCurso = false;
        if (timerJugador != null) timerJugador.stop();

        panelJuego.setIntentoHabilitado(false);
        panelJuego.setSiguienteHabilitado(true);

        for (PanelEquipo comp : uiEquipos) {
            comp.setActivo(false);
            comp.setJugadorEnTurno(-1);
        }

        panelJuego.mostrarEmbocada("FIN", 0);
    }

    private void finalizarYMostrarResultados() {
        if (juegoEnCurso) return; // no debe pasar si no ha terminado

        Equipo ganador = calcularGanador();
        mostrarResultados(ganador);
    }

    private Equipo calcularGanador() {
        Equipo mejor = null;
        for (Equipo e : equipos) {
            if (mejor == null) {
                mejor = e;
                continue;
            }
            if (e.getPuntajeEquipo() > mejor.getPuntajeEquipo()) {
                mejor = e;
            } else if (e.getPuntajeEquipo() == mejor.getPuntajeEquipo()) {
                if (e.getIntentosEquipo() > mejor.getIntentosEquipo()) {
                    mejor = e;
                }
            }
        }
        return mejor;
    }

    // ===================== RESULTADOS =====================

    public void mostrarResultados(Equipo ganador) {
        ventana.getPanelResultados().limpiarJugadores();
        ventana.getPanelResultados().actualizarResultados(
                ganador.getNombreEquipo(),
                ganador.getPuntajeEquipo()
        );

        if (ganador.getJugadoresEquipo() != null) {
            ganador.getJugadoresEquipo().forEach(j -> ventana.getPanelResultados().agregarJugadorGanador(j.getNombreJugador()));
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