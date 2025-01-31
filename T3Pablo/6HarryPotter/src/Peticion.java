import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class Peticion extends Thread{
    private Socket socket;
    private Vector<String> inventario;

    public Peticion(Socket socket, Vector<String> inventario) {
        this.socket = socket;
        this.inventario = inventario;
    }

    @Override
    public void run() {
        escuchar();
    }

    public void escuchar(){
        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;

        try {
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            if (!inventario.isEmpty()) {
                String varita = inventario.removeLast();
                bw.write(varita);
                bw.newLine();
                bw.flush();
                System.out.println("Se ha otorgado la varita " + varita);
            } else {
                // Inventario vacío, notificar al cliente
                bw.write("No hay más varitas disponibles.\n");
                bw.newLine();
                bw.newLine();
                System.out.println("No hay más varitas disponibles.");
            }
            // Cerrar los recursos después de procesar
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
