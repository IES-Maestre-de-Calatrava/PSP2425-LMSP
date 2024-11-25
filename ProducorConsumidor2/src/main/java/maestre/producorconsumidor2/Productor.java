/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.producorconsumidor2;


/**
 *
 * @author dam2
 */
public class Productor extends Thread{
    RecursoCompartido cola;
    public Productor(RecursoCompartido cola){
        this.cola=cola;
    }

    @Override
    public void run() {
        while(true){
            int num = (1+(int)(Math.random()*10));
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.getStackTrace();
//            }
            while(!cola.addCola(num)){
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                }
            }
        }
    }
    
}
