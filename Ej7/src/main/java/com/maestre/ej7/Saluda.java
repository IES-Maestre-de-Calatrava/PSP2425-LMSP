/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestre.ej7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Saluda {
    public Saluda(){
        
    }
    public synchronized void responderSaludo(){
        try {
            wait();
            System.out.println("Buenos días profesor.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Saluda.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public synchronized void saludarProfe(String nombre){
        System.out.println(nombre+": ¡Buenos días alumnos!");
        notifyAll();
    }
}
