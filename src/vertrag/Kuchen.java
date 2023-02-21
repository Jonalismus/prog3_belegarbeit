package vertrag;

import java.time.Duration;
import java.util.Collection;

public interface Kuchen {

    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();


}
