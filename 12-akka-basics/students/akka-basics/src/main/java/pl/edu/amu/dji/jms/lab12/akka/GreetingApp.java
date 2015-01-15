package pl.edu.amu.dji.jms.lab12.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class GreetingApp {
	
    public static void main(String[] args) {
    	ActorSystem system = ActorSystem.create("GreetingSystem");
    	ActorRef ref = system.actorOf(Props.create(GreetingActor.class), "greeter");
    	ref.tell(new Greeting("UAM"), ActorRef.noSender());
    }
    
}
