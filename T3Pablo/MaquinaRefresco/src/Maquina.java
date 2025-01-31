import java.io.Serializable;
import java.util.ArrayList;

public class Maquina implements Serializable {
    private ArrayList<Refresco> maquina;

    public Maquina() {
        this.maquina = new ArrayList<>();
    }

    public synchronized void addRefresco(Refresco refresco){
        maquina.add(refresco);
    }

    public synchronized Refresco removeRefresco(){
        return maquina.removeLast();
    }

    public ArrayList<Refresco> getMaquina() {
        return maquina;
    }
}
