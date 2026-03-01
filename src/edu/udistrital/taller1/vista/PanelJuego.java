/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel donde se desarrolla el juego
 * Muestra la grilla de equipos, timer, turno actual, y botones de control
 * Solo contiene la interfaz, los listeners se conectan desde el controlador
 */
public class PanelJuego extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTiempo;
    private JLabel lblTurno;
    private JLabel lblEmbocada;
    private JPanel panelGrillaEquipos;
    private JButton btnIntento;
    private JButton btnSiguiente;
    private JButton btnRegresar;

    public PanelJuego() {
        setBackground(new Color(245, 245, 245));
        inicializarComponentes();
        establecerLayout();
        agregarComponentes();
        aplicarEstilos();
    }

    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("Jugando...");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        // Etiqueta de tiempo
        lblTiempo = new JLabel("Tiempo: 00:10");
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTiempo.setForeground(new Color(200, 0, 0));

        // Etiqueta de turno
        lblTurno = new JLabel("Turno 1 - Equipo 1 - Jugador 1");
        lblTurno.setFont(new Font("Arial", Font.PLAIN, 14));

        // Etiqueta de embocada
        lblEmbocada = new JLabel("Resultado: ");
        lblEmbocada.setFont(new Font("Arial", Font.BOLD, 16));
        lblEmbocada.setForeground(new Color(0, 150, 0));

        // Panel grilla de equipos (layout se configura dinámicamente con configurarGrilla)
        panelGrillaEquipos = new JPanel();
        panelGrillaEquipos.setBackground(new Color(245, 245, 245));
        panelGrillaEquipos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botón Intento
        btnIntento = new JButton("INTENTO");
        btnIntento.setFont(new Font("Arial", Font.BOLD, 14));
        btnIntento.setForeground(Color.WHITE);
        btnIntento.setBackground(new Color(0, 150, 0));
        btnIntento.setOpaque(true);
        btnIntento.setBorderPainted(false);
        btnIntento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIntento.setPreferredSize(new Dimension(150, 40));

        // Botón Siguiente
        btnSiguiente = new JButton("SIGUIENTE");
        btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setBackground(new Color(0, 100, 200));
        btnSiguiente.setOpaque(true);
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.setPreferredSize(new Dimension(150, 40));
        btnSiguiente.setEnabled(false); // se habilita al terminar partida

        // Botón Regresar
        btnRegresar = new JButton("REGRESAR");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setBackground(new Color(180, 120, 100));
        btnRegresar.setOpaque(true);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setPreferredSize(new Dimension(150, 40));
    }

    private void establecerLayout() {
        setLayout(new BorderLayout());
    }

    private void agregarComponentes() {
        // Panel superior con título y tiempo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(245, 245, 245));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(245, 245, 245));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        panelTitulo.add(lblTiempo, BorderLayout.EAST);

        panelSuperior.add(panelTitulo, BorderLayout.NORTH);
        panelSuperior.add(lblTurno, BorderLayout.CENTER);

        // Panel central con scroll de equipos
        JScrollPane scrollEquipos = new JScrollPane(panelGrillaEquipos);
        scrollEquipos.setBackground(new Color(245, 245, 245));
        scrollEquipos.getViewport().setBackground(new Color(245, 245, 245));

        // Panel central con embocada y scroll
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(245, 245, 245));

        JPanel panelEmbocada = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEmbocada.setBackground(new Color(245, 245, 245));
        panelEmbocada.add(lblEmbocada);

        panelCentral.add(panelEmbocada, BorderLayout.NORTH);
        panelCentral.add(scrollEquipos, BorderLayout.CENTER);

        // Panel inferior con botones de acción
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        panelInferior.setBackground(new Color(245, 245, 245));
        panelInferior.add(btnIntento);
        panelInferior.add(btnSiguiente);

        // Panel para botón regresar
        JPanel panelRegreso = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelRegreso.setBackground(new Color(245, 245, 245));
        panelRegreso.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        panelRegreso.add(btnRegresar);

        // Agregar paneles a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        add(panelRegreso, BorderLayout.EAST);
    }

    private void aplicarEstilos() {
        // Efecto hover botones (opcional)
    }

    /**
     * Configura la grilla dinámicamente (ej: mockup 3 columnas).
     */
    public void configurarGrilla(int cantidadEquipos) {
        int columnas = 3;
        int filas = (int) Math.ceil((double) cantidadEquipos / columnas);
        panelGrillaEquipos.setLayout(new GridLayout(filas, columnas, 20, 20));
        panelGrillaEquipos.revalidate();
        panelGrillaEquipos.repaint();
    }

    /**
     * Habilita/deshabilita botón SIGUIENTE.
     */
    public void setSiguienteHabilitado(boolean enabled) {
        btnSiguiente.setEnabled(enabled);
    }

    /**
     * Habilita/deshabilita botón INTENTO.
     */
    public void setIntentoHabilitado(boolean enabled) {
        btnIntento.setEnabled(enabled);
    }

    public void actualizarJuego(int equipoActual, int jugadorActual, int tiempoRestante) {
        lblTiempo.setText(String.format("Tiempo: 00:%02d", tiempoRestante));
        lblTurno.setText(String.format("Equipo %d - Jugador %d", equipoActual + 1, jugadorActual + 1));
    }

    public void actualizarTiempo(int tiempoRestante) {
        lblTiempo.setText(String.format("Tiempo: 00:%02d", tiempoRestante));
    }

    public void mostrarEmbocada(String tipoEmbocada, int puntos) {
        lblEmbocada.setText(String.format("Resultado: %s (+%d puntos)", tipoEmbocada, puntos));
    }

    public void agregarTarjetaEquipo(JPanel tarjeta) {
        panelGrillaEquipos.add(tarjeta);
        panelGrillaEquipos.revalidate();
        panelGrillaEquipos.repaint();
    }

    public void limpiarGrilla() {
        panelGrillaEquipos.removeAll();
        panelGrillaEquipos.revalidate();
        panelGrillaEquipos.repaint();
    }

    // Getters de botones
    public JButton getBtnIntento() {
        return btnIntento;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JPanel getPanelGrillaEquipos() {
        return panelGrillaEquipos;
    }
}