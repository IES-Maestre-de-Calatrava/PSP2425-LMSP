/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.productorconsumidorcola;

/**
 *
 * @author dam2
 */
public class ProductorConsumidorCola {
    private static final int MAX_PRODUCTORES  = 5;
    private static final int MAX_CONSUMIDORES = 7;
    private static final int MAX_ELEMENTOS = 10;
    public static void main(String[] args) {
        Cola colaCompartida = new Cola(MAX_ELEMENTOS);
        for(int i = 0; i<MAX_PRODUCTORES;i++){
            Productor p = new Productor(colaCompartida);
            p.start();
        }
        for(int i = 0; i<MAX_CONSUMIDORES;i++){
            Consumidor c = new Consumidor(colaCompartida);
            c.start();
        }
    }
}
