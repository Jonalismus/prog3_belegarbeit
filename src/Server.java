import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventHandler;
import cli.infrastructure.AllergeneAnzeigen.AllergeneAnzeigenEventListener;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventHandler;
import cli.infrastructure.HerstellerAnzeigen.HerstellerAnzeigenEventListener;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventHandler;
import cli.infrastructure.HerstellerEinfuegen.HerstellerEinfuegenEventListener;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventHandler;
import cli.infrastructure.HerstellerLoeschen.HerstellerLoeschenEventListener;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventHandler;
import cli.infrastructure.InspektionsdatumSetzen.InspektionsEventListener;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventHandler;
import cli.infrastructure.KuchenAnzeigen.KuchenAnzeigenEventListener;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventHandler;
import cli.infrastructure.KuchenEinfuegen.KuchenEinfuegenEventListener;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventHandler;
import cli.infrastructure.KuchenLoeschen.KuchenLoeschenEventListener;
import cli.listener.NetListenerTCP;
import cli.listener.NetListenerUDP;
import cli.modus.AenderungsModus;
import cli.modus.AnzeigeModus;
import cli.modus.EinfuegenModus;
import cli.modus.LoeschModus;
import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import net.TCP.ServerTCP;
import net.UDP.ServerUDP;
import vertrag.Verkaufsobjekt;

import java.io.IOException;
import java.util.LinkedList;

public class Server {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java Server <TCP/UDP> <kapazitaet>");
            System.exit(1);
        }

        int kapazitaet = Integer.parseInt(args[1]);
        LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
        LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
        Model model = new Model(kapazitaet, verkaufsobjektLinkedList, herstellerLinkedList);

        if (args[0].equalsIgnoreCase("TCP")) {
            ServerTCP serverTCP = new ServerTCP();

            //Kuchen Einfuegen Event
            KuchenEinfuegenEventHandler addHandlerKuchen = new KuchenEinfuegenEventHandler();
            KuchenEinfuegenEventListener addListenerKuchen = new NetListenerTCP(model, serverTCP);
            addHandlerKuchen.add(addListenerKuchen);
            //Hersteller Einfuegen Event
            HerstellerEinfuegenEventHandler addHandlerHersteller = new HerstellerEinfuegenEventHandler();
            HerstellerEinfuegenEventListener addListenerHersteller = new NetListenerTCP(model, serverTCP);
            addHandlerHersteller.add(addListenerHersteller);

            EinfuegenModus einfuegenModus = new EinfuegenModus(addHandlerHersteller, addHandlerKuchen);
            serverTCP.setEinfuegeModus(einfuegenModus);

            //Kuchen Loeschen Event
            KuchenLoeschenEventHandler addHandlerKuchenLoeschen = new KuchenLoeschenEventHandler();
            KuchenLoeschenEventListener addListenerKuchenLoeschen = new NetListenerTCP(model, serverTCP);
            addHandlerKuchenLoeschen.add(addListenerKuchenLoeschen);
            //Hersteller Loeschen Event
            HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen = new HerstellerLoeschenEventHandler();
            HerstellerLoeschenEventListener addListenerHerstellerLoeschen = new NetListenerTCP(model, serverTCP);
            addHandlerHerstellerLoeschen.add(addListenerHerstellerLoeschen);

            LoeschModus loeschModus = new LoeschModus(addHandlerHerstellerLoeschen, addHandlerKuchenLoeschen);
            serverTCP.setLoeschModus(loeschModus);

            //Inspektionsdatum setzen Event
            InspektionsEventHandler addHandlerInspektion = new InspektionsEventHandler();
            InspektionsEventListener addListenerInspektion = new NetListenerTCP(model, serverTCP);
            addHandlerInspektion.add(addListenerInspektion);

            AenderungsModus aenderungsModus = new AenderungsModus(addHandlerInspektion);
            serverTCP.setAenderungsModus(aenderungsModus);

            //Allergene anzeigen Event
            AllergeneAnzeigenEventHandler addHandlerAllergene = new AllergeneAnzeigenEventHandler();
            AllergeneAnzeigenEventListener addListenerAllergene = new NetListenerTCP(model, serverTCP);
            addHandlerAllergene.add(addListenerAllergene);
            //Kuchen anzeigen Event
            KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen = new KuchenAnzeigenEventHandler();
            KuchenAnzeigenEventListener addListenerKuchenAnzeigen = new NetListenerTCP(model, serverTCP);
            addHandlerKuchenAnzeigen.add(addListenerKuchenAnzeigen);
            //Hersteller anzeigen Event
            HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen = new HerstellerAnzeigenEventHandler();
            HerstellerAnzeigenEventListener addListenerHerstellerAnzeigen = new NetListenerTCP(model, serverTCP);
            addHandlerHerstellerAnzeigen.add(addListenerHerstellerAnzeigen);

            AnzeigeModus anzeigeModus = new AnzeigeModus(addHandlerHerstellerAnzeigen, addHandlerKuchenAnzeigen, addHandlerAllergene);
            serverTCP.setAnzeigeModus(anzeigeModus);

            serverTCP.start();
        }
        else if (args[0].equalsIgnoreCase("UDP")) {
            ServerUDP serverUDP = new ServerUDP();

            //Kuchen Einfuegen Event
            KuchenEinfuegenEventHandler addHandlerKuchen = new KuchenEinfuegenEventHandler();
            KuchenEinfuegenEventListener addListenerKuchen = new NetListenerUDP(model, serverUDP);
            addHandlerKuchen.add(addListenerKuchen);
            //Hersteller Einfuegen Event
            HerstellerEinfuegenEventHandler addHandlerHersteller = new HerstellerEinfuegenEventHandler();
            HerstellerEinfuegenEventListener addListenerHersteller = new NetListenerUDP(model, serverUDP);
            addHandlerHersteller.add(addListenerHersteller);

            EinfuegenModus einfuegenModus = new EinfuegenModus(addHandlerHersteller, addHandlerKuchen);
            serverUDP.setEinfuegeModus(einfuegenModus);

            //Kuchen Loeschen Event
            KuchenLoeschenEventHandler addHandlerKuchenLoeschen = new KuchenLoeschenEventHandler();
            KuchenLoeschenEventListener addListenerKuchenLoeschen = new NetListenerUDP(model, serverUDP);
            addHandlerKuchenLoeschen.add(addListenerKuchenLoeschen);
            //Hersteller Loeschen Event
            HerstellerLoeschenEventHandler addHandlerHerstellerLoeschen = new HerstellerLoeschenEventHandler();
            HerstellerLoeschenEventListener addListenerHerstellerLoeschen = new NetListenerUDP(model, serverUDP);
            addHandlerHerstellerLoeschen.add(addListenerHerstellerLoeschen);

            LoeschModus loeschModus = new LoeschModus(addHandlerHerstellerLoeschen, addHandlerKuchenLoeschen);
            serverUDP.setLoeschModus(loeschModus);

            //Inspektionsdatum setzen Event
            InspektionsEventHandler addHandlerInspektion = new InspektionsEventHandler();
            InspektionsEventListener addListenerInspektion = new NetListenerUDP(model, serverUDP);
            addHandlerInspektion.add(addListenerInspektion);

            AenderungsModus aenderungsModus = new AenderungsModus(addHandlerInspektion);
            serverUDP.setAenderungsModus(aenderungsModus);

            //Allergene anzeigen Event
            AllergeneAnzeigenEventHandler addHandlerAllergene = new AllergeneAnzeigenEventHandler();
            AllergeneAnzeigenEventListener addListenerAllergene = new NetListenerUDP(model, serverUDP);
            addHandlerAllergene.add(addListenerAllergene);
            //Kuchen anzeigen Event
            KuchenAnzeigenEventHandler addHandlerKuchenAnzeigen = new KuchenAnzeigenEventHandler();
            KuchenAnzeigenEventListener addListenerKuchenAnzeigen = new NetListenerUDP(model, serverUDP);
            addHandlerKuchenAnzeigen.add(addListenerKuchenAnzeigen);
            //Hersteller anzeigen Event
            HerstellerAnzeigenEventHandler addHandlerHerstellerAnzeigen = new HerstellerAnzeigenEventHandler();
            HerstellerAnzeigenEventListener addListenerHerstellerAnzeigen = new NetListenerUDP(model, serverUDP);
            addHandlerHerstellerAnzeigen.add(addListenerHerstellerAnzeigen);

            AnzeigeModus anzeigeModus = new AnzeigeModus(addHandlerHerstellerAnzeigen, addHandlerKuchenAnzeigen, addHandlerAllergene);
            serverUDP.setAnzeigeModus(anzeigeModus);

            serverUDP.start();
        }
    }
}
