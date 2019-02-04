package kopr.nikdy.viac.actions;

import spark.Request;
import spark.Response;

import java.util.UUID;

public class RemoveTicketAction extends AbstractAction {

    private UUID ticketId;

    public RemoveTicketAction(Request request, Response response) {
        super(request, response);
        ticketId = extractRequestData();
    }

    private UUID extractRequestData() {
        String uuid = getRequest().params(":ticketId");
        return UUID.fromString(uuid);
    }

    public UUID getTicketId() {
        return ticketId;
    }

}
