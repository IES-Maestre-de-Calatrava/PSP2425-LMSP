/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package maestre.ej1sumasascii;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 * @version 1.0
 * Created on 8 ene 2025
 */
public class Cliente {

    public static void main(String[] args) throws IOException{
        Cliente cliente = new Cliente();
        cliente.enviarPeticion();
    }
    private void enviarPeticion() throws IOException{
        try(Socket socket = new Socket("localhost", 9876);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);){
            enviarCadenas(entrada, salida);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }
    private void enviarCadenas(BufferedReader entrada, PrintWriter salida)throws IOException{
        salida.println(2);
        salida.println("ABC");
        salida.println("ZZ");
        
        String suma1 = entrada.readLine();
        System.out.println(suma1);
        
    }
}
