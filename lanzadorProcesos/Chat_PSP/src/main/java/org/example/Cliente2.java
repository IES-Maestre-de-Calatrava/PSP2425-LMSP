package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Cliente2 {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;
    private static final Scanner scanner = new Scanner(System.in);
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public static void main(String[] args) {
        Cliente2 cliente = new Cliente2();
        cliente.iniciar();

    }

    public void iniciar(){
        try {
            conectar();
            menuPrincipal();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    private void conectar() throws IOException {
        System.out.println("Cliente: Conectando al servidor...");
        socket = new Socket(HOST, PUERTO);
        System.out.println("Cliente: Conectado al servidor");

        // Importante: crear primero el ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // Importante: flush inmediato
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Cliente: Streams inicializados");
    }

    private void cerrarConexion() {
        try {
            if (ois != null) ois.close();
            if (oos != null) oos.close();
            if (socket != null) socket.close();
            System.out.println("Cliente: Conexión cerrada");
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }


    private void menuPrincipal() {

        while (!socket.isClosed()) {
            try {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrarse");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 0 -> cerrarConexion();
                    case 1 -> iniciarSesion();
                    case 2 -> registrarse();
                    default -> System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void iniciarSesion() {
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, password);
        usuario.setLog(true);

        System.out.println("Cliente: Enviando credenciales...");
        try {
            oos.writeObject(usuario);
            oos.flush();

            usuario = (Usuario) ois.readObject();
        }catch (IOException e) {
            System.out.println("Error: Enviando credenciales...");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Recibiendo respuesta...");
        }

        if (usuario.isSession()) {
            System.out.println("¡Login exitoso!");
            menuUsuario(usuario);
        } else {
            System.out.println("Credenciales incorrectas");
        }

    }

    private void registrarse(){
        System.out.println("=== REGISTRO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, password);
        usuario.setLog(false);

        System.out.println("Cliente: Enviando solicitud de registro...");

        try {
            oos.writeObject(usuario);
            oos.flush();

            Object object = ois.readObject();
            String mensajeError = "";
            if(object instanceof Usuario){
                usuario = (Usuario) object;
            }else{
                mensajeError = (String) object;
            }

            if(usuario.isSession()){
                menuUsuario(usuario);
            }else{
                System.out.println(mensajeError);
            }

        } catch (IOException e) {
            System.out.println("Error: Enviando solicitud de registro... " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Recibiendo respuesta... " + e.getMessage());
        }

    }

    private void menuUsuario(Usuario usuario) {

        while (!socket.isClosed()) {
            try {
                mostrarMenuUsuario();
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 0 -> cerrarConexion();
                    case 1 -> listarUsuariosConectados();
                    case 2 -> listarAmigos(usuario);
                    case 3 -> addAmigo(usuario);
                    case 4 -> eliminarAmigo(usuario);
                    case 5 -> mensajeGlobal(usuario);
                    case 6 -> mensajeAmigos(usuario);
                    case 7 -> mensajePrivado(usuario);
                    case 8 -> historialMensajes(usuario);
                    default -> System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void mostrarMenuUsuario() {
        System.out.println("\n=== MENÚ USUARIO ===");
        System.out.println("1. Ver usuarios conectados");
        System.out.println("2. Ver amigos");
        System.out.println("3. Añadir amigo");
        System.out.println("4. Eliminar amigo");
        System.out.println("5. Chat global");
        System.out.println("6. Chat con amigos");
        System.out.println("7. Chat privado");
        System.out.println("8. Ver mensajes amigo");
        System.out.println("0. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
    }

    private void listarUsuariosConectados() {
        System.out.println("Cliente: Solicitando lista de usuarios...");
        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("VIEWUSERLOG");

        Object respuesta;
        try {
            oos.writeObject(mensaje);
            oos.flush();

            respuesta = ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        if (respuesta instanceof List<?>) {
            List<Usuario> usuarios = (List<Usuario>) respuesta;
            System.out.println("\n=== Usuarios Conectados ===");
            for (Usuario u : usuarios) {
                System.out.println("- " + u.getNombre());
            }
        }
    }

    private void listarAmigos(Usuario usuario)  {
        System.out.println("Cliente: Solicitando lista de amigos...");
        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("VIEWFRIENDS");
        mensaje.setEmisor(usuario);

        Object respuesta;
        try {
            oos.writeObject(mensaje);
            oos.flush();

            respuesta = ois.readObject();
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (respuesta instanceof List<?>) {
            List<Usuario> amigos = (List<Usuario>) respuesta;
            System.out.println("\n=== Amigos ===");
            for (Usuario u : amigos) {
                System.out.println("- " + u.getNombre());
            }
        }
    }

    private void addAmigo(Usuario usuario){
        System.out.println("\n=== Añadir amigos ===");
        listarUsuariosConectados();
        System.out.print("Nombre de tu amigo: ");
        String nombre = scanner.nextLine();

        Usuario amigo = new Usuario(nombre);
        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("ADDFRIEND");
        mensaje.setEmisor(usuario);
        mensaje.setReceptor(amigo);

        try {
            oos.writeObject(mensaje);
            oos.flush();
            System.out.println("Cliente: Solicitud de amistad enviada");
        } catch (IOException e) {
            System.out.println("Error al enviar solicitud de amistad: " + e.getMessage());
        }
    }

    private void eliminarAmigo(Usuario usuario){
        System.out.println("\n=== Eliminar amigos ===");
        listarAmigos(usuario);
        System.out.print("Nombre de tu amigo: ");
        String nombre = scanner.nextLine();

        Usuario amigo = new Usuario(nombre);

        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("DELFRIEND");

        mensaje.setReceptor(amigo);
        mensaje.setEmisor(usuario);

        try {
            oos.writeObject(mensaje);
            oos.flush();
            System.out.println("Cliente: Solicitud de eliminación enviada");
        } catch (IOException e) {
            System.out.println("Error al enviar solicitud de eliminado: " + e.getMessage());
        }
    }

    private void mensajeGlobal(Usuario usuario) {
        System.out.println("\n=== Chat Global ===");
        System.out.println("(Escribe 'exit' para salir)");

        Thread receptorMensajes = new Thread(() -> {
            try {
                while (true) {
                    Object objetoRecibido = ois.readObject();
                    if (objetoRecibido instanceof Mensaje) {
                        Mensaje mensajeRecibido = (Mensaje) objetoRecibido;
                        // Mostrar el mensaje con el nombre del emisor
                        System.out.println("\n" + mensajeRecibido.getEmisor().getNombre() + ": " +
                                mensajeRecibido.getContenido());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Conexión cerrada");
            }
        });
        receptorMensajes.setDaemon(true); // El hilo se cerrará cuando el programa principal termine
        receptorMensajes.start();

        String contenido;
        while (true) {
            contenido = scanner.nextLine();

            if (contenido.equalsIgnoreCase("exit")) {
                break;
            }

            Mensaje mensaje = new Mensaje();
            mensaje.setEmisor(usuario);
            mensaje.setTipo("GLOBAL");
            mensaje.setContenido(contenido);

            try {
                oos.writeObject(mensaje);
                oos.flush();
                // Mostrar el mensaje propio
                System.out.println("Tú: " + contenido);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje: " + e.getMessage());
                break;
            }
        }
    }

    private void mensajeAmigos(Usuario usuario){
        System.out.println("\n=== Chat Amigos ===");
        System.out.println("(Escribe 'exit' para salir)");

        Thread receptorMensajes = new Thread(() -> {
            try {
                while (true) {
                    Object objetoRecibido = ois.readObject();
                    if (objetoRecibido instanceof Mensaje) {
                        Mensaje mensajeRecibido = (Mensaje) objetoRecibido;
                        // Mostrar el mensaje con el nombre del emisor
                        System.out.println("\n" + mensajeRecibido.getEmisor().getNombre() + ": " +
                                mensajeRecibido.getContenido());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Conexión cerrada");
            }
        });
        receptorMensajes.setDaemon(true); // El hilo se cerrará cuando el programa principal termine
        receptorMensajes.start();

        String contenido;
        while (true) {
            contenido = scanner.nextLine();

            if (contenido.equalsIgnoreCase("exit")) {
                break;
            }

            Mensaje mensaje = new Mensaje();
            mensaje.setEmisor(usuario);
            mensaje.setTipo("SENDFRIENDS");
            mensaje.setContenido(contenido);

            try {
                oos.writeObject(mensaje);
                oos.flush();
                // Mostrar el mensaje propio
                System.out.println("Tú: " + contenido);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje: " + e.getMessage());
                break;
            }
        }
    }

    private void mensajePrivado(Usuario usuario){
        System.out.println("\n=== Chat Privado ===");
        System.out.println("(Escribe 'exit' para salir)");
        System.out.print("Nombre de tu amigo: ");
        String nombre = scanner.nextLine();

        Thread receptorMensajes = new Thread(() -> {
            try {
                while (true) {
                    Object objetoRecibido = ois.readObject();
                    if (objetoRecibido instanceof Mensaje) {
                        Mensaje mensajeRecibido = (Mensaje) objetoRecibido;
                        // Mostrar el mensaje con el nombre del emisor
                        System.out.println("\n" + mensajeRecibido.getEmisor().getNombre() + ": " +
                                mensajeRecibido.getContenido());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Conexión cerrada");
            }
        });
        receptorMensajes.setDaemon(true); // Cerrar hilo cuando el programa principal termine.
        receptorMensajes.start();

        String contenido;
        while (true) {
            contenido = scanner.nextLine();

            if (contenido.equalsIgnoreCase("exit")) {
                break;
            }

            Mensaje mensaje = new Mensaje();
            mensaje.setEmisor(usuario);
            mensaje.setTipo("SENDPRIVATE");
            mensaje.setContenido(contenido);
            mensaje.setReceptor(new Usuario(nombre));

            try {
                oos.writeObject(mensaje);
                oos.flush();
                // Mostrar mensaje emisor
                System.out.println("Tú: " + contenido);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje: " + e.getMessage());
                break;
            }
        }
    }

    private void historialMensajes(Usuario usuario){
        System.out.println("\n=== Historial de mensajes ===");
        System.out.print("Nombre de tu amigo: ");
        String nombre = scanner.nextLine();

        Usuario amigo = new Usuario(nombre);

        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("VIEWCHATFRIEND");
        mensaje.setEmisor(usuario);
        mensaje.setReceptor(amigo);

        try {
            oos.writeObject(mensaje);
            oos.flush();

            Object respuesta = ois.readObject();
            if (respuesta instanceof List<?>) {
                List<Mensaje> mensajes = (List<Mensaje>) respuesta;
                System.out.println("\n=== Mensajes ===");
                for (Mensaje m : mensajes) {
                    System.out.println("- " + m.getEmisor().getNombre() + ": " + m.getContenido());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al solicitar historial de mensajes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al recibir historial de mensajes: " + e.getMessage());
        }
    }
}