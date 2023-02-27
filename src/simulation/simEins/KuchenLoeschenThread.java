package simulation.simEins;

import geschaeftslogik.Model;
import vertrag.Verkaufsobjekt;

import java.util.List;
import java.util.Random;

public class KuchenLoeschenThread extends Thread {
    private final Model model;
    private final Random random;

    public KuchenLoeschenThread(Model model) {
        this.model = model;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            List<Verkaufsobjekt> kuchenListe = model.kuchenAbrufen("kuchen");
            if (!kuchenListe.isEmpty()) {
                int index = random.nextInt(kuchenListe.size());
                Verkaufsobjekt kuchen = kuchenListe.get(index);
                int fachnummer = kuchen.getFachnummer();
                model.verkaufsObjektLoeschen(fachnummer);
                System.out.println(this.getName() + " Kuchen geloescht" + kuchen);
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e){
                throw  new RuntimeException(e);
            }
        }
    }
}
