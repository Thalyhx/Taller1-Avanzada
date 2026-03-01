/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.modelo.Equipo;
import edu.udistrital.taller1.modelo.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public final class TarjetasFactory {

    private TarjetasFactory() {}

    public static JPanel crearTarjetaEquipo(Equipo equipo) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(350, 160));

        JLabel titulo = new JLabel(equipo.getNombreEquipo() + " (" + equipo.getNombreProyecto() + ")");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(titulo, BorderLayout.NORTH);

        JPanel jugadoresPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        jugadoresPanel.setBackground(Color.WHITE);

        List<Jugador> jugadores = equipo.getJugadoresEquipo();
        for (Jugador j : jugadores) {
            jugadoresPanel.add(crearMiniJugador(j));
        }

        card.add(jugadoresPanel, BorderLayout.CENTER);
        return card;
    }

    private static JPanel crearMiniJugador(Jugador j) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(245, 245, 245));
        p.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        Icon icon = UIManager.getIcon("OptionPane.informationIcon"); // imagen estándar
        JLabel lblIcon = new JLabel(icon);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel(j.getNombreJugador());
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(Box.createVerticalStrut(6));
        p.add(lblIcon);
        p.add(Box.createVerticalStrut(6));
        p.add(lblNombre);

        return p;
    }
}