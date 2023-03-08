package cli.modus;

import cli.infrastructure.ModelSpeichern.ModelSpeichernLadenEvent;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventHandler;

import java.util.Scanner;

public class SerialisierungsModus {

    private final ModelSpeichernEventHandler addHandlerSpeichern;

    public SerialisierungsModus(ModelSpeichernEventHandler addHandlerSpeichern){
        this.addHandlerSpeichern = addHandlerSpeichern;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine();

        switch (input){
            case "saveJOS", "loadJOS" -> {
                ModelSpeichernLadenEvent event = new ModelSpeichernLadenEvent(this, input);
                if (null != this.addHandlerSpeichern) {
                    this.addHandlerSpeichern.handle(event);
                }
            }
        }

    }

}
