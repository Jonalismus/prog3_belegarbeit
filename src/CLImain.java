import cli.HauptCLI;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventListener;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventListener;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventHandler;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventListener;
import cli.listener.AddListener;
import cli.listener.InfoListener;
import cli.modus.*;
import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import observer.AllergenObserver;
import observer.KapazitaetsObserver;
import vertrag.Verkaufsobjekt;

import java.util.LinkedList;

public class CLImain {
    //Test Eingabe Kremkuchen Hersteller1 4,50 386 36 Gluten,Erdnuss Butter //  Obsttorte Hersteller2 7,50 632 24 Gluten Apfel Sahne
    public static void main(String[] args) {
        int kapazitaet = 0;
       // boolean TCP = false; todo
       // boolean UDP = false; todo
        for (String arg : args) {
            try {
                kapazitaet = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                /* todo
                if (arg.equalsIgnoreCase("TCP")) {
                    TCP = true;
                } else if (arg.equalsIgnoreCase("UDP")) {
                    UDP = true;
                }

                 */
            }
        }
        if (kapazitaet > 0) {
            LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
            LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
            Model model = new Model(kapazitaet, verkaufsobjektLinkedList, herstellerLinkedList);
            //Observer beim Model registrieren
            KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
            model.add(kapazitaetsObserver);
            AllergenObserver allergenObserver = new AllergenObserver(model);
            model.add(allergenObserver);

            //Kuchen Einfuegen Event
            KuchenEinfuegenEventHandler addHandlerKuchen = new KuchenEinfuegenEventHandler();
            KuchenEinfuegenEventListener addListenerKuchen = new AddListener(model);
            addHandlerKuchen.add(addListenerKuchen);
            KuchenEinfuegenEventListener infoListenerKuchen = new InfoListener();
            addHandlerKuchen.add(infoListenerKuchen);
            //Hersteller Einfuegen Event
            HerstellerEinfuegenEventHandler addHandlerHersteller = new HerstellerEinfuegenEventHandler();
            HerstellerEinfuegenEventListener addListenerHersteller = new AddListener(model);
            addHandlerHersteller.add(addListenerHersteller);
            HerstellerEinfuegenEventListener infoListenerHersteller = new InfoListener();
            addHandlerHersteller.add(infoListenerHersteller);

            EinfuegenModus einfuegenModus = new EinfuegenModus(addHandlerHersteller, addHandlerKuchen);

            //Kuchen Loeschen Event
            KuchenLoeschenEventHandler addHandlerKuchenLoeschen = new KuchenLoeschenEventHandler();
            KuchenLoeschenEventListener addListenerKuchenLoeschen = new AddListener(model);
            addHandlerKuchenLoeschen.add(addListenerKuchenLoeschen);
            KuchenLoeschenEventListener infoListenerKuchenLoeschen = new InfoListener();
            addHandlerKuchenLoeschen.add(infoListenerKuchenLoeschen);
            //Hersteller Loeschen Event
            HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen = new HerstellerLoeschenEventHandler();
            HerstellerLoeschenEventListener addListenerHerstellerLoeschen = new AddListener(model);
            addHandlerHerstellerLoeschen.add(addListenerHerstellerLoeschen);
            HerstellerLoeschenEventListener infoListenerHerstellerLoeschen = new InfoListener();
            addHandlerHerstellerLoeschen.add(infoListenerHerstellerLoeschen);

            LoeschModus loeschModus = new LoeschModus(addHandlerHerstellerLoeschen, addHandlerKuchenLoeschen);

            //Inspektionsdatum setzen Event
            InspektionsEventHandler addHandlerInspektion = new InspektionsEventHandler();
            InspektionsEventListener addListenerInspektion = new AddListener(model);
            addHandlerInspektion.add(addListenerInspektion);
            InspektionsEventListener infoListenerInspektion = new InfoListener();
            addHandlerInspektion.add(infoListenerInspektion);

            AenderungsModus aenderungsModus = new AenderungsModus(addHandlerInspektion);

            //Allergene anzeigen Event
            AllergeneAnzeigenEventHandler addHandlerAllergene = new AllergeneAnzeigenEventHandler();
            AllergeneAnzeigenEventListener addListenerAllergene = new AddListener(model);
            addHandlerAllergene.add(addListenerAllergene);
            AllergeneAnzeigenEventListener infoListenerAllergene = new InfoListener();
            addHandlerAllergene.add(infoListenerAllergene);
            //Kuchen anzeigen Event
            KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen = new KuchenAnzeigenEventHandler();
            KuchenAnzeigenEventListener addListenerKuchenAnzeigen = new AddListener(model);
            addHandlerKuchenAnzeigen.add(addListenerKuchenAnzeigen);
            KuchenAnzeigenEventListener infoListenerKuchenAnzeigen = new InfoListener();
            addHandlerKuchenAnzeigen.add(infoListenerKuchenAnzeigen);
            //Hersteller anzeigen Event
            HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen = new HerstellerAnzeigenEventHandler();
            HerstellerAnzeigenEventListener addListenerHerstellerAnzeigen = new AddListener(model);
            addHandlerHerstellerAnzeigen.add(addListenerHerstellerAnzeigen);
            HerstellerAnzeigenEventListener infoListenerHerstellerAnzeigen = new InfoListener();
            addHandlerHerstellerAnzeigen.add(infoListenerHerstellerAnzeigen);

            AnzeigeModus anzeigeModus = new AnzeigeModus(addHandlerHerstellerAnzeigen, addHandlerKuchenAnzeigen, addHandlerAllergene);

            //Model speichern
            ModelSpeichernEventHandler addHandlerModelSpeichern = new ModelSpeichernEventHandler();
            ModelSpeichernEventListener addListenerModelSpeichern = new AddListener(model);
            addHandlerModelSpeichern.add(addListenerModelSpeichern);
            ModelSpeichernEventListener infoListenerModelSpeichern = new InfoListener();
            addHandlerModelSpeichern.add(infoListenerModelSpeichern);

            SerialisierungsModus serialisierungsModus = new SerialisierungsModus(addHandlerModelSpeichern);

            HauptCLI hauptCLI = new HauptCLI(einfuegenModus, loeschModus, aenderungsModus, anzeigeModus, serialisierungsModus);
            hauptCLI.start();
        }

        /* todo
        if (TCP) {
            // Start TCP client
        }

        if (UDP) {
            // Start UDP client
        }

         */


    }
}
