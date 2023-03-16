package geschaeftslogik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static vertrag.Allergen.*;

class ModelTest {

    Hersteller hersteller1;
    BigDecimal preis;
    int naherwerte;
    Duration haltbarkeit;
    List<Allergen> allergens;
    String sorte;
    String sorteZwei;
    Model model;

    @BeforeEach
    void setUp() {
        hersteller1 = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naherwerte = 123;
        haltbarkeit = Duration.ofDays(3);
        allergens = List.of(Erdnuss);
        sorte = "Butter";
        sorteZwei = "Erdbeere";
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
    }

    // Test ueberprueft, ob ein Hersteller erfolgreich hinzugefuegt werden kann
    @Test
    void herstellerEinfuegen() {
        Hersteller h = new Hersteller("hersteller1");
        assertTrue(model.herstellerEinfuegen(h));
    }

    // Test ueberprueft, ob es möglich ist, zweimal den gleich Hersteller einzufuegen. Es darf dabei nicht moeglich sein
    @Test
    void herstellerEinfuegenNichtMoeglich() {
        Hersteller h = new Hersteller("hersteller1");
        model.herstellerEinfuegen(h);
        assertFalse(model.herstellerEinfuegen(h));
    }

    // Hersteller Einfuegen als MockTest
    @Test
    public void testHerstellerEinfuegenMitMock() {
        Hersteller mockHersteller = mock(Hersteller.class);
        assertTrue(model.herstellerEinfuegen(mockHersteller));
    }

    // Kremkuchen Einfuegen MockTest
    @Test
    public void testKremkuchenEinfuegenMitMock() {
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
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(kremkuchen));
    }

    // Obstkuchen Einfuegen MockTest
    @Test
    public void testObstkuchenEinfuegenMitMock() {
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
        Obstkuchen obstkuchen = new Obstkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(obstkuchen));
    }

    // Obsttorte Einfuegen MockTest
    @Test
    public void testObsttorteEinfuegenMitMock() {
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
        Obsttorte obsttorte = new Obsttorte(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(obsttorte));
    }

    // Test zum Einfuegen eines Kuchens wenn der Hersteller nicht hinterlegt ist. Einfuegen darf nicht moeglich sein
    @Test
    public void testKuchenEinfuegenNichtMoeglich() {
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        assertFalse(model.verkaufsObjektEinfuegen(kremkuchen));
    }

    // Test zum Einfuegen eines Kuchens wenn die Kapazitaet erreicht ist. Einfuegen darf nicht moeglich sein (MockTest)
    @Test
    public void testKuchenEinfuegenWennKapazitaetErreichtMitMock() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(2, verkaufsobjektLinkedList, herstellerLinkedList);
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
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockVerkaufsobjekt.getHersteller()).thenReturn(mockHersteller);

        // Einfuegen des Verkaufsobjekts
        assertTrue(model.verkaufsObjektEinfuegen(mockVerkaufsobjekt));
    }

    // Test der prueft ob die Kuchen Liste nach dem Hinzufügen eines Kuchens die erwartete Groeße hat
    @Test
    public void testEinfuegenErhoetVerkaufsobjektListeGroesseUmEins() {
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.herstellerEinfuegen(hersteller1);
        model.verkaufsObjektEinfuegen(kremkuchen);
        assertEquals(1, model.getVerkaufobjektListe().size());
    }

    // Ueberpruefen, ob die zurückgegebene Kuchenliste den erwarteten Kuchen enthaelt
    @Test
    public void testGetVerkaufsobjektListe() {
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.herstellerEinfuegen(hersteller1);
        model.verkaufsObjektEinfuegen(testKremkuchen);
        List<Verkaufsobjekt> kuchhenListe = model.getVerkaufobjektListe();
        assertTrue(kuchhenListe.contains(testKremkuchen));
    }

    // Test der prueft ob die Hersteller Liste nach dem Hinzufuegen eines Herstellers die erwartete Groeße hat
    @Test
    public void testEinfuegenErhoetHerstellerlisteGroesseUmEins() {
        model.herstellerEinfuegen(hersteller1);
        assertEquals(1, model.getHerstellerListe().size());
    }

    // Ueberpruefen, ob die zurückgegebene Herstellerliste den erwarteten Hersteller enthaelt
    @Test
    public void testGetHerstellerListe() {
        Hersteller herstellerTest = new Hersteller("Hersteller 1");
        model.herstellerEinfuegen(herstellerTest);
        List<Hersteller> herstellerListe = model.getHerstellerListe();
        assertTrue(herstellerListe.contains(herstellerTest));
    }

    // Test zum ueberpruefen der abrufenDerHersteller() Methode, ob eine Liste mit Inhalt zurueck gegeben wird
    @Test
    public void testAbrufenDerHersteller(){
        Hersteller testHersteller = mock(Hersteller.class);
        model.getHerstellerListe().add(testHersteller);
        List<Hersteller> res = model.abrufenDerHersteller();
        assertEquals(1, res.size());
    }

    /*
    Test zum ueberpruefen der abrufenDerHersteller() Methode, ob die Hersteller auch die richtig Anzahl an Kuchen
     wiedergeben
     */
    @Test
    public void testAbrufenDerHerstellerMitAnzahlDerKuchen(){
        Hersteller testHersteller = new Hersteller("testHersteller");

        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = mock(Kremkuchen.class);
        Obstkuchen testObstkuchen = mock(Obstkuchen.class);
        Obsttorte testObsttorte = mock(Obsttorte.class);

        // Konfiguration der Mock-Objekte
        when(testKremkuchen.getHersteller()).thenReturn(testHersteller);
        when(testObstkuchen.getHersteller()).thenReturn(testHersteller);
        when(testObsttorte.getHersteller()).thenReturn(testHersteller);

        // Einfuegen der Kuchen und des Herstellers
        model.getHerstellerListe().add(testHersteller);
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Hersteller> res = model.abrufenDerHersteller();
        assertEquals(3, res.get(0).getAnzahlKuchen());
    }

    // Testet die kuchenAbrufen() Methode, ob sie eine Liste zurueck gibt
    @Test
    void testKuchenAbrufen(){
        List<Verkaufsobjekt> res = model.kuchenAbrufen(null);
        assertEquals(0, res.size());
    }

    // Testet die kuchenAbrufen() Methode, ob sie eine Liste zurueck gibt mit den richtigen Kuchen
    @Test
    void testKuchenAbrufenWennKuchenEnthalten(){
        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = mock(Kremkuchen.class);
        Obstkuchen testObstkuchen = mock(Obstkuchen.class);
        Obsttorte testObsttorte = mock(Obsttorte.class);

        LocalDateTime mockTime = mock(LocalDateTime.class);

        when(testKremkuchen.getEinfuegedatum()).thenReturn(mockTime);
        when(testObstkuchen.getEinfuegedatum()).thenReturn(mockTime);
        when(testObsttorte.getEinfuegedatum()).thenReturn(mockTime);


        // Einfuegen der Kuchen
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Verkaufsobjekt> res = model.kuchenAbrufen(null);
        assertEquals(3, res.size());
    }

    // Testet die kuchenAbrufen() Methode, ob sie eine Liste zurueck gibt nur mit Kremkuchen
    @Test
    void testKremkuchenAbrufen(){
        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obstkuchen testObstkuchen = new Obstkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obsttorte testObsttorte = new Obsttorte(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei);

        testKremkuchen.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObstkuchen.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObsttorte.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));

        // Einfuegen der Kuchen (1x Kremkuchen)
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Verkaufsobjekt> res = model.kuchenAbrufen("kremkuchen");
        assertEquals(1, res.size());
    }

    // Testet die kuchenAbrufen() Methode, ob sie eine Liste zurueck gibt nur mit Obstkuchen
    @Test
    void testObstkuchenAbrufen(){
        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obstkuchen testObstkuchen = new Obstkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obsttorte testObsttorte = new Obsttorte(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei);

        testKremkuchen.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObstkuchen.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObsttorte.setEinfuegedatum( LocalDateTime.of(2022, 1, 1, 12, 0, 0));

        // Einfuegen der Kuchen (2x Obstkuchen)
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Verkaufsobjekt> res = model.kuchenAbrufen("obstkuchen");
        assertEquals(2, res.size());
    }

    // Testet die kuchenAbrufen() Methode, ob sie eine Liste zurueck gibt nur mit Obsttorten
    @Test
    void testObsttorteAbrufen(){
        // Erstellen von Kuchen-Objekten
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obstkuchen testObstkuchen = new Obstkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Obsttorte testObsttorte = new Obsttorte(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte, sorteZwei);

        testKremkuchen.setEinfuegedatum(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObstkuchen.setEinfuegedatum(LocalDateTime.of(2022, 1, 1, 12, 0, 0));
        testObsttorte.setEinfuegedatum(LocalDateTime.of(2022, 1, 1, 12, 0, 0));

        // Einfuegen der Kuchen (3x Obsttorte)
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);
        model.getVerkaufobjektListe().add(testObsttorte);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Verkaufsobjekt> res = model.kuchenAbrufen("obsttorte");
        assertEquals(3, res.size());
    }

    // Testet die IspektionsDatumSetzen() Methode, ob das Inspektionsdatum gesetzt wird
    @Test
    void testIspektionsDatumSetzen(){
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getVerkaufobjektListe().add(testKremkuchen);
        testKremkuchen.setFachnummer(1);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        Date inspektion = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        model.inspektionsDatumSetzen(1);
        assertEquals(inspektion, testKremkuchen.getInspektionsdatum());
    }

    // Test zum loeschen eines Herstellers
    @Test
    void testloeschenHersteller(){
        Hersteller herstellerMock = mock(Hersteller.class);
        when(herstellerMock.getName()).thenReturn("herstellerMock");
        model.getHerstellerListe().add(herstellerMock);
        assertTrue(model.herstellerLoeschen("herstellerMock"));
    }

    //Test loeschen, klappt nicht, da Hersteller nicht in der Liste hinterlegt ist
    @Test
    void testloeschenHerstellerFalse(){
        Hersteller herstellerMock = mock(Hersteller.class);
        when(herstellerMock.getName()).thenReturn("herstellerMock");
        model.getHerstellerListe().add(herstellerMock);
        assertFalse(model.herstellerLoeschen("hersteller"));
    }

    //Test erfolgreiches loeschen eines Kuchens
    @Test
    void testloeschenEinesKuchens(){
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Kremkuchen testKremkuchenZwei = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        testKremkuchen.setFachnummer(1);
        testKremkuchenZwei.setFachnummer(2);
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testKremkuchenZwei);
        model.verkaufsObjektLoeschen(1);
        assertEquals(1,model.getVerkaufobjektListe().size());
    }

    // Test loeschen eines Kuchens klappt nicht, da Fachnummer nicht vergeben
    @Test
    void testloeschenEinesKuchensFehlerhaft(){
        Kremkuchen testKremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        Kremkuchen testKremkuchenZwei = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        testKremkuchen.setFachnummer(1);
        testKremkuchenZwei.setFachnummer(2);
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testKremkuchenZwei);
        assertFalse(model.verkaufsObjektLoeschen(3));
    }

    // Test zum Abrufen der enthaltenen Allergene im Automaten
    @Test
    void testAlleEnthaltenenAllergeneAnzeigen(){
        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = mock(Kremkuchen.class);
        Obstkuchen testObstkuchen = mock(Obstkuchen.class);
        Obsttorte testObsttorte = mock(Obsttorte.class);

        // Konfiguration der Mock-Objekte
        when(testKremkuchen.getAllergene()).thenReturn(Arrays.asList(Erdnuss, Gluten));
        when(testObstkuchen.getAllergene()).thenReturn(Collections.singleton(Gluten));
        when(testObsttorte.getAllergene()).thenReturn(Collections.singleton(Sesamsamen));

        // Kuchen in die Liste einfuegen
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Allergen> res = model.allergeneAbrufen(true);
        assertEquals(3, res.size());
    }

    // Test zum Abrufen der nicht enthaltenen Allergene im Automaten
    @Test
    void testAlleNichtEnthalteneAllergeneAnzeigen(){
        // Erstellen von Mock-Objekten
        Kremkuchen testKremkuchen = mock(Kremkuchen.class);
        Obstkuchen testObstkuchen = mock(Obstkuchen.class);
        Obsttorte testObsttorte = mock(Obsttorte.class);

        // Konfiguration der Mock-Objekte
        when(testKremkuchen.getAllergene()).thenReturn(Arrays.asList(Erdnuss, Gluten));
        when(testObstkuchen.getAllergene()).thenReturn(Collections.singleton(Gluten));
        when(testObsttorte.getAllergene()).thenReturn(Collections.singleton(Sesamsamen));

        // Kuchen in die Liste einfuegen
        model.getVerkaufobjektListe().add(testKremkuchen);
        model.getVerkaufobjektListe().add(testObstkuchen);
        model.getVerkaufobjektListe().add(testObsttorte);

        List<Allergen> res = model.allergeneAbrufen(false);
        assertEquals(1, res.size());
    }

}




