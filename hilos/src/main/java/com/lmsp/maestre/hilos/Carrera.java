/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lmsp.maestre.hilos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 3 oct 2024
 */
public class Carrera extends Thread{
    String nombre;
    public Carrera(int prioridad, String nombre){
        this.nombre = nombre;
        setPriority(prioridad);
    }
    @Override
    public void run(){
        for(int c = 1;c<=30;c++){
            System.out.print(c+"mt ");
            this.yield();
        }
        System.out.println("\n llego a la meta "+nombre);
    }
}
