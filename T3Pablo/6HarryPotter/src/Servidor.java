import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor {
    public static void main(String[] args) {
        Vector<String> inventario = new Vector<>();
        inventario.add("Varita de Saúco");
        inventario.add("Varita de Acebo y Pluma de Fénix");
        inventario.add("Varita de Vid y Pelo de Unicornio");
        inventario.add("Varita de Fresno y Nervio de Dragón");
        inventario.add("Varita de Cerezo y Pluma de Fénix");
        inventario.add("Varita de Nogal y Pelo de Thestral");
        inventario.add("Varita de Abeto y Pelo de Unicornio");
        inventario.add("Varita de Sauce y Nervio de Dragón");
        inventario.add("Varita de Espino y Pluma de Fénix");
        inventario.add("Varita de Tejo y Pluma de Fénix");

        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Conectado.");
            while (!inventario.isEmpty()) {
                socket = serverSocket.accept();
                Peticion peticion = new Peticion(socket, inventario);
                peticion.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
