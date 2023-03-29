package view.cli;


import view.cli.modus.*;

import java.util.Scanner;

public class HauptCLI implements Runnable {
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
        Modus aktuellerModus = anzeigeModus;

        while (true) {
            input = scanner.nextLine();

            if (input.startsWith(":")) {
                switch (input) {
                    case ":c" -> aktuellerModus = einfuegeModus;
                    case ":r" -> aktuellerModus = anzeigeModus;
                    case ":d" -> aktuellerModus = loeschModus;
                    case ":u" -> aktuellerModus = aenderungsModus;
                    case ":p" -> aktuellerModus = serialisierungsModus;
                    default -> System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
                }
            } else {
                aktuellerModus.handleInput(input);
            }
        }
    }


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        Modus aktuellerModus = anzeigeModus;

        while (true) {
            input = scanner.nextLine();

            if (input.startsWith(":")) {
                switch (input) {
                    case ":c" -> aktuellerModus = einfuegeModus;
                    case ":r" -> aktuellerModus = anzeigeModus;
                    case ":d" -> aktuellerModus = loeschModus;
                    case ":u" -> aktuellerModus = aenderungsModus;
                    case ":p" -> aktuellerModus = serialisierungsModus;
                    default -> System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
                }
            } else {
                aktuellerModus.handleInput(input);
            }
        }
    }
}
