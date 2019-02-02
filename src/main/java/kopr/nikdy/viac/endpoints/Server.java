package kopr.nikdy.viac.endpoints;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import kopr.nikdy.viac.actors.MasterActor;

import static spark.Spark.*;

public class Server {

    public static void registerEndpoints() {
        ActorSystem system = ActorSystem.create();
        ActorRef master = system.actorOf(MasterActor.props());

        // matches "GET /hello/foo" and "GET /hello/bar"
// request.params(":name") is 'foo' or 'bar'
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        path("/lot", () -> {
            post("/", (request, response) -> {

            });

            get("/capacity", (request, response) -> {

            });

            post("/:lotId", (request, response) -> {

            });


        });

        post("/", (request, response) -> master.tell(, ActorRef.noSender()));
        put("/", (request, response) -> master.tell(, ActorRef.noSender()));

        get("/", (request, response) -> master.tell(, ActorRef.noSender()));
        get("/", (request, response) -> master.tell(, ActorRef.noSender()));
        get("/", (request, response) -> master.tell(, ActorRef.noSender()));
        get("/", (request, response) -> master.tell(, ActorRef.noSender()));
    }

}
