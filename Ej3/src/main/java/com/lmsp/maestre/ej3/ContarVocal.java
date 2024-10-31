/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lmsp.maestre.ej3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 27 sept 2024
 */
public class ContarVocal {
    private static String nombreArchivo;
    private static char vocal;

    public static void main(String[] args) {
        nombreArchivo = args[0];
        vocal = (args[1].toCharArray()[0]);
        
        int contador = 0;
        String contadorStr=null;
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    if (Character.toLowerCase(c) == Character.toLowerCase(vocal)) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Guardar el resultado en un archivo
        try (FileWriter escritor = new FileWriter("resultados_" + Character.toLowerCase(vocal) + ".txt")) {
            contadorStr=Integer.toString(contador);
            escritor.write(contadorStr);
            escritor.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
        
    }
}
