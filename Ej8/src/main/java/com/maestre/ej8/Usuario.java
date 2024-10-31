/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestre.ej8;

/**
 *
 * @author Usuario
 */
public class Usuario extends Thread{
    private int pisoDestino;
    private Ascensor ascensor;
    public Usuario(int pisoDestino){
        this.pisoDestino=pisoDestino;
        this.ascensor = Ascensor.getInstance();
    }

    public int getPisoDestino() {
        return pisoDestino;
    }
    
    @Override
    public void run(){
        Ascensor.moverAscensor(getPisoDestino());
    }
}
