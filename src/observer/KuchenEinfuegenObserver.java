package observer;

import geschaeftslogik.Model;

public class KuchenEinfuegenObserver implements Observer{

    private final Model model;
    private int anzahlKuchen;

    public KuchenEinfuegenObserver(Model model){
        this.model = model;
        this.anzahlKuchen = model.getVerkaufobjektListe().size();
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Model) {
            int aktuelleAnzahlKuchen = model.getVerkaufobjektListe().size();
            if (aktuelleAnzahlKuchen > anzahlKuchen) {
                System.out.println("Ein Kuchen wurde hinzugefügt");
            }
            anzahlKuchen = aktuelleAnzahlKuchen;
        }
    }
}
