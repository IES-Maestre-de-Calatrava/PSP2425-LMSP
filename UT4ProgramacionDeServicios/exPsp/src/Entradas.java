import java.io.Serializable;

public class Entradas implements Serializable {
    private String evento;
    private int numEntradas;
    public Entradas(String evento, int numEntradas){
        this.evento = evento;
        this.numEntradas = numEntradas;
    }
    public String getEvento(){
        return this.evento;
    }
    public int getNumEntradas(){
        return this.numEntradas;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
