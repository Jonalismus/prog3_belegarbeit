package cli;

import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventHandler;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventListener;
import cli.listener.AddListener;
import cli.listener.InfoListener;
import cli.modus.*;
import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Verkaufsobjekt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {

    private HauptCLI hauptCLI;
    private Model model;


    @BeforeEach
    void setUp() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        //Kuchen Einfuegen Event ohne einhängen der Handler und Listener
        KuchenEinfuegenEventHandler addHandlerKuchen = new KuchenEinfuegenEventHandler();
        //Hersteller Einfuegen Event
        HerstellerEinfuegenEventHandler addHandlerHersteller = new HerstellerEinfuegenEventHandler();
        HerstellerEinfuegenEventListener addListenerHersteller = new AddListener(model);
        addHandlerHersteller.add(addListenerHersteller);
        HerstellerEinfuegenEventListener infoListenerHersteller = new InfoListener();
        addHandlerHersteller.add(infoListenerHersteller);

        EinfuegenModus einfuegenModus = new EinfuegenModus(addHandlerHersteller, addHandlerKuchen);

        //Kuchen Loeschen Event
        KuchenLoeschenEventHandler addHandlerKuchenLoeschen = new KuchenLoeschenEventHandler();
        //Hersteller Loeschen Event
        HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen = new HerstellerLoeschenEventHandler();

        LoeschModus loeschModus = new LoeschModus(addHandlerHerstellerLoeschen, addHandlerKuchenLoeschen);

        //Inspektionsdatum setzen Event
        InspektionsEventHandler addHandlerInspektion = new InspektionsEventHandler();

        AenderungsModus aenderungsModus = new AenderungsModus(addHandlerInspektion);

        //Allergene anzeigen Event
        AllergeneAnzeigenEventHandler addHandlerAllergene = new AllergeneAnzeigenEventHandler();
        //Kuchen anzeigen Event
        KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen = new KuchenAnzeigenEventHandler();
        //Hersteller anzeigen Event
        HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen = new HerstellerAnzeigenEventHandler();
        HerstellerAnzeigenEventListener addListenerHerstellerAnzeigen = new AddListener(model);
        addHandlerHerstellerAnzeigen.add(addListenerHerstellerAnzeigen);


        AnzeigeModus anzeigeModus = new AnzeigeModus(addHandlerHerstellerAnzeigen, addHandlerKuchenAnzeigen, addHandlerAllergene);

        //Mode speichern
        ModelSpeichernEventHandler addHandlerModelSpeichern = new ModelSpeichernEventHandler();
        ModelSpeichernEventListener addListenerModelSpeichern = new AddListener(model);
        addHandlerModelSpeichern.add(addListenerModelSpeichern);

        SerialisierungsModus serialisierungsModus = new SerialisierungsModus(addHandlerModelSpeichern);

        hauptCLI = new HauptCLI(einfuegenModus, loeschModus, aenderungsModus, anzeigeModus, serialisierungsModus);
    }


    /*
    CLI-Test der überprueft ob ein Hersteller hinzugefuegt werden kann mit der Eingabe ":c"
    und der Eingabe "Hersteller"
     */
    @Test
    void testHerstellerEinfuegen() {
        // Hersteller über das CLI einfuegen
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(":c\n".getBytes());
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream("Hersteller\n".getBytes());
        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);

        // Setzt die beiden Eingaben ins System (:c und anschließend Hersteller)
        System.setIn(sequenceInputStream);

        try {
            hauptCLI.start();
        } catch (Exception e) {
            /*
            beim zweiten Durchlaufen der hauptCLI.start()
            Methode wird eine Exception geworfen da
            keine weitere Eingabe erfolgt. Damit der Test nicht
            Abbricht wird die Exception abgefangen
             */
        }

        // Herstellerliste abrufen
        Hersteller hersteller = model.getHerstellerListe().get(0);

        // Überprüfen, ob der Hersteller korrekt eingefuegt wurde
        assertEquals("Hersteller", hersteller.getName());
    }


    /*
    CLI-Test der ueberprueft ob ein Hersteller hinzugefuegt werden kann mit der Eingabe ":c"
    und der Eingabe "Hersteller Test"
     */
    @Test
    void testHerstellerEinfuegenMitLeertaste() {
        // Hersteller über das CLI einfuegen
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(":c\n".getBytes());
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream("Hersteller Test\n".getBytes());
        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);

        // Setzt die beiden Eingaben ins System (:c und anschließend Hersteller)
        System.setIn(sequenceInputStream);

        try {
            hauptCLI.start();
        } catch (Exception e) {
            /*
            beim zweiten Durchlaufen der hauptCLI.start()
            Methode wird eine Exception geworfen da
            keine weitere Eingabe erfolgt. Damit der Test nicht
            Abbricht wird die Exception abgefangen
             */
        }

        // Herstellerliste abrufen
        Hersteller hersteller = model.getHerstellerListe().get(0);

        // Ueberprüfen, ob der Hersteller korrekt eingefügt wurde
        assertEquals("Hersteller Test", hersteller.getName());
    }

    /*
    CLI-Test der das Anzeigen von Herstellern ueberprueft
    */
    @Test
    void testHerstellerAnzeigen() {
        // Herstellerliste initialisieren
        Hersteller hersteller = new Hersteller("Hersteller1");
        model.getHerstellerListe().add(hersteller);

        // Umlenken der System.out-Ausgabe auf einen ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Herstellerliste über das CLI anzeigen
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(":r\n".getBytes());
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream("Hersteller\n".getBytes());
        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);
        System.setIn(sequenceInputStream);

        try {
            hauptCLI.start();
        } catch (Exception e) {
            /*
            beim zweiten Durchlaufen der hauptCLI.start()
            Methode wird eine Exception geworfen da
            keine weitere Eingabe erfolgt. Damit der Test nicht
            Abbricht wird die Exception abgefangen
             */
        }

        // Wiederherstellen der Standard-Ausgabe
        System.setOut(System.out);

        // Überprüfen der Ausgabe
        String expectedOutput = "[Hersteller1] [Anzahl Kuchen: 0]";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}
