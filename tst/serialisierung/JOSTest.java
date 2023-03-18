package serialisierung;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import org.junit.jupiter.api.Test;
import vertrag.Verkaufsobjekt;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JOSTest {

    // Test um die Erstellung der Datei zu pruefen
    @Test
    public void testSerialisierenJOSDateiErstellung() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);
        jos.serialisierenJOS();
        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        assertTrue(file.exists());
    }

    @Test
    public void testSerialisierenJOSOrdnerErstellung() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);
        File folder = new File("src/serialisierung/speicherstandJOS/");
        if (folder.exists()) {
            folder.delete();
        }
        jos.serialisierenJOS();
        assertTrue(folder.exists());
    }


    // Test überprueft, ob das gelesene Objekt eine Instanz von Model ist
    @Test
    void serialisierenJOSInstanz() throws IOException, ClassNotFoundException {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);

        jos.serialisierenJOS();

        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Model serializedModel = (Model) objectInputStream.readObject();

            assertNotNull(serializedModel);
        }
    }

    // Test prueft ob die Eigenschaften des gelesenen Modells übereinstimmen
    @Test
    public void testSerialisierenJOSDateiInhalt() throws IOException, ClassNotFoundException {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);
        jos.serialisierenJOS();

        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Model serializedModel = (Model) objectInputStream.readObject();

            assertEquals(model.getKapazitaet(), serializedModel.getKapazitaet());
        }
    }

    // Test ueberprueft ob eine Vorhandene Datei deserialsiert wird
    @Test
    public void testDeserialisierenJOSDateiVorhanden() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);
        jos.serialisierenJOS();
        Model deserializedModel = jos.deserialisierenJOS();
        assertTrue(Objects.nonNull(deserializedModel));
    }

    @Test
    public void testDeserialisierenJOSRichtigeEigenschaften() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JOS jos = new JOS(model);

        jos.serialisierenJOS();

        Model deserializedModel = jos.deserialisierenJOS();

        assertEquals(model.getKapazitaet(), deserializedModel.getKapazitaet());
    }





}