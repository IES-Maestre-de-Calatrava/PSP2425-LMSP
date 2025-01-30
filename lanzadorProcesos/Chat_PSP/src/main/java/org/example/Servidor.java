package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO = 12345;
    private ServerSocket serverSocket;
    private final ListaUsuariosConectados listaUsuariosConectados;

    public Servidor() {
        this.listaUsuariosConectados = new ListaUsuariosConectados();
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }

    public void iniciar() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor: Iniciado en puerto " + PUERTO);

            while (!serverSocket.isClosed()) {
                System.out.println("Servidor: Esperando conexiones...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Servidor: Nueva conexión aceptada");

                Peticion peticion = new Peticion(clientSocket, listaUsuariosConectados);
                peticion.start();
            }
        } catch (IOException e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
    }
}
