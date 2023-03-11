package cli.modus;

import cli.infrastructure.InspektionsdatumSetzen.InspektionsEvent;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;

public class AenderungsModus implements Modus {

    private final InspektionsEventHandler addHandlerInspektion;

    public AenderungsModus(InspektionsEventHandler addHandlerInspektion) {
        this.addHandlerInspektion = addHandlerInspektion;
    }


    @Override
    public void handleInput(String input) {
        if (LoeschModus.nummerPruefen(input)) {
            // Inspektionsdatum nach Fachnummer loeschen
            InspektionsEvent event = new InspektionsEvent(this, input);
            if (null != this.addHandlerInspektion) {
                this.addHandlerInspektion.handle(event);
            }
        } else {
            System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
        }
    }
    }



