import view.cli.*;
import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventListener;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventListener;
import control.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;
import control.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import control.infrastructure.ModelSpeichern.ModelSpeichernEventHandler;
import control.infrastructure.ModelSpeichern.ModelSpeichernEventListener;
import control.listener.Listener;
import control.listener.InfoListener;
import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import net.TCP.ClientTCP;
import net.UDP.ClientUDP;
import view.observer.AllergenObserver;
import view.observer.KapazitaetsObserver;
import vertrag.Verkaufsobjekt;
import view.cli.modus.*;

import java.io.*;
import java.util.LinkedList;

public class CLImain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int kapazitaet = 0;
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        boolean TCP = false;
        boolean UDP = false;
        for (String arg : args) {
            try {
                kapazitaet = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                if (arg.equalsIgnoreCase("TCP")) {
                    TCP = true;
                }
                else if (arg.equalsIgnoreCase("UDP")) {
                    UDP = true;
                }
            }
        }
        if (TCP) {
            ClientTCP client = new ClientTCP();
            client.start();
        }
        if (UDP) {
            ClientUDP clientUDP = new ClientUDP();
            clientUDP.start();
        }
        if (kapazitaet >= 0) {
            Model model = new Model(kapazitaet, verkaufsobjektLinkedList, herstellerLinkedList);
            //Observer beim Model registrieren
            KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
            model.add(kapazitaetsObserver);
            AllergenObserver allergenObserver = new AllergenObserver(model);
            model.add(allergenObserver);

            //Kuchen Einfuegen Event
            KuchenEinfuegenEventHandler addHandlerKuchen = new KuchenEinfuegenEventHandler();
            KuchenEinfuegenEventListener addListenerKuchen = new Listener(model);
            addHandlerKuchen.add(addListenerKuchen);
            KuchenEinfuegenEventListener infoListenerKuchen = new InfoListener();
            addHandlerKuchen.add(infoListenerKuchen);
            //Hersteller Einfuegen Event
            HerstellerEinfuegenEventHandler addHandlerHersteller = new HerstellerEinfuegenEventHandler();
            HerstellerEinfuegenEventListener addListenerHersteller = new Listener(model);
            addHandlerHersteller.add(addListenerHersteller);
            HerstellerEinfuegenEventListener infoListenerHersteller = new InfoListener();
            addHandlerHersteller.add(infoListenerHersteller);

            EinfuegenModus einfuegenModus = new EinfuegenModus(addHandlerHersteller, addHandlerKuchen);

            //Kuchen Loeschen Event
            KuchenLoeschenEventHandler addHandlerKuchenLoeschen = new KuchenLoeschenEventHandler();
            KuchenLoeschenEventListener addListenerKuchenLoeschen = new Listener(model);
            addHandlerKuchenLoeschen.add(addListenerKuchenLoeschen);
            KuchenLoeschenEventListener infoListenerKuchenLoeschen = new InfoListener();
            addHandlerKuchenLoeschen.add(infoListenerKuchenLoeschen);
            //Hersteller Loeschen Event
            HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen = new HerstellerLoeschenEventHandler();
            HerstellerLoeschenEventListener addListenerHerstellerLoeschen = new Listener(model);
            addHandlerHerstellerLoeschen.add(addListenerHerstellerLoeschen);
            HerstellerLoeschenEventListener infoListenerHerstellerLoeschen = new InfoListener();
            addHandlerHerstellerLoeschen.add(infoListenerHerstellerLoeschen);

            LoeschModus loeschModus = new LoeschModus(addHandlerHerstellerLoeschen, addHandlerKuchenLoeschen);

            //Inspektionsdatum setzen Event
            InspektionsEventHandler addHandlerInspektion = new InspektionsEventHandler();
            InspektionsEventListener addListenerInspektion = new Listener(model);
            addHandlerInspektion.add(addListenerInspektion);
            InspektionsEventListener infoListenerInspektion = new InfoListener();
            addHandlerInspektion.add(infoListenerInspektion);

            AenderungsModus aenderungsModus = new AenderungsModus(addHandlerInspektion);

            //Allergene anzeigen Event
            AllergeneAnzeigenEventHandler addHandlerAllergene = new AllergeneAnzeigenEventHandler();
            AllergeneAnzeigenEventListener addListenerAllergene = new Listener(model);
            addHandlerAllergene.add(addListenerAllergene);
            AllergeneAnzeigenEventListener infoListenerAllergene = new InfoListener();
            addHandlerAllergene.add(infoListenerAllergene);
            //Kuchen anzeigen Event
            KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen = new KuchenAnzeigenEventHandler();
            KuchenAnzeigenEventListener addListenerKuchenAnzeigen = new Listener(model);
            addHandlerKuchenAnzeigen.add(addListenerKuchenAnzeigen);
            KuchenAnzeigenEventListener infoListenerKuchenAnzeigen = new InfoListener();
            addHandlerKuchenAnzeigen.add(infoListenerKuchenAnzeigen);
            //Hersteller anzeigen Event
            HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen = new HerstellerAnzeigenEventHandler();
            HerstellerAnzeigenEventListener addListenerHerstellerAnzeigen = new Listener(model);
            addHandlerHerstellerAnzeigen.add(addListenerHerstellerAnzeigen);
            HerstellerAnzeigenEventListener infoListenerHerstellerAnzeigen = new InfoListener();
            addHandlerHerstellerAnzeigen.add(infoListenerHerstellerAnzeigen);

            AnzeigeModus anzeigeModus = new AnzeigeModus(addHandlerHerstellerAnzeigen, addHandlerKuchenAnzeigen, addHandlerAllergene);

            //Model speichern
            ModelSpeichernEventHandler addHandlerModelSpeichern = new ModelSpeichernEventHandler();
            ModelSpeichernEventListener addListenerModelSpeichern = new Listener(model);
            addHandlerModelSpeichern.add(addListenerModelSpeichern);
            ModelSpeichernEventListener infoListenerModelSpeichern = new InfoListener();
            addHandlerModelSpeichern.add(infoListenerModelSpeichern);

            SerialisierungsModus serialisierungsModus = new SerialisierungsModus(addHandlerModelSpeichern);

            HauptCLI hauptCLI = new HauptCLI(einfuegenModus, loeschModus, aenderungsModus, anzeigeModus, serialisierungsModus);
            hauptCLI.start();
        }
    }
}
