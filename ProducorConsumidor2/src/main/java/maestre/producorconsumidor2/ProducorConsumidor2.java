/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.producorconsumidor2;

/**
 *
 * @author dam2
 */
public class ProducorConsumidor2 {
    private static final int MAX_PRODUCTORES = 5;
    private static final int MAX_CONSUMIDORES = 7;
    private static final int MAX_ELEM = 10;
    public static void main(String[] args) {
        RecursoCompartido cola = new RecursoCompartido(MAX_ELEM);
        for(int i = 0; i < MAX_PRODUCTORES; i++){
            Productor p = new Productor(cola);
            p.start();
        }
        for(int i = 0; i < MAX_CONSUMIDORES; i++){
            Consumidor c = new Consumidor(cola);
            c.start();
        }
        
    }
}
