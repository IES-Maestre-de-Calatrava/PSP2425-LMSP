/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.ejbanco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class Banco {

    public static void main(String[] args) {
        try {
            Cuenta cuenta = new Cuenta();
            
            Ingresa ingresa = new Ingresa(cuenta);
            Cobra cobra = new Cobra(cuenta);
            
            ingresa.start();
            cobra.start();
            
            ingresa.join();
            cobra.join();
            System.out.println(cuenta);
        } catch (InterruptedException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

