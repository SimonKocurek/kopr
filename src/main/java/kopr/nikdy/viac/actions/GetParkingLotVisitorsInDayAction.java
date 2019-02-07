package kopr.nikdy.viac.actions;

import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class GetParkingLotVisitorsInDayAction extends Action {

    private Integer parkingLotId;

    private LocalDate day;

    public GetParkingLotVisitorsInDayAction(Request request, Response response, CountDownLatch pendingTasks) {
        super(request, response, pendingTasks);
        parkingLotId = extractRequestParkingLotId();
        day = extractRequestDay();
    }

    private Integer extractRequestParkingLotId() {
        String lotIdPath = getRequest().params(":lotId");
        return Integer.valueOf(lotIdPath);
    }

    private LocalDate extractRequestDay() {
        String day = getRequest().queryParams("day");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(day, formatter);
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public LocalDate getDay() {
        return day;
    }

}
