package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import kopr.nikdy.viac.actions.AddTicketAction;
import kopr.nikdy.viac.actions.RemoveTicketAction;
import kopr.nikdy.viac.persistance.Database;

import java.sql.SQLException;

public class TicketActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddTicketAction.class, this::handleAddTicketAction)
                .match(RemoveTicketAction.class, this::handleRemoveTicketAction)

                .build();
    }

    private void handleAddTicketAction(AddTicketAction action) {
        try {
            Database.addTicket(action.getTicket());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleRemoveTicketAction(RemoveTicketAction action) {
        try {
            Database.removeTicket(action.getTicketId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Props props() {
        return Props.create(TicketActor.class);
    }

}
