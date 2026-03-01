/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.modelo;

/**
 *
 * @author sebas
 */
public class Jugador {
    private long codigoJugador;
    private String nombreJugador;
    private int puntosJugador;
    private int intentosJugador;

    public Jugador() {
    }

    public Jugador(long codigoJugador, String nombreJugador) {
        this.codigoJugador = codigoJugador;
        this.nombreJugador = nombreJugador;
    }
    
    public long getCodigoJugador() {
        return codigoJugador;
    }

    public void setCodigoJugador(long codigoJugador) {
        this.codigoJugador = codigoJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }

    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }

    public int getIntentosJugador() {
        return intentosJugador;
    }

    public void setIntentosJugador(int intentosJugador) {
        this.intentosJugador = intentosJugador;
    }

    @Override
    public String toString() {
        return "Jugador: {" + "codigoJugador=" + codigoJugador + ", nombreJugador=" + nombreJugador + ", puntosJugador=" + puntosJugador + ", intentosJugador=" + intentosJugador + '}';
    }    
}
