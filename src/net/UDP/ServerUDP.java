package net.UDP;

import cli.modus.*;
import geschaeftslogik.Hersteller;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

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

    private DatagramSocket serverSocket;

    String antwortAnClient = "";

    DatagramPacket datagramPacket;
    public ServerUDP() throws IOException {
        this.serverSocket = new DatagramSocket(5000);
        System.out.println("Server started.");
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

    public void handleInput(String input, DatagramPacket packet) throws IOException {
        if (input.startsWith(":")) {
            switch (input) {
                case ":c":
                    aktuellerModus = einfuegeModus;
                    break;
                case ":r":
                    aktuellerModus = anzeigeModus;
                    break;
                case ":d":
                    aktuellerModus = loeschModus;
                    break;
                case ":u":
                    aktuellerModus = aenderungsModus;
                    break;
                default:
                    sendPacket("Ungueltiger Befehl. Bitte versuchen Sie es erneut", packet);
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
                System.out.println("Nachricht empfangen: " + receivedMessage);
                handleInput(receivedMessage, receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String responseMessage = antwortAnClient;
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                serverSocket.send(responsePacket);
            }
        }
    }

    public void sendPacket(String message, DatagramPacket receivePacket) throws IOException {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacket);
    }

    public void sendHerstellerListToServer(List<Hersteller> output) throws IOException {
        StringBuilder result = new StringBuilder();
        for (Hersteller h : output) {
            result.append("[").append(h).append("] [Anzahl Kuchen: ").append(h.getAnzahlKuchen()).append("] || ");
        }
        sendPacket(result.toString(), datagramPacket);
    }

    public void sendKuchenListToServer(List<Verkaufsobjekt> output) throws IOException {
        StringBuilder result = new StringBuilder();
        for (Verkaufsobjekt k : output) {
            result.append(k).append(" || ");
        }
        sendPacket(result.toString(), datagramPacket);
    }

    public void sendAllergenListToServer(List<Allergen> output) throws IOException {
        StringBuilder result = new StringBuilder();
        for (Allergen a : output) {
            result.append(a.toString()).append(" || ");
        }
        sendPacket(result.toString(), datagramPacket);
    }

    public void sendInfoToServer(String output) throws IOException {
        sendPacket(output, datagramPacket);
    }
}

