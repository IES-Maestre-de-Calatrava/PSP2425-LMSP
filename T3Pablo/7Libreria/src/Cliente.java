import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String [] libros = {"Harry Potter", "El Hombre Iluminado", "1984", "Cien años de soledad", "El Principito"};

        InetSocketAddress inetSocketAddress;
        Socket socket;
        Libro libro;

        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            inetSocketAddress = new InetSocketAddress("localhost", 12345);
            socket = new Socket();
            socket.connect(inetSocketAddress);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            System.out.println(libros[1]);
            bw.write(libros[1]);
            bw.newLine();
            bw.write(3);
            bw.flush();

            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String mensaje = br.readLine();

            System.out.println(mensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
