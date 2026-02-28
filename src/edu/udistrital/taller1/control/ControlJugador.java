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
        misPropiedades = cargar();
    }

    public void listarArchivo() {
        misPropiedades.list(System.out);
    }

    public void mostrarDatos() {
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j  <= 3; j++) {
                String nombreJugador = misPropiedades.getProperty("equipo" + i + "jugador"+j+"nombre");
                int codigoJugador = Integer.parseInt(misPropiedades.getProperty("equipo" + i + "jugador"+j+"codigo"));
                Jugador jugador = new Jugador(codigoJugador,nombreJugador);
            }
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
            System.out.println("No se pudo cargar el archivo properties");
        }

        return null;
    }
}
