/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lmsp.maestre.ej5;

/**
 *
 * @author Usuario
 */
public class CuentaPalabras extends Thread{
    private int contador;
    private String linea;
    public CuentaPalabras(String linea){
        this.contador = 0;
        this.linea = linea;
    }
    public int getContador(){
        return this.contador;
    }
    public String getLinea(){
        return this.linea;
    }
    @Override
    public void run(){
        String[] palabras = this.getLinea().split(" ");
        for(String palabra:palabras){
            if (palabra.length()>0)
                this.contador++;
        }
    }
}
