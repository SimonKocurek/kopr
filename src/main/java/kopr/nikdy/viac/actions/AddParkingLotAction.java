package kopr.nikdy.viac.actions;

import com.google.gson.Gson;
import kopr.nikdy.viac.entities.ParkingLot;
import spark.Request;
import spark.Response;

public class AddParkingLotAction extends AbstractAction {

    private ParkingLot parkingLot;

    public AddParkingLotAction(Request request, Response response) {
        super(request, response);
        parkingLot = getRequestContent();
    }

    private ParkingLot getRequestContent() {
        String parkingLotJson = getRequest().body();
        return new Gson().fromJson(parkingLotJson, ParkingLot.class);
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

}
