import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Cliente {
    public static void main(String[] args) {
        InetSocketAddress isa;
        Socket socket;
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        try {
            isa = new InetSocketAddress("localhost", 12345);
            socket = new Socket();
            socket.connect(isa);

            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String varita = br.readLine();
            System.out.println(varita);
        } catch (SocketException w) {
            System.out.println("Se acabo la reparticion de varitas");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
