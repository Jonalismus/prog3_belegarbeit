package control.listener;

import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEvent;
import control.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventListener;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEvent;
import control.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEvent;
import control.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEvent;
import control.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventListener;
import control.infrastructure.InspektionsdatumSetzen.InspektionsEvent;
import control.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEvent;
import control.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEvent;
import control.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEvent;
import control.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import control.infrastructure.ModelSpeichern.ModelSpeichernEventListener;
import control.infrastructure.ModelSpeichern.ModelSpeichernLadenEvent;
import geschaeftslogik.*;
import net.TCP.ServerTCP;
import serialisierung.JBP;
import serialisierung.JOS;
import serialisierung.SingletonModel;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class NetListenerTCP implements HerstellerEinfuegenEventListener, KuchenEinfuegenEventListener, HerstellerLoeschenEventListener, KuchenLoeschenEventListener, InspektionsEventListener, AllergeneAnzeigenEventListener, KuchenAnzeigenEventListener, HerstellerAnzeigenEventListener, ModelSpeichernEventListener {


    private final ServerTCP serverTCP;

    public NetListenerTCP(Model model, ServerTCP serverTCP){
        SingletonModel.getInstance().setModel(model);
        this.serverTCP = serverTCP;
    }
    @Override
    public void onHerstellerEinfuegenEvent(HerstellerEinfuegenEvent herstellerEinfuegenEvent) {
        Hersteller hersteller = new Hersteller(herstellerEinfuegenEvent.getHersteller());
        SingletonModel.getInstance().getModel().herstellerEinfuegen(hersteller);
        serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
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
                serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
            }
            case "Obstkuchen" -> {
                Obstkuchen Obstkuchen = new Obstkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
                SingletonModel.getInstance().getModel().verkaufsObjektEinfuegen(Obstkuchen);
                serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
            }
            case "Obsttorte" -> {
                Obsttorte Obsttorte = new Obsttorte(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte, sorteZwei[0]);
                SingletonModel.getInstance().getModel().verkaufsObjektEinfuegen(Obsttorte);
                serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
            }
        }
    }

    @Override
    public void onHerstellerLoeschenEvent(HerstellerLoeschenEvent event) {
        SingletonModel.getInstance().getModel().herstellerLoeschen(event.getHersteller());
        serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onKuchenLoeschenEvent(KuchenLoeschenEvent event) {
        int fachnummer = Integer.parseInt(event.getFachnummer());
        SingletonModel.getInstance().getModel().verkaufsObjektLoeschen(fachnummer);
        serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onInspektionsEvent(InspektionsEvent event) {
        int fachnummer = Integer.parseInt(event.getFachnummer());
        SingletonModel.getInstance().getModel().inspektionsDatumSetzen(fachnummer);
        serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onAllergeneAnzeigenEvent(AllergeneAnzeigenEvent event) {
        if(event.getAllergene().equals("allergene i")){
            List<Allergen> allergene = SingletonModel.getInstance().getModel().allergeneAbrufen(true);
            serverTCP.sendAllergenListToServer(allergene);
        } else if (event.getAllergene().equals("allergene e")){
            List<Allergen> allergene = SingletonModel.getInstance().getModel().allergeneAbrufen(false);
            serverTCP.sendAllergenListToServer(allergene);
        }
    }

    @Override
    public void onHerstellerAnzeigenEvent(HerstellerAnzeigenEvent event) {
        List<Hersteller> res = SingletonModel.getInstance().getModel().abrufenDerHersteller();
        serverTCP.sendHerstellerListToServer(res); // sendet die Ausgabe an den Client über ServerTCP

    }

    @Override
    public void onKuchenAnzeigenEvent(KuchenAnzeigenEvent event) {
        List<Verkaufsobjekt> res = SingletonModel.getInstance().getModel().kuchenAbrufen(event.getKuchenTyp());
        serverTCP.sendKuchenListToServer(res);
    }

    @Override
    public void onModelSpeichernEvent(ModelSpeichernLadenEvent event) {
        JOS jos = new JOS(SingletonModel.getInstance().getModel());
        JBP jbp = new JBP(SingletonModel.getInstance().getModel());
        if (event.getSpeicherArt().equals("saveJOS")) {
            jos.serialisierenJOS();
            serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
        }
        if (event.getSpeicherArt().equals("loadJOS")) {
            Model deserialisirungsModel = jos.deserialisierenJOS();
            if (deserialisirungsModel != null) {
                SingletonModel.getInstance().setModel(deserialisirungsModel);
                serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
            }
        }
        if (event.getSpeicherArt().equals("saveJBP")) {
            jbp.serialisierenJBP();
            serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
        }
        if (event.getSpeicherArt().equals("loadJBP")) {
            Model deserialisirungsModel = jbp.deserialisierenJBP();
            if (deserialisirungsModel != null) {
                SingletonModel.getInstance().setModel(deserialisirungsModel);
                serverTCP.sendInfoToServer("Eine Operation wurde ausgefuehrt");
            }
        }
    }
}
