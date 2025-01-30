import java.util.HashMap;

public class Libreria {
    private HashMap<String, Libro> librosHashMap = new HashMap<>();

    public Libreria() {
        librosHashMap.put("Harry Potter", new Libro("Harry Potter", 20));
        librosHashMap.put("El Hombre Iluminado", new Libro("El Hombre Iluminado", 15));
        librosHashMap.put("1984", new Libro("1984", 10));
        librosHashMap.put("Cien años de soledad", new Libro("Cien años de soledad", 12));
        librosHashMap.put("El Principito", new Libro("El Principito", 5));
    }

    public synchronized Libro saberLibro(String nombre){
        return librosHashMap.get(nombre);
    }

    public synchronized Libro entregarLibro(String n, int cantidad) {
        Libro libro = librosHashMap.get(n);
        if (libro == null) {
            return null;
        }
        else {
            Libro libro1 = new Libro(libro.getNombre(), librosHashMap.get(n).retirarLibro(cantidad));
            return libro1;
        }
    }

    @Override
    public String toString() {
        StringBuilder lista = new StringBuilder();
        for (Libro libro: librosHashMap.values()) {
            lista.append(libro);
            lista.append("\n");
        }
        return lista.toString();
    }
}
