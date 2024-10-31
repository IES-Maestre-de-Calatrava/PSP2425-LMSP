package com.lmsp.maestre.hilos.alternando;
/**
 * Crear un hilo que visualice por pantalla 
 * en un bucle infinito la palabra TAC
 * Dentro del bucle, utiliza el m�todo sleep() 
 * para que nos de tiempo a ver las palabras 
 * que se visualizan cuando lo ejecutemos
 * 
 * @author santa
 *
 */
public class HiloTAC extends Thread {
	
	TicTac tictac;
	public HiloTAC(TicTac tictac) {
		this.tictac = tictac;
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				System.out.println("TAC");
				
				tictac.avisar();
				tictac.esperar();
				
				this.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
