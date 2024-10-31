/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.lmsp.maestre.hilos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 3 oct 2024
 */
public class UsarCarrera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Carrera conejo = new Carrera(1,"conejo");
        Carrera tortuga = new Carrera(5,"tortuga");
        Carrera leopardo = new Carrera(8,"leopardo");
        
        conejo.start();
        tortuga.start();
        leopardo.start();
        
        conejo.join();
        tortuga.join();
        leopardo.join();
    }

}
