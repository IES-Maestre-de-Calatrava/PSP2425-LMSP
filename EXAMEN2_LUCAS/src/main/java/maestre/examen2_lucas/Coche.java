/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.examen2_lucas;


/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 25 nov 2024
 */
public class Coche extends Thread{
    private Parking parking;
    public Coche(String nombre, Parking parking){
        this.setName(nombre);
        this.parking=parking;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            int numPlaza = this.parking.entrar();
            if(numPlaza != -1){
                aparcar(numPlaza, parking);
                try {
                    this.sleep(1+((int)Math.random()*3)*1000);
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                }
                parking.salir(numPlaza);
                salir(numPlaza, parking);
                
                
            }
            else{
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                }
            }
        }
    }
    public synchronized void aparcar(int numPlaza, Parking parking){
        System.out.println(this.getName()+" ha entrado al parking a la plaza numero "+numPlaza);
        System.out.println("Plazas libres: "+parking.plazasLibres());
        parking.muestra();
    }
    public synchronized void salir(int numPlaza, Parking parking){
        System.out.println(this.getName()+" ha salido del parking de la plaza numero "+numPlaza);
        System.out.println("Plazas libres: "+parking.plazasLibres());
        parking.muestra();
    }
    
}
