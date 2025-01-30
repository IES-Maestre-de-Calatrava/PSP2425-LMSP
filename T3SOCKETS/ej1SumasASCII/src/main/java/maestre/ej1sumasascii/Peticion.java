/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ej1sumasascii;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 8 ene 2025
 */
public class Peticion extends Thread{
    Socket cliente;
    public Peticion(Socket cliente){
        this.cliente = cliente;
    }
    private int extraerNumero(String linea) {
		
        int numero;
        try {
                numero = Integer.parseInt(linea);
        } catch (NumberFormatException e) {
                numero = 0;
        }

        return numero;
    }
    private int calcular(String entrada) {

        int resultado = 0;
        char[] aChars = entrada.toCharArray();
        for(int i = 0; i<aChars.length;i++){
            resultado += (int)aChars[i];
        }
        
        return resultado;
    }
    private void escuchar() throws IOException{
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = cliente.getInputStream();
            isr = new InputStreamReader(is);
            bf = new BufferedReader(isr);
            String entradas = bf.readLine();
            os = cliente.getOutputStream();
            pw = new PrintWriter(os);
            for(int i =0;i<extraerNumero(entradas);i++){
                String entrada = bf.readLine();
                int result = this.calcular(entrada);
                pw.write(result + "\n");
                pw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }finally {
            close(pw);
            close(os);
            close(bf);
            close(isr);
            close(is);
                                  
        }
    }
    private void close(Closeable socket) {
           try {
                   if (null != socket) {
                           socket.close();
                   }
           } catch (IOException e) {
                   e.printStackTrace();
           }
    }
    public void run(){
        try {
            escuchar();
        } catch (IOException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
