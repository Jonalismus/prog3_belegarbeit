package geschaeftslogik;

import vertrag.Verkaufsobjekt;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Model {


    private final List<Hersteller> herstellerListe;

    public List<Hersteller> getHerstellerListe() {
        return herstellerListe;
    }

    private final List<Verkaufsobjekt> verkaufobjektListe;

    public List<Verkaufsobjekt> getKuchenListe() {
        return verkaufobjektListe;
    }


    int kapazitaet;


    public Model(int kapazitaet) {
        this.kapazitaet = kapazitaet;
        this.herstellerListe = new LinkedList<>();
        this.verkaufobjektListe = new LinkedList<>();
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
        if (verkaufobjektListe.size() >= kapazitaet) {
            return false;
        }

        // Prüfen, ob der Kuchen zu einem bereits existierenden Hersteller gehört
        if (herstellerListe.size() == 0) {
            return false;
        }
        for (Hersteller h : herstellerListe) {
            if (h.equals(verkaufsobjekt.getHersteller())) {
                // Fachnummer vergeben
                verkaufsobjekt.setFachnummer(verkaufobjektListe.size() + 1);

                // Einfuegedatum vergeben
                LocalDateTime date = LocalDateTime.now();
                verkaufsobjekt.setEinfuegedatum(date);

                // Kuchen in die Liste einfuegen
                return verkaufobjektListe.add(verkaufsobjekt);
            }
        }
        return false;
    }

    // Abruf aller Hersteller mit der Anzahl ihrer Kuchen
    public List<geschaeftslogik.Hersteller> abrufenDerHersteller() {
        int count = 0;
        List<Verkaufsobjekt> listeKuchen = new LinkedList<>(verkaufobjektListe);
        for (Hersteller h : herstellerListe) {
            for (int i = 0; i < verkaufobjektListe.size(); i++) {
                if (listeKuchen.get(i).getHersteller().equals(h)) {
                    count++;
                }
            }
            h.setAnzahlKuchen(count);
            count = 0;
        }
    return herstellerListe;
    }

    /*
    Gibt aus, welche Kuchen im Automaten sind. Wird ein Kuchentyp angegeben, werden nur Kuchen von diesen
    Kuchentypen aufgelistet
     */
    public List<Verkaufsobjekt> kuchenAbrufen(String kuchentyp) {
        if (kuchentyp == null || kuchentyp.equals("Kuchen") || kuchentyp.equals("kuchen")) {
            return new LinkedList<>(verkaufobjektListe);
        }
        List<Verkaufsobjekt> ergebnisListe = new LinkedList<>();
        for (Verkaufsobjekt verkaufsobjekt : verkaufobjektListe) {
            if (verkaufsobjekt.getTyp().equals(kuchentyp.substring(0, 1).toUpperCase() + kuchentyp.substring(1).toLowerCase())) {
                ergebnisListe.add(verkaufsobjekt);
            }
        }
        return ergebnisListe;
    }

    // Abruf aller vorhandenen oder nicht vorhandenen Allergene im Automaten


    //Setzen des Datums der letzten Uberpruefung (inspektionsdatum)
    public void ispektionsDatumSetzen(int fachnummer){
        for(Verkaufsobjekt verkaufsobjekt : verkaufobjektListe){
            if(verkaufsobjekt.getFachnummer() == fachnummer){
                LocalDateTime localDateTime = LocalDateTime.now().withSecond(0).withNano(0);
                Date inspektion = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                verkaufsobjekt.setInspektionsdatum(inspektion);
            }
        }
    }

    // Entfernen eines Herstellers
    public boolean herstellerLoeschen(String name) {
        for (Hersteller h : herstellerListe) {
            if (h.getName().equals(name)) {
                herstellerListe.remove(h);
                return true;
            }
        }
        return false;
    }

}
