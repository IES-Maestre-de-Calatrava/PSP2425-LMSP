/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.filosofos;


/**
 *
 * @author dam2
 */
public class Filosofo extends Thread{
    Cubierto palillo;
    private int posPalilloIzq, posPalilloDer;
    public Filosofo(String nombre,Cubierto palillo,int posPalilloIzq, int posPalilloDer){
        this.setName(nombre);
        this.palillo=palillo;
        this.posPalilloIzq=posPalilloIzq;
        this.posPalilloDer=posPalilloDer;
        
    }
    @Override
    public void run() {
        while(true){
            if(palillo.cogerPalillos(this.posPalilloIzq, this.posPalilloDer)){
               comer();
               palillo.soltarPalillos(this.posPalilloIzq, this.posPalilloDer);
               pensar();
            }
        }
       
    }
    private void comer(){
        System.out.println(Thread.currentThread().getName()+" comiendo");
    }
    private void pensar(){
        System.out.println(Thread.currentThread().getName()+" pensando...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
    }
}
