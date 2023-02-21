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
}