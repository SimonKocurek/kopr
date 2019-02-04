package kopr.nikdy.viac.actions;

import com.google.gson.Gson;
import kopr.nikdy.viac.entities.ParkingTicket;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.UUID;

public class AddTicketAction extends AbstractAction {

    private ParkingTicket ticket;

    public AddTicketAction(Request request, Response response) {
        super(request, response);
        ticket = extractRequestData();
    }

    private ParkingTicket extractRequestData() {
        String parkingLotJson = getRequest().body();
        ParkingTicket ticket = new Gson().fromJson(parkingLotJson, ParkingTicket.class);

        ticket.setId(UUID.randomUUID());
        ticket.setArrivalTime(LocalDateTime.now());

        return ticket;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

}
