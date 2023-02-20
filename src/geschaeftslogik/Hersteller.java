package geschaeftslogik;

public class Hersteller implements vertrag.Hersteller {

    private final String name;

    public Hersteller(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
