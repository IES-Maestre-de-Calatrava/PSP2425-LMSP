package socketDatagram;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class EmisorDatagram {

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Creando socket datagram");
                        InetSocketAddress addrOwn = new InetSocketAddress ("localhost", 5556);
			DatagramSocket datagramsocket = new DatagramSocket(addrOwn);
			System.out.println("Enviando mensaje");
			
			String mensaje = "mensaje desde el emisor";
			
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket datagrama_ = new DatagramPacket (mensaje.getBytes(),
					mensaje.getBytes().length,addr,5555);
			
			datagramsocket.send(datagrama_);
			System.out.println("Mensaje enviado");
                        System.out.println("Recibiendo mensaje");
			
			byte[] mensajeByte = new byte[25];
			DatagramPacket datagrama1 = new DatagramPacket (mensajeByte,mensajeByte.length);
			datagramsocket.receive(datagrama1);//se bloquea hasta que recibe un mensaje
			
			System.out.println("Mensaje recibido: " + new String(mensajeByte));
                        
			System.out.println("Cerrando el socket datagrama");
			datagramsocket.close();
			
			System.out.println("Terminado");
			
		} catch(IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
