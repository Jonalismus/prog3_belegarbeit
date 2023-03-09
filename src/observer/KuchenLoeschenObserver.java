package observer;

import geschaeftslogik.Model;

public class KuchenLoeschenObserver implements Observer{

    private final Model model;
    private int anzahlKuchen;

    public KuchenLoeschenObserver(Model model){
        this.model = model;
        this.anzahlKuchen = model.getVerkaufobjektListe().size();
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Model) {
            int aktuelleAnzahlKuchen = model.getVerkaufobjektListe().size();
            if (aktuelleAnzahlKuchen < anzahlKuchen) {
                System.out.println("Ein Kuchen wurde entfernt");
            }
            anzahlKuchen = aktuelleAnzahlKuchen;
        }
    }
}
