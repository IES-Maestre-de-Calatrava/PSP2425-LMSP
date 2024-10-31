/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.lmsp.psp.lanzadorprocesos.sumador;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 13 sept 2024
 */
public class Sumador {
    public static int sumar(int n1, int n2){
        return (n1+n2);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Sumador main: ");
        int n1= Integer.parseInt(args[0]);
        int n2= Integer.parseInt(args[1]);
        int suma= Sumador.sumar(n1, n2);
        System.out.println(suma);
    }

}


