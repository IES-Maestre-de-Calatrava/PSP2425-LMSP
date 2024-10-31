package com.lmsp.maestre.hilos.alternando;

import com.lmsp.maestre.hilos.alternando.HiloTAC;
import com.lmsp.maestre.hilos.alternando.HiloTIC;

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
                
                Thread.currentThread().sleep(2000);
                
                hTIC.interrupt();
                hTIC.interrupt();
		
	}

}
