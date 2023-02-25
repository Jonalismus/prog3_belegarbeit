package cli;


import cli.modus.AenderungsModus;
import cli.modus.AnzeigeModus;
import cli.modus.EinfuegenModus;
import cli.modus.LoeschModus;

import java.util.Scanner;

public class HauptCLI {
    private final EinfuegenModus einfuegeModus;
    private final LoeschModus loeschModus;
    private final AenderungsModus aenderungsModus;
    private final AnzeigeModus anzeigeModus;

    public HauptCLI(EinfuegenModus einfuegeModus, LoeschModus loeschModus, AenderungsModus aenderungsModus, AnzeigeModus anzeigeModus) {
        this.einfuegeModus = einfuegeModus;
        this.loeschModus = loeschModus;
        this.aenderungsModus = aenderungsModus;
        this.anzeigeModus = anzeigeModus;
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
                default -> System.out.println("Ungueltiger Befehl. Bitte versuchen Sie es erneut");
            }
        }
    }

}
