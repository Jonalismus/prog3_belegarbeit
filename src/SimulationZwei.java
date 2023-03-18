import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import view.observer.KapazitaetsObserver;
import view.observer.KuchenEinfuegenObserver;
import view.observer.KuchenLoeschenObserver;
import simulation.simEins.KuchenEinfuegenThread;
import simulation.simEins.KuchenLoeschenThread;
import vertrag.Verkaufsobjekt;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationZwei {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Kapazitaet ein:");
        int kapazitaet = scanner.nextInt();
        System.out.println("Bitte geben Sie die Anzahl der Threads ein:");
        int anzahlThreads = scanner.nextInt();
        Lock lock = new ReentrantLock();
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(kapazitaet, verkaufsobjektLinkedList, herstellerLinkedList);
        model.herstellerEinfuegen(new Hersteller("ThreadHersteller"));
        KuchenEinfuegenObserver kuchenEinfuegenObserver = new KuchenEinfuegenObserver(model);
        model.add(kuchenEinfuegenObserver);
        KuchenLoeschenObserver kuchenLoeschenObserver = new KuchenLoeschenObserver(model);
        model.add(kuchenLoeschenObserver);
        KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
        model.add(kapazitaetsObserver);
        for (int i = 0; i < anzahlThreads; i++) {
            KuchenEinfuegenThread einfuegenThread = new KuchenEinfuegenThread(model, lock, 42);
            KuchenLoeschenThread loeschenThread = new KuchenLoeschenThread(model, lock, 42);
            einfuegenThread.setName("EinfuegenThread-" + (i+1));
            loeschenThread.setName("LoeschenThread-" + (i+1));
            einfuegenThread.start();
            loeschenThread.start();
        }
    }
}
