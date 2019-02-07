package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import kopr.nikdy.viac.actions.ActionDone;
import kopr.nikdy.viac.actions.AddParkingLotAction;
import kopr.nikdy.viac.actions.GetParkingLotUsagesInPercentAction;
import kopr.nikdy.viac.actions.GetParkingLotVisitorsInDayAction;
import kopr.nikdy.viac.persistance.Database;
import org.eclipse.jetty.http.HttpStatus;

import java.sql.SQLException;
import java.util.Map;

public class ParkingLotActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddParkingLotAction.class, this::handleAddParkingLotAction)
                .match(GetParkingLotVisitorsInDayAction.class, this::handleGetParkingLotVisitorsInDayAction)
                .match(GetParkingLotUsagesInPercentAction.class, this::handleGetParkingLotUsagesInPercentAction)

                .build();
    }

    private void handleAddParkingLotAction(AddParkingLotAction action) {
        try {
            Database.addParkingLot(action.getParkingLot());
            action.setResponseBody(action.getParkingLot());

        } catch (Exception e) {
            action.setErrorResponse("Failed creating parking lot", e, HttpStatus.Code.BAD_REQUEST);
        }

        getSender().tell(new ActionDone(action), getSelf());
    }

    private void handleGetParkingLotVisitorsInDayAction(GetParkingLotVisitorsInDayAction action) {
        try {
            int parkingLotVisitorsDuringDay = Database.getParkingLotVisitorsDuringDay(action.getParkingLotId(), action.getDay());
            action.setResponseBody(parkingLotVisitorsDuringDay);

        } catch (SQLException e) {
            action.setErrorResponse("Failed getting parking lot visitors in a day", e, HttpStatus.Code.BAD_REQUEST);
        }

        getSender().tell(new ActionDone(action), getSelf());
    }

    private void handleGetParkingLotUsagesInPercentAction(GetParkingLotUsagesInPercentAction action) {
        try {
            Map<Integer, Double> usagesInPercent = Database.getUsagesInPercent(action.getIds());
            action.setResponseBody(usagesInPercent);

        } catch (SQLException e) {
            action.setErrorResponse("Failed getting parking lot usages", e, HttpStatus.Code.BAD_REQUEST);
        }

        getSender().tell(new ActionDone(action), getSelf());
    }

    public static Props props() {
        return Props.create(ParkingLotActor.class);
    }

}
