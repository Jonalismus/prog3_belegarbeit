package simulation.simDrei;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Kremkuchen;
import geschaeftslogik.Model;
import geschaeftslogik.Obsttorte;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

import static vertrag.Allergen.Erdnuss;

public class KuchenEinfuegenThreadModifiziert extends Thread {

    private final Model model;
    private final Random random;

    private final Hersteller hersteller = new Hersteller("ThreadHersteller");
    private final BigDecimal preis = new BigDecimal("3.20");
    private final Duration haltbarkeit = Duration.ofDays(3);
    private final List<Allergen> allergens = List.of(Erdnuss);
    private final Lock lock;
    private final Condition condition;

    public KuchenEinfuegenThreadModifiziert(Model model, Lock lock, Condition condition) {
        this.model = model;
        this.lock = lock;
        this.condition = condition;
        this.random = new Random();
    }

    @Override
    public void run() {
        int naherwerte = 123;
        String sorte = "Butter";
        String sorteZwei = "Erdbeere";
        while (true) {
            lock.lock();
            try {
                while (model.getKuchenListe().size() >= model.getKapazitaet()) {
                    System.out.println("Kuchenautomat ist voll " + this.getName() + " wartet...");
                    condition.await();
                }
                Verkaufsobjekt kuchen = random.nextBoolean() ? new Obsttorte(hersteller, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei) : new Kremkuchen(hersteller, preis, naherwerte, haltbarkeit, allergens, sorte);
                System.out.println(this.getName() + " Fuegt einen Kuchen hinzu");
                model.verkaufsObjektEinfuegen(kuchen);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
