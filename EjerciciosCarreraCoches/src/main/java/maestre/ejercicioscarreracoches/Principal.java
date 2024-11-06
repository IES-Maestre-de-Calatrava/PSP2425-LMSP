/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.ejercicioscarreracoches;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class Principal {

    public static void main(String[] args) {
        Carrera c = Carrera.getInstance();
        System.out.println("Distancia carrera: "+c.getDistancia());
    }
}
