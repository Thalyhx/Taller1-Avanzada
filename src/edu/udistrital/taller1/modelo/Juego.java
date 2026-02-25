/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.modelo;

import java.util.List;
import edu.udistrital.taller1.modelo.Equipo;

/**
 *
 * @author sebas
 */
public class Juego {
    private int tiempo;
    private List<Equipo> equipos;

    public Juego() {
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Juego{" + "tiempo=" + tiempo + ", equipos=" + equipos + '}';
    } 
}
