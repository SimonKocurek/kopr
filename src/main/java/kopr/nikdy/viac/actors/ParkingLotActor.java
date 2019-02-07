package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.actions.ActionDone;
import kopr.nikdy.viac.actions.AddParkingLotAction;
import kopr.nikdy.viac.actions.GetParkingLotUsagesInPercentAction;
import kopr.nikdy.viac.actions.GetParkingLotVisitorsInDayAction;
import kopr.nikdy.viac.persistance.Database;

import java.sql.SQLException;
import java.util.Map;

public class ParkingLotActor extends AbstractActor {

    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

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

            getSender().tell(new ActionDone(action), getSelf());

        } catch (SQLException e) {
            logger.error("Failed creating parking lot, " + action);
            e.printStackTrace();
        }
    }

    private void handleGetParkingLotVisitorsInDayAction(GetParkingLotVisitorsInDayAction action) {
        try {
            int parkingLotVisitorsDuringDay = Database.getParkingLotVisitorsDuringDay(action.getParkingLotId(), action.getDay());
            action.setResponseBody(parkingLotVisitorsDuringDay);

            getSender().tell(new ActionDone(action), getSelf());

        } catch (SQLException e) {
            logger.error("Failed getting parking lot visitors in day, " + action);
            e.printStackTrace();
        }
    }

    private void handleGetParkingLotUsagesInPercentAction(GetParkingLotUsagesInPercentAction action) {
        try {
            Map<Integer, Double> usagesInPercent = Database.getUsagesInPercent(action.getIds());
            action.setResponseBody(usagesInPercent);

            getSender().tell(new ActionDone(action), getSelf());

        } catch (SQLException e) {
            logger.error("Failed getting parking lot usages, " + action);
            e.printStackTrace();
        }
    }

    public static Props props() {
        return Props.create(ParkingLotActor.class);
    }

}
