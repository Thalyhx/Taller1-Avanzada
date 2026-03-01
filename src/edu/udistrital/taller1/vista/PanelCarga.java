/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.vista;

import javax.swing.*;
import java.awt.*;

public class PanelCarga extends JPanel {
    
    private JLabel lblEstado;
    private JTextArea txtResumen;
    private JButton btnContinuar;

    public PanelCarga() {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 230, 230));
        
        lblEstado = new JLabel("Estado de la Carga: Pendiente", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResumen);
        
        btnContinuar = new JButton("Continuar a Configuración");
        btnContinuar.setEnabled(false); // Solo se activa si la carga es exitosa

        add(lblEstado, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnContinuar, BorderLayout.SOUTH);
    }

    // Métodos para que el controlador actualice la vista
    public void mostrarExito(String resumen) {
        lblEstado.setText("¡Carga Exitosa!");
        lblEstado.setForeground(new Color(0, 150, 0));
        txtResumen.setText(resumen);
        btnContinuar.setEnabled(true);
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}
