package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.actions.AddTicketAction;
import kopr.nikdy.viac.actions.RemoveTicketAction;
import kopr.nikdy.viac.entities.ParkingTicket;

import java.util.UUID;

public class TicketActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddTicketAction.class, action -> )
                .match(RemoveTicketAction.class, action -> getSender().tell())

                .build();
    }

    /**
     * Create a new ticket for a car that just arrived
     *
     * @param ticket
     * @return
     */
    private UUID addTicket(ParkingTicket ticket) {
        return null;
    }

    /**
     *
     * @param id
     */
    private void removeTicket(UUID id) {

    }

    public static Props props() {
        return Props.create(TicketActor.class);
    }

}
