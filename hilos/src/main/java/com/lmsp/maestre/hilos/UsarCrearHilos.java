/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.lmsp.maestre.hilos;

import java.util.ArrayList;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 25 sept 2024
 */
public class UsarCrearHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());
        
        ArrayList<CrearHilos> aL = new ArrayList<>();
        for(int i = 0; i <3; i++){
            CrearHilos h = new CrearHilos();//crear hilo
            h.setName("HILO "+i);//damos nombre al hilo
            h.setPriority(i+1);//damos prioridad
            h.start();//iniciar hilo
            aL.add(h);//Almacenamos en un
            
            //h.join();
            //System.out.println("Informacion del "+h.getName()+" : "+h.toString());
        }
        for(CrearHilos t:aL){//Recorremos lista para hacer join a cada hilo
            t.join();
            System.out.println("Informacion del "+t.getName()+" : "+t.toString());
        }
        System.out.println("3 hilos creados...");
        System.out.println("Hilos activos: "+Thread.activeCount());
    }

}
