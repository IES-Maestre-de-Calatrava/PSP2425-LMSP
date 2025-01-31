import java.util.Observable;
import java.util.Observer;

public class Avion extends Observable {

    private String nombre;
    private String direccion;
    private int velocidad;
    private int altura;

    public Avion(String nombre, String direccion, int velocidad, int altura) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.velocidad = velocidad;
        this.altura = altura;
    }

    /*
     *  Único observador
     *  En el caso de que hubiera varios, podrían guardarse
     *  como colección
     */
    private Observer observer;

    public Avion(String nombre, int velocidad, int altura) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getAltura() {
        return altura;
    }

    public void girar(String nuevaDireccion) {
        if (nuevaDireccion.equals("izquierda") || nuevaDireccion.equals("derecha")) {
            this.direccion = nuevaDireccion;
            notifyObservers("direccion"); // Notificas a los observadores sobre el cambio.

        } else {
            System.out.println("Dirección inválida: " + nuevaDireccion);
        }
    }


    public void subir() {
        altura = altura + 100;
        notifyObservers("subir");
    }
    public void bajar() {
        altura = altura - 100;
        notifyObservers("bajar");
    }

    public void acelerar() {
        velocidad = velocidad + 200;
        notifyObservers("acelerar");
    }


    public void frenar() {
        velocidad = velocidad - 200;
        notifyObservers("frenar");
    }

    @Override
    public void addObserver(Observer o) {
        this.observer = o;
    }


    @Override
    public void notifyObservers(Object object) {
        if (observer != null) {
            observer.update(this, object);
        }
    }

    @Override
    public String toString() {
        return "Avion{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", velocidad=" + velocidad +
                ", altura=" + altura +
                '}';
    }
}
