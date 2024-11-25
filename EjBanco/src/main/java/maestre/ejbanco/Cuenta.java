/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejbanco;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 8 nov 2024
 */
public class Cuenta {
    private int dineroMax;
    private int saldo;
    public Cuenta(){
        saldo = 0;
        dineroMax = 5000;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "saldo=" + saldo + '}';
    }
    
    
    public synchronized void ingresar(int cantidad){
        if(saldo <= dineroMax - 500){
            saldo += cantidad;
        }
    }
    public synchronized void retirar(int cantidad){
        if(saldo >= 300){
            saldo -= cantidad;
        }
    }
    
}
