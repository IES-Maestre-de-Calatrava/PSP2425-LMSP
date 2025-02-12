import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    private static Cliente cliente = new Cliente();
    private void ejecucion() throws IOException {
        try(Socket socket = new Socket("localhost",5555)){


        }
    }
    public static void main(String[] args) throws IOException {
        cliente.ejecucion();
    }
}
