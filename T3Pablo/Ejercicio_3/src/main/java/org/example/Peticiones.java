package org.example;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Peticiones extends Thread{
    ConexionOracle bbdd = new ConexionOracle(); // deberia crearse en este caso una conexion para cada cliente
    private Socket socket;

    public Peticiones(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            escuchar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void escuchar() throws IOException, ClassNotFoundException {
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Empleado empleado = null;
        ArrayList<Empleado> empleados = new ArrayList<>();
        bbdd.abrirConexion();
        try {
            System.out.println("Conexion recibida!");
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            Consulta consulta = (Consulta) ois.readObject();
            System.out.println("Objeto recibido: " + consulta);
            switch (consulta.getConsulta()) {
                case "id" -> {
                    empleado = EmpleadoDAO.selectById(bbdd, consulta.getRecurso().getId_empleado());
                    System.out.println("Caso");
                    System.out.println(empleado);
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    oos.writeObject(empleado);
                }
                case "all" -> {
                    System.out.println("Paco");
                    empleados = EmpleadoDAO.selectAll(bbdd);
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    oos.writeObject(empleados);
                }
                case "insert" -> {
                    EmpleadoDAO.insertarEmpleado(bbdd, consulta.getRecurso());
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    oos.writeObject("El empleado ha sido insertado.");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        bbdd.cerrarConexion();
    }

}
