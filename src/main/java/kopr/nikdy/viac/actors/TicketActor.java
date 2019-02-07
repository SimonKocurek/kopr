package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.actions.ActionDone;
import kopr.nikdy.viac.actions.AddTicketAction;
import kopr.nikdy.viac.actions.RemoveTicketAction;
import kopr.nikdy.viac.persistance.Database;

import java.sql.SQLException;

public class TicketActor extends AbstractActor {

    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddTicketAction.class, this::handleAddTicketAction)
                .match(RemoveTicketAction.class, this::handleRemoveTicketAction)

                .build();
    }

    private void handleAddTicketAction(AddTicketAction action) {
        try {
            int remainingCapacity = Database.getParkingLotRemainingCapacity(action.getTicket().getParkingLotId());
            if (remainingCapacity <= 0) {
                action.setResponseBody("No space");
                getSender().tell(new ActionDone(action), getSelf());
            }

            Database.addTicket(action.getTicket());
            action.setResponseBody(action.getTicket());

            getSender().tell(new ActionDone(action), getSelf());

        } catch (SQLException e) {
            logger.error("Failed adding ticket, " + e);
            e.printStackTrace();
        }
    }

    private void handleRemoveTicketAction(RemoveTicketAction action) {
        try {
            Database.removeTicket(action.getTicketId());
            action.setResponseBody(action.getTicketId());

            getSender().tell(new ActionDone(action), getSelf());

        } catch (SQLException e) {
            logger.error("Failed removing ticket, " + e);
            e.printStackTrace();
        }
    }

    public static Props props() {
        return Props.create(TicketActor.class);
    }

}
