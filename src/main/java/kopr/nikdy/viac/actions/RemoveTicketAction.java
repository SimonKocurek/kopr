package kopr.nikdy.viac.actions;

import spark.Request;
import spark.Response;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class RemoveTicketAction extends Action {

    private UUID ticketId;

    public RemoveTicketAction(Request request, Response response, CountDownLatch pendingTasks) {
        super(request, response, pendingTasks);
        ticketId = extractRequestData();
    }

    private UUID extractRequestData() {
        String uuid = getRequest().params(":ticketId");
        return UUID.fromString(uuid.toLowerCase());
    }

    public UUID getTicketId() {
        return ticketId;
    }

}
