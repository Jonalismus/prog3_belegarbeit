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
import geschaeftslogik.Model;
import control.singelton.SingletonModel;
import control.singelton.SingeltionSceneController;

public class GUIundCLIListener implements HerstellerEinfuegenEventListener, KuchenEinfuegenEventListener, HerstellerLoeschenEventListener, KuchenLoeschenEventListener, InspektionsEventListener, AllergeneAnzeigenEventListener, KuchenAnzeigenEventListener, HerstellerAnzeigenEventListener{

    public GUIundCLIListener(Model model) {
        SingletonModel.getInstance().setModel(model);
    }

    @Override
    public void onHerstellerEinfuegenEvent(HerstellerEinfuegenEvent herstellerEinfuegenEvent) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onEinfuegenEvent(KuchenEinfuegenEvent kuchenEinfuegenEvent) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onHerstellerLoeschenEvent(HerstellerLoeschenEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onKuchenLoeschenEvent(KuchenLoeschenEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onInspektionsEvent(InspektionsEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onAllergeneAnzeigenEvent(AllergeneAnzeigenEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onHerstellerAnzeigenEvent(HerstellerAnzeigenEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

    @Override
    public void onKuchenAnzeigenEvent(KuchenAnzeigenEvent event) {
        SingeltionSceneController.getInstance().getSceneController().tableUpdateausloesen();
    }

}
