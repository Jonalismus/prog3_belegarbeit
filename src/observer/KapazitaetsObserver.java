package observer;

import geschaeftslogik.Model;

public class KapazitaetsObserver implements Observer {

   private final Model model;

    public KapazitaetsObserver(Model model){
        this.model = model;
    }

    @Override
    public synchronized void update(Subject subject) {
        if (subject instanceof Model) {
            int benutzeKapazitaet = this.model.getKuchenListe().size();
            double percentage = (double) benutzeKapazitaet / this.model.getKapazitaet() * 100;
            if (percentage >= 90) {
                System.out.println("ACHTUNG: Kapazitaet zu " + (int) percentage + "% ausgelastet!");
            }
        }
    }
}
