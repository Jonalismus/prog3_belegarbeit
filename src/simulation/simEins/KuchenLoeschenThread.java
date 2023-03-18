package simulation.simEins;

import geschaeftslogik.Model;
import vertrag.Verkaufsobjekt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class KuchenLoeschenThread extends Thread {
    private final Model model;
    private final Random random;
    private final Lock lock;

    public KuchenLoeschenThread(Model model, Lock lock, long seed) {
        this.model = model;
        this.lock = lock;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                List<Verkaufsobjekt> kuchenListe = model.kuchenAbrufen("kuchen");
                if (!kuchenListe.isEmpty()) {
                    int index = random.nextInt(kuchenListe.size());
                    Verkaufsobjekt kuchen = kuchenListe.get(index);
                    int fachnummer = kuchen.getFachnummer();
                    System.out.println(this.getName() + " Probiert Kuchen zu loeschen: " + kuchen);
                    model.verkaufsObjektLoeschen(fachnummer);
                } else {
                    System.out.println("Kuchenliste ist leer " + this.getName() + " kann nichts löschen");
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
