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
    }

    public ParkingLot getParkingLot() throws InvalidRequestParametersException {
        if (parkingLot == null) {
            extractParkingLotParameter();
        }

        return parkingLot;
    }

    private void extractParkingLotParameter() throws InvalidRequestParametersException {
        try {
            String parkingLotJson = getRequest().body();
            parkingLot = new Gson().fromJson(parkingLotJson, ParkingLot.class);

        } catch (Exception e) {
            throw new InvalidRequestParametersException("{\"name\": str, \"capacity\": int}");
        }

        validateParameters();
    }

    private void validateParameters() throws InvalidRequestParametersException {
        if (parkingLot.getName() == null) {
            throw new InvalidRequestParametersException("{\"name\": str, ...}");
        }

        if (parkingLot.getCapacity() == null) {
            throw new InvalidRequestParametersException("{\"capacity\": int, ...}");
        }

        if (parkingLot.getCapacity() < 0) {
            throw new InvalidRequestParametersException("{\"capacity\": int > 0, ...}");
        }
    }

}
