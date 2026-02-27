/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.modelo.Jugador;

/**
 *
 * @author sebas
 */
public class ControlJugador {
        /**
     * Crea un nuevo jugador
     */
    public Jugador crearJugador(int codigo, String nombre) {

        Jugador jugador = new Jugador();
        jugador.setCodigoJugador(codigo);
        jugador.setNombreJugador(nombre);
        jugador.setPuntosJugador(0);
        jugador.setIntentosJugador(0);

        return jugador;
    }

    /**
     * Registrar un intento del jugador
     */
    public void registrarIntento(Jugador jugador) {

        int intentos = jugador.getIntentosJugador();
        jugador.setIntentosJugador(intentos + 1);
    }

    /**
     * Sumar puntos al jugador
     */
    public void sumarPuntos(Jugador jugador, int puntos) {

        int puntosActuales = jugador.getPuntosJugador();
        jugador.setPuntosJugador(puntosActuales + puntos);
    }

    /**
     * Reiniciar estadísticas del jugador
     */
    public void reiniciarJugador(Jugador jugador) {

        jugador.setPuntosJugador(0);
        jugador.setIntentosJugador(0);
    }

    /**
     * Obtener resumen del jugador
     */
    public String mostrarJugador(Jugador jugador) {
        return jugador.toString();
    }
}
