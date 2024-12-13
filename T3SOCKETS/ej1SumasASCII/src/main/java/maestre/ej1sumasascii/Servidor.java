/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maestre.ej1sumasascii;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LMSP by Lucas Manuel Serrano Perez
 */
public class Servidor {
    
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

    public void escuchar() throws IOException{
            System.out.println("Arrancado el servidor");
            ServerSocket socketEscucha = null;
            Socket conexion=null;
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader bf = null;
            OutputStream os = null;
            PrintWriter pw = null;
            try {
                    socketEscucha = new ServerSocket(9876);
                    while (true) {
                            try {
                                    conexion = socketEscucha.accept();
                                    System.out.println("Conexion recibida!");
                                    is = conexion.getInputStream();
                                    isr = new InputStreamReader(is);
                                    bf = new BufferedReader(isr);
                                    String entradas = bf.readLine();
                                    os = conexion.getOutputStream();
                                    pw = new PrintWriter(os);
                                    for(int i =0;i<extraerNumero(entradas);i++){
                                        String entrada = bf.readLine();
                                        int result = this.calcular(entrada);
                                        pw.write(result + "\n");
                                        pw.flush();
                                    }

                                    
                                    
                            } catch (IOException e) {
                                    System.out.println("Error al aceptar conexion "+e.getMessage());
                                    e.printStackTrace();
                                    throw e;
                            } finally {
                                    close(pw);
                                    close(os);
                                    close(bf);
                                    close(isr);
                                    close(is);
                                    close(conexion);
                            }
                    }
            } catch (IOException e) {
                    System.out.println("No se pudo poner un socket a escuchar "+e.getMessage());
                    e.printStackTrace();
                    throw e;
            } finally {
                    close(socketEscucha);
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


    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor();
        server.escuchar();
    }
}
