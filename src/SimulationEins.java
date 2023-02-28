import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import observer.KapazitaetsObserver;
import observer.KuchenEinfuegenObserver;
import observer.KuchenLoeschenObserver;
import simulation.simEins.KuchenEinfuegenThread;
import simulation.simEins.KuchenLoeschenThread;

import java.util.Scanner;

public class SimulationEins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Kapazitaet ein:");
        int kapazitaet = scanner.nextInt();
        Model model = new Model(kapazitaet);

        KuchenEinfuegenObserver kuchenEinfuegenObserver = new KuchenEinfuegenObserver(model);
        model.add(kuchenEinfuegenObserver);
        KuchenLoeschenObserver kuchenLoeschenObserver = new KuchenLoeschenObserver(model);
        model.add(kuchenLoeschenObserver);
        KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
        model.add(kapazitaetsObserver);

        model.herstellerEinfuegen(new Hersteller("ThreadHersteller"));
        KuchenEinfuegenThread einfuegenThread = new KuchenEinfuegenThread(model);
        KuchenLoeschenThread loeschenThread = new KuchenLoeschenThread(model);
        einfuegenThread.start();
        loeschenThread.start();
    }

}
