/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestre.ej8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ascensor {
    private static int pisoActual;
    private static int maxPisos;
    private static Ascensor instance = null;
    private Ascensor(){
        this.maxPisos = 15;
        this.pisoActual = 0;
    }
    private synchronized static void createInstance(){
        if(instance == null){
            instance = new Ascensor();
        }
    }
    public static Ascensor getInstance(){
        if(instance == null){
            createInstance();
        }
        return instance;
    }

    public static int getPisoActual() {
        return pisoActual;
    }

    public static void setPisoActual(int pisoActual) {
        Ascensor.pisoActual = pisoActual;
    }

    public static int getMaxPisos() {
        return maxPisos;
    }
    public synchronized static void moverAscensor(int pisoDestino){
        if(pisoDestino <= getMaxPisos() && pisoDestino >=0){
            System.out.println("Estamos en el piso "+getPisoActual());
            while(getPisoActual() != pisoDestino){
                if(getPisoActual() > pisoDestino){
                    try {
                        Thread.currentThread().sleep(1000);
                        setPisoActual(getPisoActual()-1);
                        System.out.println(getPisoActual()+"...");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Ascensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(getPisoActual() < pisoDestino){
                    try {
                        Thread.currentThread().sleep(1000);
                        setPisoActual(getPisoActual()+1);
                        System.out.println(getPisoActual()+"...");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Ascensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Hemos llegado al piso "+getPisoActual());
            }
        }else{
            System.out.println("Numero de piso no existente");
        }
        
    }
}
