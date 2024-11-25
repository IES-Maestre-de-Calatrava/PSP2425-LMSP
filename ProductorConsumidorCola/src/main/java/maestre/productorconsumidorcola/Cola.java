/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.productorconsumidorcola;

import java.util.LinkedList;

/**
 *
 * @author dam2
 */
public class Cola {
    private int numElemMax;
    LinkedList<Integer> cola;
    public Cola(int max){
        cola = new LinkedList<Integer>();
        this.numElemMax = max;
    }
    private synchronized boolean estaLLena(){
        return (cola.size() == this.numElemMax);
    }
    public synchronized boolean encolar(int num){
        boolean booEncolar=false;
        if(!estaLLena()){
            cola.add(num);
            booEncolar = true;
        }
        return booEncolar;
    }
    public synchronized boolean desencolar(){
        boolean booDesencola=false;
        if(!cola.isEmpty()){
            int numDes = cola.remove();
            booDesencola = true;
            System.out.println("Consumidor recupero el numero:"+numDes);
        }
        return booDesencola;
    }
}
