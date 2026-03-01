/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra los resultados finales del juego
 * Diseño inspirado en la imagen de ganador
 */
public class PanelResultados extends JPanel {
    
    private JLabel lblTitulo;
    private JLabel lblGanador;
    private JLabel lblPuntaje;
    private JLabel lblJugadores;
    private JPanel panelJugadores;
    private JLabel lblHistorial;
    private JTextArea textAreaHistorial;
    private JButton btnSalir;
    
    public PanelResultados() {
        setBackground(new Color(245, 245, 245));
        inicializarComponentes();
        establecerLayout();
        agregarComponentes();
        aplicarEstilos();
    }
    
    private void inicializarComponentes() {
        // Título "GANADOR"
        lblTitulo = new JLabel("🏆 GANADOR 🏆");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(new Color(0, 150, 0));
        
        // Nombre del equipo ganador
        lblGanador = new JLabel("Nombre equipo");
        lblGanador.setFont(new Font("Arial", Font.BOLD, 32));
        lblGanador.setHorizontalAlignment(JLabel.CENTER);
        
        // Puntaje
        lblPuntaje = new JLabel("Puntuación: 0");
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntaje.setHorizontalAlignment(JLabel.CENTER);
        
        // Etiqueta de jugadores
        lblJugadores = new JLabel("Jugadores:");
        lblJugadores.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Panel con jugadores
        panelJugadores = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelJugadores.setBackground(new Color(245, 245, 245));
        
        // Historial
        lblHistorial = new JLabel("Historial de Victorias:");
        lblHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Área de texto para historial
        textAreaHistorial = new JTextArea(4, 60);
        textAreaHistorial.setFont(new Font("Courier New", Font.PLAIN, 12));
        textAreaHistorial.setEditable(false);
        textAreaHistorial.setBackground(Color.WHITE);
        textAreaHistorial.setLineWrap(true);
        textAreaHistorial.setWrapStyleWord(true);
        
        // Botón Salir
        btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(180, 120, 100));
        btnSalir.setOpaque(true);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setPreferredSize(new Dimension(150, 40));
    }
    
    private void establecerLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    private void agregarComponentes() {
        add(Box.createVerticalStrut(20));
        
        // Título
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);
        
        add(Box.createVerticalStrut(15));
        
        // Ganador
        lblGanador.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblGanador);
        
        // Puntaje
        lblPuntaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblPuntaje);
        
        add(Box.createVerticalStrut(20));
        
        // Jugadores
        lblJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblJugadores);
        
        panelJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panelJugadores);
        
        add(Box.createVerticalStrut(20));
        
        // Historial
        lblHistorial.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblHistorial);
        
        add(Box.createVerticalStrut(5));
        add(new JScrollPane(textAreaHistorial));
        
        add(Box.createVerticalStrut(20));
        
        // Botón Salir
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.add(btnSalir);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panelBotones);
        
        add(Box.createVerticalStrut(20));
    }
    
    private void aplicarEstilos() {
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(160, 100, 80));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(180, 120, 100));
            }
        });
    }
    
    /**
     * Actualiza los datos del ganador
     */
    public void actualizarResultados(String nombreEquipo, int puntaje) {
        lblGanador.setText(nombreEquipo);
        lblPuntaje.setText("Puntuación: " + puntaje + " puntos");
    }
    
    /**
     * Agrega un jugador ganador al panel
     */
    public void agregarJugadorGanador(String nombre) {
        JPanel panelJugador = new JPanel();
        panelJugador.setLayout(new BoxLayout(panelJugador, BoxLayout.Y_AXIS));
        panelJugador.setBackground(new Color(255, 255, 200));
        panelJugador.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelJugador.setPreferredSize(new Dimension(120, 120));
        
        JLabel lblIcono = new JLabel("⭐");
        lblIcono.setFont(new Font("Arial", Font.PLAIN, 40));
        lblIcono.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        
        panelJugador.add(Box.createVerticalStrut(5));
        panelJugador.add(lblIcono);
        panelJugador.add(Box.createVerticalStrut(5));
        panelJugador.add(lblNombre);
        
        panelJugadores.add(panelJugador);
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }
    
    /**
     * Limpia los jugadores
     */
    public void limpiarJugadores() {
        panelJugadores.removeAll();
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }
    
    /**
     * Establece el historial
     */
    public void establecerHistorial(String historial) {
        textAreaHistorial.setText(historial);
        textAreaHistorial.setCaretPosition(0);
    }
    
    // Getters
    public JButton getBtnSalir() {
        return btnSalir;
    }
}