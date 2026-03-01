/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import edu.udistrital.taller1.modelo.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelJugador extends JPanel {

    private final JLabel lblIcono;
    private final JLabel lblNombre;
    private final JLabel lblPuntos;
    private final JLabel lblIntentos; // NUEVO

    private final Color colorNormal = Color.WHITE;
    private final Color colorTurno = new Color(30, 30, 30);
    private final Color colorTextoTurno = Color.WHITE;

    private boolean enTurno = false;

    private final Icon iconoInactivo;
    private final Icon iconoActivo;

    public PanelJugador(Jugador jugador, Icon icono) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(colorNormal);
        setBorder(new EmptyBorder(8, 8, 8, 8));

        iconoInactivo = crearIconoPersonaje(false, 64);
        iconoActivo = crearIconoPersonaje(true, 64);

        lblIcono = new JLabel(iconoInactivo);
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblNombre = new JLabel(jugador.getNombreJugador());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblPuntos = new JLabel("Puntuación: " + jugador.getPuntosJugador());
        lblPuntos.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);

        // NUEVO: intentos del jugador
        lblIntentos = new JLabel("Intentos: " + jugador.getIntentosJugador());
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 11));
        lblIntentos.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lblIcono);
        add(Box.createVerticalStrut(6));
        add(lblNombre);
        add(Box.createVerticalStrut(4));
        add(lblPuntos);
        add(Box.createVerticalStrut(2));
        add(lblIntentos);

        setEnTurno(false);
    }

    public void actualizarPuntos(int puntos) {
        lblPuntos.setText("Puntuación: " + puntos);
        revalidate();
        repaint();
    }

    // NUEVO
    public void actualizarIntentos(int intentos) {
        lblIntentos.setText("Intentos: " + intentos);
        revalidate();
        repaint();
    }

    public void setEnTurno(boolean value) {
        this.enTurno = value;
        lblIcono.setIcon(enTurno ? iconoActivo : iconoInactivo);

        if (enTurno) {
            setBackground(colorTurno);
            lblNombre.setForeground(colorTextoTurno);
            lblPuntos.setForeground(colorTextoTurno);
            lblIntentos.setForeground(colorTextoTurno);
        } else {
            setBackground(colorNormal);
            lblNombre.setForeground(Color.BLACK);
            lblPuntos.setForeground(Color.BLACK);
            lblIntentos.setForeground(Color.BLACK);
        }
        repaint();
    }

    private static Icon crearIconoPersonaje(boolean activo, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int stroke = Math.max(3, size / 16);
            int pad = stroke;
            int d = size - pad * 2;

            if (activo) {
                g.setColor(Color.BLACK);
                g.fillOval(pad, pad, d, d);

                int headD = (int) (d * 0.35);
                int headX = pad + (d - headD) / 2;
                int headY = pad + (int) (d * 0.18);
                g.setColor(Color.WHITE);
                g.fillOval(headX, headY, headD, headD);

                int bodyW = (int) (d * 0.58);
                int bodyH = (int) (d * 0.32);
                int bodyX = pad + (d - bodyW) / 2;
                int bodyY = pad + (int) (d * 0.55);
                g.fillRoundRect(bodyX, bodyY, bodyW, bodyH, bodyH, bodyH);

                g.setColor(Color.BLACK);
                g.fillRect(pad, pad + (int) (d * 0.78), d, pad + d);

            } else {
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(stroke));

                g.drawOval(pad, pad, d, d);

                int headD = (int) (d * 0.35);
                int headX = pad + (d - headD) / 2;
                int headY = pad + (int) (d * 0.18);
                g.drawOval(headX, headY, headD, headD);

                int arcW = (int) (d * 0.70);
                int arcH = (int) (d * 0.55);
                int arcX = pad + (d - arcW) / 2;
                int arcY = pad + (int) (d * 0.40);
                g.drawArc(arcX, arcY, arcW, arcH, 0, -180);
            }
        } finally {
            g.dispose();
        }
        return new ImageIcon(img);
    }
}
