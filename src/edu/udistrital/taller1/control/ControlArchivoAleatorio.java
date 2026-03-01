/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.udistrital.taller1.control;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ControlArchivoAleatorio {

    private static final String FILE_NAME = "resultados_balero.dat";
    private static final int LEN_NOMBRE_EQUIPO = 40;
    private static final int LEN_NOMBRE_JUGADOR = 40;

    private static final int RECORD_SIZE_BYTES =
            4 + // clave
            (LEN_NOMBRE_EQUIPO * 2) +
            (LEN_NOMBRE_JUGADOR * 2) * 3 +
            4;  // puntaje

    public record Resultado(int clave, String equipo, String j1, String j2, String j3, int puntaje) {}

    private final File file;

    public ControlArchivoAleatorio() {
        this.file = new File(System.getProperty("user.dir"), FILE_NAME);
    }

    public int siguienteClave() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long len = raf.length();
            return (int) (len / RECORD_SIZE_BYTES) + 1;
        }
    }

    public void guardar(Resultado r) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            raf.writeInt(r.clave());
            writeFixedString(raf, r.equipo(), LEN_NOMBRE_EQUIPO);
            writeFixedString(raf, r.j1(), LEN_NOMBRE_JUGADOR);
            writeFixedString(raf, r.j2(), LEN_NOMBRE_JUGADOR);
            writeFixedString(raf, r.j3(), LEN_NOMBRE_JUGADOR);
            raf.writeInt(r.puntaje());
        }
    }

    public List<Resultado> leerTodos() throws IOException {
        List<Resultado> out = new ArrayList<>();
        if (!file.exists()) return out;

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE_BYTES;
            for (int i = 0; i < count; i++) {
                int clave = raf.readInt();
                String equipo = readFixedString(raf, LEN_NOMBRE_EQUIPO);
                String j1 = readFixedString(raf, LEN_NOMBRE_JUGADOR);
                String j2 = readFixedString(raf, LEN_NOMBRE_JUGADOR);
                String j3 = readFixedString(raf, LEN_NOMBRE_JUGADOR);
                int puntaje = raf.readInt();

                out.add(new Resultado(clave, equipo, j1, j2, j3, puntaje));
            }
        }
        return out;
    }

    public int contarVictorias(String nombreEquipo) throws IOException {
        int count = 0;
        for (Resultado r : leerTodos()) {
            if (r.equipo().equalsIgnoreCase(nombreEquipo)) count++;
        }
        return count;
    }

    private static void writeFixedString(RandomAccessFile raf, String s, int len) throws IOException {
        String value = (s == null) ? "" : s;
        if (value.length() > len) value = value.substring(0, len);
        StringBuilder sb = new StringBuilder(value);
        while (sb.length() < len) sb.append(' ');
        for (int i = 0; i < len; i++) {
            raf.writeChar(sb.charAt(i));
        }
    }

    private static String readFixedString(RandomAccessFile raf, int len) throws IOException {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }
}