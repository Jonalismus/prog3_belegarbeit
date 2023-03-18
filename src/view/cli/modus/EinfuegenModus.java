package view.cli.modus;

import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEvent;
import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEvent;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;

public class EinfuegenModus implements Modus {

    private final HerstellerEinfuegenEventHandler addHandlerHersteller;
    private final KuchenEinfuegenEventHandler addHandlerKuchen;

    public EinfuegenModus(HerstellerEinfuegenEventHandler addHandlerHersteller, KuchenEinfuegenEventHandler addHandlerKuchen) {
        this.addHandlerHersteller = addHandlerHersteller;
        this.addHandlerKuchen = addHandlerKuchen;
    }


    @Override
    public void handleInput(String input) {
        String[] commandParts = input.split(" ");

        if (commandParts.length <= 4) {
            // Hersteller einfügen
            String herstellerName = String.join(" ", commandParts);
            HerstellerEinfuegenEvent event = new HerstellerEinfuegenEvent(this,herstellerName);
            if (null != this.addHandlerHersteller) {
                this.addHandlerHersteller.handle(event);
            }
        } else if(commandParts.length == 7 || commandParts.length == 8) {
            // Kuchen einfügen
            KuchenEinfuegenEvent event2 = new KuchenEinfuegenEvent(this, commandParts[0], commandParts[1], commandParts[2], commandParts[3], commandParts[4], commandParts[5], commandParts[6]);
            if (null != this.addHandlerKuchen) {
                this.addHandlerKuchen.handle(event2);
            }
        }
    }
}
