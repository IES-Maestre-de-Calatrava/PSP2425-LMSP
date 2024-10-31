/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.maestre.ej6;

import java.io.File;

/**
 *
 * @author Usuario
 */
public class Ej6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombreDirectorio = "directory";
        String clase = "com.maestre.ej6.PrincipalProcess";
        int exitValue = 0;
        String classPath = ".;./target/classes";
        String palabraBuscada = "source";
        try{
            ProcessBuilder pb = new ProcessBuilder("java","-cp",classPath, clase, nombreDirectorio,palabraBuscada );
            pb.redirectError(new File("files"+File.separator+"error_"+System.currentTimeMillis()+".log"));
            //Escribir datos generados por sumador en un fichero
            pb.redirectOutput(new File("files"+File.separator+"resultado_busqueda.txt"));
            Process process = pb.start();
            exitValue = process.waitFor();
            System.out.println("Exit value: "+exitValue);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            System.exit(0);
        }
    }
    
}
