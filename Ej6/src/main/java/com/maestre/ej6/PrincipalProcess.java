/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.maestre.ej6;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PrincipalProcess {
    public static String directorio;
    public static String palabraBuscada;
    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Se debe pasar directorio y palabra");
        }
        directorio = args[0];
        palabraBuscada = args[1]; 
        iniciaHilos(directorio);
    }
    public static void iniciaHilos(String directorio){
        File dir = new File(directorio);
        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            for(File f:files){
       
                try {
                    WordFileReader wfr = new WordFileReader(f,palabraBuscada);
                    wfr.start();
                    wfr.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrincipalProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
        }else{
            System.out.println("El directorio no se encuentra");
        }
    }
}
