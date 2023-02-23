package cli.modus;

import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEvent;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEvent;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;

import java.util.Scanner;

public class EinfuegenModus {

    private final HerstellerEinfuegenEventHandler addHandlerHersteller;
    private final KuchenEinfuegenEventHandler addHandlerKuchen;

    public EinfuegenModus(HerstellerEinfuegenEventHandler addHandlerHersteller, KuchenEinfuegenEventHandler addHandlerKuchen) {
        this.addHandlerHersteller = addHandlerHersteller;
        this.addHandlerKuchen = addHandlerKuchen;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();

        String[] commandParts = input.split(" ");

        if (commandParts.length < 4) {
            // Hersteller einfügen
            HerstellerEinfuegenEvent event = new HerstellerEinfuegenEvent(this, commandParts[0]);
            if (null != this.addHandlerHersteller) {
                this.addHandlerHersteller.handle(event);
            }
        } else {
            // Kuchen einfügen
            KuchenEinfuegenEvent event2 = new KuchenEinfuegenEvent(this, commandParts[0], commandParts[1], commandParts[2], commandParts[3], commandParts[4], commandParts[5], commandParts.length > 6 ? commandParts[6] : null);
            if (null != this.addHandlerKuchen) {
                this.addHandlerKuchen.handle(event2);
            }
        }
    }
}
