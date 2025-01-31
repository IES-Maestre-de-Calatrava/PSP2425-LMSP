import java.io.Serializable;

public class Libro implements Serializable {
    private String nombre;
    private int cantidad;

    public Libro(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public synchronized int retirarLibro(int num) {
        if (num <= cantidad) {
            cantidad -= num;
            return num;
        } else{
            int disponible = cantidad;
            cantidad = 0;
            return disponible;
        }
    }

    @Override
    public String toString() {
        return nombre + " y " + cantidad + " unidades.";
    }
}
