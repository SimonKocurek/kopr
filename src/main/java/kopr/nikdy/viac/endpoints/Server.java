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

        /**
         * Add a parking Lot and return the generated object with ID
         *
         * request body: {"name": str, "capacity": int}
         * response body: {"id": int, "name": str, "capacity": int}
         */
        post("/parkingLot", (request, response) -> {
            master.tell(new AddParkingLotAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        /**
         * Get in percent how used are parking lots
         *
         * request query params: ?id=<firstLot>&id=<secondLot>&id=...
         * response body: {"firstLot": "98%", "secondLot": "23%", ...}
         */
        get("/parkingLot/usage", (request, response) -> {
            master.tell(new GetParkingLotUsagesInPercentAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        /**
         * Get number of visitors during a specified day on a parking lot.
         * This counts every ticket that was added and later removed during the day.
         *
         * request query params: ?day=<day> *2001-2-20
         * response body: 32
         */
        get("/parkingLot/:lotId/visitors", (request, response) -> {
            master.tell(new GetParkingLotVisitorsInDayAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        /**
         * Create a new ticket belonging to some parking lot
         *
         * request body: {"car_licence_plate": str, "parking_lot_id": int}
         * response body: {
         *   "id": uuid,
         *   "car_licence_plate": str,
         *   "parking_lot_id": int,
         *   "arrival_time": date,
         *   "leave_time": null
         * }
         */
        post("/ticket", (request, response) -> {
            master.tell(new AddTicketAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

        /**
         * Mark ticket as used and record it's leave time
         *
         * request empty *:ticketId is UUID in hexadecimal format
         * response body : {
         *   "id": uuid,
         *   "car_licence_plate": str,
         *   "parking_lot_id": int,
         *   "arrival_time": date,
         *   "leave_time": date
         * }
         */
        delete("/ticket/:ticketId", (request, response) -> {
            master.tell(new RemoveTicketAction(request, response), ActorRef.noSender());

            response.wait();
            return response;
        });

    }

}
