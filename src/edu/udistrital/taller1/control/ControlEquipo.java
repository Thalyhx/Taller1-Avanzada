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

/**
 *
 * @author sebas
 */
public class ControlEquipo {
    private ControlPrincipal cPrincipal;
    private List<Equipo> listaEquipos;
    Properties misPropiedades;
    File f;
    

    public ControlEquipo(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        misPropiedades = new Properties();
        misPropiedades = cargar();
        listaEquipos = new ArrayList<>();
    } 
    
    public void listarArchivo() {
        misPropiedades.list(System.out);
    }
    
    public void mostrarDatos(){
        for(int i=1; i<=Integer.parseInt(misPropiedades.getProperty("cantidadEquipos"));i++){
          String proyectoCurricularEquipo =  misPropiedades.getProperty("equipo"+i+"proyecto");
          String nombreEquipo =  misPropiedades.getProperty("equipo"+i+"nombre");
          Equipo equipo = new Equipo(proyectoCurricularEquipo, nombreEquipo);
          listaEquipos.add(equipo);
        }
    }

    private Properties cargar() {
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.showOpenDialog(fc);
            f = fc.getSelectedFile();
            FileInputStream archivo = new FileInputStream(f);
            Properties propiedades = new Properties();
            propiedades.load(archivo);
            archivo.close();
            if (!propiedades.isEmpty()) {
                return propiedades;
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar el archivo properties");
        }

       return null;
    }  
}
