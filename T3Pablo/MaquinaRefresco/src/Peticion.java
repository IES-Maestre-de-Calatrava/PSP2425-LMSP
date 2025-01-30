import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Peticion extends Thread{

    private Maquina maquina;
    private Socket socket;

    public Peticion(Socket socket, Maquina maquina) {
        this.maquina = maquina;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Hilo");
        escuchar();
    }

    private void escuchar(){
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        ObjectOutputStream oos = null;

        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            int num = br.read();
            System.out.println(num);
            oos = new ObjectOutputStream(socket.getOutputStream());
            for (int i = 0; i < num; i++) {
                Refresco refresco = maquina.removeRefresco();
                System.out.println("Se sacado el refresco: " + refresco);
                oos.writeObject(refresco);
                oos.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
