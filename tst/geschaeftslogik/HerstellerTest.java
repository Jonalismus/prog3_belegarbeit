package geschaeftslogik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerTest {

    // Test überprüft, ob der Constructor der Klasse Hersteller korrekt funktioniert
    @Test
    public void testConstructor() {
        String name = "Hersteller1";
        Hersteller hersteller = new Hersteller(name);
        assertEquals(name, hersteller.getName());
    }

    /*
    Test überprüft, ob die Methode getName() der Klasse Hersteller wie erwartet den Namen des Herstellers
    zurückgibt, der beim Erstellen des Hersteller-Objekts übergeben wurde
     */
    @Test
    void testGetName() {
        Hersteller hersteller = new Hersteller("Hersteller XYZ");
        assertEquals("Hersteller XYZ", hersteller.getName());
    }
}