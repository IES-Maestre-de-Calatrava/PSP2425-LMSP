/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.recursoscompartidos;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */

class Contador {
    private int cuenta = 0;
    // Método sincronizado para incrementar la cuenta
    public synchronized void incrementar() {
        cuenta++;
    }
    // Método para obtener el valor de la cuenta
    public synchronized int getCuenta() {
        return cuenta;
    }
}

