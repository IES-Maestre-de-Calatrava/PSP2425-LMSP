
package com.maestre.ej7;

import java.util.ArrayList;
import java.util.List;


public class Ej7 {

    public static void main(String[] args) {
        Saluda s = new Saluda();
        Aula profesor = new Aula("Profesor Andrés", true, s);
        Aula alumno1 = new Aula("Juan",false,s);
        Aula alumno2 = new Aula("Pedro",false,s);
        Aula alumno3 = new Aula("Sergio",false,s);
        Aula alumno4 = new Aula("Alicia",false,s);
        Aula alumno5 = new Aula("Freddy",false,s);
        Aula alumno6 = new Aula("Jose",false,s);
        
        alumno1.start();
        alumno2.start();
        alumno3.start();
        alumno4.start();
        alumno5.start();
        alumno6.start();
        profesor.start();
        
        System.exit(0);
        
    }
}
