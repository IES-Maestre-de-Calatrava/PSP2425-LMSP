/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lmsp.psp.lanzadorprocesos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class LanzadorProcesos {
    public void ejecutar(String ruta){
        ProcessBuilder pb;
        try{
            pb = new ProcessBuilder(ruta);
            pb.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String ruta= "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
        LanzadorProcesos lp = new LanzadorProcesos();
        lp.ejecutar(ruta);
        System.out.println("Finalizado");
    }
}
