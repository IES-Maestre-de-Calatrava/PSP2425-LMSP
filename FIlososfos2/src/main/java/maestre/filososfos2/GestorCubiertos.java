/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.filososfos2;

/**
 *
 * @author dam2
 */
public class GestorCubiertos {
    boolean [] palilloLibre;
    public GestorCubiertos(int num){
        this.palilloLibre = new boolean[num];
        for(int i = 0;i<num;i++){
            palilloLibre[i]=true;
        }
    }
    private boolean estaLibre(int posPalillo){
        return (palilloLibre[posPalillo]);
    }
    public synchronized boolean cogerPalillos(int pos1, int pos2){
        boolean booCoger=false;
        if(estaLibre(pos1) && estaLibre(pos2)){
            palilloLibre[pos1]=false;
            palilloLibre[pos2]=false;
            System.out.println("Se han cogido los palillos "+pos1+" y "+pos2);
            booCoger=true;
        }
        return booCoger;
    }
    public synchronized void soltarPalillos(int pos1, int pos2){
        palilloLibre[pos1]=true;
        palilloLibre[pos2]=true;
        System.out.println("Se han soltado los palillos "+pos1+" y "+pos2);

    }
}
