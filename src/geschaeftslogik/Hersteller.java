package geschaeftslogik;

public class Hersteller implements vertrag.Hersteller {

    private final String name;


    private int anzahlKuchen;

    public Hersteller(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getAnzahlKuchen() {
        return anzahlKuchen;
    }

    public void setAnzahlKuchen(int anzahlKuchen) {
        this.anzahlKuchen = anzahlKuchen;
    }
}
