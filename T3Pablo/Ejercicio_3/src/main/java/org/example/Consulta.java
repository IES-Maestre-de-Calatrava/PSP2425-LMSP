package org.example;

import java.io.Serializable;

public class Consulta implements Serializable {

    private String consulta;
    private Operaciones operacion;
    private Empleado recurso;


    // Cambiar en casa operacion
    public Consulta(String consulta, Empleado recurso) {
        this.consulta = consulta;
        this.recurso = recurso;
    }

    public Consulta(Operaciones operacion, Empleado empleado){
        this.operacion = operacion;
        this.recurso = empleado;
    }

    public Consulta() {
    }

    public String getConsulta() {
        return consulta;
    }
    public Empleado getRecurso() {
        return recurso;
    }


    @Override
    public String toString() {
        return "Consulta{" +
                "consulta='" + consulta + '\'' +
                ", recurso=" + recurso +
                '}';
    }
}
