/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.recursoscompartidos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 31 oct 2024
 */
public class UsarTarea {
    public static void main(String[] args) throws Exception {
        //recurso compartido
        Contador contador = new Contador();
        // Crear dos hilos que comparten contador y hacen lo mismo
        Tarea hilo1 = new Tarea(contador);
        Tarea hilo2 = new Tarea(contador);
        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        // Esperar a que ambos hilos terminen
        hilo1.join();
        hilo2.join();

        // Mostrar el valor final del contador
        System.out.println("Cuenta final: " + contador.getCuenta());
    }
}
