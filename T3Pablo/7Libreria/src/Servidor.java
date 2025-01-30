import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        Libreria libreria;

        try {
            serverSocket = new ServerSocket(12345);
            socket = new Socket();
            libreria = new Libreria();
            System.out.println("Conectado.");
            while (true){
                socket = serverSocket.accept();
                Peticion peticion = new Peticion(socket, libreria);
                peticion.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
