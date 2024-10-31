package com.lmsp.maestre.hilos.alternando;
/**
 * Crear un hilo que visualice por pantalla 
 * en un bucle infinito la palabra TIC
 * Dentro del bucle, utiliza el m�todo sleep() 
 * para que nos de tiempo a ver las palabras 
 * que se visualizan cuando lo ejecutemos
 * 
 * @author santa
 *
 */
public class HiloTIC extends Thread {
	
	TicTac tictac;
	public HiloTIC(TicTac tictac) {
		this.tictac = tictac;
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				System.out.println("TIC");
				
				tictac.avisar();
				tictac.esperar();
				
				this.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}			
		}
	}
}
