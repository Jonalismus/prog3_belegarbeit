package geschaeftslogik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObsttorteTest {

    Hersteller hersteller;
    BigDecimal preis;
    int naehrwert;
    Duration haltbarkeit;
    Collection<Allergen> allergene;
    String sorte;
    String sorteZwei;
    Obsttorte testObsttorte;

    @BeforeEach
    void setUp() {
        hersteller = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naehrwert = 123;
        haltbarkeit = Duration.ofDays(3);
        allergene = List.of(Allergen.Gluten, Allergen.Sesamsamen);
        sorte = "Erdbeere";
        sorteZwei = "Sahne";
        testObsttorte = new Obsttorte(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte, sorteZwei);
    }

    // Testet die getKremsorte Methode
    @Test
    void getKremsorte() {
        assertEquals("Sahne",  testObsttorte.getKremsorte());
    }

    // Testet die getHersteller Methode
    @Test
    void getHersteller() {
        assertEquals(hersteller, testObsttorte.getHersteller());
    }

    // Testet die getAllergene Methode
    @Test
    void getAllergene() {
        assertEquals(List.of(Allergen.Gluten, Allergen.Sesamsamen), testObsttorte.getAllergene());
    }

    // Testet die getNaehrwert Methode
    @Test
    void getNaehrwert() {
        assertEquals(naehrwert, testObsttorte.getNaehrwert());
    }

    // Testet die getHaltbarkeit Methode
    @Test
    void getHaltbarkeit() {
        assertEquals(haltbarkeit, testObsttorte.getHaltbarkeit());
    }

    // Testet die getObstsorte Methode
    @Test
    void getObstsorte() {
        assertEquals("Erdbeere", testObsttorte.getObstsorte());
    }

    // Testet die getPreis Methode
    @Test
    void getPreis() {
        assertEquals(preis, testObsttorte.getPreis());
    }

    // Testet die getInspektionsdatum und setInspektions Methode
    @Test
    void getSetInspektionsdatum() {
        LocalDate localDate = LocalDate.now();
        Date inspektion = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        testObsttorte.setInspektionsdatum(inspektion);
        assertEquals(inspektion, testObsttorte.getInspektionsdatum());
    }

    // Testet die getEifuegedatum Methode
    @Test
    void getEinfuegedatum() {
        LocalDateTime einfuegedatum = LocalDateTime.of(2022, 3, 1, 12, 0, 0);
        testObsttorte.setEinfuegedatum(einfuegedatum);
        assertEquals(einfuegedatum, testObsttorte.getEinfuegedatum());
    }

    // Testet die getFachnummer Methode
    @Test
    void getFachnummer() {
        Model model = new Model(3);
        model.herstellerEinfuegen(hersteller);
        model.verkaufsObjektEinfuegen(testObsttorte);
        assertEquals(1, testObsttorte.getFachnummer());
    }

    // Testet die setFachnummer Methode
    @Test
    void setFachnummer() {
        int fachnummer = 42;
        testObsttorte.setFachnummer(fachnummer);
        assertEquals(42, testObsttorte.getFachnummer());
    }

    // Testet die setEinfuegedatum Methode
    @Test
    void setEinfuegedatum() {
        LocalDateTime erwartetesDatum = LocalDateTime.of(2022, 1, 1, 12, 0, 0);
        testObsttorte.setEinfuegedatum(erwartetesDatum);
        assertEquals(erwartetesDatum, testObsttorte.getEinfuegedatum());
    }

    // Testet ob der Kuchentyp als String zurueck gegeben wird
    @Test
    public void testGetKuchenTyp() {
        assertEquals("Obsttorte", testObsttorte.getTyp());
    }

    //Testet die toString Methode, auch bezüglich der verbleibenden Haltbarkeit
    @Test
    public void testToString() {
        // Test Obsttorte erstellen
        LocalDateTime jetzt = LocalDateTime.of(2023, 2, 23, 23, 20, 21);
        LocalDateTime einfuegedatum = LocalDateTime.of(2023, 2, 18, 12, 0, 0);
        Hersteller hersteller = new Hersteller("Test Hersteller");
        BigDecimal preis = BigDecimal.valueOf(2.99);
        int naehrwert = 250;
        Duration haltbarkeit = Duration.ofDays(10);
        Collection<Allergen> allergene = Arrays.asList(Allergen.Gluten, Allergen.Haselnuss);
        String sorte = "Schoko";
        Obsttorte obsttorte = new Obsttorte(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte, sorte);
        obsttorte.setFachnummer(1);
        obsttorte.setInspektionsdatum(new Date());
        obsttorte.setEinfuegedatum(einfuegedatum);

        Duration verstrichen = Duration.between(einfuegedatum, jetzt);
        long verbleibendeTage = haltbarkeit.minus(verstrichen).toDays();
        String erwartetAusgabe = "[Obsttorte] [Fachnummer: 1] [Inspektionsdatum: " + obsttorte.getInspektionsdatum() + "] [verbleibende Haltbarkeit in Tagen: " + verbleibendeTage + "]";

        assertEquals(erwartetAusgabe, obsttorte.toString());
    }
}