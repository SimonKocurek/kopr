package kopr.nikdy.viac.example;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.Broadcast;
import akka.routing.RoundRobinPool;

import java.util.HashMap;
import java.util.Map;

public class MasterActor extends AbstractActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

	private ActorRef sentenceCounter = getContext().actorOf(SentenceCountActor.props()
			.withRouter(new RoundRobinPool(3)));

	private Map<String, Integer> allFrequencies = new HashMap<>();

	@Override
	public void preStart() throws Exception {
		super.preStart();
		getContext().watch(sentenceCounter);
	}

	@SuppressWarnings("unchecked")
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