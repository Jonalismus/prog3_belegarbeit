package net.UDP;

import geschaeftslogik.Hersteller;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;
import view.cli.modus.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class ServerUDP {

    private EinfuegenModus einfuegeModus;
    private LoeschModus loeschModus;
    private AenderungsModus aenderungsModus;
    private AnzeigeModus anzeigeModus;

    private Modus aktuellerModus;

    String antwortAnClient = "";

    DatagramPacket datagramPacket;

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
                default -> antwortAnClient = "Ungueltiger Befehl. Bitte versuchen Sie es erneut";
            }
        } else {
            if(aktuellerModus == null){
                aktuellerModus = anzeigeModus;
            }
            aktuellerModus.handleInput(input);
        }
    }

    public void start() throws IOException {
        int port = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("UDP Server gestartet.");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivePacket);
                datagramPacket = receivePacket;

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                antwortAnClient = "";
                handleInput(receivedMessage);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String responseMessage = antwortAnClient;
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                serverSocket.send(responsePacket);
            }
        }
    }

    public void sendHerstellerListToServer(List<Hersteller> output){
        StringBuilder result = new StringBuilder();
        for (Hersteller h : output) {
            result.append("[").append(h).append("] [Anzahl Kuchen: ").append(h.getAnzahlKuchen()).append("] || ");
        }
        antwortAnClient = result.toString();
    }

    public void sendKuchenListToServer(List<Verkaufsobjekt> output){
        StringBuilder result = new StringBuilder();
        for (Verkaufsobjekt k : output) {
            result.append(k).append(" || ");
        }
        antwortAnClient = result.toString();
    }

    public void sendAllergenListToServer(List<Allergen> output){
        StringBuilder result = new StringBuilder();
        for (Allergen a : output) {
            result.append(a.toString()).append(" || ");
        }
        antwortAnClient = result.toString();
    }

    public void sendInfoToServer(String output){
        antwortAnClient = output;
    }
}

