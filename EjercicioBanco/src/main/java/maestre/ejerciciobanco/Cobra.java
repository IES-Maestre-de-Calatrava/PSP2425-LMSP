/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejerciciobanco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 8 nov 2024
 */
public class Cobra extends Thread{
    private int cantidad;
    private Cuenta cuenta;
    public Cobra(Cuenta cuenta){
        cantidad = 300;
        this.cuenta = cuenta;
    }
    @Override
    public void run(){
        boolean acaba = false;
        int acum = 0;
        while(!acaba){
            
            cuenta.retirar(cantidad);
            acum+=cantidad;
            try {
                Thread.sleep(3000);
                if(acum >=6000){
                acaba = true;
            }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cobra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
