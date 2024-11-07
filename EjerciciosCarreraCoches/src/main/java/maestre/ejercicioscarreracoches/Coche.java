/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejercicioscarreracoches;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String getNombre() {
        return nombre;
    }

    public int getnMetrosIter() {
        return nMetrosIter;
    }

    public int getnMetros() {
        return nMetros;
    }

    public void setnMetros(int nMetros) {
        this.nMetros = nMetros;
    }
    Carrera carrera = Carrera.getInstance();
    @Override
    public void run(){
        while(!carrera.isAcabada()){
            nMetros += nMetrosIter;
            carrera.avanzar(this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
