/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lmsp.psp.multiplicador;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class Multiplicador {

    public static void main(String[] args) {
        int n1= Integer.parseInt(args[0]);
        int n2= Integer.parseInt(args[1]);
        int result= Multiplicador.multiplicar(n1, n2);
        System.out.println(result);
    }
    
    public static int multiplicar(int n1, int n2){
        return (n1*n2);
    }
}
