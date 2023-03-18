package serialisierung;

import geschaeftslogik.Model;

import java.io.*;

public class JOS {

    private final Model model;

    public JOS(Model model) {
        this.model = model;
    }

    public void serialisierenJOS() {
        File folder = new File("src/serialisierung/speicherstandJOS/");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println("Konnte Ordner nicht erstellen: " + folder);
                return;
            }
        }
        File file = new File(folder, "saveModel.ser");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(model);
        } catch (IOException e) {
            System.out.println("Ein I/O-Fehler ist aufgetreten: " + e.getMessage());
        }
    }


    public Model deserialisierenJOS(){
        File file = new File("src/serialisierung/speicherstandJOS/saveModel.ser");
        Model model = null;

        try (FileInputStream inputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
             model = (Model) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Die Datei ist nicht vorhanden: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ein I/O-Fehler ist aufgetreten: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Die Model-Klasse wurde nicht gefunden: " + e.getMessage());
        }

        return model;
    }
}






