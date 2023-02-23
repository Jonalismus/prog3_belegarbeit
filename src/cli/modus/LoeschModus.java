package cli.modus;


import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEvent;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEvent;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;

import java.util.Scanner;

public class LoeschModus {

    private final HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen;
    private final KuchenLoeschenEventHandler addHandlerKuchenLoeschen;

    public LoeschModus(HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen, KuchenLoeschenEventHandler addHandlerKuchenLoeschen) {
        this.addHandlerHerstellerLoeschen = addHandlerHerstellerLoeschen;
        this.addHandlerKuchenLoeschen = addHandlerKuchenLoeschen;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();

        if (nummerPruefen(input)) {
            // Kuchen nach Fachnummer loeschen
            KuchenLoeschenEvent event = new KuchenLoeschenEvent(this, input);
            if (null != this.addHandlerKuchenLoeschen) {
                this.addHandlerKuchenLoeschen.handle(event);
            }
        } else {
            // Hersteller nach Namen loeschen
            HerstellerLoeschenEvent event2 = new HerstellerLoeschenEvent(this, input);
            if (null != this.addHandlerHerstellerLoeschen) {
                this.addHandlerHerstellerLoeschen.handle(event2);
            }
        }
    }

    // Methode prueft ob eine Zahl eingegeben wurde
    public static boolean nummerPruefen(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}