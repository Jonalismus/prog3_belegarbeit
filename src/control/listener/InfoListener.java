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
import control.infrastructure.ModelSpeichern.ModelSpeichernLadenEvent;
import control.infrastructure.ModelSpeichern.ModelSpeichernEventListener;

public class InfoListener implements HerstellerEinfuegenEventListener, KuchenEinfuegenEventListener, HerstellerLoeschenEventListener, KuchenLoeschenEventListener, InspektionsEventListener, AllergeneAnzeigenEventListener, KuchenAnzeigenEventListener, HerstellerAnzeigenEventListener, ModelSpeichernEventListener {
    @Override
    public void onHerstellerEinfuegenEvent(HerstellerEinfuegenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onEinfuegenEvent(KuchenEinfuegenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onHerstellerLoeschenEvent(HerstellerLoeschenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onKuchenLoeschenEvent(KuchenLoeschenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onInspektionsEvent(InspektionsEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onAllergeneAnzeigenEvent(AllergeneAnzeigenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onHerstellerAnzeigenEvent(HerstellerAnzeigenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onKuchenAnzeigenEvent(KuchenAnzeigenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }

    @Override
    public void onModelSpeichernEvent(ModelSpeichernLadenEvent event) {
        System.out.println("Eine Operation wurde ausgefuehrt");
    }
}
