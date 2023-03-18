package net.TCP;

import geschaeftslogik.Hersteller;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;
import view.cli.modus.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerTCP {


    private EinfuegenModus einfuegeModus;
    private LoeschModus loeschModus;
    private AenderungsModus aenderungsModus;
    private AnzeigeModus anzeigeModus;

    private Modus aktuellerModus;

    private String nachrichtAnClient = "";


    private SerialisierungsModus serialisierungsModus;

    public void setSerialisierungsModus(SerialisierungsModus serialisierungsModus) {
        this.serialisierungsModus = serialisierungsModus;
    }

    public void setEinfuegeModus(EinfuegenModus einfuegeModus) {
        this.einfuegeModus = einfuegeModus;
    }

    public void setLoeschModus(LoeschModus loeschModus) {
        this.loeschModus = loeschModus;
    }

    public void setAenderungsModus(AenderungsModus aenderungsModus) {
        this.aenderungsModus = aenderungsModus;
    }

    public void setAnzeigeModus(AnzeigeModus anzeigeModus) {
        this.anzeigeModus = anzeigeModus;
    }

    public void handleInput(String input) {
        if (input.startsWith(":")) {
            switch (input) {
                case ":c" -> aktuellerModus = einfuegeModus;
                case ":r" -> aktuellerModus = anzeigeModus;
                case ":d" -> aktuellerModus = loeschModus;
                case ":u" -> aktuellerModus = aenderungsModus;
                case ":p" -> aktuellerModus = serialisierungsModus;
                default -> nachrichtAnClient = "Ungueltiger Befehl. Bitte versuchen Sie es erneut";
            }
        } else {
            if(aktuellerModus == null){
                aktuellerModus = anzeigeModus;
            }
            aktuellerModus.handleInput(input);
        }
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("TCP Server gestartet");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client hat sich verbunden.");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        handleInput(inputLine);
                        out.println(nachrichtAnClient);
                        nachrichtAnClient = "";
                    }
                } catch (IOException e) {
                    System.err.println("Fehler aufgetreten: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler aufgetreten: " + e.getMessage());
        }
    }

    public void sendHerstellerListToServer(List<Hersteller> output) {
        StringBuilder result = new StringBuilder();
        for (Hersteller h : output) {
            result.append("[").append(h).append("] [Anzahl Kuchen: ").append(h.getAnzahlKuchen()).append("] || ");
        }
        this.nachrichtAnClient = result.toString();

    }

    public void sendKuchenListToServer(List<Verkaufsobjekt> output){
        StringBuilder result = new StringBuilder();
        for(Verkaufsobjekt k : output){
            result.append(k).append(" || ");
        }
        this.nachrichtAnClient = result.toString();
    }

    public void sendAllergenListToServer(List<Allergen> output){
        StringBuilder result = new StringBuilder();
        for(Allergen a : output){
            result.append(a.toString()).append(" || ");
        }
        this.nachrichtAnClient = result.toString();
    }

    public void sendInfoToServer(String output) {
        this.nachrichtAnClient = output;
    }

}
