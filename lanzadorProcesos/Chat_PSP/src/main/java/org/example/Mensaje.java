package org.example;

import java.io.Serializable;
import java.util.List;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id_mensaje;
    private String tipo;
    private String contenido;
    private Usuario emisor;
    private Usuario receptor;
    private List<Usuario> destinatarios;

    public Mensaje() {}

    // Getters y setters
    public int getIdMensaje() { return id_mensaje; }
    public void setIdMensaje(int id_mensaje) { this.id_mensaje = id_mensaje; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Usuario getEmisor() { return emisor; }
    public void setEmisor(Usuario emisor) { this.emisor = emisor; }
    public Usuario getReceptor() { return receptor; }
    public void setReceptor(Usuario receptor) { this.receptor = receptor; }
    public List<Usuario> getDestinatarios() { return destinatarios; }
    public void setDestinatarios(List<Usuario> destinatarios) { this.destinatarios = destinatarios; }
}
