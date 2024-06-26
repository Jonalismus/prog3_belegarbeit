package vertrag;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public interface Verkaufsobjekt {
    BigDecimal getPreis();
    Date getInspektionsdatum();
    void setInspektionsdatum(Date inspektionsdatum);
    int getFachnummer();
    Hersteller getHersteller();
    void setFachnummer(int fachnummer);
    void setEinfuegedatum(LocalDateTime einfuegedatum);
    String getTyp();
    Collection<Allergen> getAllergene();
    LocalDateTime getEinfuegedatum();
    Duration getHaltbarkeit();
    void setVerbleibendeHaltbarkeit(long toDays);
}
