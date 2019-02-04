package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.actions.AddParkingLotAction;
import kopr.nikdy.viac.actions.GetParkingLotUsagesInPercentAction;
import kopr.nikdy.viac.actions.GetParkingLotVisitorsInDayAction;

import java.time.LocalDate;
import java.util.List;

public class ParkingLotActor extends AbstractActor {

    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddParkingLotAction.class, this::handleAddParkingLotAction)
                .match(GetParkingLotVisitorsInDayAction.class, this::handleGetParkingLotVisitorsInDayAction)
                .match(GetParkingLotUsagesInPercentAction.class, this::handleGetParkingLotVisitorsDuringDayAction)

                .build();
    }

    private <P> void handleAddParkingLotAction(P p) {

    }

    private void handleGetParkingLotVisitorsInDayAction(GetParkingLotVisitorsInDayAction action) {
        action.markCompleted();
    }

    /**
     *
     * @param ids
     * @return
     */
    private List<Double> getParkingLotsUsagesInPercent(List<Integer> ids) {
        return null;
    }

    private void handleGetParkingLotVisitorsDuringDayAction(GetParkingLotUsagesInPercentAction action) {
        action.markCompleted();
    }

    /**
     *
     * @param id
     * @param day
     * @return
     */
    private int getParkingLotVisitorsDuringDay(Integer id, LocalDate day) {
        return 0;
    }


    public static Props props() {
        return Props.create(ParkingLotActor.class);
    }

}
