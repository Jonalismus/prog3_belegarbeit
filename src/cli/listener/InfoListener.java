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

public class InfoListener implements HerstellerEinfuegenEventListener, KuchenEinfuegenEventListener, HerstellerLoeschenEventListener, KuchenLoeschenEventListener, InspektionsEventListener, AllergeneAnzeigenEventListener, KuchenAnzeigenEventListener, HerstellerAnzeigenEventListener {
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
}
