/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejbanco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 8 nov 2024
 */
public class Ingresa extends Thread{
    private int cantidad;
    private Cuenta cuenta;
    public Ingresa(Cuenta cuenta){
        cantidad = 500;
        this.cuenta = cuenta;
    }
    @Override
    public void run(){
        boolean acaba = false;
        int count = 0;
        while(!acaba){
            cuenta.ingresar(cantidad);
            try {
                Thread.sleep(2000);
                count++;
                if (count * 2 >= 90){
                    acaba = true;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Ingresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
