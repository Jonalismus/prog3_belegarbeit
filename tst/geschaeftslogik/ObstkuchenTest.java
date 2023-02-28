package geschaeftslogik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObstkuchenTest {

    Hersteller hersteller;
    BigDecimal preis;
    int naehrwert;
    Duration haltbarkeit;
    Collection<Allergen> allergene;
    String sorte;
    Obstkuchen testObstkuchen;

    @BeforeEach
    void setUp() {
        hersteller = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naehrwert = 123;
        haltbarkeit = Duration.ofDays(3);
        allergene = List.of(Allergen.Gluten, Allergen.Sesamsamen);
        sorte = "Erdbeere";
        testObstkuchen = new Obstkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
    }

    // Testet die getHersteller Methode
    @Test
    void getHersteller() {
        assertEquals(hersteller, testObstkuchen.getHersteller());
    }

    // Testet die getAllergne Methode
    @Test
    void getAllergene() {
        assertEquals(List.of(Allergen.Gluten, Allergen.Sesamsamen), testObstkuchen.getAllergene());
    }

    // Testet die getNaehrwer Methode
    @Test
    void getNaehrwert() {
        assertEquals(naehrwert, testObstkuchen.getNaehrwert());
    }

    // Testet die getHaltbarkeit Methode
    @Test
    void getHaltbarkeit() {
        assertEquals(haltbarkeit, testObstkuchen.getHaltbarkeit());
    }

    // Testet die getPreis Methode
    @Test
    void getPreis() {
        assertEquals(preis, testObstkuchen.getPreis());
    }

    // Testet die getInspektionsdatum und setInspektions Methode
    @Test
    void getInspektionsdatum() {
        LocalDate localDate = LocalDate.now();
        Date inspektion = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        testObstkuchen.setInspektionsdatum(inspektion);
        assertEquals(inspektion, testObstkuchen.getInspektionsdatum());
    }

    // Testet die getFachnummer Methode
    @Test
    void getFachnummer() {
        Model model = new Model(3);
        model.herstellerEinfuegen(hersteller);
        model.verkaufsObjektEinfuegen(testObstkuchen);
        assertEquals(1, testObstkuchen.getFachnummer());
    }

    // Testet die getEinfuegedatum Methode
    @Test
    void  getEinfuegedatum() {
        LocalDateTime einfuegedatum = LocalDateTime.of(2022, 3, 1, 12, 0, 0);
        testObstkuchen.setEinfuegedatum(einfuegedatum);
        assertEquals(einfuegedatum, testObstkuchen.getEinfuegedatum());
    }

    // Testet die setFachnummer Methode
    @Test
    void setFachnummer() {
        int fachnummer = 42;
        testObstkuchen.setFachnummer(fachnummer);
        assertEquals(42, testObstkuchen.getFachnummer());
    }

    // Testet die setEinfuege Methode
    @Test
    void setEinfuegedatum() {
        LocalDateTime erwartetesDatum = LocalDateTime.of(2022, 1, 1, 12, 0, 0);
        testObstkuchen.setEinfuegedatum(erwartetesDatum);
        assertEquals(erwartetesDatum, testObstkuchen.getEinfuegedatum());
    }

    // Testet die getObstsorte Methode
    @Test
    void getObstsorte() {
        assertEquals("Erdbeere", testObstkuchen.getObstsorte());
    }

    // Testet ob der Kuchentyp als String zurueck gegeben wird
    @Test
    public void testGetKuchenTyp() {
        assertEquals("Obstkuchen", testObstkuchen.getTyp());
    }

}