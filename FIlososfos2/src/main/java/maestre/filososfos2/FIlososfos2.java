/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.filososfos2;

/**
 *
 * @author dam2
 */
public class FIlososfos2 {
    private static final int NUM_FILOSOFOS = 5;
    private static final int NUM_CUBIERTOS = 5;
    public static void main(String[] args) {
        GestorCubiertos cubierto = new GestorCubiertos(NUM_CUBIERTOS);
        Filosofo[] mesa = new Filosofo[NUM_FILOSOFOS];
        Filosofo f;
        int posPalilloIzq,posPalilloDer;
        for(int i = 0; i < mesa.length; i++){
            if(i == 0){
                posPalilloIzq = NUM_CUBIERTOS -1;
                posPalilloDer = 0;
            }
            else{
                posPalilloIzq = i - 1;
                posPalilloDer = i;
            }
            f = new Filosofo("Filosofo "+(i+1),cubierto,posPalilloIzq,posPalilloDer);
            mesa[i] = f;
        }
        //ARRANCAR HILOS
        for(int i = 0; i < mesa.length;i++){
            mesa[i].start();
        }
    }
}
