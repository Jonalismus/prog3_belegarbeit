package view.cli.modus;


import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEvent;
import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEvent;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;

public class LoeschModus implements Modus {

    private final HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen;
    private final KuchenLoeschenEventHandler addHandlerKuchenLoeschen;

    public LoeschModus(HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen, KuchenLoeschenEventHandler addHandlerKuchenLoeschen) {
        this.addHandlerHerstellerLoeschen = addHandlerHerstellerLoeschen;
        this.addHandlerKuchenLoeschen = addHandlerKuchenLoeschen;
    }

    @Override
    public void handleInput(String input) {
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
