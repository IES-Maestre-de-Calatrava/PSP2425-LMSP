package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EmpleadoDAO {
    public static void insertarEmpleado(ConexionOracle bbdd, Empleado empleado) throws SQLException {
        String nombre        = empleado.getNombre();
        int    edad          = empleado.getEdad();
        String direccion     = empleado.getDireccion();

        bbdd.insert("insert into Empleado (NOMBRE, EDAD, DIRECCION) values(?, ?, ?)", nombre, edad, direccion);
    }

    public static Empleado selectById(ConexionOracle bbdd, int empleado) throws SQLException {
        Optional<ResultSet> rs = bbdd.select("SELECT * FROM EMPLEADO WHERE ID_EMPLEADO=?", empleado);
        Empleado empleado1 = new Empleado();
        if (rs.isPresent()) {
            while (rs.get().next()) {

                empleado1.setId_empleado(rs.get().getInt("ID_EMPLEADO"));
                empleado1.setNombre(rs.get().getString("NOMBRE"));
                empleado1.setEdad(rs.get().getInt("EDAD"));
                empleado1.setDireccion(rs.get().getString("DIRECCION"));
            }
        }
        return empleado1;
    }

    public static ArrayList<Empleado> selectAll(ConexionOracle bbdd) throws SQLException {
        Optional<ResultSet> rs = bbdd.select("SELECT * FROM EMPLEADO");
        ArrayList<Empleado> empleados = new ArrayList<>();

        if (rs.isPresent()) {
            while (rs.get().next()) {
                empleados.add( new Empleado(rs.get().getInt("ID_EMPLEADO"),
                rs.get().getString("NOMBRE"),
                rs.get().getInt("EDAD"),
                rs.get().getString("DIRECCION")));
            }
        }
        return empleados;
    }
}
