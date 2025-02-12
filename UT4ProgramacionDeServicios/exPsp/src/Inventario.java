import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    private Map<String,Integer> evento = new HashMap<>();
    public Inventario(){
        evento.put("Concierto",15);
        evento.put("Teatro",25);
        evento.put("Cine",20);
        evento.put("Deportes",40);
    }
    public synchronized List<String> listaEventosDisp(){
        List<String> resultados = new ArrayList<>();
        
        return resultados;
    }
    public synchronized int dameEntradas(Entradas entradas){
        int entradasEntregadas = 0;
        if(evento.containsKey(entradas.getEvento())){
            if(evento.get(entradas.getEvento()) >= entradas.getNumEntradas()){
                evento.replace(entradas.getEvento(), evento.get(entradas.getEvento()) - entradas.getNumEntradas());
                entradasEntregadas= entradas.getNumEntradas();
            }else{
                entradasEntregadas = evento.get(entradas.getEvento());
                evento.replace(entradas.getEvento(), 0);
            }
        }
        return entradasEntregadas;
    }
    public Map<String, Integer> getMap(){
        return this.evento;
    }

}
