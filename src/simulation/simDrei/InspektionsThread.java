package simulation.simDrei;

import geschaeftslogik.Model;
import vertrag.Verkaufsobjekt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class InspektionsThread extends Thread{
    private final Model model;
    private final Random random = new Random();
    private final Condition condition;
    private final Lock lock;

    public InspektionsThread(Model model, Condition condition, Lock lock) {
        this.model = model;
        this.condition = condition;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                List<Verkaufsobjekt> kuchenListe = model.kuchenAbrufen("kuchen");
                if (!kuchenListe.isEmpty()) {
                    int index = random.nextInt(kuchenListe.size());
                    System.out.println(this.getName() + " Setzt Inspektionsdatum");
                    model.inspektionsDatumSetzen(index);
                } else {
                    System.out.println(this.getName() + " wartet auf Kuchen, da die Liste leer ist");
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
