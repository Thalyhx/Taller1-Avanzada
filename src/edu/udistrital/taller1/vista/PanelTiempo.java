/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel donde se configura el tiempo de juego por equipo
 * Muestra los equipos cargados (número variable) y permite ingresar tiempo
 */
public class PanelTiempo extends JPanel {
    
    private JLabel lblTitulo;
    private JPanel panelEquipos;
    private JLabel lblTiempo;
    private JTextField txtTiempo;
    private JButton btnJugar;
    private JButton btnRegresar;
    
    // Variables para controlar el grid dinámico
    private int equiposAgregados = 0;
    private static final int COLUMNAS = 2; // Máximo 2 columnas
    
    public PanelTiempo() {
        setBackground(new Color(245, 245, 245));
        inicializarComponentes();
        establecerLayout();
        agregarComponentes();
        aplicarEstilos();
    }
    
    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("Equipos Cargados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        
        // Panel de equipos con BorderLayout
        // permite ajustar dinámicamente según cantidad de equipos
        panelEquipos = new JPanel();
        panelEquipos.setBackground(new Color(245, 245, 245));
        panelEquipos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Etiqueta y campo de tiempo
        lblTiempo = new JLabel("Tiempo de juego (segundos por equipo):");
        lblTiempo.setFont(new Font("Arial", Font.PLAIN, 14));
        
        txtTiempo = new JTextField("30", 10);
        txtTiempo.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTiempo.setPreferredSize(new Dimension(100, 30));
        
        // Botón Jugar
        btnJugar = new JButton("JUGAR");
        btnJugar.setFont(new Font("Arial", Font.BOLD, 16));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setBackground(Color.BLACK);
        btnJugar.setOpaque(true);
        btnJugar.setBorderPainted(false);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnJugar.setPreferredSize(new Dimension(150, 40));
        
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
        // Panel superior con título
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(245, 245, 245));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        
        // Panel central con scroll
        JScrollPane scrollEquipos = new JScrollPane(panelEquipos);
        scrollEquipos.setBackground(new Color(245, 245, 245));
        scrollEquipos.getViewport().setBackground(new Color(245, 245, 245));
        
        // Panel inferior con tiempo y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(245, 245, 245));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panelTiempo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelTiempo.setBackground(new Color(245, 245, 245));
        panelTiempo.add(lblTiempo);
        panelTiempo.add(txtTiempo);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.add(btnRegresar);
        panelBotones.add(btnJugar);
        
        panelInferior.add(panelTiempo, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.EAST);
        
        // Agregar paneles
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollEquipos, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void aplicarEstilos() {
        aplicarHover(btnJugar, Color.BLACK, new Color(50, 50, 50));
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
     * Limpiar grilla y reiniciar contador
     * Limpia la grilla
     */
    public void limpiarGrilla() {
        panelEquipos.removeAll();
        panelEquipos.setLayout(null); // Resetear layout
        equiposAgregados = 0;
        panelEquipos.revalidate();
        panelEquipos.repaint();
    }
    
    /**
     * método que calcula filas dinámicamente
     * Agrega una tarjeta de equipo a la grilla
     * Calcula automáticamente el número de filas según cantidad de equipos
     */
    public void agregarTarjetaEquipo(JPanel tarjeta) {
        // Calcular número de filas necesarias
        int filas = (int) Math.ceil((double) (equiposAgregados + 1) / COLUMNAS);
        
        // CORRECCION: Crear GridLayout dinámico basado en cantidad de equipos
        GridLayout gridLayout = new GridLayout(filas, COLUMNAS, 15, 15);
        panelEquipos.setLayout(gridLayout);
        
        // Agregar tarjeta
        panelEquipos.add(tarjeta);
        equiposAgregados++;
        
        panelEquipos.revalidate();
        panelEquipos.repaint();
    }
    
    /**
     * Obtiene el tiempo ingresado
     */
    public int getTiempoIngresado() {
        try {
            return Integer.parseInt(txtTiempo.getText());
        } catch (NumberFormatException e) {
            return 30; // Por defecto 30 segundos
        }
    }
    
    // Getters
    public JButton getBtnJugar() {
        return btnJugar;
    }
    
    public JButton getBtnRegresar() {
        return btnRegresar;
    }
}
