package view.cli.modus;

import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEvent;
import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEvent;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEvent;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;

public class AnzeigeModus implements Modus {

    private final HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen;
    private final KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen;
    private final AllergeneAnzeigenEventHandler addHandlerAllergeneAnzeigen;

    public AnzeigeModus(HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen, KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen, AllergeneAnzeigenEventHandler addHandlerAllergeneAnzeigen) {
        this.addHandlerHerstellerAnzeigen = addHandlerHerstellerAnzeigen;
        this.addHandlerKuchenAnzeigen = addHandlerKuchenAnzeigen;
        this.addHandlerAllergeneAnzeigen = addHandlerAllergeneAnzeigen;
    }

    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "kremkuchen", "obstkuchen", "obsttorte", "kuchen" -> {
                // Kremkuchen, Obstkuchen, Obsttorte oder alle Kuchen anzeigen
                KuchenAnzeigenEvent kuchenEvent = new KuchenAnzeigenEvent(this, input);
                if (null != this.addHandlerKuchenAnzeigen) {
                    this.addHandlerKuchenAnzeigen.handle(kuchenEvent);
                }
            }
            case "allergene i", "allergene e" -> {
                // Alle enthaltenen oder nicht enthaltene Allergene im Automaten anzeigen
                AllergeneAnzeigenEvent allergeneEvent = new AllergeneAnzeigenEvent(this, input);
                if (null != this.addHandlerAllergeneAnzeigen) {
                    this.addHandlerAllergeneAnzeigen.handle(allergeneEvent);
                }
            }
            case "hersteller" -> {
                // Alle Hersteller im Automaten anzeigen
                HerstellerAnzeigenEvent herstellerEvent = new HerstellerAnzeigenEvent(this, input);
                if (null != this.addHandlerHerstellerAnzeigen) {
                    this.addHandlerHerstellerAnzeigen.handle(herstellerEvent);
                }
            }
            default -> System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
        }
    }

}
