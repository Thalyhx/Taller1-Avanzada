/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        // Se usa ruta relativa para evitar errores en otros computadores
        File archivo = new File(ruta);
        
        if (!archivo.exists()) {
            return lineas; // Retorna lista vacía si no existe
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            // No usar System.out.print aquí si esta clase está en el Modelo (Regla i)
        }
        return lineas;
    }
}
