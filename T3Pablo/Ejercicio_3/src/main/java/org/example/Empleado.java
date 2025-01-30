package org.example;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Empleado implements Serializable {
    private int id_empleado;
    private String nombre;
    private int edad;
    private String direccion;

    public Empleado() {
    }

    public Empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Empleado(int id_empleado, String nombre, int edad, String direccion) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public Empleado(String nombre, int edad, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id_empleado=" + id_empleado +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
