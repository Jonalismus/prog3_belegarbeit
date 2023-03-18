package serialisierung;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import org.junit.jupiter.api.Test;
import vertrag.Verkaufsobjekt;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JBPTest {

    @Test
    public void testSerialisierenJBPDateiErstellung() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);
        jbp.serialisierenJBP();
        File file = new File("src/serialisierung/speicherstandJBP/saveModelJBP.xml");
        assertTrue(file.exists());
    }

    @Test
    public void testSerialisierenJBPOrdnerErstellung() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        JBP jbp = new JBP(model);
        File folder = new File("src/serialisierung/speicherstandJBP/");
        if (folder.exists()) {
            folder.delete();
        }
        jbp.serialisierenJBP();
        assertTrue(folder.exists());
    }

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

    @Test
    public void testDeserialisierenJBPDateiNichtVorhanden() {
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(10, verkaufsobjektLinkedList, herstellerLinkedList);
        File file = new File("src/serialisierung/speicherstandJBP/saveModelJBP.xml");
        if (file.exists()) {
            file.delete();
        }
        JBP jbp = new JBP(model);
        Model deserializedModel = jbp.deserialisierenJBP();
        assertNull(deserializedModel);
    }


}