package org.example;

import org.example.persistence.DBManager;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Peticion extends Thread{
    private Socket socket;
    private ListaUsuariosConectados listaUsuariosConectados;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean running = true;

    public Peticion(Socket socket, ListaUsuariosConectados listaUsuariosConectados){
        this.socket = socket;
        this.listaUsuariosConectados = listaUsuariosConectados;
    }

    @Override
    public void run() {
        escucha();
    }

    private void escucha() {
        try {
            System.out.println("Servidor: Iniciando nueva conexión");

            // Importante: crear primero el ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush(); // Importante: flush inmediato
            ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("Servidor: Streams inicializados");

            while (!socket.isClosed()) {
                Object objeto = ois.readObject();
                procesarObjeto(objeto);
            }

        } catch (EOFException | SocketException e) {
            System.out.println("Servidor: Cliente desconectado");
            listaUsuariosConectados.remove(socket);
        } catch (Exception e) {
            System.out.println("Error en petición: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    private void procesarObjeto(Object objeto) throws IOException{
        if (objeto instanceof Usuario) {
            procesarUsuario((Usuario) objeto);
        } else if (objeto instanceof Mensaje) {
            procesarMensaje((Mensaje) objeto);
        }
    }

    private void procesarUsuario(Usuario usuario) throws IOException {
        System.out.println("Servidor: Procesando usuario: " + usuario.getNombre());
        if (usuario.isLog() && verificarCredenciales(usuario.getNombre(), usuario.getPassword())) {
            usuario.setSession(true);
            listaUsuariosConectados.add(usuario, socket, oos);
            System.out.println("Servidor: Usuario autenticado: " + usuario.getNombre());
            oos.writeObject(usuario);
            oos.flush();
        }
        else if (!usuario.isLog()){
            try {
                insertarUsuario(usuario);
                usuario.setSession(true);
                System.out.println("Servidor: Usuario registrado: " + usuario.getNombre());
                usuario.setSession(true);
                oos.writeObject(usuario);
                oos.flush();
            } catch (SQLException e) {
                String mensaje = new String(e.getMessage());
                oos.writeObject(e.getMessage());
                oos.flush();
            }
        }else{
            System.out.println("Servidor: Usuario no registrado");
        }
    }

    private void procesarMensaje(Mensaje mensaje) throws IOException {
        System.out.println("Servidor: Procesando mensaje tipo: " + mensaje.getTipo());

        switch (mensaje.getTipo()){
            case "VIEWUSERLOG" -> enviarListaUsuarios();
            case "VIEWFRIENDS" -> enviarListaAmigos(mensaje);
            case "ADDFRIEND" -> addAmigo(mensaje.getEmisor(), mensaje.getReceptor());
            case "DELFRIEND" -> eliminarAmigo(mensaje.getEmisor(), mensaje.getReceptor());
            case "GLOBAL" -> enviarMensajeGlobal(mensaje);
            case "SENDFRIENDS" -> enviarMensajeAmigos(mensaje);
            case "SENDPRIVATE" -> enviarMensajePrivado(mensaje);
            case "VIEWCHATFRIEND" -> getHistorialAmigo(mensaje);
            default -> System.out.println("Mensaje no reconocido");
        }
    }

    private void enviarListaUsuarios() throws IOException {
        List<Usuario> usuarios = listaUsuariosConectados.getListUsuariosConectados();
        System.out.println("Servidor: Enviando lista de " + usuarios.size() + " usuarios");
        oos.writeObject(usuarios);
        oos.flush();
    }

    private void enviarListaAmigos(Mensaje mensaje) throws IOException {
        List<Usuario> usuarios = getListaAmigos(mensaje.getEmisor());
        System.out.println("Servidor: Enviando lista de " + usuarios.size() + " amigos");
        oos.writeObject(usuarios);
        oos.flush();
    }

    private void cerrarConexion() {
        try {
            if (ois != null) ois.close();
            if (oos != null) oos.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

    // Métodos de base de datos

    private boolean verificarCredenciales(String username, String password) {
        boolean esValido = false;
        String query = "SELECT CONTRASENA FROM USUARIOS WHERE NOMBRE = ?";

        try (Connection conn = DBManager.getInstance().abrirConexion();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("CONTRASENA");
                // Aquí deberías verificar la contraseña (puedes usar bcrypt o similar)
                esValido = storedPassword.equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DBManager.getInstance().cerrarConexion();
        return esValido;
    }

    public List<Usuario> getListaAmigos(Usuario usuario) {
        List<Usuario> listaAmigos = new ArrayList<>();
        String query = """
        SELECT U.ID, U.NOMBRE 
        FROM AMIGOS A
        JOIN USUARIOS U ON A.AMIGO_ID = U.ID
        WHERE A.USUARIO_ID = ?;
        """;
        usuario.setIdUsuario(getUsuarioByNombre(usuario.getNombre()).getIdUsuario());

        try (Connection conn = DBManager.getInstance().abrirConexion();
             PreparedStatement pst = conn.prepareStatement(query)) {

            // Configurar el parámetro de la consulta
            pst.setInt(1, usuario.getIdUsuario());

            try (ResultSet rs = pst.executeQuery()) {
                // Iterar sobre los resultados
                while (rs.next()) {
                    // Crear un objeto Usuario con los datos del resultado
                    Usuario amigo = new Usuario();
                    amigo.setIdUsuario(rs.getInt("ID"));
                    amigo.setNombre(rs.getString("NOMBRE"));
                    listaAmigos.add(amigo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener la lista de amigos para el usuario "
                    + usuario.getNombre());
        }

        return listaAmigos;
    }

    public boolean addAmigo(Usuario usuario, Usuario usuarioAmigo) {
        // Consulta SQL para agregar la relación de amistad
        String query = """
        INSERT INTO AMIGOS (USUARIO_ID, AMIGO_ID)
        VALUES (?, ?);
        """;
        //usuario.setIdUsuario(getUsuarioByNombre(usuario.getNombre()).getIdUsuario()); Esta lista no es necesaria
        usuarioAmigo.setIdUsuario(getUsuarioByNombre(usuarioAmigo.getNombre()).getIdUsuario());
        Connection conn = null;

        try {
            conn = DBManager.getInstance().abrirConexion();
            // Desactivar auto-commit para manejar la transacción manualmente
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // Insertar la relación de amistad usuario -> amigo
                stmt.setInt(1, usuario.getIdUsuario());
                stmt.setInt(2, usuarioAmigo.getIdUsuario());
                stmt.executeUpdate();

                // Insertar la relación inversa amigo -> usuario
                stmt.setInt(1, usuarioAmigo.getIdUsuario());
                stmt.setInt(2, usuario.getIdUsuario());
                stmt.executeUpdate();
            }

            // Confirmar la transacción
            conn.commit();
            System.out.println("Relación de amistad añadida con éxito entre "
                    + usuario.getNombre() + " y " + usuarioAmigo.getNombre());
            return true; // Operación exitosa

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al agregar la relación de amistad entre "
                    + usuario.getNombre() + " y " + usuarioAmigo.getNombre());
            try {
                // Intentar revertir los cambios en caso de fallo
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false; // Operación fallida
        } finally {
            // Cerrar la conexión después de commit o rollback
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void getHistorialAmigo(Mensaje mensaje) {
        System.out.println("Iniciando búsqueda de historial..."); // Log para depuración
        List<Mensaje> historial = new ArrayList<>();

        String query = """
        SELECT U.NOMBRE AS EMISOR, A.NOMBRE AS RECEPTOR, M.CONTENIDO
        FROM MENSAJES M
        JOIN USUARIOS U ON M.REMITENTE_ID = U.ID
        JOIN USUARIOS A ON M.DESTINATARIO_ID = A.ID
        WHERE (M.REMITENTE_ID = ? AND M.DESTINATARIO_ID = ?) 
           OR (M.REMITENTE_ID = ? AND M.DESTINATARIO_ID = ?)
        ORDER BY M.ID
        """;

        Usuario usuario ,amigo;
        usuario = getUsuarioByNombre(mensaje.getEmisor().getNombre());
        amigo = getUsuarioByNombre(mensaje.getReceptor().getNombre());

        try (Connection conn = DBManager.getInstance().abrirConexion();
             PreparedStatement prepared = conn.prepareStatement(query)) {

            prepared.setInt(1, usuario.getIdUsuario());
            prepared.setInt(2, amigo.getIdUsuario());
            prepared.setInt(3, amigo.getIdUsuario());
            prepared.setInt(4, usuario.getIdUsuario());

            System.out.println("Ejecutando consulta SQL..."); // Log para depuración

            try (ResultSet rs = prepared.executeQuery()) {

                while (rs.next()) {
                    Mensaje mensaje1 = new Mensaje();

                    // Crear objetos Usuario correctamente
                    Usuario emisor = new Usuario();
                    emisor.setNombre(rs.getString("EMISOR"));
                    Usuario receptor = new Usuario();
                    receptor.setNombre(rs.getString("RECEPTOR"));

                    mensaje1.setEmisor(emisor);
                    mensaje1.setReceptor(receptor);
                    mensaje1.setContenido(rs.getString("CONTENIDO"));

                    historial.add(mensaje1);
                }


            }

            System.out.println("Mensajes encontrados: " + historial.size()); // Log para depuración
            oos.writeObject(historial);
            oos.flush();

            System.out.println("Historial enviado al cliente"); // Log para depuración

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            try {
                oos.writeObject(new ArrayList<>());
                oos.flush();
            } catch (IOException ex) {
                System.err.println("Error al enviar respuesta vacía: " + ex.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error al enviar historial: " + e.getMessage());
        }
    }

    private void enviarListaVacia() {
        try {
            oos.reset();
            oos.writeObject(new ArrayList<Mensaje>());
            oos.flush();
            System.out.println("Lista vacía enviada al cliente");
        } catch (IOException e) {
            System.err.println("Error al enviar lista vacía: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void insertarUsuario(Usuario usuario)throws SQLException{

        String query = "INSERT INTO USUARIOS (NOMBRE, CONTRASENA) VALUES (?, ?)";

        try (Connection conn = DBManager.getInstance().abrirConexion();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getPassword()); // Consider hashing the password before saving
            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Servidor: Usuario insertado con éxito: " + usuario.getNombre());
            } else {
                System.out.println("Servidor: No se pudo insertar el usuario: " + usuario.getNombre());
            }
        } catch (SQLException e) {
            throw new SQLException("El usuario ya existe");
        }
    }


    private Usuario getUsuarioByNombre(String nombre){
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";

        try(Connection conexion = DBManager.getInstance().abrirConexion();
            PreparedStatement prepared = conexion.prepareStatement(consulta)){

            prepared.setString(1, nombre);

            ResultSet respuesta = prepared.executeQuery();
            if (respuesta.next()) {
                usuario = new Usuario();
                usuario.setNombre(respuesta.getString("NOMBRE"));
                usuario.setIdUsuario(respuesta.getInt("ID"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }


    private void eliminarAmigo(Usuario usuario, Usuario usuarioAmigo) {
        String query = "DELETE FROM AMIGOS WHERE (USUARIO_ID = ? AND AMIGO_ID = ?) OR (usuario_id = ? AND amigo_id = ?)";
        usuarioAmigo.setIdUsuario(getUsuarioByNombre(usuarioAmigo.getNombre()).getIdUsuario());
        try (Connection conn = DBManager.getInstance().abrirConexion();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, usuario.getIdUsuario());
            pst.setInt(2, usuarioAmigo.getIdUsuario());
            pst.setInt(3, usuarioAmigo.getIdUsuario());
            pst.setInt(4, usuario.getIdUsuario());

            int filasAfectadas = pst.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Amigo eliminado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se ha eliminado el amigo");
            try {
                oos.writeObject("No tienes amigos con ese nombre o tu lista de amigos está vacía");
                oos.flush();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }

    private void enviarMensajeAmigos(Mensaje mensaje) {
        List<Usuario> amigos = getListaAmigos(mensaje.getEmisor());

        for(Usuario u:amigos){
            try {
                // Usar el ObjectOutputStream almacenado en el mapa de streams
                ObjectOutputStream userOos = listaUsuariosConectados.getOutputStream(u.getNombre());
                if(userOos != null && !u.getNombre().equals(mensaje.getEmisor().getNombre())) {
                    userOos.writeObject(mensaje);
                    userOos.flush();
                    System.out.println("Servidor: Mensaje enviado a " + u.getNombre());
                }
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje a " + u.getNombre() + ": " + e.getMessage());
            }
        }
    }



    private void enviarMensajeGlobal(Mensaje mensaje) {
        List<Usuario> usuarios = listaUsuariosConectados.getListUsuariosConectados();

        for(Usuario u:usuarios){
            try {
                // Usar el ObjectOutputStream almacenado en el mapa de streams
                ObjectOutputStream userOos = listaUsuariosConectados.getOutputStream(u.getNombre());
                if(userOos != null && !u.getNombre().equals(mensaje.getEmisor().getNombre())) {
                    userOos.writeObject(mensaje);
                    userOos.flush();
                    System.out.println("Servidor: Mensaje enviado a " + u.getNombre());
                }
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje a " + u.getNombre() + ": " + e.getMessage());
            }
        }
    }

    private void enviarMensajePrivado(Mensaje mensaje){
        List<Usuario> usuarios = listaUsuariosConectados.getListUsuariosConectados();

        // Insertar el mensaje en la base de datos previamente
        String queryInsertMensaje = "INSERT INTO MENSAJES (REMITENTE_ID, DESTINATARIO_ID, CONTENIDO) VALUES (?, ?, ?)";
        Usuario emisor, receptor;
        emisor = getUsuarioByNombre(mensaje.getEmisor().getNombre());
        receptor = getUsuarioByNombre(mensaje.getReceptor().getNombre());

        try (Connection conn = DBManager.getInstance().abrirConexion()) {
            // Desactivar auto-commit para manejar la transacción
            conn.setAutoCommit(false);

            try (PreparedStatement pstInsert = conn.prepareStatement(queryInsertMensaje)) {

                try {
                    pstInsert.setInt(1, emisor.getIdUsuario());
                    pstInsert.setInt(2, receptor.getIdUsuario());
                    pstInsert.setString(3, mensaje.getContenido());
                    pstInsert.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // Confirmar la transacción si todo va bien
                conn.commit();
            } catch (SQLException e) {
                // Revertir la transacción en caso de error
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObjectOutputStream userOos = null;
        try {
            userOos = listaUsuariosConectados.getOutputStream(mensaje.getReceptor().getNombre());
            if (userOos != null) {
                userOos.writeObject(mensaje);
                userOos.flush();
            } else {

                System.out.println("Usuario no encontrado: " + mensaje.getReceptor().getNombre());
            }
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje privado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
