package observer;

import geschaeftslogik.Model;

public class KuchenLoeschenObserver implements Observer{

    private final Model model;
    private int anzahlKuchen;

    public KuchenLoeschenObserver(Model model){
        this.model = model;
        this.anzahlKuchen = model.getKuchenListe().size();
    }

    @Override
    public synchronized void update(Subject subject) {
        if (subject instanceof Model) {
            int aktuelleAnzahlKuchen = model.getKuchenListe().size();
            if (aktuelleAnzahlKuchen < anzahlKuchen) {
                System.out.println("Ein Kuchen wurde entfernt");
            }
            anzahlKuchen = aktuelleAnzahlKuchen;
        }
    }
}
