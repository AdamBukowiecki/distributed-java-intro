package pl.edu.amu.dji.jms.lab12.akka;

import java.io.Serializable;

public final class Greeting implements Serializable {

	private String message;
	
	public String getMessage() {
		return message;
	}

	public Greeting(String str) {
		message = str;
	}

	public String getWho() {
		return message;
	}
		
}
