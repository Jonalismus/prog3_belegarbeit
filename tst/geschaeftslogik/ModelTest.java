package geschaeftslogik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
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

    @BeforeEach
    void setUp() {
        hersteller1 = new Hersteller("hersteller1");
        preis = new BigDecimal("3.20");
        naherwerte = 123;
        haltbarkeit = Duration.ofDays(3);
        allergens = List.of(Allergen.Erdnuss);
        sorte = "Butter";
    }

    // Test überprueft, ob ein Hersteller erfolgreich hinzugefuegt werden kann
    @Test
    void herstellerEinfuegen() {
        Model m = new Model(3);
        Hersteller h = new Hersteller("hersteller1");
        assertTrue(m.herstellerEinfuegen(h));
    }

    // Test überprueft, ob es möglich ist, zweimal den gleich Hersteller einzufuegen. Es darf dabei nicht moeglich sein
    @Test
    void herstellerEinfuegenNichtMoeglich() {
        Model m = new Model(3);
        Hersteller h = new Hersteller("hersteller1");
        m.herstellerEinfuegen(h);
        assertFalse(m.herstellerEinfuegen(h));
    }

    // Hersteller Einfuegen als MockTest
    @Test
    public void testHerstellerEinfuegenWithMock() {
        Hersteller mockHersteller = mock(Hersteller.class);
        Model model = new Model(3);
        assertTrue(model.herstellerEinfuegen(mockHersteller));
    }

    // Kremkuchen Einfuegen MockTest
    @Test
    public void testKremkuchenEinfuegenWithMock(){
        Model model = new Model(10);
        // Erstellen von Mock-Objekten
        Kremkuchen mockKremkuchen = mock(Kremkuchen.class);
        Hersteller mockHersteller = mock(Hersteller.class);
        model.herstellerEinfuegen(mockHersteller);

        // Konfiguration der Mock-Objekte
        when(mockKremkuchen.getHersteller()).thenReturn(mockHersteller);

        // Test des Einfügens des Kremkuchens
        assertTrue(model.verkaufsObjektEinfuegen(mockKremkuchen));
    }

    // Kremkuchen Einfuegen Test
    @Test
    public void testKremkuchenEinfuegen(){
        Model model = new Model(3);
        Kremkuchen kremkuchen = new Kremkuchen(hersteller1, preis, naherwerte, haltbarkeit, allergens, sorte);
        model.getHerstellerListe().add(hersteller1);
        assertTrue(model.verkaufsObjektEinfuegen(kremkuchen));
    }

}