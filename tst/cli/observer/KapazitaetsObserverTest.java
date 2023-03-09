package cli.observer;

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
import cli.listener.AddListener;
import cli.listener.InfoListener;
import cli.modus.AenderungsModus;
import cli.modus.AnzeigeModus;
import cli.modus.EinfuegenModus;
import cli.modus.LoeschModus;
import geschaeftslogik.Hersteller;
import geschaeftslogik.Kremkuchen;
import geschaeftslogik.Model;
import observer.AllergenObserver;
import observer.KapazitaetsObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.Allergen.Erdnuss;

class KapazitaetsObserverTest {

    Hersteller hersteller1;
    BigDecimal preis;
    int naherwerte;
    Duration haltbarkeit;
    List<Allergen> allergens;
    String sorte;
    Model model;

    @BeforeEach
    void setUp() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        hersteller1 = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naherwerte = 123;
        haltbarkeit = Duration.ofDays(3);
        allergens = List.of(Erdnuss);
        sorte = "Butter";
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

    }

    // Testet den Observer, ob er bei 90% auslastung eine Meldung von sich gibt
    @Test
    void testKapazitaetsObserver() {

        Kremkuchen kremkuchenTest = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);

        // Umlenken der System.out-Ausgabe auf einen ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Fülle den Automat bis zur 90% Auslastung
        for (int i = 0; i < 9; i++) {
            model.verkaufsObjektEinfuegen(kremkuchenTest);
        }

        // Überprüfen, ob der Observer noch keine Warnung ausgegeben hat
        assertEquals("", outputStream.toString());

        // Füge den 10. Kuchen hinzu, um die 90% Kapazitätsgrenze zu überschreiten
        model.verkaufsObjektEinfuegen(kremkuchenTest);

        // Überprüfen, ob der Observer eine Warnung ausgegeben hat
        String expectedOutput = "ACHTUNG: Kapazitaet zu 90% ausgelastet!\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Wiederherstellen der Standard-Ausgabe
        System.setOut(System.out);

    }



}