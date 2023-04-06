package view.observer;

import geschaeftslogik.Model;
import vertrag.Allergen;

import java.util.List;

public class AllergenObserver implements Observer {

    private final Model model;
    private List<Allergen> letzteAllergene;

    public AllergenObserver(Model model) {
        this.model = model;
        this.letzteAllergene = model.allergeneAbrufen(true);
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Model) {
            List<Allergen> aktuelleAllergene = this.model.allergeneAbrufen(true);
            if (!aktuelleAllergene.equals(letzteAllergene)) {
                System.out.println("Es gab Aenderungen an den Allergenen im Automaten:");
                System.out.println("Alte Allergene: " + letzteAllergene);
                System.out.println("Neue Allergene: " + aktuelleAllergene);
                letzteAllergene = aktuelleAllergene;
            }
        }
    }
}
