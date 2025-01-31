import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    public static String [] nombre = {"Coca Cola", "Sprite", "Fanta", "Tonica", "Nestea", "Cerveza", "Zumo", "Agua"};

    public static void main(String[] args) {
        InetSocketAddress isd = null;
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            isd = new InetSocketAddress("localhost", 12345);
            socket = new Socket();
            socket.connect(isd);


            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            bw.write(5);
            bw.flush();

            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            System.out.println("Refrescos recibidos: ");
            for (int i = 0; i < 5; i++) {
                Refresco refresco = (Refresco) ois.readObject();
                System.out.println("    " + refresco);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
