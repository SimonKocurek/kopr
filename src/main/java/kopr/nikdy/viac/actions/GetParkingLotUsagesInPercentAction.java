package kopr.nikdy.viac.actions;

import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetParkingLotUsagesInPercentAction extends AbstractAction {

    private List<Integer> ids;

    public GetParkingLotUsagesInPercentAction(Request request, Response response) {
        super(request, response);
        ids = getRequestContent();
    }

    private List<Integer> getRequestContent() {
        return Arrays.stream(getRequest().queryParamsValues("id"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public List<Integer> getIds() {
        return ids;
    }

}
