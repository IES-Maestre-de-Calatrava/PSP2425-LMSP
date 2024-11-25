/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.producorconsumidor2;


/**
 *
 * @author dam2
 */
public class Consumidor extends Thread{
    RecursoCompartido cola;
    public Consumidor(RecursoCompartido cola){
        this.cola = cola;
    }

    @Override
    public void run() {
        while(true){
            if(!cola.removeCola()){
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                }
            }
        }
    }
    
    
}
