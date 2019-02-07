package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.actions.ActionDone;
import kopr.nikdy.viac.actions.AddTicketAction;
import kopr.nikdy.viac.actions.RemoveTicketAction;
import kopr.nikdy.viac.persistance.Database;
import org.eclipse.jetty.http.HttpStatus;

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
                action.setErrorResponse("Cannot add ticket to a full parking lot", HttpStatus.Code.BAD_REQUEST);

            } else {
                Database.addTicket(action.getTicket());
                action.setResponseBody(action.getTicket());
            }

        } catch (Exception e) {
            action.setErrorResponse("Adding ticket", e, HttpStatus.Code.BAD_REQUEST);
        }

        getSender().tell(new ActionDone(action), getSelf());
    }

    private void handleRemoveTicketAction(RemoveTicketAction action) {
        try {
            Database.removeTicket(action.getTicketId());
            action.setResponseBody(action.getTicketId());

        } catch (Exception e) {
            action.setErrorResponse("Failed removing ticket", e, HttpStatus.Code.BAD_REQUEST);
        }

        getSender().tell(new ActionDone(action), getSelf());
    }

    public static Props props() {
        return Props.create(TicketActor.class);
    }

}
