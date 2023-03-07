import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import simulation.simDrei.InspektionsThread;
import simulation.simDrei.KuchenEinfuegenThreadModifiziert;
import simulation.simDrei.KuchenLoeschenThreadModifiziert;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulationDrei {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Kapazitaet ein:");
        int capacity = scanner.nextInt();
        System.out.println("Bitte geben Sie die Anzahl der Threads ein:");
        int numThreads = scanner.nextInt();
        System.out.println("Bitte geben Sie das Intervall in Millisekunden ein:");
        int interval = scanner.nextInt();

        // Initialize model and lock
        Model model = new Model(capacity);
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        model.herstellerEinfuegen(new Hersteller("ThreadHersteller"));

        for (int i = 0; i < numThreads; i++) {
            Thread einfuegenThread = new KuchenEinfuegenThreadModifiziert(model, lock, condition);
            einfuegenThread.start();

            Thread loeschenThread = new KuchenLoeschenThreadModifiziert(model, lock, condition);
            loeschenThread.start();

            Thread inspektionsThread = new InspektionsThread(model, condition, lock);
            inspektionsThread.start();

            einfuegenThread.setName("EinfuegenThread-" + (i+1));
            loeschenThread.setName("LoeschenThread-" + (i+1));
            inspektionsThread.setName("InspektionsThread-" + (i+1));
        }


        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(() -> {
            lock.lock();
            try {
                System.out.println("Kuchenautomat Zustand: Kapazität " + model.getKapazitaet() + ", Anzahl Kuchen " + model.getKuchenListe().size());
            } finally {
                lock.unlock();
            }
        }, 0, interval, TimeUnit.MILLISECONDS);

    }
}
