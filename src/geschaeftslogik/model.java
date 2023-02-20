package geschaeftslogik;

import vertrag.Hersteller;

import java.util.LinkedList;
import java.util.List;

public class model {

    private List<Hersteller> herstellerListe = new LinkedList<>();

    public model(){
    }

    /*
    Anlegen von Herstellern; dabei muss sichergestellt sein, dass kein Name
    mehr als einmal vorkommt
     */
    public boolean herstellerEinfuegen(Hersteller hersteller){
        for (Hersteller h : herstellerListe) {
            if (h.getName().equals(hersteller.getName())) {
                return false;
            }
        }
        herstellerListe.add(hersteller);
        return true;
    }

}
