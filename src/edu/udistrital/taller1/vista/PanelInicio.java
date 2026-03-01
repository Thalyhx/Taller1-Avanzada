/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel inicial donde se carga el archivo de equipos
 * Solo contiene la interfaz, los listeners se conectan desde el controlador
 */
public class PanelInicio extends JPanel {
    
    private JLabel lblTitulo;
    private JLabel lblImagen;
    private JButton btnPrecargarEquipos;
    private JButton btnSalir;
    
    public PanelInicio() {
        setBackground(new Color(240, 240, 240));
        inicializarComponentes();
        establecerLayout();
        agregarComponentes();
        aplicarEstilos();
        
        
        //cargando imagen
        ImageIcon icon = cargarImagen("images/emboque.png", 160, 160);
    if (icon != null) {
        lblImagen.setText(""); // por si no se carga imagen
        setImagen(icon);
    }
    }
    
    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("Juego del Balero");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        
        // Imagen (balero - emoji por ahora)
        lblImagen = new JLabel("🎈");
        lblImagen.setFont(new Font("Arial", Font.PLAIN, 120));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        
        // Botón Precargar Equipos
        btnPrecargarEquipos = new JButton("PRECARGAR EQUIPOS");
        btnPrecargarEquipos.setFont(new Font("Arial", Font.BOLD, 16));
        btnPrecargarEquipos.setForeground(Color.WHITE);
        btnPrecargarEquipos.setBackground(Color.BLACK);
        btnPrecargarEquipos.setOpaque(true);
        btnPrecargarEquipos.setBorderPainted(false);
        btnPrecargarEquipos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrecargarEquipos.setPreferredSize(new Dimension(250, 50));
        
        // Botón Salir
        btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(200, 0, 0));
        btnSalir.setOpaque(true);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setPreferredSize(new Dimension(250, 50));
    }
    
    private void establecerLayout() {
        setLayout(new GridBagLayout());
    }
    
    private void agregarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Título
        add(lblTitulo, gbc);
        
        // Imagen
        gbc.gridy = 1;
        gbc.insets = new Insets(40, 20, 40, 20);
        add(lblImagen, gbc);
        
        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(240, 240, 240));
        panelBotones.add(btnPrecargarEquipos);
        panelBotones.add(btnSalir);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(panelBotones, gbc);
    }
    
    private void aplicarEstilos() {
        // Efecto hover - Precargar
        btnPrecargarEquipos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrecargarEquipos.setBackground(new Color(50, 50, 50));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrecargarEquipos.setBackground(Color.BLACK);
            }
        });
        
        // Efecto hover - Salir
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(150, 0, 0));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setBackground(new Color(200, 0, 0));
            }
        });
    }
    
    /**
     * Carga una imagen y la redimensiona
     */
    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        try {
            java.net.URL imagenURL = getClass().getClassLoader().getResource(ruta);
            
            if (imagenURL == null) {
                System.err.println("No se encontró: " + ruta);
                return null;
            }
            
            ImageIcon icon = new ImageIcon(imagenURL);
            Image imagen = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagen);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Establece la imagen del panel
     */
    public void setImagen(ImageIcon icon) {
        lblImagen.setIcon(icon);
    }
    
    // Getters (sin listeners, se conectan desde el controlador)
    public JButton getBtnPrecargarEquipos() {
        return btnPrecargarEquipos;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }
}
