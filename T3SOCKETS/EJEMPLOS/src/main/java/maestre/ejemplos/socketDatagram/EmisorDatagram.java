package socketDatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmisorDatagram {

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Creando socket datagram");
			DatagramSocket datagramsocket = new DatagramSocket();
			System.out.println("Enviando mensaje");
			
			String mensaje = "mensaje desde el emisor";
			
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket datagrama_ = new DatagramPacket (mensaje.getBytes(),
					mensaje.getBytes().length,addr,5555);
			
			datagramsocket.send(datagrama_);
			System.out.println("Mensaje enviado");
			System.out.println("Cerrando el socket datagrama");
			datagramsocket.close();
			
			System.out.println("Terminado");
			
		} catch(IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
