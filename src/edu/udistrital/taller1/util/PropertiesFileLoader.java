/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.util;

import javax.swing.*;
import java.awt.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class PropertiesFileLoader {

    private PropertiesFileLoader() {}

    public static Properties loadOrChoose(Component parent, String defaultFileName) throws IOException {
        // 1) Intentar en el directorio de ejecución (user.dir)
        File f = new File(System.getProperty("user.dir"), defaultFileName);
        if (f.exists() && f.isFile()) {
            return loadFromFile(f);
        }

        // 2) Intentar en la misma carpeta donde está el .jar (si aplica)
        // (cuando ejecutas jar doble click, user.dir puede variar)
        try {
            File jarDir = new File(PropertiesFileLoader.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI())
                    .getParentFile();

            if (jarDir != null) {
                File f2 = new File(jarDir, defaultFileName);
                if (f2.exists() && f2.isFile()) {
                    return loadFromFile(f2);
                }
            }
        } catch (Exception ignored) {
            // si no se puede resolver, seguimos
        }

        // 3) Si no se encontró, pedirlo al usuario (robusto y portable)
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione " + defaultFileName);
        chooser.setSelectedFile(new File(defaultFileName));

        int result = chooser.showOpenDialog(parent);
        if (result != JFileChooser.APPROVE_OPTION) {
            throw new FileNotFoundException("No se seleccionó el archivo " + defaultFileName);
        }

        File selected = chooser.getSelectedFile();
        if (!selected.exists() || !selected.isFile()) {
            throw new FileNotFoundException("Archivo inválido: " + selected.getAbsolutePath());
        }

        return loadFromFile(selected);
    }

    private static Properties loadFromFile(File file) throws IOException {
        Properties p = new Properties();
        try (Reader r = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            p.load(r);
        }
        return p;
    }
}
