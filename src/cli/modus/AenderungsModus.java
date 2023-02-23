package cli.modus;

import cli.infrastructure.InspektionsdatumSetzen.InspektionsEvent;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;


import java.util.Scanner;

public class AenderungsModus {

    private final InspektionsEventHandler addHandlerInspektion;

    public AenderungsModus(InspektionsEventHandler addHandlerInspektion) {
        this.addHandlerInspektion = addHandlerInspektion;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();


        if (LoeschModus.nummerPruefen(input)) {
            // Inspektionsdatum nach Fachnummer loeschen
            InspektionsEvent event = new InspektionsEvent(this, input);
            if (null != this.addHandlerInspektion) {
                this.addHandlerInspektion.handle(event);
            }
        }
    }

}


