package geschaeftslogik;

import vertrag.Allergen;
import vertrag.Hersteller;
import vertrag.Kuchen;
import vertrag.Verkaufsobjekt;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public class Obsttorte implements Verkaufsobjekt, Kuchen, vertrag.Obsttorte{

    private final Hersteller hersteller;
    private final BigDecimal preis;
    private final int naehrwert;
    private final Duration haltbarkeit;
    private final Collection<Allergen> allergene;
    private final String sorteEins;

    private final String sorteZwei;
    private int fachnummer;

    private LocalDateTime einfuegedatum;
    private Date inspektionsdatum;
    private long verbleibendeHaltbarkeit;


    public Obsttorte(Hersteller hersteller, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergene, String sorteEins, String sorteZwei) {
        this.hersteller = hersteller;
        this.preis = preis;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.allergene = allergene;
        this.sorteEins = sorteEins;
        this.sorteZwei = sorteZwei;
    }

    @Override
    public String getKremsorte() {
        return sorteZwei;
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
    public void setVerbleibendeHaltbarkeit(long verbleibendeHaltbarkeit) {
        this.verbleibendeHaltbarkeit = verbleibendeHaltbarkeit;
    }

    @Override
    public String getTyp() {
        return "Obsttorte";
    }

    @Override
    public String getObstsorte() {
        return sorteEins;
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

    @Override
    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    @Override
    public void setEinfuegedatum(LocalDateTime einfuegedatum) {
        this.einfuegedatum = einfuegedatum;
    }
    public LocalDateTime getEinfuegedatum() {
        return einfuegedatum;
    }

    public String toString() {
        return "[Obsttorte] [Fachnummer: " + fachnummer + "] [Inspektionsdatum: " + inspektionsdatum + "] [verbleibende Haltbarkeit in Tagen: " + verbleibendeHaltbarkeit + "]";
    }

}
