package org.example;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaUsuariosConectados implements Serializable {
    private final Map<String, Socket> socketsPorUsuario;
    private final Map<Socket, String> usuarioPorSocket;
    private Map<String, ObjectOutputStream> outputStreams;
    private final List<Usuario> usuarios;

    public ListaUsuariosConectados() {
        this.socketsPorUsuario = new HashMap<>();
        this.usuarioPorSocket = new HashMap<>();
        this.outputStreams = new HashMap<>();
        this.usuarios = new ArrayList<>();
    }

    public synchronized void add(Usuario usuario, Socket socket, ObjectOutputStream oos) {
        socketsPorUsuario.put(usuario.getNombre(), socket);
        usuarioPorSocket.put(socket, usuario.getNombre());
        outputStreams.put(usuario.getNombre(), oos);
        usuarios.add(usuario);
        System.out.println("Usuario añadido: " + usuario.getNombre());
    }

    public synchronized void remove(Socket socket) {
        socketsPorUsuario.remove(usuarioPorSocket.get(socket));
        outputStreams.remove(usuarioPorSocket.get(socket));
        usuarios.removeIf(u -> u.getNombre().equals(usuarioPorSocket.get(socket)));
        System.out.println("Usuario eliminado: " + usuarioPorSocket.get(socket));
        usuarioPorSocket.remove(socket);
    }

    public synchronized List<Usuario> getListUsuariosConectados() {
        return new ArrayList<>(usuarios);
    }

    public synchronized Socket getSocket(String nombreUsuario) {
        return socketsPorUsuario.get(nombreUsuario);
    }

    public synchronized ObjectOutputStream getOutputStream(String nombre) {
        return outputStreams.get(nombre);
    }
}
