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
        
        // Panel grilla de equipos (2x2)
        panelGrillaEquipos = new JPanel(new GridLayout(2, 2, 15, 15));
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
        // Efecto hover botones
        aplicarHover(btnIntento, new Color(0, 150, 0), new Color(0, 120, 0));
        aplicarHover(btnSiguiente, new Color(0, 100, 200), new Color(0, 80, 160));
        aplicarHover(btnRegresar, new Color(180, 120, 100), new Color(160, 100, 80));
    }
    
    private void aplicarHover(JButton btn, Color colorNormal, Color colorHover) {
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorHover);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorNormal);
            }
        });
    }
    
    /**
     * Actualiza la vista del juego con los datos del turno actual
     */
    public void actualizarJuego(int equipoActual, int jugadorActual, int tiempoRestante) {
        lblTiempo.setText(String.format("Tiempo: 00:%02d", tiempoRestante));
        lblTurno.setText(String.format("Equipo %d - Jugador %d", equipoActual + 1, jugadorActual + 1));
    }
    
    /**
     * Actualiza solo el tiempo
     */
    public void actualizarTiempo(int tiempoRestante) {
        lblTiempo.setText(String.format("Tiempo: 00:%02d", tiempoRestante));
    }
    
    /**
     * Muestra el resultado de una embocada
     */
    public void mostrarEmbocada(String tipoEmbocada, int puntos) {
        lblEmbocada.setText(String.format("Resultado: %s (+%d puntos)", tipoEmbocada, puntos));
    }
    
    /**
     * Agrega una tarjeta de equipo a la grilla
     */
    public void agregarTarjetaEquipo(JPanel tarjeta) {
        panelGrillaEquipos.add(tarjeta);
        panelGrillaEquipos.revalidate();
        panelGrillaEquipos.repaint();
    }
    
    /**
     * Limpia la grilla de equipos
     */
    public void limpiarGrilla() {
        panelGrillaEquipos.removeAll();
        panelGrillaEquipos.revalidate();
        panelGrillaEquipos.repaint();
    }
    
    // Getters de botones (para conectar listeners desde el controlador)
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