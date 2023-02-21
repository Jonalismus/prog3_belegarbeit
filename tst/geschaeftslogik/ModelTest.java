package geschaeftslogik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelTest {

    Hersteller hersteller1;
    BigDecimal preis;
    int naherwerte;
    Duration haltbarkeit;
    List<Allergen> allergens;
    String sorte;
    String sorteZwei;

    @BeforeEach
    void setUp() {
        hersteller1 = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naherwerte = 123;
        haltbarkeit = Duration.ofDays(3);
        allergens = List.of(Allergen.Erdnuss);
        sorte = "Butter";
        sorteZwei = "Erdbeere";
    }

    // Test ueberprueft, ob ein Hersteller erfolgreich hinzugefuegt werden kann
    @Test
    void herstellerEinfuegen() {
        Model m = new Model(3);
        Hersteller h = new Hersteller("hersteller1");
        assertTrue(m.herstellerEinfuegen(h));
    }

    // Test ueberprueft, ob es möglich ist, zweimal den gleich Hersteller einzufuegen. Es darf dabei nicht moeglich sein
    @Test
    void herstellerEinfuegenNichtMoeglich() {
        Model m = new Model(3);
        Hersteller h = new Hersteller("hersteller1");
        m.herstellerEinfuegen(h);
        assertFalse(m.herstellerEinfuegen(h));
    }

    // Hersteller Einfuegen als MockTest
    @Test
    public void testHerstellerEinfuegenMitMock() {
        Hersteller mockHersteller = mock(Hersteller.class);
        Model model = new Model(3);
        assertTrue(model.herstellerEinfuegen(mockHersteller));
    }

    // Kremkuchen Einfuegen MockTest
    @Test
    public void testKremkuchenEinfuegenMitMock() {
        Model model = new Model(10);
        // Erstellen von Mock-Objekten
        Kremkuchen mockKremkuchen = mock(Kremkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockKremkuchen.getHersteller()).thenReturn(mockHersteller);

        // Test des Einfuegens des Kremkuchens
        assertTrue(model.verkaufsObjektEinfuegen(mockKremkuchen));
    }

    // Kremkuchen Einfuegen Test
    @Test
    public void testKremkuchenEinfuegen() {
        Model model = new Model(3);
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(kremkuchen));
    }

    // Obstkuchen Einfuegen MockTest
    @Test
    public void testObstkuchenEinfuegenMitMock() {
        Model model = new Model(10);
        // Erstellen von Mock-Objekten
        Obstkuchen mockObstkuchen = mock(Obstkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockObstkuchen.getHersteller()).thenReturn(mockHersteller);

        // Test des Einfuegens des Obstkuchens
        assertTrue(model.verkaufsObjektEinfuegen(mockObstkuchen));
    }

    // Obstkuchen Einfuegen Test
    @Test
    public void testObstkuchenEinfuegen() {
        Model model = new Model(3);
        Obstkuchen obstkuchen = new Obstkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(obstkuchen));
    }

    // Obsttorte Einfuegen MockTest
    @Test
    public void testObsttorteEinfuegenMitMock() {
        Model model = new Model(10);
        // Erstellen von Mock-Objekten
        Obsttorte mockObsttorte = mock(Obsttorte.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockObsttorte.getHersteller()).thenReturn(mockHersteller);

        // Test des Einfuegens der Obsttorte
        assertTrue(model.verkaufsObjektEinfuegen(mockObsttorte));
    }

    // Obsttorte Einfuegen Test
    @Test
    public void testObsttorteEinfuegen() {
        Model model = new Model(3);
        Obsttorte obsttorte = new Obsttorte(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(obsttorte));
    }

    // Test zum Einfuegen eines Kuchens wenn der Hersteller nicht hinterlegt ist. Einfuegen darf nicht moeglich sein
    @Test
    public void testKuchenEinfuegenNichtMoeglich() {
        Model model = new Model(3);
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        assertFalse(model.verkaufsObjektEinfuegen(kremkuchen));
    }

    // Test zum Einfuegen eines Kuchens wenn die Kapazitaet erreicht ist. Einfuegen darf nicht moeglich sein (MockTest)
    @Test
    public void testKuchenEinfuegenWennKapazitaetErreichtMitMock() {
        Model model = new Model(2);
        // Erstellen von Mock-Objekten
        Obsttorte mockObsttorte = mock(Obsttorte.class);
        Obstkuchen mockObstkuchen = mock(Obstkuchen.class);
        Kremkuchen mockKremkuchen = mock(Kremkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);

        //Hersteller einfuegen
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockObsttorte.getHersteller()).thenReturn(mockHersteller);
        when(mockObstkuchen.getHersteller()).thenReturn(mockHersteller);
        when(mockKremkuchen.getHersteller()).thenReturn(mockHersteller);

        //Einfuegen der Kuchen
        model.verkaufsObjektEinfuegen(mockObstkuchen);
        model.verkaufsObjektEinfuegen(mockObstkuchen);

        // Einfuegen des Kuchens des Kremkuchen, der nicht hinzugefuegt werden kann, da Kapazitaet erreicht
        assertFalse(model.verkaufsObjektEinfuegen(mockKremkuchen));
    }

    // Test zum pruefen ob ein Einfuegedatum vergeben wurde (Mockito-Test)
    @Test
    public void EinfuegedatumTestMitMock() {
        // Erstellen von Mock-Objekten
        Kremkuchen mockKremkuchen = mock(Kremkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        Model model = new Model(10);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockKremkuchen.getHersteller()).thenReturn(mockHersteller);

        // Einfuegen des Kuchens
        model.verkaufsObjektEinfuegen(mockKremkuchen);

        // Überprüfen, dass das Einfuegedatum gesetzt wurde
        verify(mockKremkuchen).setEinfuegedatum(any(LocalDateTime.class));
    }

    // Test zum pruefen ob eine Fachnummer vergeben wurde (Mockito-Test)
    @Test
    public void FachnummerTestMitMock() {
        // Erstellen von Mock-Objekten
        Kremkuchen mockKremkuchen = mock(Kremkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        Model model = new Model(10);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockKremkuchen.getHersteller()).thenReturn(mockHersteller);

        // Einfuegen des Kuchens
        model.verkaufsObjektEinfuegen(mockKremkuchen);

        // Überprüfen, ueberpruefen ob eine Fachnummer vergeben wurde
        verify(mockKremkuchen).setFachnummer(1);
    }

    // Test zum pruefen ob ein Verkaufsobjekt hinzugefuegt werden kann (Mockito-Test)
    @Test
    public void VerkaufsobjektEinfuegenTestMitMock() {
        // Erstellen von Mock-Objekten
        Verkaufsobjekt mockVerkaufsobjekt = mock(Verkaufsobjekt.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        Model model = new Model(10);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockVerkaufsobjekt.getHersteller()).thenReturn(mockHersteller);

        // Einfuegen des Verkaufsobjekts
        assertTrue(model.verkaufsObjektEinfuegen(mockVerkaufsobjekt));
    }

    // Test der prueft ob die Kuchen Liste nach dem Hinzufügen eines Kuchens die erwartete Groeße hat
    @Test
    public void testEinfuegenErhoetVerkaufsobjektListeGroesseUmEins() {
        Model model = new Model(3);
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.herstellerEinfuegen(hersteller1);
        model.verkaufsObjektEinfuegen(kremkuchen);
        assertEquals(1, model.getKuchenListe().size());
    }

    // Ueberpruefen, ob die zurückgegebene Kuchenliste den erwarteten Kuchen enthaelt
    @Test
    public void testGetVerkaufsobjektListe() {
        Model model = new Model(5);
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.herstellerEinfuegen(hersteller1);
        model.verkaufsObjektEinfuegen(testKremkuchen);
        List<Verkaufsobjekt> kuchhenListe = model.getKuchenListe();
        assertTrue(kuchhenListe.contains(testKremkuchen));
    }

    // Test der prueft ob die Hersteller Liste nach dem Hinzufuegen eines Herstellers die erwartete Groeße hat
    @Test
    public void testEinfuegenErhoetHerstellerlisteGroesseUmEins() {
        Model model = new Model(3);
        model.herstellerEinfuegen(hersteller1);
        assertEquals(1, model.getHerstellerListe().size());
    }

    // Ueberpruefen, ob die zurückgegebene Herstellerliste den erwarteten Hersteller enthaelt
    @Test
    public void testGetHerstellerListe() {
        Model model = new Model(5);
        Hersteller herstellerTest = new Hersteller("Hersteller 1");
        model.herstellerEinfuegen(herstellerTest);
        List<vertrag.Hersteller> herstellerListe = model.getHerstellerListe();
        assertTrue(herstellerListe.contains(herstellerTest));
    }


}




