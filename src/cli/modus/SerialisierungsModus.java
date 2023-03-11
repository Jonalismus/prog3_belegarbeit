package cli.modus;

import cli.infrastructure.ModelSpeichern.ModelSpeichernEventHandler;
import cli.infrastructure.ModelSpeichern.ModelSpeichernLadenEvent;

public class SerialisierungsModus implements Modus{

    private final ModelSpeichernEventHandler addHandlerSpeichern;

    public SerialisierungsModus(ModelSpeichernEventHandler addHandlerSpeichern){
        this.addHandlerSpeichern = addHandlerSpeichern;
    }

    @Override
    public void handleInput(String input) {
        switch (input){
            case "saveJOS", "loadJOS", "saveJBP", "loadJBP" -> {
                ModelSpeichernLadenEvent event = new ModelSpeichernLadenEvent(this, input);
                if (null != this.addHandlerSpeichern) {
                    this.addHandlerSpeichern.handle(event);
                }
            }
        }
    }
}
