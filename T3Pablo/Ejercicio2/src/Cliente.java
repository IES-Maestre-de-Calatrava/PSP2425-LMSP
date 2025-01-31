import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente {
    public static void main(String[] args) {
        String [] preguntas =
                {       "¿cual es la capital de portugal?",
                        "¿quien escribio don quijote de la mancha ?",
                        "¿cual es el rio mas largo del mundo?",
                        "¿en que año llego el hombre a la luna?",
                        "¿cual es el idioma mas hablado en el mundo?"};

        InetSocketAddress socketAddress = null;
        Socket socket = null;
        ObjectOutput objectOutput = null;
        ObjectInputStream ois = null;

        try {
            Random random = new Random();
            String pregunta = preguntas[random.nextInt(preguntas.length)];

            socketAddress = new InetSocketAddress("localhost", 12345);
            socket = new Socket();
            socket.connect(socketAddress);
            objectOutput = new ObjectOutputStream(socket.getOutputStream());

            objectOutput.writeObject(pregunta);
            objectOutput.flush();

            ois = new ObjectInputStream(socket.getInputStream());

            System.out.println(pregunta);
            String respuesta = (String) ois.readObject();
            System.out.println(respuesta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}