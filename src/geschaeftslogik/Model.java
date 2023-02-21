package geschaeftslogik;

import vertrag.Hersteller;
import vertrag.Verkaufsobjekt;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Model {


    private final List<Hersteller> herstellerListe;
    public List<Hersteller> getHerstellerListe() {
        return herstellerListe;
    }
    private final List<Verkaufsobjekt> kuchenListe;
    public List<Verkaufsobjekt> getKuchenListe() { return kuchenListe;
    }


    int kapazitaet;


    public Model(int kapazitaet) {
        this.kapazitaet = kapazitaet;
        this.herstellerListe = new LinkedList<>();
        this.kuchenListe = new LinkedList<>();
    }

    /*
    Anlegen von Herstellern; dabei muss sichergestellt sein, dass kein Name
    mehr als einmal vorkommt
     */
    public boolean herstellerEinfuegen(Hersteller hersteller) {
        for (Hersteller h : herstellerListe) {
            if (h.getName().equals(hersteller.getName())) {
                return false;
            }
        }
        herstellerListe.add(hersteller);
        return true;
    }

    /*
    Methode zum Einfuegen von Kuchen
     */
    public boolean verkaufsObjektEinfuegen(Verkaufsobjekt verkaufsobjekt) {
        // Prüfen, ob die Gesamtkapazität nicht überschritten wird
        if (kuchenListe.size() >= kapazitaet) {
            return false;
        }

        // Prüfen, ob der Kuchen zu einem bereits existierenden Hersteller gehört
        if(herstellerListe.size() == 0){
            return false;
        }
        for (Hersteller h : herstellerListe) {
            if (h.equals(verkaufsobjekt.getHersteller())) {
                // Fachnummer vergeben
                verkaufsobjekt.setFachnummer(kuchenListe.size() + 1);

                // Einfuegedatum vergeben
                LocalDateTime date = LocalDateTime.now();
                verkaufsobjekt.setEinfuegedatum(date);

                // Kuchen in die Liste einfuegen
                return kuchenListe.add(verkaufsobjekt);
            }
        }
        return false;
    }


}
