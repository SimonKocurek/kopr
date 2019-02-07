package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.RoundRobinPool;
import kopr.nikdy.viac.actions.*;

public class MasterActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    private final ActorRef parkingLotActor = getContext().actorOf(
            ParkingLotActor.props().withRouter(new RoundRobinPool(2))
    );

    private final ActorRef ticketActor = getContext().actorOf(
            TicketActor.props().withRouter(new RoundRobinPool(10))
    );

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddTicketAction.class, action -> ticketActor.tell(action, getSelf()))
                .match(RemoveTicketAction.class, action -> ticketActor.tell(action, getSelf()))

                .match(AddParkingLotAction.class, action -> parkingLotActor.tell(action, getSelf()))
                .match(GetParkingLotUsagesInPercentAction.class, action -> parkingLotActor.tell(action, getSelf()))
                .match(GetParkingLotVisitorsInDayAction.class, action -> parkingLotActor.tell(action, getSelf()))

                .match(ActionDone.class, Action::markCompleted)
                .build();
    }

    public static Props props() {
        return Props.create(MasterActor.class);
    }

}
