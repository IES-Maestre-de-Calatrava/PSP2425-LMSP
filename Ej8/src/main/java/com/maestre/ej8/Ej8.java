/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.maestre.ej8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ej8 {

    public static void main(String[] args) {
        try {
            Ascensor ascensor = Ascensor.getInstance();
            Usuario u1 = new Usuario(14);
            Usuario u2 = new Usuario(12);
            Usuario u3 = new Usuario(0);
            Usuario u4 = new Usuario(15);
            
            u1.start();
            u2.start();
            u3.start();
            u4.start();
            
            u1.join();
            u2.join();
            u3.join();
            u4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ej8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
