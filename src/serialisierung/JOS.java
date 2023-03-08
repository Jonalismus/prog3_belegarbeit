package serialisierung;

import geschaeftslogik.Model;

import java.io.*;

public class JOS {

    private final Model model;

    public JOS(Model model) {
        this.model = model;
    }

    public void serialisierenJOS() throws IOException {
        File folder = new File("src\\serialisierung\\speicherstandJOS\\");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("Konnte Ordner nicht erstellen: " + folder);
            }
        }
        File file = new File(folder, "saveModel.ser");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(model);
        }
    }

    public Model deserialisieren() throws IOException, ClassNotFoundException {
        File file = new File("src\\serialisierung\\speicherstandJOS\\saveModel.ser");
        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (Model) objectInputStream.readObject();
        }
    }
}






