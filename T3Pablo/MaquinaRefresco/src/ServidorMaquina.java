import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class ServidorMaquina {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Maquina maquina = new Maquina();

            for (int i = 0; i < 10; i++) {
                maquina.addRefresco(new Refresco());
            }

            System.out.println("Conectado");
            while (!maquina.getMaquina().isEmpty()) {
                 Socket socket = serverSocket.accept();
                 Peticion peticion = new Peticion(socket, maquina);
                 peticion.start();
                System.out.println(maquina.getMaquina().size());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
