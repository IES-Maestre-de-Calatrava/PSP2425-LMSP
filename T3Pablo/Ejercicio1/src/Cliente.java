import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = null;
        Socket cliente = null;
        BufferedReader bfr = null;
        PrintWriter pw = null;
        InputStreamReader isr = null;

        try {
            inetSocketAddress = new InetSocketAddress("localhost", 9877);
            cliente = new Socket();
            cliente.connect(inetSocketAddress);

            String number = "2";

            String cadena = "ABC";
            String cadena2 = "ZZ";
            String [] array = {cadena, cadena2};
            // Abrir canal de escritura
            pw = new PrintWriter(cliente.getOutputStream());
            pw.println(number);
            for (int i = 0; i < Integer.parseInt(number); i++) {
                pw.println(array[i]);
            }
            pw.flush();

            // Abrir canal de lectura
            isr = new InputStreamReader(cliente.getInputStream());
            bfr = new BufferedReader(isr);

            for (int i = 0; i < Integer.parseInt(number); i++) {

                String resultado = bfr.readLine();
                System.out.println(array[i] + " es: " + resultado);
            }
        } catch (IOException e) {
            throw e;
        }
        finally {
            close(bfr);
            close(isr);
            close(pw);
            close(cliente);
        }
    }

    private static void close(Closeable socket) {
        try {
            if (null != socket) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}