/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.productorconsumidorcola;

/**
 *
 * @author dam2
 */
public class Productor extends Thread{
    Cola colaCompartida;
    public Productor(Cola cola){
        this.colaCompartida=cola;
    }

    @Override
    public void run() {
        while(true){
            int num=1+(int)(Math.random()*10);
            while(!colaCompartida.encolar(num)){
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
