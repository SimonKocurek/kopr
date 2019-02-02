package kopr.nikdy.viac;

import kopr.nikdy.viac.persistance.Database;

import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Database.initialize();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            Database.close();
        }
    }

//        ActorSystem system = ActorSystem.create();
//        ActorRef master = system.actorOf(MasterActor.props());
//
//        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", ActorRef.noSender());
//        master.tell("Dog is man's best friend", ActorRef.noSender());
//        master.tell("Dog and Fox belong to the same family", ActorRef.noSender());
//        master.tell("The dog was the first domesticated species", ActorRef.noSender());
//        master.tell("The origin of the domestic dog is not clear.", ActorRef.noSender());
//
//        master.tell(new EofMessage(), ActorRef.noSender());

}