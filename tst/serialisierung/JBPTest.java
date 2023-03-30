package serialisierung;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import org.junit.jupiter.api.Test;
import vertrag.Verkaufsobjekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JBPTest {



    // Test um die Erstellung der Datei zu pruefen
    @Test
    public void testSerialisierenJBPDateiErstellung() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);
        jbp.serialisierenJBP();
        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        assertTrue(file.exists());
    }



    // Test überprueft, ob das gelesene Objekt eine Instanz von Model ist
    @Test
    void serialisierenJBPInstanz() throws IOException, ClassNotFoundException {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);

        jbp.serialisierenJBP();

        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
             Model serializedModel = (Model) objectInputStream.readObject();

             assertNotNull(serializedModel);
        }
    }

    // Test prueft ob die Eigenschaften des gelesenen Modells übereinstimmen
    @Test
    public void testSerialisierenJBPDateiInhalt() throws IOException, ClassNotFoundException {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);
        jbp.serialisierenJBP();

        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Model serializedModel = (Model) objectInputStream.readObject();

            assertEquals(model.getKapazitaet(), serializedModel.getKapazitaet());
        }
    }

    // Test ueberprueft ob eine Vorhandene Datei deserialsiert wird
    @Test
    public void testDeserialisierenJBPDateiVorhanden() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);
        jbp.serialisierenJBP();
        Model deserializedModel = jbp.deserialisierenJBP();
        assertTrue(Objects.nonNull(deserializedModel));
    }

    @Test
    public void testDeserialisierenJBPRichtigeEigenschaften() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);

        jbp.serialisierenJBP();

        Model deserializedModel = jbp.deserialisierenJBP();

        assertEquals(model.getKapazitaet(), deserializedModel.getKapazitaet());
    }


}