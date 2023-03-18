package view.observer;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Kremkuchen;
import geschaeftslogik.Model;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        hersteller1 = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naherwerte = 123;
        haltbarkeit = Duration.ofDays(3);
        allergens = List.of(Erdnuss);
        sorte = "Butter";

    }

    // Testet den Observer, ob er bei 90% auslastung eine Meldung von sich gibt
    @Test
    void testKapazitaetsObserver() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
        model.add(kapazitaetsObserver);

        Kremkuchen kremkuchenTest = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);

        // Umlenken der System.out-Ausgabe auf einen ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Fülle den Automat bis zur 90% Auslastung
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);
        model.verkaufsObjektEinfuegen(kremkuchenTest);

        // Überprüfen, ob der Observer eine Warnung ausgegeben hat
        String expectedOutput = "ACHTUNG: Kapazitaet zu 90% ausgelastet!\n";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    // Testet den Observer, ob er bei unter 90% auslastung keine Meldung von sich gibt
    @Test
    void testKapazitaetsObserverKeineAusgabe() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        KapazitaetsObserver kapazitaetsObserver = new KapazitaetsObserver(model);
        model.add(kapazitaetsObserver);

        Kremkuchen kremkuchenTest = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);

        // Umlenken der System.out-Ausgabe auf einen ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        model.verkaufsObjektEinfuegen(kremkuchenTest);

        // Überprüfen, ob der Observer eine Warnung ausgegeben hat
        String expectedOutput = "";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }


}