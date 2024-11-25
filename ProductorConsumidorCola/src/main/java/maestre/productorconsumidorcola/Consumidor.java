/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.productorconsumidorcola;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Consumidor extends Thread{
    Cola colaCompartida;
    public Consumidor(Cola cola){
        this.colaCompartida=cola;
    }

    @Override
    public void run() {
        while(true){
            if(!colaCompartida.desencolar()){
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}
