import java.io.*;
import java.net.Socket;

public class Peticion extends Thread{
    private Socket socket;
    private Libreria libreria;

    public Peticion(Socket socket, Libreria libreria) {
        this.socket = socket;
        this.libreria = libreria;
    }

    @Override
    public void run() {
        escucha();
    }

    private void escucha() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String nombre = br.readLine();
            int num = br.read();

            Libro libro = libreria.saberLibro(nombre);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            if (libro == null) {
                bw.write("Ese libro no esta aqui");
                bw.flush();
            } else if (libro.getCantidad() == 0) {
                bw.write("El libro " + libro.getNombre() + " esta agotado.");
                bw.flush();
            } else {
                bw.write("Se le ha entregado el libro " + libreria.entregarLibro(nombre, num));
                bw.flush();
            }
            System.out.println(libreria);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
                isr.close();
                is.close();
                os.close();
                osw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
