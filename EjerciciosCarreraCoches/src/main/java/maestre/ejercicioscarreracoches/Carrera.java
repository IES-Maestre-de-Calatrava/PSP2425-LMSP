/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ejercicioscarreracoches;

import java.util.Random;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 6 nov 2024
 */
public class Carrera {
    private static Carrera instance = null;
    private int distancia;
    private boolean carreraAcabada;
    private Carrera(){
        Random rnd = new Random();
        this.distancia = rnd.nextInt(1000)+1;
        this.carreraAcabada = false;
    }
    public int getDistancia() {
        return distancia;
    }
    public synchronized boolean isAcabada(){
        return carreraAcabada;
    }
    private static synchronized void createInstance(){
        if(instance == null){
            instance = new Carrera();
        }
    }
    public static Carrera getInstance(){
        if(instance == null){
            createInstance();
        }
        return instance;
    }
    public synchronized void avanzar(Coche coche){
        if(coche.getnMetros()>= distancia){
           
        }
        
    }
}
