package kopr.nikdy.viac.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kopr.nikdy.viac.example.SentenceCountActor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ParkingLotActor extends AbstractActor {

    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, sentence -> {
                    logger.info("Handling '{}'", sentence);
                    Map<String, Integer> frequencies = calculateFrequencies(sentence);
                    getSender().tell(frequencies, getSelf());
                })
                .build();
    }

    public static Props props() {
        return Props.create(SentenceCountActor.class);
    }

    /**
     *
     * @param ids
     * @return
     */
    private List<Double> parkingLotsUsagesInPercent(List<Integer> ids) {
        return null;
    }

    /**
     *
     * @param id
     * @param day
     * @return
     */
    private int parkingLotVisitorsDuringDay(Integer id, LocalDate day) {
        return 0;
    }

}
