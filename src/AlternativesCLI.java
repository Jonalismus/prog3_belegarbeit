import cli.HauptCLI;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import cli.listener.AddListener;
import cli.listener.InfoListener;
import cli.modus.AenderungsModus;
import cli.modus.AnzeigeModus;
import cli.modus.EinfuegenModus;
import cli.modus.LoeschModus;
import observer.AllergenObserver;
import geschaeftslogik.Model;

public class AlternativesCLI {

        /*
        Command Line Interface indem die Funktionen Loeschen von Herstellern
        und das Auflisten der Allergene deaktiviert ist.
        Außerdem ist der Observer zur Ueberwachung der Kapaztaet nicht aktiv
         */
        public static void main(String[] args) {
            int kapazitaet = 0;
            for (String arg : args) {
                try {
                    kapazitaet = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    return;
                }
            }
            if (kapazitaet > 0) {
                Model model = new Model(kapazitaet);
                System.out.println("Achtung! Löschen von Herstellern und das Auflisten der Allergene nicht möglich!");
                //Observer beim Model registrieren
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

                HauptCLI hauptCLI = new HauptCLI(einfuegenModus, loeschModus, aenderungsModus, anzeigeModus);
                hauptCLI.start();
            }

        }
    }

