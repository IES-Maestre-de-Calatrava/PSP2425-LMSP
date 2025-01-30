package org.example;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idUsuario;
    private String nombre;
    private String password;
    private boolean session;
    private boolean log;

    public Usuario(){}

    public Usuario(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
        this.session = false;
        this.log = false;
    }
    public Usuario(String nombre){
        this.nombre = nombre;
    }
    public Usuario(int idUsuario, String nombre, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.session = false;
        this.log = false;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int id){this.idUsuario = id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
