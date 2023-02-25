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

    //Test schreiben todo
    public String toString() {
        return "[" + name + "] [Anzahl Kuchen: " + anzahlKuchen + "]";
    }

    /* Code copy
    Quelle: https://falconbyte.net/java-objekte-vergleichen.php#:~:text=Der%20Vergleichsoperator%20%3D%3D%20testet%2C%20ob,die%20Methode%20equals()%20eingesetzt.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hersteller h) {
            return (name.equals(h.name));
        } else {
            return false;
        }
    }
}
