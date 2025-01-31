import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPreguntas {
    public static void main(String[] args) {



        ServerSocket socket = null;


        try {
            socket = new ServerSocket(12345);
            while (true) {
                Socket peticion = socket.accept();
                Peticion peticion1 = new Peticion(peticion);
                peticion1.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != socket) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
