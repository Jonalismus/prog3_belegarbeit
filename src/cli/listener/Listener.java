package cli.listener;

import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEvent;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventListener;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEvent;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEvent;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEvent;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventListener;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEvent;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEvent;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEvent;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEvent;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import cli.infrastructure.ModelSpeichern.ModelSpeichernLadenEvent;
import cli.infrastructure.ModelSpeichern.ModelSpeichernEventListener;
import geschaeftslogik.*;
import serialisierung.JBP;
import serialisierung.JOS;
import serialisierung.SingletonModel;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Listener implements HerstellerEinfuegenEventListener, KuchenEinfuegenEventListener, HerstellerLoeschenEventListener, KuchenLoeschenEventListener, InspektionsEventListener, AllergeneAnzeigenEventListener, KuchenAnzeigenEventListener, HerstellerAnzeigenEventListener, ModelSpeichernEventListener {


    private final Model model;

    public Listener(Model model){
        SingletonModel.getInstance().setModel(model);
        this.model = SingletonModel.getInstance().getModel();
    }
    @Override
    public void onHerstellerEinfuegenEvent(HerstellerEinfuegenEvent herstellerEinfuegenEvent) {
        Hersteller hersteller = new Hersteller(herstellerEinfuegenEvent.getHersteller());
        SingletonModel.getInstance().getModel().herstellerEinfuegen(hersteller);
    }

    @Override
    public void onEinfuegenEvent(KuchenEinfuegenEvent kuchenEinfuegenEvent) {

        Hersteller hersteller = new Hersteller(kuchenEinfuegenEvent.getHersteller());

        BigDecimal preis;
        try {
            preis = new BigDecimal(kuchenEinfuegenEvent.getPreis().replace(",", "."));
        } catch (IllegalArgumentException | ArithmeticException e) {
            return;
        }

        int naehrwert;
        try {
            naehrwert = Integer.parseInt(kuchenEinfuegenEvent.getNaehrwert());
        } catch (NumberFormatException e) {
            return;
        }

        Duration haltbarkeit;
        try {
            haltbarkeit = Duration.ofDays(Integer.parseInt(kuchenEinfuegenEvent.getHaltbarkeit()));
        } catch (NumberFormatException e) {
            return;
        }

        String[] allergenStrings = kuchenEinfuegenEvent.getAllergene().split(",");
        Collection<Allergen> allergene = new ArrayList<>();
        for (String allergenString : allergenStrings) {
            allergene.add(Allergen.fromString(allergenString));
        }
        String sorte = kuchenEinfuegenEvent.getSorte();
        String[] sorteZwei = kuchenEinfuegenEvent.getSorteZwei();


        switch (kuchenEinfuegenEvent.getKuchentyp()) {
            case "Kremkuchen" -> {
                Kremkuchen kremkuchen = new Kremkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
                SingletonModel.getInstance().getModel().verkaufsObjektEinfuegen(kremkuchen);
            }
            case "Obstkuchen" -> {
                Obstkuchen Obstkuchen = new Obstkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
                SingletonModel.getInstance().getModel().verkaufsObjektEinfuegen(Obstkuchen);
            }
            case "Obsttorte" -> {
                Obsttorte Obsttorte = new Obsttorte(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte, sorteZwei[0]);
                SingletonModel.getInstance().getModel().verkaufsObjektEinfuegen(Obsttorte);
            }
        }
    }

    @Override
    public void onHerstellerLoeschenEvent(HerstellerLoeschenEvent event) {
        SingletonModel.getInstance().getModel().herstellerLoeschen(event.getHersteller());
    }

    @Override
    public void onKuchenLoeschenEvent(KuchenLoeschenEvent event) {
        int fachnummer = Integer.parseInt(event.getFachnummer());
        SingletonModel.getInstance().getModel().verkaufsObjektLoeschen(fachnummer);
    }

    @Override
    public void onInspektionsEvent(InspektionsEvent event) {
        int fachnummer = Integer.parseInt(event.getFachnummer());
        SingletonModel.getInstance().getModel().inspektionsDatumSetzen(fachnummer);
    }

    @Override
    public void onAllergeneAnzeigenEvent(AllergeneAnzeigenEvent event) {
        if(event.getAllergene().equals("allergene i")){
            List<Allergen> allergene = SingletonModel.getInstance().getModel().allergeneAbrufen(true);
            for(Allergen a : allergene){
                System.out.println(a.toString());
            }
        } else if (event.getAllergene().equals("allergene e")){
            List<Allergen> allergene = SingletonModel.getInstance().getModel().allergeneAbrufen(false);
            for(Allergen a : allergene){
                System.out.println(a.toString());
            }
        }
    }

    @Override
    public void onHerstellerAnzeigenEvent(HerstellerAnzeigenEvent event) {
        List<Hersteller> res =  SingletonModel.getInstance().getModel().abrufenDerHersteller();
        for(Hersteller h : res){
            System.out.println("[" + h + "] [Anzahl Kuchen: " + h.getAnzahlKuchen() + "]");
        }
    }

    @Override
    public void onKuchenAnzeigenEvent(KuchenAnzeigenEvent event) {
        List<Verkaufsobjekt> res = SingletonModel.getInstance().getModel().kuchenAbrufen(event.getKuchenTyp());
        for(Verkaufsobjekt re : res){
            System.out.println(re);
        }
    }

    @Override
    public void onModelSpeichernEvent(ModelSpeichernLadenEvent event) {
        JOS jos = new JOS(model);
        JBP jbp = new JBP(model);
        if(event.getSpeicherArt().equals("saveJOS")){
            try {
                jos.serialisierenJOS();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(event.getSpeicherArt().equals("loadJOS")){
            try {
                Model deserialisirungsModel = jos.deserialisierenJOS();
                SingletonModel.getInstance().setModel(deserialisirungsModel);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(event.getSpeicherArt().equals("saveJBP")){
            try{
                jbp.serialisierenJBP();
            } catch (IOException e){
                throw new RuntimeException();
            }
        }
        if(event.getSpeicherArt().equals("loadJBP")){
            try{
                Model deserialisirungsModel = jbp.deserialisierenJBP();
                SingletonModel.getInstance().setModel(deserialisirungsModel);
            } catch (IOException e){
                throw new RuntimeException();
            }
        }
    }
}