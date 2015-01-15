package pl.edu.amu.dji.jms.lab12.akka;

import akka.actor.Actor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class GreetingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if(arg0 instanceof Greeting) {
			log.info("Hello " + ((Greeting) arg0).getWho());
		}
		
	}
	
	public static final Creator<Actor> CREATOR = new Creator<Actor>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -957920703562397197L;

		@Override
		public Actor create() throws Exception {
			return new GreetingActor();
		}
		
	};

}
