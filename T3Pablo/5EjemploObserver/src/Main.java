public class Main {
    public static void main(String[] args) {
        Avion avion = new Avion("howa", "izquierda", 100, 500);
        AvionPerseguidor vehiculo = new AvionPerseguidor("Mitsubishi", "izquierda", 100, 500);
        avion.addObserver(vehiculo);
        avion.subir();
        avion.bajar();
        avion.girar("izquierda");
        avion.girar("derecha");
        avion.acelerar();
        avion.frenar();
        System.out.println(avion);
    }
}
