/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maestre.producorconsumidor2;

import java.util.LinkedList;

/**
 *
 * @author dam2
 */
public class RecursoCompartido {
    private int numMax;
    LinkedList<Integer> cola;
    public RecursoCompartido(int max){
        cola = new LinkedList<Integer>();
        this.numMax=max;
    }
    private synchronized boolean estaLLeno(){
        
        return (cola.size() == this.numMax);
    }
    public synchronized boolean addCola(int num){
        boolean booAdd = false;
        if(!estaLLeno()){
            cola.add(num);
            System.out.println("Numero "+num+" añadido a la cola (productor).");
            booAdd = true;
        }
        else{
            System.out.println("LLENO");
        }
        return booAdd;
    }
    public synchronized boolean removeCola(){
        boolean booRemove = false;
        if(!cola.isEmpty()){
            int numRemove = cola.remove(); 
            System.out.println("Numero "+numRemove+" recuperado de la cola (consumidor).");
            booRemove = true;
        }
        return booRemove;
    }
}
