package it.polimi.deib.blackboard_mediator;

import java.io.UnsupportedEncodingException;

public class BlackboardException extends Exception {

	public BlackboardException() {
	}

	public BlackboardException(String message) {
		super(message);
	}

	public BlackboardException(Throwable cause) {
		super(cause);
	}

	public BlackboardException(String message, Throwable cause) {
		super(message, cause);
	}

}
