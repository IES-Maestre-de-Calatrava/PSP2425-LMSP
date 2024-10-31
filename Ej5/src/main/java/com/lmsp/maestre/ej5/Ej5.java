/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lmsp.maestre.ej5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class Ej5 {
    
    
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("fichero.txt"))){
            String linea;
            int sumaTotal = 0;
            while((linea = reader.readLine()) != null){
                CuentaPalabras cp = new CuentaPalabras(linea);
                System.out.println("Voy a arrancar un hilo");
                cp.start();
                try {
                    cp.join();
                    System.out.println("He acabado un hilo");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ej5.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Añado a la suma total");
                sumaTotal+=cp.getContador();
            }
            System.out.println(sumaTotal);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ej5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ej5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
