package vertrag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
}
