/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import edu.udistrital.taller1.modelo.Jugador;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebas
 */
public class ControlJugador {

    private ControlPrincipal cPrincipal;
    Properties misPropiedades;
    File f;

    public ControlJugador(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        misPropiedades = new Properties();
       ///  misPropiedades = cargar();  se carga en el constructor?
    }

    public void listarArchivo() {
        if (misPropiedades != null) { // verificar
            misPropiedades.list(System.out);
        }
    }

//    public void mostrarDatos() {
//        for (int i = 1; i <= 2; i++) {
//            for (int j = 1; j  <= 3; j++) {
//                String nombreJugador = misPropiedades.getProperty("equipo" + i + "jugador"+j+"nombre");
//                int codigoJugador = Integer.parseInt(misPropiedades.getProperty("equipo" + i + "jugador"+j+"codigo"));
//                
//                String codigoStr = misPropiedades.getProperty("equipo" + i + ".jugador" + j + ".codigo");
//                
//                if (nombreJugador != null && codigoStr != null) {
//                    int codigoJugador = Integer.parseInt(codigoStr);
//                    Jugador jugador = new Jugador(codigoJugador, nombreJugador);
//                }
//            }
//        }
//    }
    
    private List<Jugador> construirJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        
        for (int i = 1; i <= 10; i++) { // Soporta hasta 10 equipos
            for (int j = 1; j <= 3; j++) { // 3 jugadores por equipo
                String nombreJugador = misPropiedades.getProperty("equipo" + i + ".jugador" + j + ".nombre");
                String codigoStr = misPropiedades.getProperty("equipo" + i + ".jugador" + j + ".codigo");
                
                if (nombreJugador == null || codigoStr == null) {
                    continue;
                }
                
                try {
                    int codigo = Integer.parseInt(codigoStr);
                    Jugador jugador = new Jugador(codigo, nombreJugador);
                    jugadores.add(jugador);
                } catch (NumberFormatException e) {
                    System.err.println("Código inválido: " + codigoStr);
                }
            }
        }
        
        return jugadores;
    }
    
    public List<Jugador> cargarJugadoresDesdeArchivo() {
        this.misPropiedades = cargar();
        
        if (misPropiedades == null) {
            System.out.println("No se cargó archivo");
            return new ArrayList<>();
        }
        
        return construirJugadores();
    }
    
    private Properties cargar() {
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.showOpenDialog(fc);
            
            fc.setDialogTitle("Selecciona archivo de jugadores");
            int resultado = fc.showOpenDialog(null);
            
            // Verifica si el usuario seleccionó un archivo
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            
            
            f = fc.getSelectedFile();
            FileInputStream archivo = new FileInputStream(f);
            Properties propiedades = new Properties();
            propiedades.load(archivo);
            archivo.close();
            
            if (!propiedades.isEmpty()) {
                return propiedades;
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo properties");
        }

        return null;
    }
}
