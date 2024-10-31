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
public class Aula extends Thread {
    private String nombre;
    private Saluda saludo;
    private boolean esProfesor;
    public Aula(String nombre, boolean esProfesor, Saluda saludo){
        this.nombre=nombre;
        this.esProfesor=esProfesor;
        this.saludo=saludo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Saluda getSaludo() {
        return saludo;
    }

    public void setSaludo(Saluda saludo) {
        this.saludo = saludo;
    }

    public boolean esProfesor() {
        return esProfesor;
    }

    public void setEsProfesor(boolean esProfesor) {
        this.esProfesor = esProfesor;
    }
    
    @Override
    public void run(){
        if(esProfesor()){
            getSaludo().saludarProfe(getNombre());
        }else{
            getSaludo().responderSaludo();
            try {
                join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Aula.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
