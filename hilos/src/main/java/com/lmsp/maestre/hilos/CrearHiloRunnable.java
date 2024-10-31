/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lmsp.maestre.hilos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 25 sept 2024
 */
public class CrearHiloRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hola, soy"+Thread.currentThread().getName()
        +"\n Prioridad: "+Thread.currentThread().getPriority());
    }

}
