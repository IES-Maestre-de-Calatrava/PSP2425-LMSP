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
    
    

    

    public void escuchar() throws IOException{
            System.out.println("Arrancado el servidor");
            ServerSocket socketEscucha = null;
            Socket conexion=null;
            try {
                    socketEscucha = new ServerSocket(9876);
                    while (true) {
                            try {
                                    System.out.println("Esperando peticion");
                                    conexion = socketEscucha.accept();
                                    System.out.println("Conexion recibida!");
                                    Peticion hilo = new Peticion(conexion);
                                    hilo.run();
                               
                            } catch (IOException e) {
                                    System.out.println("Error al aceptar conexion "+e.getMessage());
                                    e.printStackTrace();
                                    throw e;
                            } finally {
                
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
