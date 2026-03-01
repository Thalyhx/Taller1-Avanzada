/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import edu.udistrital.taller1.modelo.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelJugador extends JPanel {

    private final JLabel lblIcono;
    private final JLabel lblNombre;
    private final JLabel lblPuntos;

    private final Color colorNormal = Color.WHITE;
    private final Color colorTurno = new Color(30, 30, 30);      // oscuro
    private final Color colorTextoTurno = Color.WHITE;

    private boolean enTurno = false;

    public PanelJugador(Jugador jugador, Icon icono) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(colorNormal);
        setBorder(new EmptyBorder(8, 8, 8, 8));

        lblIcono = new JLabel(icono);
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblNombre = new JLabel(jugador.getNombreJugador());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblPuntos = new JLabel("Puntuación: " + jugador.getPuntosJugador());
        lblPuntos.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lblIcono);
        add(Box.createVerticalStrut(6));
        add(lblNombre);
        add(Box.createVerticalStrut(4));
        add(lblPuntos);

        setEnTurno(false);
    }

    public void actualizarPuntos(int puntos) {
        lblPuntos.setText("Puntuación: " + puntos);
    }

    public void setEnTurno(boolean value) {
        this.enTurno = value;
        if (enTurno) {
            setBackground(colorTurno);
            lblNombre.setForeground(colorTextoTurno);
            lblPuntos.setForeground(colorTextoTurno);
        } else {
            setBackground(colorNormal);
            lblNombre.setForeground(Color.BLACK);
            lblPuntos.setForeground(Color.BLACK);
        }
        repaint();
    }
}
