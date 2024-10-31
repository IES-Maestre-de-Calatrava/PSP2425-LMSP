/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lmsp.maestre.ej3;


import java.io.BufferedReader;
import java.io.File;
//import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Usuario
 */
public class Ej3 {

    public static void main(String[] args) {
        // Nombre del archivo
        String nombreArchivo = "hola.txt";
        String clase = "com.lmsp.maestre.ej3.ContarVocal";
        int exitValue = 0;
        String classPath = ".;./target/classes";
        String[] cars = {"a", "e", "i", "o", "u"};
        try{
            for(String c:cars){
                ProcessBuilder pb = new ProcessBuilder("java","-cp",classPath, clase, nombreArchivo,c );
                pb.redirectError(new File("files"+File.separator+"error_"+System.currentTimeMillis()+".log"));
                //Escribir datos generados por sumador en un fichero
                //pb.redirectOutput(new File("files"+File.separator+"salida"));
                Process process = pb.start();
                exitValue = process.waitFor();
                System.out.println("Exit value: "+exitValue);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       
        // Leer los resultados de los archivos
        int totalA = leerResultados("resultados_a.txt");
        int totalE = leerResultados("resultados_e.txt");
        int totalI = leerResultados("resultados_i.txt");
        int totalO = leerResultados("resultados_o.txt");
        int totalU = leerResultados("resultados_u.txt");

        // Mostrar el resultado final
        System.out.println("Total de vocales en el archivo: " + (totalA + totalE + totalI + totalO + totalU));
    }

    // Método para leer los resultados de un archivo
    private static int leerResultados(String nombreArch) {
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArch))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                contador = Integer.parseInt(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contador;
    }
}

    

