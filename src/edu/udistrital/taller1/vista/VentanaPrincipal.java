/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Nath */
public class VentanaPrincipal extends JFrame{
    
    private JLabel lblTitulo;
    private JLabel lblImagen;
    private JButton btnPrecargarEquipos;
    
    public VentanaPrincipal() {
        
        setTitle("Juego del Balero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        inicializarComponentes();
        establecerLayout();
        agregarComponentes();
        aplicarEstilos();
    }
    
    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("Juego del Balero");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        
        // Imagen 
        lblImagen = new JLabel();
        lblImagen.setIcon(cargarImagen("recursos/imagenes/balero.png", 200, 200));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        
        // Botón
        btnPrecargarEquipos = new JButton("PRECARGAR EQUIPOS");
        btnPrecargarEquipos.setFont(new Font("Arial", Font.BOLD, 16));
        btnPrecargarEquipos.setForeground(Color.WHITE);
        btnPrecargarEquipos.setBackground(Color.BLACK);
        btnPrecargarEquipos.setOpaque(true);
        btnPrecargarEquipos.setBorderPainted(false);
        btnPrecargarEquipos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrecargarEquipos.setPreferredSize(new Dimension(250, 50));
    }
    
    private void establecerLayout() {
        setLayout(new GridBagLayout());
        // Fondo 
        setBackground(new Color(240, 240, 240)); 
    }
    
    private void agregarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // título
        add(lblTitulo, gbc);
        
        //  imagen
        gbc.gridy = 1;
        gbc.insets = new Insets(40, 20, 40, 20);
        add(lblImagen, gbc);
        
        //  botón
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(btnPrecargarEquipos, gbc);
    }
    
    private void aplicarEstilos() {
        // Efecto 
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
    }
    
    /**
     * Carga una imagen y la redimensiona
     * @param ruta Ruta de la imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     * @return ImageIcon redimensionado
     */
    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        try {
            ImageIcon icon = new ImageIcon(ruta);
            Image imagen = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagen);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            // por si no encuentra la imagen
            return null;
        }
    }
    
    
    //Getters
    
    public JButton getBtnPrecargarEquipos() {
        return btnPrecargarEquipos;
    }
    
    public JLabel getLblImagen() {
        return lblImagen;
    }
    
    public void setImagen(ImageIcon icon) {
        lblImagen.setIcon(icon);
    }
}
