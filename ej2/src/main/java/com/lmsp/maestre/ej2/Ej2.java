package com.lmsp.maestre.ej2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.io.File;

public class Ej2 {
    public static void main(String[] args) throws Exception {
        //String ruta = "C:\\Windows\\System32";
        // Crear el proceso para ejecutar el comando
        ProcessBuilder pb = new ProcessBuilder("cmd","/C","dir");//.exec("cmd /C dir")
        //pb.directory(new File(ruta));//directorio sobre el que hacer dir
        pb.redirectError(new File("files"+File.separator+"error_"+System.currentTimeMillis()+".log"));
        //Escribir datos generados por sumador en un fichero
        pb.redirectOutput(new File("files"+File.separator+"salidaEj2.txt"));

        Process process = pb.start();
        // Esperar a que el proceso termine
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            // Leer la salida del proceso
            /*BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }*/
        } else {
            System.out.println("El proceso terminó con errores. Código de salida: " + exitCode);
        }
    }
}
