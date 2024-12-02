package socketDatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ReceptorDatagram {

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Creando socket datagram");
			
			InetSocketAddress addr = new InetSocketAddress ("localhost", 5555);
			DatagramSocket datagramSocket = new DatagramSocket(addr);
			
			System.out.println("Recibiendo mensaje");
			
			byte[] mensaje = new byte[25];
			DatagramPacket datagrama1 = new DatagramPacket (mensaje,25);
			datagramSocket.receive(datagrama1);//se bloquea hasta que recibe un mensaje
			
			System.out.println("Mensaje recibido: " + new String(mensaje));
			
			System.out.println("Enviando mensaje");
			
			InetAddress addr2 = InetAddress.getByName("localhost");
			DatagramPacket datagrama2 = new DatagramPacket (mensaje,mensaje.length,addr2,5556);
			datagramSocket.send(datagrama2);
			
			System.out.println("Mensaje enviado");
			System.out.println("Cerrando el socket datagrama");
			datagramSocket.close();
			
			System.out.println("Terminado");
			
		} catch(IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
