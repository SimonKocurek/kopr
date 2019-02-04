package kopr.nikdy.viac.endpoints;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import kopr.nikdy.viac.actions.*;
import kopr.nikdy.viac.actors.MasterActor;

import static spark.Spark.*;

public class Server {

    public static void registerEndpoints() {
        ActorSystem system = ActorSystem.create();
        ActorRef master = system.actorOf(MasterActor.props());

        post("/parkingLot", (request, response) -> {
            master.tell(new AddParkingLotAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        get("/parkingLot/usage", (request, response) -> {
            master.tell(new GetParkingLotUsagesInPercentAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        get("/parkingLot/:lotId/visitors", (request, response) -> {
            master.tell(new GetParkingLotVisitorsInDayAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        post("/parkingLot/:lotId/ticket", (request, response) -> {
            master.tell(new AddTicketAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        delete("/ticket/:ticketId", (request, response) -> {
            master.tell(new RemoveTicketAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

    }

}
