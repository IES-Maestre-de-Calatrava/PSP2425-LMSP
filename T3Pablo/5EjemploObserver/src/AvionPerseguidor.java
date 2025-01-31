import java.util.Observable;
import java.util.Observer;

public class AvionPerseguidor implements Observer {

    private String nombre;
    private String direccion;
    private int velocidad;
    private int altura;

    public AvionPerseguidor(String nombre, String direccion, int velocidad, int altura) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.velocidad = velocidad;
        this.altura = altura;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Avion) {
            Avion avion = (Avion) o;

            if (arg instanceof String) {
                String cambio = (String) arg;
                if (cambio.equals("subir")) {
                    System.out.println("El avión perseguidor ha visto que su altura ha subido a " + avion.getAltura() + "m");
                } else if (cambio.equals("bajar")) {
                    System.out.println("El avión perseguidor ha visto que su altura ha bajado a " + avion.getAltura() + "m");
                } else if (cambio.equals("direccion")) {
                    this.direccion = avion.getDireccion();
                    System.out.println("El avión perseguidor ha visto que su dirección ha cambiado a " + this.direccion);
                } else if (cambio.equals("acelerar")) {
                    this.velocidad = avion.getVelocidad();
                    System.out.println("El avión perseguidor ha visto que su velocidad ha aumentado a " + this.velocidad + " km/h");
                } else if (cambio.equals("frenar")) {
                    this.velocidad = avion.getVelocidad();
                    System.out.println("El avión perseguidor ha visto que su velocidad ha disminuido a " + this.velocidad + " km/h");
                }
            }
        }
    }
}
