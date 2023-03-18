package serialisierung;

import geschaeftslogik.*;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class JBP {

    private final Model model;

    public JBP(Model model) {
        this.model = model;
    }

    public void serialisierenJBP() {
        File folder = new File("src/serialisierung/speicherstandJBP/");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println("Konnte Ordner nicht erstellen: " + folder);
            }
        }
        File file = new File(folder, "saveModelJBP.xml");

        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.setPersistenceDelegate(Model.class, new DefaultPersistenceDelegate(new String[]{"kapazitaet", "verkaufobjektListe", "herstellerListe"}));
            encoder.setPersistenceDelegate(Hersteller.class, new DefaultPersistenceDelegate(new String[]{"name"}));
            encoder.setPersistenceDelegate(Kremkuchen.class, new DefaultPersistenceDelegate(new String[]{"hersteller", "preis", "naehrwert", "haltbarkeit", "allergene", "sorte"}));
            encoder.setPersistenceDelegate(Obstkuchen.class, new DefaultPersistenceDelegate(new String[]{"hersteller", "preis", "naehrwert", "haltbarkeit", "allergene", "sorte"}));
            encoder.setPersistenceDelegate(Obsttorte.class, new DefaultPersistenceDelegate(new String[]{"hersteller", "preis", "naehrwert", "haltbarkeit", "allergene", "sorteEins", "sorteZwei"}));
            //https://gist.github.com/sachin-handiekar/185f5de2a9e027783a9f
            encoder.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate() {
                        protected Expression instantiate(Object oldInstance, Encoder out) {
                            BigDecimal bd = (BigDecimal) oldInstance;
                            return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[]{
                                    bd.toString()
                            });
                        }

                        protected boolean mutatesTo(Object oldInstance, Object newInstance) {
                            return oldInstance.equals(newInstance);
                        }
                    }

            );
            //https://stackoverflow.com/questions/41373566/localdate-serialization-error
            encoder.setPersistenceDelegate(Duration.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDate, Encoder encdr) {
                            return new Expression(localDate,
                                    Duration.class,
                                    "parse",
                                    new Object[]{localDate.toString()});
                        }
                    });
            encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object oldInstance, Encoder out) {
                    LocalDateTime dateTime = (LocalDateTime) oldInstance;
                    return new Expression(
                            dateTime,
                            LocalDateTime.class,
                            "of",
                            new Object[]{
                                    dateTime.getYear(),
                                    dateTime.getMonth(),
                                    dateTime.getDayOfMonth(),
                                    dateTime.getHour(),
                                    dateTime.getMinute(),
                                    dateTime.getSecond(),
                                    dateTime.getNano()
                            }
                    );
                }
            });
            encoder.writeObject(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Model deserialisierenJBP() {
        Model model = null;
        File file = new File("src/serialisierung/speicherstandJBP/saveModelJBP.xml");

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            model = (Model) decoder.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Die Datei ist nicht vorhanden: " + file.getAbsolutePath());
        }
        return model;
    }
}
