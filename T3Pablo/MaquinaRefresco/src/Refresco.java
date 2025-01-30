import java.io.Serializable;
import java.util.Random;

public class Refresco implements Serializable {
    private String nombre;

    public Refresco() {
        Random random = new Random();
        int num = random.nextInt(Cliente.nombre.length);
        this.nombre = Cliente.nombre[num];
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
