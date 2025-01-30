import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(9877);
            System.out.println("Arranca");

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Peticion hilo = new Peticion(socket);
                    hilo.start();
                } catch (IOException ex) {
                    throw ex;
                }

            }
        }catch(IOException e) {
            throw e;
        }
        finally {
            try {
                if (null != serverSocket) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
