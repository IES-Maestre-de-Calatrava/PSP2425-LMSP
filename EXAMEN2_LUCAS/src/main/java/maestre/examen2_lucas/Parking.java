/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.examen2_lucas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 25 nov 2024
 * 
 * Recurso compartido Parking, con un numero limitado de plazas
 */
public class Parking {
    //Atributos
    private boolean[] plazaOcupada;
    /**
     * Constructor que crea el array booleano plazaOcupada con el tamanio del parametro y lo inicializa a false
     * @param numPlazas Numero de plazas de nuestro Parking
     */
    public Parking(int numPlazas){
        this.plazaOcupada=new boolean[numPlazas];
        for(int i = 0; i<numPlazas;i++){
            plazaOcupada[i]=false;
        }
    }
    public int plazasLibres(){
        int resultado = 0;
        for(int i = 0; i<plazaOcupada.length;i++){
            if(plazaOcupada[i] == false){
                resultado++;
            }
        }
        return resultado;
    }
    public synchronized int entrar(){
        int numPlaza=-1;
        if(plazasLibres() > 0){
            boolean aparcado = false;
            for(int i = 0;!aparcado && i<plazaOcupada.length;i++){
                if(plazaOcupada[i] == false){
                    numPlaza = i;
                    plazaOcupada[i] = true;
                    aparcado = true;
                }
            }
            notifyAll();
        }else{
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        return numPlaza;
    }
    public synchronized void salir(int numPlaza){
        plazaOcupada[numPlaza] = false;
    }

    
    public void muestra() {
        System.out.print("Parking: [");
        for(int i = 0; i<plazaOcupada.length;i++){
            if (i!= plazaOcupada.length-1){
                System.out.print(plazaOcupada[i]+", ");
            }
            else{
                System.out.print(plazaOcupada[i]);
            }
        }
        System.out.println("]");
    }
    
    
}
