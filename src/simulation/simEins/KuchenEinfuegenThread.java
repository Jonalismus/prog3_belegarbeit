package simulation.simEins;

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

import static vertrag.Allergen.Erdnuss;


public class KuchenEinfuegenThread extends Thread{

    private final Model model;
    private final Random random;

    private final Hersteller hersteller = new Hersteller("ThreadHersteller");
    private final BigDecimal preis = new BigDecimal("3.20");
    private final Duration haltbarkeit = Duration.ofDays(3);
    private final List<Allergen> allergens = List.of(Erdnuss);
    private final Lock lock;

    public KuchenEinfuegenThread(Model model, Lock lock, long seed) {
        this.model = model;
        this.lock = lock;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        int naherwerte = 123;
        String sorte = "Butter";
        String sorteZwei = "Erdbeere";
        while (true) {
            lock.lock();
            try {
                Verkaufsobjekt kuchen = random.nextBoolean() ? new Obsttorte(hersteller, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei) : new Kremkuchen(hersteller, preis, naherwerte, haltbarkeit, allergens, sorte);
                System.out.println(this.getName() + " Probiert Kuchen hinzufuegen");
                model.verkaufsObjektEinfuegen(kuchen);
            } finally {
                lock.unlock();
            }
        }
    }
}
