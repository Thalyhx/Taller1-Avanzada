/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Clase ControlPanel juego, esta clase maneja la logica del panel*/

package edu.udistrital.taller1.control;

import edu.udistrital.taller1.modelo.Equipo;
import edu.udistrital.taller1.modelo.TipoEmbocada;
import edu.udistrital.taller1.vista.PanelJuego;
import java.util.List;
import javax.swing.Timer;

/**
 * Clase ControlPanelJuego, maneja la logica del panel de juego
 * @author natha
 */
public class ControlPanelJuego {
    
    private ControlPrincipal cPrincipal;
    private PanelJuego panelJuego;
    private List<Equipo> equipos; 
    
    // Variables para controlar el turno
    private int equipoActual;
    private int jugadorActual;
    private int turnoNumero;
    
    // Variables para controlar el tiempo de partida
    private Timer timerJuego;
    private int tiempoRestante;
    private int tiempoPorJugador;
    
    // Estado del juego
    private boolean juegoEnCurso;
    
    // Constructor
    public ControlPanelJuego(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.equipoActual = 0;
        this.jugadorActual = 0;
        this.turnoNumero = 1;
        this.juegoEnCurso = false;
    }
    
    /**
     * Inicia la partida con los equipos precargados
     */
    public void iniciarPartida(PanelJuego panelJuego, List<Equipo> equipos, int tiempoTotalSegundos) {
        this.panelJuego = panelJuego;
        this.equipos = equipos;
        this.tiempoPorJugador = tiempoTotalSegundos / (equipos.size() * 3);
        this.juegoEnCurso = true;
        this.equipoActual = 0;
        this.jugadorActual = 0;
        this.turnoNumero = 1;
       
      
        iniciarTurnoActual();
    }
    
    /**
     * Inicia el turno actual
     */
    private void iniciarTurnoActual() {
        if (equipoActual < equipos.size()) {
            Equipo equipo = equipos.get(this.equipoActual);
            // CORRECCION: Comentado - Equipo no tiene metodo getJugadores()
            // if (jugadorActual < equipoActual.getJugadores().size()) {
            if (jugadorActual < 3) { // Asumiendo 3 jugadores por equipo
                tiempoRestante = tiempoPorJugador;
                
                // Actualizar vista
                // CORRECCION: Metodo actualizarJuego solo recibe 3 parametros, no 4
                panelJuego.actualizarJuego(
                    equipoActual,
                    jugadorActual,
                    tiempoRestante
                );
                
                iniciarTimerJugador();
            }
        } else {
            finalizarPartida();
        }
    }
    
    /**
     * Inicia el timer para el jugador actual
     */
    private void iniciarTimerJugador() {
        if (timerJuego != null) {
            timerJuego.stop();
        }
        
        timerJuego = new Timer(1000, e -> {
            tiempoRestante--;
            panelJuego.actualizarTiempo(tiempoRestante);
            
            if (tiempoRestante <= 0) {
                terminarTurnoJugador();
            }
        });
        timerJuego.start();
    }
    
    /**
     * CORRECCION: Eliminada variable 'embocada' sin definir
     * TODO: Implementar generacion aleatoria de embocadas
     * Genera una embocada aleatoria y suma puntos
     */
    public void registrarIntento() {
    if (!juegoEnCurso) return;
    
    // CORRECCION: Renombrada a 'equipo' para evitar conflicto con 'this.equipoActual'
    Equipo equipo = equipos.get(this.equipoActual);
    
    // CORRECCION: Comentado - Equipo no tiene metodo getJugadores()
    // var jugador = equipo.getJugadores().get(jugadorActual);
    
    // CORRECCION: Comentada logica incompleta
    // TipoEmbocada embocada = TipoEmbocada.alAzar();
    // jugador.agregarPuntos(embocada.getPuntos());
    // jugador.incrementarIntentos();
    // panelJuego.mostrarEmbocada(embocada);
    
    System.out.println("Intento registrado");
    // CORRECCION: Usa 'this.equipoActual' y 'this.jugadorActual' para claridad
    panelJuego.actualizarJuego(this.equipoActual, this.jugadorActual, this.tiempoRestante);
}
    
    /**
     * Termina el turno del jugador actual
     */
    public void terminarTurnoJugador() {
        if (timerJuego != null) {
            timerJuego.stop();
        }
        
        jugadorActual++;
        
        // Pasar al siguiente equipo si este ya termino sus 3 jugadores
        if (jugadorActual >= 3) {
            jugadorActual = 0;
            equipoActual++;
            turnoNumero++;
        }
        
        iniciarTurnoActual();
    }
    
    /**
     * CORRECCION: Agregado metodo finalizarPartida()
     * Finaliza la partida
     */
    private void finalizarPartida() {
        juegoEnCurso = false;
        if (timerJuego != null) {
            timerJuego.stop();
        }
        System.out.println("Partida finalizada");
    }
    
    // CORRECCION: Agregados getters para acceder a variables de turno
    public int getEquipoActual() {
        return equipoActual;
    }
    
    public int getJugadorActual() {
        return jugadorActual;
    }
    
    public int getTurnoNumero() {
        return turnoNumero;
    }
    
    public boolean isJuegoEnCurso() {
        return juegoEnCurso;
    }
}