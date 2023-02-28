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


}