package cli;


import cli.modus.*;

import java.util.Scanner;

public class HauptCLI {
    private final EinfuegenModus einfuegeModus;
    private final LoeschModus loeschModus;
    private final AenderungsModus aenderungsModus;
    private final AnzeigeModus anzeigeModus;

    private final SerialisierungsModus serialisierungsModus;

    public HauptCLI(EinfuegenModus einfuegeModus, LoeschModus loeschModus, AenderungsModus aenderungsModus, AnzeigeModus anzeigeModus, SerialisierungsModus serialisierungsModus) {
        this.einfuegeModus = einfuegeModus;
        this.loeschModus = loeschModus;
        this.aenderungsModus = aenderungsModus;
        this.anzeigeModus = anzeigeModus;
        this.serialisierungsModus = serialisierungsModus;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            switch (input) {
                case ":c" -> einfuegeModus.start();
                case ":r" -> anzeigeModus.start();
                case ":d" -> loeschModus.start();
                case ":u" -> aenderungsModus.start();
                case ":p" -> serialisierungsModus.start();
                default -> System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
            }
        }
    }

}
