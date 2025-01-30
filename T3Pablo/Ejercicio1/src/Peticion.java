import java.io.*;
import java.net.Socket;

public class Peticion extends Thread{

    private Socket socket;

    public Peticion(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        escuchar();
    }

    private void escuchar(){
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStream os = null;
        PrintWriter pw = null;

        try {
            System.out.println("Conexion recibida!");
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String numero = br.readLine();
            String [] array = new String[Integer.parseInt(numero)];
            Integer [] sumas = new Integer[Integer.parseInt(numero)];

            for (int i = 0; i < array.length; i++) {
                array[i] = br.readLine();
                sumas[i] = palabra(array[i]);

            }

            os = socket.getOutputStream();
            pw = new PrintWriter(os);

            for (Integer suma : sumas) {
                pw.println(suma);
            }
            pw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private int palabra(String cadena) {
        int resultado = 0;
        char[] cadenaArray = cadena.toCharArray();
//        for (int i = 0; i < cadenaArray.length; i++) {
//            resultado = resultado + cadena.charAt(i);
//        }

        for(char c: cadena.toCharArray()){
            resultado = resultado + (int)c;
        }

        return resultado;
    }
}