/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import edu.udistrital.taller1.modelo.Equipo;
import edu.udistrital.taller1.modelo.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelEquipo extends JPanel {

    private final JLabel lblNombreEquipo;
    private final JLabel lblPuntajeEquipo;
    private final JLabel lblIntentosEquipo; // NUEVO

    private final JPanel panelJugadores;
    private final List<PanelJugador> panelesJugadores = new ArrayList<>();

    private final Color colorActivo = new Color(160, 180, 230);
    private final Color colorInactivo = new Color(235, 235, 235);

    public PanelEquipo(Equipo equipo, Icon iconoJugador) {
        setLayout(new BorderLayout(10, 10));
        setOpaque(true);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        setPreferredSize(new Dimension(300, 170));

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        lblNombreEquipo = new JLabel(equipo.getNombreEquipo());
        lblNombreEquipo.setFont(new Font("Arial", Font.BOLD, 14));

        lblPuntajeEquipo = new JLabel("Puntuación equipo: " + equipo.getPuntajeEquipo());
        lblPuntajeEquipo.setFont(new Font("Arial", Font.PLAIN, 12));

        // NUEVO
        lblIntentosEquipo = new JLabel("Intentos equipo: " + equipo.getIntentosEquipo());
        lblIntentosEquipo.setFont(new Font("Arial", Font.PLAIN, 12));

        header.add(lblNombreEquipo);
        header.add(Box.createVerticalStrut(4));
        header.add(lblPuntajeEquipo);
        header.add(Box.createVerticalStrut(2));
        header.add(lblIntentosEquipo);

        add(header, BorderLayout.NORTH);

        panelJugadores = new JPanel(new GridLayout(1, 3, 10, 0));
        panelJugadores.setOpaque(false);

        if (equipo.getJugadoresEquipo() != null) {
            for (Jugador j : equipo.getJugadoresEquipo()) {
                PanelJugador pj = new PanelJugador(j, iconoJugador);
                panelesJugadores.add(pj);
                panelJugadores.add(pj);
            }
        }

        add(panelJugadores, BorderLayout.CENTER);

        setActivo(false);
        setJugadorEnTurno(-1);
    }

    public void setActivo(boolean activo) {
        setBackground(activo ? colorActivo : colorInactivo);
        repaint();
    }

    public void setJugadorEnTurno(int idxJugador) {
        for (int i = 0; i < panelesJugadores.size(); i++) {
            panelesJugadores.get(i).setEnTurno(i == idxJugador);
        }
    }

    public void actualizarPuntajeEquipo(int puntos) {
        lblPuntajeEquipo.setText("Puntuación equipo: " + puntos);
    }

    // NUEVO
    public void actualizarIntentosEquipo(int intentos) {
        lblIntentosEquipo.setText("Intentos equipo: " + intentos);
    }

    public void actualizarPuntosJugador(int idxJugador, int puntosJugador) {
        if (idxJugador >= 0 && idxJugador < panelesJugadores.size()) {
            panelesJugadores.get(idxJugador).actualizarPuntos(puntosJugador);
        }
    }

    // NUEVO
    public void actualizarIntentosJugador(int idxJugador, int intentosJugador) {
        if (idxJugador >= 0 && idxJugador < panelesJugadores.size()) {
            panelesJugadores.get(idxJugador).actualizarIntentos(intentosJugador);
        }
    }
}
