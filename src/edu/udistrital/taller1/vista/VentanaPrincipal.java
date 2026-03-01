/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal que contiene todos los paneles
 * Usa CardLayout para cambiar entre paneles
 */
public class VentanaPrincipal extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    private PanelInicio panelInicio;
    private PanelTiempo panelTiempo;
    private PanelJuego panelJuego;
    private PanelResultados panelResultados;
    
    public VentanaPrincipal() {
        setTitle("Juego del Balero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Crear paneles
        panelInicio = new PanelInicio();
        panelTiempo = new PanelTiempo();
        panelJuego = new PanelJuego();
        panelResultados = new PanelResultados();
        
        // Agregar paneles al CardLayout
        mainPanel.add(panelInicio, "inicio");
        mainPanel.add(panelTiempo, "tiempo");
        mainPanel.add(panelJuego, "juego");
        mainPanel.add(panelResultados, "resultados");
        
        add(mainPanel);
    }
    
    /**
     * Cambia a la pantalla especificada
     */
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
    }
    
    // Getters de los paneles
    public PanelInicio getPanelInicio() {
        return panelInicio;
    }
    
    public PanelTiempo getPanelTiempo() {
        return panelTiempo;
    }
    
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }
    
    public PanelResultados getPanelResultados() {
        return panelResultados;
    }
}