package geschaeftslogik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerTest {


    /*
    Test überprüft, ob die Methode getName() der Klasse Hersteller wie erwartet den Namen des Herstellers
    zurückgibt, der beim Erstellen des Hersteller-Objekts übergeben wurde
     */
    @Test
    void testGetName() {
        Hersteller hersteller = new Hersteller("Hersteller XYZ");
        assertEquals("Hersteller XYZ", hersteller.getName());
    }

    // Test zum ueberpruefen der getter und setter Methode zur Anzahl der Kuchen
    @Test
    void testGetterUndSetterAnzahlKuchen(){
        Hersteller hersteller = new Hersteller("Hersteller XYZ");
        hersteller.setAnzahlKuchen(2);
        assertEquals(2, hersteller.getAnzahlKuchen());
    }

    // Test zum ueberprüfen, der Equals Methode. Sollte True zurueck liefen.
    @Test
    public void testEquals() {
        Hersteller hersteller1 = new Hersteller("Hersteller 1");
        Hersteller hersteller2 = new Hersteller("Hersteller 1");
        assertEquals(hersteller1, hersteller2);
    }

    // Test zum ueberprüfen, der Equals Methode. Sollte false zurueck liefen.
    @Test
    public void testEqualsFalse() {
        Hersteller hersteller1 = new Hersteller("Hersteller 1");
        Hersteller hersteller2 = new Hersteller("Hersteller 2");
        assertNotEquals(hersteller1, hersteller2);
    }


}