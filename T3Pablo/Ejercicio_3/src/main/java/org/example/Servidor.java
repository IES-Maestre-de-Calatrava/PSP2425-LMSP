package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor = null;
        Peticiones peticiones = null;

        try {
            servidor = new ServerSocket(12345);

            while (true) {
                Socket socket = servidor.accept();
                peticiones = new Peticiones(socket);
                peticiones.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != servidor) {
                    servidor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
