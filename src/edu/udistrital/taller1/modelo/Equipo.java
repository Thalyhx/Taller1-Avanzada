/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.modelo;

import java.util.List;
import edu.udistrital.taller1.modelo.Jugador;

/**
 *
 * @author sebas
 */
public class Equipo {
    private int codigoEquipo;
    private String nombreEquipo;
    private List<Jugador> jugadoresEquipo;
    private int puntajeEquipo;
    private int intentosEquipo;

    public Equipo() {
    }

    public int getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(int codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<Jugador> getJugadoresEquipo() {
        return jugadoresEquipo;
    }

    public void setJugadoresEquipo(List<Jugador> jugadoresEquipo) {
        this.jugadoresEquipo = jugadoresEquipo;
    }

    public int getPuntajeEquipo() {
        return puntajeEquipo;
    }

    public void setPuntajeEquipo(int puntajeEquipo) {
        this.puntajeEquipo = puntajeEquipo;
    }

    public int getIntentosEquipo() {
        return intentosEquipo;
    }

    public void setIntentosEquipo(int intentosEquipo) {
        this.intentosEquipo = intentosEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" + "codigoEquipo=" + codigoEquipo + ", nombreEquipo=" + nombreEquipo + ", jugadoresEquipo=" + jugadoresEquipo + ", puntajeEquipo=" + puntajeEquipo + ", intentosEquipo=" + intentosEquipo + '}';
    }
}
