import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Peticion extends Thread{

    private static final Map<String, String> respuestas;

    static {
        // Inicializamos un mapa con las preguntas y respuestas
        respuestas = new HashMap<>();
        respuestas.put("¿cual es la capital de portugal?", "Lisboa");
        respuestas.put("¿quien escribio don quijote de la mancha ?", "Miguel de Cervantes");
        respuestas.put("¿cual es el rio mas largo del mundo?", "Amazonas");
        respuestas.put("¿en que año llego el hombre a la luna?", "1969");
        respuestas.put("¿cual es el idioma mas hablado en el mundo?", "chino mandarin");
    }

    Socket socket;
    public Peticion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        escuchar();
    }

    private void escuchar() {
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            System.out.println("Conexion recibida!");
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            String cadena = (String) ois.readObject();
            System.out.println(cadena);

            oos.writeObject(responderPregunta(cadena));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String responderPregunta(String pregunta) {
        System.out.println(pregunta);
        System.out.println(respuestas.get(pregunta));
        return respuestas.get(pregunta);
    }



}
