import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private Inventario inventario;
    public ClientHandler(Socket socket,Inventario inventario){
        this.socket = socket;
        this.inventario = inventario;
    }

    @Override
    public void run(){
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            while(socket.isConnected()){
                Object object = in.readObject();
                if(object instanceof Peticion){
                    Peticion peticion = (Peticion) object;
                    if(peticion.getTipo() == "EVENTOS"){
                        List<String> lista = inventario.listaEventosDisp();
                        out.writeObject(lista);
                        out.flush();
                    }else if(peticion.getTipo() == "ENTRADAS"){
                        Entradas entradas = peticion.getEntradas();
                        if(inventario.dameEntradas(entradas) > 0){
                            out.writeObject(entradas);
                            out.flush();
                        }else{

                        }
                    }
                }else{
                    System.out.println("No es un objeto válido");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
