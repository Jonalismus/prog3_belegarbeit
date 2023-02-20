package vertrag;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public interface Kuchen {
    Hersteller getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();
    void setFachnummer(int fachnummer);
    void setEinfuegedatum(LocalDateTime einfuegedatum);
}
