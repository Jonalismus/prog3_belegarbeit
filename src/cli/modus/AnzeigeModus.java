package cli.modus;

import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEvent;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEvent;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEvent;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;


import java.util.Scanner;

public class AnzeigeModus {

    private final HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen;
    private final KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen;
    private final AllergeneAnzeigenEventHandler addHandlerAllergeneAnzeigen;

    public AnzeigeModus(HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen, KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen, AllergeneAnzeigenEventHandler addHandlerAllergeneAnzeigen) {
        this.addHandlerHerstellerAnzeigen = addHandlerHerstellerAnzeigen;
        this.addHandlerKuchenAnzeigen = addHandlerKuchenAnzeigen;
        this.addHandlerAllergeneAnzeigen = addHandlerAllergeneAnzeigen;
    }


    // Bei falscher Eingabe wird nicht in die HauptCli gewechselt
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();

        switch (input.toLowerCase()) {
            case "kremkuchen", "obstkuchen", "obsttorte", "kuchen" -> {
                // Kremkuchen, Obstkuchen, Obsttorte oder alle Kuchen anzeigen
                KuchenAnzeigenEvent event = new KuchenAnzeigenEvent(this, input);
                if (null != this.addHandlerKuchenAnzeigen) {
                    this.addHandlerKuchenAnzeigen.handle(event);
                }
            }
            case "allergene i", "allergene e" -> {
                // Alle enthaltenen oder nicht enthaltene Allergene im Automaten  anzeigen
                AllergeneAnzeigenEvent event = new AllergeneAnzeigenEvent(this, input);
                if (null != this.addHandlerAllergeneAnzeigen) {
                    this.addHandlerAllergeneAnzeigen.handle(event);
                }
            }
            case "hersteller" -> {
                // Alle Hersteller im Automaten  anzeigen
                HerstellerAnzeigenEvent event = new HerstellerAnzeigenEvent(this, input);
                if (null != this.addHandlerHerstellerAnzeigen) {
                    this.addHandlerHerstellerAnzeigen.handle(event);
                }
            }
        }
    }

}
