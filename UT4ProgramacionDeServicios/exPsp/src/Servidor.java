import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    private Inventario inventario = new Inventario();
    private static Servidor servidor = new Servidor();

    private void ejecucion() throws IOException {
        System.out.println("Arrancando servidor...");
        try(ServerSocket serverSocket = new ServerSocket(5555)){
            System.out.println("Servidor a la escucha.");
            while(true){
                new ClientHandler(serverSocket.accept(), inventario).start();
                System.out.println("Peticion aceptada.");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        servidor.ejecucion();
    }
}