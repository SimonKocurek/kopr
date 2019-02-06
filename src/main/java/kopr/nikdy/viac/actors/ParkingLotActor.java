package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import kopr.nikdy.viac.actions.AddParkingLotAction;
import kopr.nikdy.viac.actions.GetParkingLotUsagesInPercentAction;
import kopr.nikdy.viac.actions.GetParkingLotVisitorsInDayAction;
import kopr.nikdy.viac.persistance.Database;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleGetParkingLotVisitorsInDayAction(GetParkingLotVisitorsInDayAction action) {
        try {

            int parkingLotVisitorsDuringDay = Database.getParkingLotVisitorsDuringDay(action.getParkingLotId(), action.getDay());
            action.setResponseBody(parkingLotVisitorsDuringDay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleGetParkingLotUsagesInPercentAction(GetParkingLotUsagesInPercentAction action) {
        try {
            Map<Integer, Double> usagesInPercent = Database.getUsagesInPercent(action.getIds());
            action.setResponseBody(usagesInPercent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Props props() {
        return Props.create(ParkingLotActor.class);
    }

}
