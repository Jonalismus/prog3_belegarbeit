package geschaeftslogik;

import cli.observer.Subject;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Model extends Subject {


    private final List<Hersteller> herstellerListe;

    public List<Hersteller> getHerstellerListe() {
        return herstellerListe;
    }

    private final List<Verkaufsobjekt> verkaufobjektListe;

    public List<Verkaufsobjekt> getKuchenListe() {
        return verkaufobjektListe;
    }


    public int getKapazitaet() {return kapazitaet;
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
    public synchronized boolean herstellerEinfuegen(Hersteller hersteller) {
        for (Hersteller h : herstellerListe) {
            if (h.getName().equals(hersteller.getName()) || hersteller.getName().equals("")) {
                return false;
            }
        }
        herstellerListe.add(hersteller);
        return true;
    }

    /*
    Methode zum Einfuegen von Kuchen
     */
    public synchronized boolean  verkaufsObjektEinfuegen(Verkaufsobjekt verkaufsobjekt) {
        // Prüfen, ob die Gesamtkapazitaet nicht überschritten wird
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
                verkaufobjektListe.add(verkaufsobjekt);
                notifyObservers();
                return true;
            }
        }
        return false;
    }

    // Abruf aller Hersteller mit der Anzahl ihrer Kuchen
    public synchronized List<geschaeftslogik.Hersteller> abrufenDerHersteller() {
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
    public synchronized List<Verkaufsobjekt> kuchenAbrufen(String kuchentyp) {
        List<Verkaufsobjekt> ergebnisListe = new LinkedList<>();
        if (kuchentyp == null || kuchentyp.equals("Kuchen") || kuchentyp.equals("kuchen")) {
            for(Verkaufsobjekt verkaufsobjekt : verkaufobjektListe){
                LocalDateTime jetzt = LocalDateTime.now();
                Duration verstrichen = Duration.between(verkaufsobjekt.getEinfuegedatum(), jetzt);
                verkaufsobjekt.setVerbleibendeHaltbarkeit(verkaufsobjekt.getHaltbarkeit().minus(verstrichen).toDays());
                ergebnisListe.add(verkaufsobjekt);
            }
            return ergebnisListe;
        }
        for (Verkaufsobjekt verkaufsobjekt : verkaufobjektListe) {
            if (verkaufsobjekt.getTyp().equals(kuchentyp.substring(0, 1).toUpperCase() + kuchentyp.substring(1).toLowerCase())) {
                LocalDateTime jetzt = LocalDateTime.now();
                Duration verstrichen = Duration.between(verkaufsobjekt.getEinfuegedatum(), jetzt);
                verkaufsobjekt.setVerbleibendeHaltbarkeit(verkaufsobjekt.getHaltbarkeit().minus(verstrichen).toDays());
                ergebnisListe.add(verkaufsobjekt);
            }
        }
        return ergebnisListe;
    }

    // Abruf aller vorhandenen oder nicht vorhandenen Allergene im Automaten
    public synchronized List<Allergen> allergeneAbrufen(boolean vorhanden) {
        Allergen[] alleAllergene = Allergen.values();

        List<Allergen> ergebnisListe = new LinkedList<>();
        for (Allergen allergen : alleAllergene) {
            boolean istVorhanden = false;
            for (Verkaufsobjekt verkaufsobjekt : verkaufobjektListe) {
                if (verkaufsobjekt.getAllergene().contains(allergen)) {
                    istVorhanden = true;
                    break;
                }
            }
            if (istVorhanden == vorhanden) {
                ergebnisListe.add(allergen);
            }
        }
        return ergebnisListe;
    }


    //Setzen des Datums der letzten Uberpruefung (inspektionsdatum)
    public synchronized void ispektionsDatumSetzen(int fachnummer){
        for(Verkaufsobjekt verkaufsobjekt : verkaufobjektListe){
            if(verkaufsobjekt.getFachnummer() == fachnummer){
                LocalDateTime localDateTime = LocalDateTime.now().withSecond(0).withNano(0);
                Date inspektion = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                verkaufsobjekt.setInspektionsdatum(inspektion);
            }
        }
    }

    // Entfernen eines Herstellers
    public synchronized boolean herstellerLoeschen(String name) {
        for (Hersteller h : herstellerListe) {
            if (h.getName().equals(name)) {
                herstellerListe.remove(h);
                return true;
            }
        }
        return false;
    }

    // Entfernen eine Kuchens, wenn Loeschen Erfolgreich, werden die Fachnummer neu vergeben
    public synchronized boolean verkaufsObjektLoeschen(int fachnummer) {
        for (Verkaufsobjekt v : verkaufobjektListe) {
            if (v.getFachnummer() == fachnummer) {
                verkaufobjektListe.remove(v);
                for(int i = 0; i < verkaufobjektListe.size(); i++){
                    verkaufobjektListe.get(i).setFachnummer(i+1);
                }
                notifyObservers();
                return true;
            }
        }
        return false;
    }


}
