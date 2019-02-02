package kopr.nikdy.viac.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.Broadcast;
import akka.routing.RoundRobinPool;

import java.util.HashMap;
import java.util.Map;

public class MasterActor extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    private final ActorRef parkingLotActor = getContext().actorOf(ParkingLotActor.props());

    private final ActorRef ticketActor = getContext().actorOf(
            ParkingLotActor.props().withRouter(new RoundRobinPool(10))
    );

    private Map<String, Integer> allFrequencies = new HashMap<>();

    public void preStart() throws Exception {
        super.preStart();
        getContext().watch(parkingLotActor);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, sentence -> sentenceCounter.tell(sentence, getSelf()))
                .match(Map.class, frequencies -> {
                    allFrequencies = MapUtils.aggregate(frequencies, allFrequencies);
                })
                .match(EofMessage.class, eof -> {
                    sentenceCounter.tell(new Broadcast(PoisonPill.getInstance()), getSelf());
                })
                .match(Terminated.class, message -> {
                    logger.info(allFrequencies.toString());
                    getContext().system().terminate();
                })
                .build();
    }

    public static Props props() {
        return Props.create(MasterActor.class);
    }

}
