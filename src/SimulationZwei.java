import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import observer.KapazitaetsObserver;
import observer.KuchenEinfuegenObserver;
import observer.KuchenLoeschenObserver;
import simulation.simEins.KuchenEinfuegenThread;
import simulation.simEins.KuchenLoeschenThread;

import java.util.Scanner;

public class SimulationZwei {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Kapazitaet ein:");
        int kapazitaet = scanner.nextInt();
        System.out.println("Bitte geben Sie die Anzahl der Threads ein:");
        int anzahlThreads = scanner.nextInt();
        Model model = new Model(kapazitaet);
        model.herstellerEinfuegen(new Hersteller("ThreadHersteller"));
        KuchenEinfuegenObserver kuchenEinfuegenObserver = new KuchenEinfuegenObserver(model);
        model.add(kuchenEinfuegenObserver);
        KuchenLoeschenObserver kuchenLoeschenObserver = new KuchenLoeschenObserver(model);
        model.add(kuchenLoeschenObserver);
        KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
        model.add(kapazitaetsObserver);
        for (int i = 0; i < anzahlThreads; i++) {
            KuchenEinfuegenThread einfuegenThread = new KuchenEinfuegenThread(model);
            KuchenLoeschenThread loeschenThread = new KuchenLoeschenThread(model);
            einfuegenThread.setName("EinfuegenThread-" + (i+1));
            loeschenThread.setName("LoeschenThread-" + (i+1));
            einfuegenThread.start();
            loeschenThread.start();
        }
    }
}
