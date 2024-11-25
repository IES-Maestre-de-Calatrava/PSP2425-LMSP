/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.filososfos2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Filosofo extends Thread{
    GestorCubiertos cubiertos;
    private int posCIzq;
    private int posCDer;
    public Filosofo(String name, GestorCubiertos cubiertos, int posCIzq, int posCDer){
        this.setName(name);
        this.posCIzq=posCIzq;
        this.posCDer=posCDer;
        this.cubiertos = cubiertos;
    }

    @Override
    public void run() {
        while(true){
            if(cubiertos.cogerPalillos(posCIzq, posCDer)){
                comer();
                cubiertos.soltarPalillos(posCIzq, posCDer);
                try {
                pensar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
    }
    public void comer(){
        System.out.println(this.getName()+" está comiendo");
    }
    public void pensar() throws InterruptedException{
        System.out.println(this.getName()+" está pensando...");
        Thread.currentThread().sleep(5000);
    }
    
}
