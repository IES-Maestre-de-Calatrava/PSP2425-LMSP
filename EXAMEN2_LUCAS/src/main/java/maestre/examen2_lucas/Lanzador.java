/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package maestre.examen2_lucas;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 25 nov 2024
 */
public class Lanzador {

    private static final int NUM_PLAZAS = 5;
    private static final int NUM_COCHES = 10;
    public static void main(String[] args) throws InterruptedException {
        Parking parking = new Parking(NUM_PLAZAS);
        Coche[] coches = new Coche[NUM_COCHES];
        for(int i = 0; i<NUM_COCHES;i++){
            Coche coche = new Coche("Coche "+(i+1), parking);
            coches[i] = coche;
            coche.start();
        }

        Thread.currentThread().sleep(10000);
        
        for(int i = 0;i<NUM_COCHES;i++){
            coches[i].interrupt();
        }
        
    }

}
