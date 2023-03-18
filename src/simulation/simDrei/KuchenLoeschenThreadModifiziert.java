package simulation.simDrei;

import geschaeftslogik.Model;
import vertrag.Verkaufsobjekt;

import java.util.Comparator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class KuchenLoeschenThreadModifiziert extends Thread {
    private final Model model;
    private final Lock lock;
    private final Condition condition;

    public KuchenLoeschenThreadModifiziert(Model model, Lock lock, Condition condition) {
        this.model = model;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (model.kuchenAbrufen("kuchen").isEmpty()) {
                    System.out.println("Kuchenliste ist leer " + this.getName() + " kann nichts loeschen");
                    condition.await();
                }
                Verkaufsobjekt kuchen = model.kuchenAbrufen("kuchen").stream()
                        .min(Comparator.comparing(Verkaufsobjekt::getInspektionsdatum))
                        .orElse(null);
                if (kuchen != null) {
                    System.out.println(this.getName() + " Probiert Kuchen zu loeschen: " + kuchen);
                    model.verkaufsObjektLoeschen(kuchen.getFachnummer());
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
