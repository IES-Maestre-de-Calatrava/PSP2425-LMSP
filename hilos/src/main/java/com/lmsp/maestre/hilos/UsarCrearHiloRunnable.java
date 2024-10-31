/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lmsp.maestre.hilos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class UsarCrearHiloRunnable {

    public static void main(String[] args) {
        Thread t = new Thread(new CrearHiloRunnable());
        t.start();
    }
}
