package geschaeftslogik;

import vertrag.Allergen;
import vertrag.Hersteller;
import vertrag.Kuchen;
import vertrag.Verkaufsobjekt;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public class Kremkuchen implements Verkaufsobjekt, Kuchen, vertrag.Kremkuchen, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Hersteller hersteller;
    private final BigDecimal preis;
    private final int naehrwert;
    private final Duration haltbarkeit;
    private final Collection<Allergen> allergene;

    public String getSorte() {
        return sorte;
    }

    private final String sorte;
    private int fachnummer;

    private Date inspektionsdatum;
    private LocalDateTime einfuegedatum;



    private String kuchentyp;


    public void setVerbleibendeHaltbarkeit(long verbleibendeHaltbarkeit) {
        this.verbleibendeHaltbarkeit = verbleibendeHaltbarkeit;
    }

    private long verbleibendeHaltbarkeit;

    public Kremkuchen(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergene, String sorte) {
        this.hersteller = hersteller;
        this.preis = preis;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.allergene = allergene;
        this.sorte = sorte;
    }

    @Override
    public String getKremsorte() {
        return sorte;
    }

    @Override
    public Hersteller getHersteller() {
        return hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    @Override
    public String getTyp() {
        return "Kremkuchen";
    }

    @Override
    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return inspektionsdatum;
    }

    @Override
    public void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    @Override
    public int getFachnummer() {
        return fachnummer;
    }

    public String getKuchentyp() {
        return "Kremkuchen";
    }

    public LocalDateTime getEinfuegedatum() {
        return einfuegedatum;
    }

    @Override
    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    @Override
    public void setEinfuegedatum(LocalDateTime einfuegedatum) {
        this.einfuegedatum = einfuegedatum;
    }


    public String toString() {
        return "[Kremkuchen] [Fachnummer: " + fachnummer + "] [Inspektionsdatum: " + inspektionsdatum + "] [verbleibende Haltbarkeit in Tagen: " + verbleibendeHaltbarkeit + "]";
    }

}
