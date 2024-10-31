/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.lmsp.psp.lanzadorprocesos.sumador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 18 sept 2024
 */
public class Lanzador {
    public static void main(String[] args) throws Exception{
        Lanzador l = new Lanzador();
        l.LanzadorSumador(10, 51, "ficherosalida");
        //l.LanzadorSumador(51, 100);
    }
    public void LanzadorSumador(int n1, int n2, String fSalida) throws IOException, InterruptedException{
        String clase = "com.lmsp.psp.lanzadorprocesos.sumador.Sumador";
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
        // Mostramos caracter a caracter la salida generada por Sumador 
        /*if(exitValue == 0){
            try (InputStream is = process.getInputStream()) {
                int c; 
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                } 
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
         }*/
       
    }
    

 
}
