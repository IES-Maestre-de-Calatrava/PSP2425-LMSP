package org.example.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/chat-t6";
    private final String USER = "root";
    private final String PASSWORD = "toor";
    private static DBManager instancia;

    // Constructor privado para evitar instanciación externa
    private DBManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    public static DBManager getInstance() {
        if (instancia == null) {
            synchronized (DBManager.class) {
                if (instancia == null) {
                    instancia = new DBManager();
                }
            }
        }
        return instancia;
    }


    public Connection abrirConexion() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al abrir la conexión a la base de datos", e);
        }
        return connection;
    }


    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión a la base de datos", e);
        }
    }
}
