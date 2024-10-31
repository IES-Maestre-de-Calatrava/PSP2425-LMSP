package com.lmsp.maestre.hilos.alternando;
/**
 * Main hace uso de los hilos TIC TAC
 * �Se visualizan los texto TIC TAC de forma ordenada?
 * @author santa
 *
 */
public class UsaHiloTICTAC {
	
	public static void main (String[] args) throws InterruptedException {
		
		TicTac tictac = new TicTac();
		//Creo los hilos
		HiloTIC hTIC = new HiloTIC(tictac);
		HiloTAC hTAC = new HiloTAC(tictac);
		
		//los arranco
		hTIC.start();
		hTAC.start();
		
                Thread.currentThread().sleep(5000);
                
                hTIC.interrupt();
                hTAC.interrupt();
                
	}

}
