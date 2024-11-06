/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejercicioscarreracoches;

import java.util.Random;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 6 nov 2024
 */
public class Coche extends Thread{
    private String nombre;
    private int nMetrosIter;
    private int nMetros;
    public Coche(String nombre){
        Random rnd = new Random();
        this.nombre = nombre;
        this.nMetrosIter = rnd.nextInt(100)+1;
        this.nMetros = 0;
    }
    @Override
    public void run(){
        while(true){
            
        }
    }
}
