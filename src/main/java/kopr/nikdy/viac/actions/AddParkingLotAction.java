package kopr.nikdy.viac.actions;

import com.google.gson.Gson;
import kopr.nikdy.viac.entities.ParkingLot;
import spark.Request;
import spark.Response;

import java.util.concurrent.CountDownLatch;

public class AddParkingLotAction extends Action {

    private ParkingLot parkingLot;

    public AddParkingLotAction(Request request, Response response, CountDownLatch pendingTasks) {
        super(request, response, pendingTasks);
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
