import java.io.Serializable;

public class Peticion implements Serializable {
    private String tipo;
    private Entradas entradas;
    public Peticion(String tipo, Entradas entradas){
        this.tipo = tipo;
        this.entradas = entradas;
    }
    public String getTipo(){
        return this.tipo;
    }
    public Entradas getEntradas(){
        return this.entradas;
    }
}
