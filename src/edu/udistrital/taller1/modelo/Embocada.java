/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.udistrital.taller1.modelo;

/**
 *
 * @author sebas
 */
public enum Embocada {
    SIMPLE(2, "Emboque básico y directo en el primer intento."),
    DOBLE(10, "Consiste en realizar dos giros o movimientos técnicos antes de encajar la pieza."),
    VERTICAL(3, "La técnica estándar donde la pieza sube recta y se encaja."),
    MARIQUITA(4, "Una técnica específica que suele involucrar un giro o balanceo particular."),
    PUÑALADA(5, "Emboque rápido y seco, a menudo realizado de manera directa y contundente."),
    PURTIÑA(6, "Variante técnica tradicional para atrapar la bola."),
    DOMINIO_REVES(8, "Emboque realizado con la mano en posición invertida o girando el palo al revés.");
    
    private final int puntaje;
    private final String descripcion;
    
    // Constructor
    Embocada(int puntaje, String descripcion) {
        this.puntaje = puntaje;
        this.descripcion = descripcion;
    }
    
    // Getters
    public int getPuntaje() {
        return puntaje;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

