package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente {
    static ConexionOracle conexionOracle = ConexionOracle.getInstance();
    public static void main(String[] args) {
        InetSocketAddress socketAddress = null;
        Socket cliente = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Empleado empleado = new Empleado("Pablo", 19, "Calle Primo Rivera");
        Empleado empleadoId = new Empleado(1);


        String selectById = "id";
        String selectAll = "all";
        String insert = "insert";

        try {
            socketAddress = new InetSocketAddress("localhost", 12345);
            cliente = new Socket();
            cliente.connect(socketAddress);

//            // SelectAll
//            selectAll(selectAll, empleadoId, cliente);
//
//            // SelectById
//            selectById(selectById, empleadoId, cliente);

            insert(insert, empleado, cliente);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void selectAll(String selectAll, Empleado empleadoId, Socket cliente) throws IOException, ClassNotFoundException {
        Consulta consulta;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        consulta = new Consulta(selectAll, empleadoId);
        oos = new ObjectOutputStream(cliente.getOutputStream());

        oos.writeObject(consulta);
        oos.flush();

        ois = new ObjectInputStream(cliente.getInputStream());

        System.out.println();
        ArrayList<Empleado> respuesta = (ArrayList<Empleado>) ois.readObject();
        for (Empleado empleado1: respuesta) {
            System.out.println(empleado1);
        }
    }


    private static void selectById(String selectAll, Empleado empleadoId, Socket cliente) throws IOException, ClassNotFoundException {
        Consulta consulta;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        consulta = new Consulta(selectAll, empleadoId);
        oos = new ObjectOutputStream(cliente.getOutputStream());

        oos.writeObject(consulta);
        oos.flush();

        ois = new ObjectInputStream(cliente.getInputStream());

        System.out.println();
        Empleado respuesta = (Empleado) ois.readObject();
        System.out.println(respuesta);

    }

    private static void insert(String selectAll, Empleado empleadoId, Socket cliente) throws IOException, ClassNotFoundException {
        Consulta consulta;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        consulta = new Consulta(selectAll, empleadoId);
        oos = new ObjectOutputStream(cliente.getOutputStream());

        oos.writeObject(consulta);
        oos.flush();

        ois = new ObjectInputStream(cliente.getInputStream());

        System.out.println();
        String respuesta = (String) ois.readObject();
        System.out.println(respuesta);

    }
}