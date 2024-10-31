/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lmsp.psp.multiplicador;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 19 sept 2024
 */
public class Lanzador {
    public void LanzadorMultiplicador(int n1, int n2, String fSalida) throws IOException, InterruptedException{
        String clase = "com.lmsp.psp.multiplicador.Multiplicador";
        ProcessBuilder pb;
        Process process = null;
        int exitValue = 0;
        try{
            String classPath = ".;./target/classes";
            pb = new ProcessBuilder("java","-cp",classPath, clase, String.valueOf(n1), String.valueOf(n2));
            pb.redirectError(new File("files"+File.separator+"error_"+System.currentTimeMillis()+".log"));
            //Escribir datos generados por sumador en un fichero
            pb.redirectOutput(new File("files"+File.separator+fSalida));
            //cambia el directorio de trabajo, al directorio donde se encuentran las .class
           // pb.directory(new File("target/classes"));
            
            process = pb.start();
            
            exitValue = process.waitFor();
            System.out.println("Exit value: "+exitValue);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
     public static void main(String[] args) throws Exception{
        Lanzador l = new Lanzador();
        l.LanzadorMultiplicador(100, 10, "ficherosalida");
       
    }
}
