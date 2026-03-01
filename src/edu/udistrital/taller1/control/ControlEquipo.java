/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import java.io.File;
import java.util.Properties;
import edu.udistrital.taller1.modelo.Equipo;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;


public class ControlEquipo {
    private ControlPrincipal cPrincipal;
    private Properties misPropiedades;
    private File f;
    
    public ControlEquipo(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.misPropiedades = new Properties();
    } 
    
    /**
     * Carga equipos desde un archivo seleccionado por el usuario
     */
    public List<Equipo> cargarEquiposDesdeArchivo() {
        this.misPropiedades = cargar();
        
        if (misPropiedades == null) {
            return new ArrayList<>();
        }
        
        return construirEquipos();
    }
    
    /**
     * Construye los equipos desde las propiedades cargadas
     */
    private List<Equipo> construirEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        
        int i = 1;
        while (true) {
            String proyecto = misPropiedades.getProperty("equipo" + i + ".proyecto");
            String nombre = misPropiedades.getProperty("equipo" + i + ".nombre");
            
            if (proyecto == null || nombre == null) {
                break;
            }
            
            Equipo equipo = new Equipo(proyecto, nombre);
            equipos.add(equipo);
            i++;
        }
        
        return equipos;
    }
    
   
    public void reiniciarEquipos(List<Equipo> equipos) {
        if (equipos == null || equipos.isEmpty()) {
            return;
        }
        
        for (Equipo equipo : equipos) {
            // Reiniciar puntajes e intentos del equipo
            equipo.setPuntajeEquipo(0);
            equipo.setIntentosEquipo(0);
            
            // Reiniciar puntajes e intentos de cada jugador
            if (equipo.getJugadoresEquipo() != null) {
                equipo.getJugadoresEquipo().forEach(jugador -> {
                    jugador.setPuntosJugador(0);
                    jugador.setIntentosJugador(0);
                });
            }
        }
        
    }
    
    public void listarArchivo() {
        if (misPropiedades != null) {
        }
    }
    
    public void mostrarDatos(){
        
        for (int i = 1; i <= Integer.parseInt(misPropiedades.getProperty("cantidadEquipos")); i++)
        {
          String proyectoCurricularEquipo =  misPropiedades.getProperty("equipo"+i+".proyecto");
          String nombreEquipo =  misPropiedades.getProperty("equipo"+i+".nombre");
          Equipo equipo = new Equipo(proyectoCurricularEquipo, nombreEquipo);
        }
    }
    
    private Properties cargar() {
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.setDialogTitle("Selecciona archivo de equipos");
            int resultado = fc.showOpenDialog(null);
            
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            
            f = fc.getSelectedFile();
            FileInputStream archivo = new FileInputStream(f);
            Properties propiedades = new Properties();
            propiedades.load(archivo);
            archivo.close();
            
        } catch (Exception e) {
            System.err.println("No se pudo cargar el archivo properties: " + e.getMessage());
        }

       return null;
    }
}
