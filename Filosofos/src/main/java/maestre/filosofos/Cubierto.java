/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.filosofos;

/**
 *
 * @author dam2
 */
public class Cubierto {
    boolean[] palilloLibre;
    public Cubierto(int num){
        palilloLibre = new boolean[num];
        for(int i = 0; i<num; i++){
            palilloLibre[i]=true;
        }
    }
    public synchronized boolean cogerPalillos(int pos1, int pos2){
        boolean booCoger=false;
        if(palilloLibre[pos1] && palilloLibre[pos2]){
            booCoger=true;
            palilloLibre[pos1]=false;
            palilloLibre[pos2]=false;
            System.out.println("Se han cogido los palillos");
        }
        return booCoger;
    }
    public synchronized void soltarPalillos(int pos1, int pos2){
        palilloLibre[pos1]=true;
        palilloLibre[pos2]=true;
        System.out.println("Se han soltado los palillos"); 
    }
}
