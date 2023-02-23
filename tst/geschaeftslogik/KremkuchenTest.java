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

class KremkuchenTest {

    Hersteller hersteller;
    BigDecimal preis;
    int naehrwert;
    Duration haltbarkeit;
    Collection<Allergen> allergene;
    String sorte;
    Kremkuchen testKremkuchen;

    @BeforeEach
    void setUp() {
        hersteller = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naehrwert = 123;
        haltbarkeit = Duration.ofDays(3);
        allergene = List.of(Allergen.Gluten, Allergen.Sesamsamen);
        sorte = "Butter";
        testKremkuchen = new Kremkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
    }

    // Testet die getKremsorte Methode
    @Test
    void getKremsorte() {
        assertEquals("Butter", testKremkuchen.getKremsorte());
    }

    // Testet die getHersteller Methode
    @Test
    void getHersteller() {
        assertEquals(hersteller, testKremkuchen.getHersteller());
    }

    // Testet die getAllergene Methode
    @Test
    void getAllergene() {
        assertEquals(List.of(Allergen.Gluten, Allergen.Sesamsamen), testKremkuchen.getAllergene());
    }

    // Testet die getNaehrwert Methode
    @Test
    void getNaehrwert() {
        assertEquals(naehrwert, testKremkuchen.getNaehrwert());
    }

    // Testet die getHaltbarkeit Methode
    @Test
    void getHaltbarkeit() {
        assertEquals(haltbarkeit, testKremkuchen.getHaltbarkeit());
    }

    // Testet die getPreis Methode
    @Test
    void getPreis() {
        assertEquals(preis, testKremkuchen.getPreis());
    }

    // Testet die getInspektionsdatum und setIspektionsdatum Methode
    @Test
    void getSetInspektionsdatum() {
        LocalDate localDate = LocalDate.now();
        Date inspektion = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        testKremkuchen.setInspektionsdatum(inspektion);
        assertEquals(inspektion, testKremkuchen.getInspektionsdatum());
    }

    // Testet die getEinfuegedatum Methode
    @Test
    void  getEinfuegedatum() {
        LocalDateTime einfuegedatum = LocalDateTime.of(2022, 3, 1, 12, 0, 0);
        testKremkuchen.setEinfuegedatum(einfuegedatum);
        assertEquals(einfuegedatum, testKremkuchen.getEinfuegedatum());
    }

    // Testet die getFachnummer Methode
    @Test
    void getFachnummer() {
        Model model = new Model(3);
        model.herstellerEinfuegen(hersteller);
        model.verkaufsObjektEinfuegen(testKremkuchen);
        assertEquals(1, testKremkuchen.getFachnummer());
    }

    // Testet die setFachnummer Methode
    @Test
    void setFachnummer() {
        int fachnummer = 42;
        testKremkuchen.setFachnummer(fachnummer);
        assertEquals(42, testKremkuchen.getFachnummer());
    }

    // Testet die setEinfuegedatum Methode
    @Test
    void setEinfuegedatum() {
        LocalDateTime erwartetesDatum = LocalDateTime.of(2022, 1, 1, 12, 0, 0);
        testKremkuchen.setEinfuegedatum(erwartetesDatum);
        assertEquals(erwartetesDatum, testKremkuchen.getEinfuegedatum());
    }

    // Testet ob der Kuchentyp als String zurueck gegeben wird
    @Test
    public void testGetKuchenTyp() {
        assertEquals("Kremkuchen", testKremkuchen.getTyp());
    }

    //Testet die toString Methode, auch bezüglich der verbleibenden Haltbarkeit
    @Test
    public void testToString() {
        // Test Kremkuchen erstellen
        LocalDateTime jetzt = LocalDateTime.of(2023, 2, 23, 23, 20, 21);
        LocalDateTime einfuegedatum = LocalDateTime.of(2023, 2, 18, 12, 0, 0);
        Hersteller hersteller = new Hersteller("Test Hersteller");
        BigDecimal preis = BigDecimal.valueOf(2.99);
        int naehrwert = 250;
        Duration haltbarkeit = Duration.ofDays(10);
        Collection<Allergen> allergene = Arrays.asList(Allergen.Gluten, Allergen.Haselnuss);
        String sorte = "Schoko";
        Kremkuchen kremkuchen = new Kremkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
        kremkuchen.setFachnummer(1);
        kremkuchen.setInspektionsdatum(new Date());
        kremkuchen.setEinfuegedatum(einfuegedatum);

        Duration verstrichen = Duration.between(einfuegedatum, jetzt);
        long verbleibendeTage = haltbarkeit.minus(verstrichen).toDays();
        String erwartetAusgabe = "[Kremkuchen] [Fachnummer: 1] [Inspektionsdatum: " + kremkuchen.getInspektionsdatum() + "] [verbleibende Haltbarkeit in Tagen: " + verbleibendeTage + "]";

        assertEquals(erwartetAusgabe, kremkuchen.toString());
    }


}