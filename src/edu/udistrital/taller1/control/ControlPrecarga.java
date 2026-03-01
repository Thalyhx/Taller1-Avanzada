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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControlPrecarga {

    public List<Equipo> cargarEquiposYJugadores(Properties equipoProps, Properties jugadorProps) {
        int cantidadEquipos = parseIntRequired(equipoProps, "cantidadEquipos");
        int cantidadJugadoresPorEquipo = parseIntRequired(equipoProps, "cantidadJugadores");

        int cantidadEquiposJ = parseIntRequired(jugadorProps, "cantidadEquipos");
        int cantidadJugadoresJ = parseIntRequired(jugadorProps, "cantidadJugadores");

        if (cantidadEquipos != cantidadEquiposJ) {
            throw new IllegalStateException("cantidadEquipos no coincide entre archivos");
        }
        if (cantidadJugadoresPorEquipo != cantidadJugadoresJ) {
            throw new IllegalStateException("cantidadJugadores no coincide entre archivos");
        }

        List<Equipo> equipos = new ArrayList<>();

        for (int e = 1; e <= cantidadEquipos; e++) {
            String proyecto = required(equipoProps, "equipo." + e + ".proyecto");
            String nombreEquipo = required(equipoProps, "equipo." + e + ".nombre");

            Equipo equipo = new Equipo(proyecto, nombreEquipo);

            List<Jugador> jugadores = new ArrayList<>();
            for (int j = 1; j <= cantidadJugadoresPorEquipo; j++) {
                String nombreJugador = required(jugadorProps, "equipo." + e + ".jugador." + j + ".nombre");
                String codigoStr = required(jugadorProps, "equipo." + e + ".jugador." + j + ".codigo");

                long codigo;
                try {
                    // CAMBIO: ahora se parsea como long (Jugadores usan long en el modelo)
                    codigo = Long.parseLong(codigoStr.trim());
                } catch (NumberFormatException ex) {
                    throw new IllegalStateException(
                            "Código inválido: " + codigoStr + " (equipo " + e + ", jugador " + j + ")"
                    );
                }

                jugadores.add(new Jugador(codigo, nombreJugador));
            }

            equipo.setJugadoresEquipo(jugadores);
            equipo.setPuntajeEquipo(0);
            equipo.setIntentosEquipo(0);

            equipos.add(equipo);
        }

        return equipos;
    }

    private static String required(Properties p, String key) {
        String v = p.getProperty(key);
        if (v == null || v.isBlank()) throw new IllegalStateException("Falta clave requerida: " + key);
        return v;
    }

    private static int parseIntRequired(Properties p, String key) {
        String v = required(p, key);
        try {
            // Estos siguen siendo int porque son cantidades (no códigos)
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalStateException("Entero inválido en " + key + ": " + v);
        }
    }
}
